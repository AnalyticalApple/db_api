package xyz.matthewsavage.db_api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private final RepoApps APPS_REPO;
    private final RepoNato NATO_REPO;
    private final RepoReaderUserMangas READER_USER_MANGAS_REPO;
    private final RepoReaderUsers READER_USERS_REPO;
    private final RepoViz VIZ_REPO;
    private final RepoMangaCoreReports REPO_MANGA_CORE_REPORTS;
    private final RepoMangaCore MANGA_REPO;

    public final static Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(RepoApps APPS_REPO, RepoNato NATO_REPO, RepoReaderUserMangas READER_USER_MANGAS_REPO, RepoReaderUsers READER_USERS_REPO, RepoViz VIZ_REPO, RepoMangaCoreReports REPO_MANGA_CORE_REPORTS, RepoMangaCore MANGA_REPO) {
        this.APPS_REPO = APPS_REPO;
        this.NATO_REPO = NATO_REPO;
        this.READER_USER_MANGAS_REPO = READER_USER_MANGAS_REPO;
        this.READER_USERS_REPO = READER_USERS_REPO;
        this.VIZ_REPO = VIZ_REPO;
        this.REPO_MANGA_CORE_REPORTS = REPO_MANGA_CORE_REPORTS;
        this.MANGA_REPO = MANGA_REPO;
    }

    @RequestMapping(path = "/reader_user_add_or_update", method = RequestMethod.POST)
    public void readerUserAddOrUpdate(@RequestBody String json) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        READER_USERS_REPO.insertOrUpdate(
                jsonObject.get("clientId").getAsInt(),
                jsonObject.get("login").getAsString(),
                jsonObject.get("bookmark").getAsLong(),
                jsonObject.get("bookmarkCreatedTimestamp").getAsLong(),
                jsonObject.get("lease").getAsLong()
                );
    }

    @RequestMapping(path = "/reader_user_set_lease", method = RequestMethod.POST)
    public ObjectReaderUser readerUserSetLease(@RequestBody String json) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        ObjectReaderUser remoteUser = new ObjectReaderUser(jsonObject.get("bookmarkCreatedTimestamp").getAsLong(), jsonObject.get("bookmark").getAsLong());
        ObjectReaderUser localUser = READER_USERS_REPO.findById(jsonObject.get("clientId").getAsInt()).orElse(null);

        if (Objects.nonNull(localUser)) {
            localUser.setLease(jsonObject.get("lease").getAsLong());

            if (remoteUser.getBookmarkCreatedTimestamp() > localUser.getBookmarkCreatedTimestamp()) {
                localUser.setBookmarkCreatedTimestamp(remoteUser.getBookmarkCreatedTimestamp());
                localUser.setBookmark(remoteUser.getBookmark());
            }
            READER_USERS_REPO.save(localUser);
        }
        return localUser;
    }

    // pretty sure this is deprecated, need to check tho
    @RequestMapping(path = "/reader_user_mangas_get", method = RequestMethod.POST)
    public List<ObjectReaderUserMangas> getAllModifiedAfter(@RequestBody String json) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        return READER_USER_MANGAS_REPO.getAllModifiedAfter(jsonObject.get("clientId").getAsInt(), jsonObject.get("").getAsLong());
    }

    @RequestMapping("/reader_user_get_lease")
    public long readerUserGetLease(@RequestParam(name = "client_id") int clientId) {
        ObjectReaderUser readerUser = READER_USERS_REPO.findById(clientId).orElse(null);

        if (Objects.nonNull(readerUser)) {
            return readerUser.getLease();
        }
        return -1L;
    }

    @RequestMapping(path = "/reader_user_mangas_pull", method = RequestMethod.POST)
    public List<ObjectReaderUserMangas> getAllModifiedAfter(@RequestBody String json, @RequestParam(name = "last_modified") long lastModified) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        return READER_USER_MANGAS_REPO.getAllModifiedAfter(jsonObject.get("clientId").getAsInt(), lastModified);
    }

    @PostMapping(path = "/sync_manga_reports")
    public List<ObjectMangaCoreReports> submitMangaReport(@RequestBody String reportJson) {
        JsonObject[] jsonObjects = new Gson().fromJson(reportJson, JsonObject[].class);

        Arrays.stream(jsonObjects).forEach(jsonObject -> {
            long reportId = jsonObject.get("reportId").getAsLong();
            long id = jsonObject.get("id").getAsLong();
            int chapterNum = jsonObject.get("chapterNum").getAsInt();
            int errorCode = jsonObject.get("errorCode").getAsInt();
            String errorLabel = jsonObject.get("errorLabel").getAsString();
            String errorNotes = jsonObject.get("errorNotes").getAsString();
            int resolutionCode = jsonObject.get("resolutionCode").getAsInt();
            String resolutionLabel = jsonObject.get("resolutionLabel").getAsString();
            String resolutionNotes = jsonObject.get("resolutionNotes").getAsString();

            REPO_MANGA_CORE_REPORTS.insertIfNotExists(reportId, id, chapterNum, errorCode, errorLabel, errorNotes, resolutionCode, resolutionLabel, resolutionNotes);
        });
        return REPO_MANGA_CORE_REPORTS.findAll();
    }

    @RequestMapping(path = "/reader_user_mangas_push", method = RequestMethod.POST)
    public void mangaUserUpload(@RequestBody String json, @RequestParam(name = "client_id") int clientId) {
        JsonObject[] jsonObject = new Gson().fromJson(json, JsonObject[].class);

        List<ObjectReaderUserMangas> readerUserMangas = Arrays.stream(jsonObject).map(object -> new ObjectReaderUserMangas(
                clientId,
                object.get("id").getAsLong(),
                object.get("verNum").getAsLong(),
                object.get("catNum").getAsInt(),
                object.get("currentChapter").getAsInt(),
                object.get("currentPage").getAsInt(),
                object.get("newChaptersTally").getAsInt(),
                object.get("isFavorite").getAsInt(),
                object.get("lastReadTimestamp").getAsLong(),
                object.get("lastModifiedTimestamp").getAsLong(),
                0
        )).toList();

        for (ObjectReaderUserMangas readerUserManga : readerUserMangas) {
            READER_USER_MANGAS_REPO.insertOrUpdate(
                    Long.parseLong(readerUserManga.getClientId() + "" + readerUserManga.getMangaId()),
                    readerUserManga.getClientId(),
                    readerUserManga.getMangaId(),
                    readerUserManga.getVerNum(),
                    readerUserManga.getCatNum(),
                    readerUserManga.getCurrentChapter(),
                    readerUserManga.getCurrentPage(),
                    readerUserManga.getNewChaptersTally(),
                    readerUserManga.getIsFavorite(),
                    readerUserManga.getLastReadTimestamp(),
                    readerUserManga.getLastModifiedTimestamp(),
                    readerUserManga.getIsMobile()
            );
        }
    }

    @GetMapping("/reader_user_get_cur_user_id")
    public int readerUserGetCurId(@RequestParam(name= "login") String login) {
        return READER_USERS_REPO.getUserId(login);
    }

    @GetMapping("/reader_user_get_new_user_id")
    public int readerUserGetLastModified() {
        Integer nextFreeId = READER_USERS_REPO.getNewestUser();
        if (Objects.isNull(nextFreeId)) {
            return 1;
        }
        return nextFreeId + 1;
    }

    @GetMapping("/reader_user_get_last_modified")
    public long readerUserGetLastModified(@RequestParam(name = "id") long id) {
        Long lastModified = READER_USER_MANGAS_REPO.getLastModified(id);
        if (Objects.isNull(lastModified)) {
            lastModified = 0L;
        }
        return lastModified;
    }

    @PostMapping("/app_upload")
    public void uploadTest(@RequestParam("file") MultipartFile file, @RequestParam(name = "id") int id) {
        ObjectApps app = APPS_REPO.findById(id).orElseThrow();
        app.setVersion();
        APPS_REPO.save(app);
        new Storage(id).save(file);
    }

    @GetMapping("/get_all_apps")
    public List<ObjectApps> getAllApps() {
        return APPS_REPO.findAll();
    }

    @GetMapping("/get_app_by_id")
    public ObjectApps getNatoById(@RequestParam(name = "id") int id) {
        return APPS_REPO.findById(id).orElseThrow();
    }

    @RequestMapping(path = "/add_app", method = RequestMethod.POST)
    public void addApp(@RequestBody String appJson) {
        JsonObject jsonObject = new Gson().fromJson(appJson, JsonObject.class);
        APPS_REPO.save(new ObjectApps(
                jsonObject.get("id").getAsInt(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("version").getAsLong(),
                jsonObject.get("pathServer").getAsString(),
                jsonObject.get("pathClient").getAsString(),
                jsonObject.get("lastModified").getAsLong()
        ));
    }

    @GetMapping("/remove_app")
    public void removeApp(@RequestParam(name = "id") int id) {
        APPS_REPO.delete(APPS_REPO.findById(id).orElseThrow());
    }

    @GetMapping("/get_all_nato")
    public List<ObjectNato> getAllNato() {
        return NATO_REPO.findAll();
    }

    @GetMapping("/get_post_timestamp_nato")
    public List<ObjectNato> getPostTimestampNato(@RequestParam(name = "id") long id) {
        return NATO_REPO.getPostTimestampNato(id);
    }

    @GetMapping("/get_new_manga_titles")
    public List<ObjectMangaCore> getNewMangaTitles(@RequestParam(name = "id") long id) {
        return MANGA_REPO.getNewMangaTitles(id);
    }

    @GetMapping("/get_available_for_download")
    public List<ObjectMangaCore> getAvailableForDownload() {
        return MANGA_REPO.getAvailableForDownload();
    }

    @GetMapping("/get_manga_by_id")
    public ObjectMangaCore getMangaById(@RequestParam(name = "id") long id) {
        return MANGA_REPO.findById(id).orElseThrow();
    }

    @PutMapping("/change_source/{id}/{newSourceNum}/{newSourceURL}")
    public ResponseEntity<Void> changeMangaSource(@PathVariable Long id, @PathVariable int newSourceNum, @PathVariable String newSourceURL) {
        ObjectMangaCore manga = MANGA_REPO.findById(id).orElse(null);
        if (Objects.nonNull(manga)) {
            Storage.deleteDirectory(new File("/var/www/html/media2/manga/mangas/0" + File.separator + manga.getId()));
            manga.setSourceNum(newSourceNum);
            manga.setHighestChapter(-1);
            manga.setUrlParent(newSourceURL);
            manga.setUrlLastDownloaded("");
            manga.setUrlNextDownload("");
            manga.setDataVer(-1);
            manga.setCheckForUpdates(1);
            manga.setIsDownloading(1);
            manga.setDownloadPriority(79);
            MANGA_REPO.save(manga);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/queue_manga_for_download/{id}/{priority}")
    public ResponseEntity<Void> queueMangaForDownload(@PathVariable Long id, @PathVariable int priority) {
        ObjectMangaCore manga = MANGA_REPO.findById(id).orElse(null);
        if (Objects.nonNull(manga) && manga.getSourceNum() == 0 && manga.getCheckForUpdates() == 0) {
            manga.setCheckForUpdates(1);
            manga.setIsDownloading(1);
            manga.setDownloadPriority(priority);
            MANGA_REPO.save(manga);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get_available_nato")
    public List<ObjectNato> getAvailableNato() {
        return NATO_REPO.getAvailableNato();
    }

    @GetMapping("/get_nato_by_id")
    public ObjectNato getNatoById(@RequestParam(name = "id") long id) {
        return NATO_REPO.findById(id).orElseThrow();
    }

    @GetMapping("/queue_nato_download")
    public String queueNatoDownload(@RequestParam(name = "id") long id) {
        ObjectNato manga = NATO_REPO.findById(id).orElse(null);
        if (Objects.nonNull(manga) && manga.getSourceNum() == 0 && manga.getCheckForUpdates() == 0 && manga.getIsRetailRelease() == 0) {
            manga.setCheckForUpdates(1);
            manga.setIsDownloading(1);
            NATO_REPO.save(manga);
        }
        return "Request Processed";
    }

    @GetMapping("/get_all_viz")
    public List<ObjectViz> getAllViz() {
        return VIZ_REPO.findAll();
    }

}
