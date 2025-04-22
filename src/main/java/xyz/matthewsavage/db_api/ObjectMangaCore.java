package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "manga_core")
public class ObjectMangaCore {

    @Id
    private Long id;
    private int sourceNum;
    private long dataVer;
    private long infoVer;
    private String title;
    private String titleAlt;
    private String genres;
    private String genresJson;
    private String authors;
    private String summary;
    private String muAddress;
    private int highestChapter;
    private int checkForUpdates;
    private String urlParent;
    private String urlLastDownloaded;
    private String urlNextDownload;
    private int isDownloading;
    private int downloadPriority;

    public ObjectMangaCore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSourceNum() {
        return sourceNum;
    }

    public void setSourceNum(int sourceNum) {
        this.sourceNum = sourceNum;
    }

    public long getDataVer() {
        return dataVer;
    }

    public void setDataVer(long dataVer) {
        this.dataVer = dataVer;
    }

    public long getInfoVer() {
        return infoVer;
    }

    public void setInfoVer(long infoVer) {
        this.infoVer = infoVer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleAlt() {
        return titleAlt;
    }

    public void setTitleAlt(String titleAlt) {
        this.titleAlt = titleAlt;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getGenresJson() {
        return genresJson;
    }

    public void setGenresJson(String genresJson) {
        this.genresJson = genresJson;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMuAddress() {
        return muAddress;
    }

    public void setMuAddress(String muAddress) {
        this.muAddress = muAddress;
    }

    public int getHighestChapter() {
        return highestChapter;
    }

    public void setHighestChapter(int highestChapter) {
        this.highestChapter = highestChapter;
    }

    public int getCheckForUpdates() {
        return checkForUpdates;
    }

    public void setCheckForUpdates(int checkForUpdates) {
        this.checkForUpdates = checkForUpdates;
    }

    public String getUrlParent() {
        return urlParent;
    }

    public void setUrlParent(String urlParent) {
        this.urlParent = urlParent;
    }

    public String getUrlLastDownloaded() {
        return urlLastDownloaded;
    }

    public void setUrlLastDownloaded(String urlLastDownloaded) {
        this.urlLastDownloaded = urlLastDownloaded;
    }

    public String getUrlNextDownload() {
        return urlNextDownload;
    }

    public void setUrlNextDownload(String urlNextDownload) {
        this.urlNextDownload = urlNextDownload;
    }

    public int getIsDownloading() {
        return isDownloading;
    }

    public void setIsDownloading(int isDownloading) {
        this.isDownloading = isDownloading;
    }

    public int getDownloadPriority() {
        return downloadPriority;
    }

    public void setDownloadPriority(int downloadPriority) {
        this.downloadPriority = downloadPriority;
    }
}
