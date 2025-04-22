package xyz.matthewsavage.db_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RepoMangaCoreReports extends JpaRepository<ObjectMangaCoreReports, Integer> {

//    @Query(value = "INSERT INTO reader_users_mangas (unique_id, client_id, manga_id, ver_num, cat_num, current_chapter, current_page, new_chapters_tally, is_favorite, last_read_timestamp, last_modified_timestamp, is_mobile) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12) ON DUPLICATE KEY UPDATE ver_num = ?4, cat_num = ?5, current_chapter = ?6, current_page = ?7, new_chapters_tally = ?8, is_favorite = ?9, last_read_timestamp = ?10, last_modified_timestamp = ?11, is_mobile = ?12", nativeQuery = true)
//    void insertOrUpdate(long uniqueId, long clientId, long mangaId, long verNum, int catNUm, int currentChapter, int currentPage, int newChaptersTally, int isFavorite, long lastReadTimestamp, long lastModifiedTimestamp, int isMobile);
//
//    @Query(value = "SELECT MAX(last_modified_timestamp) FROM reader_users_mangas WHERE client_id = ?1", nativeQuery = true)
//    Long getLastModified(long id);
//
//    @Query(value = "SELECT * FROM reader_users_mangas WHERE client_id = ?1 AND last_modified_timestamp > ?2", nativeQuery = true)
//    List<ObjectReaderUserMangas> getAllModifiedAfter(int clientId, long lastModifiedTimestamp);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO manga_core_reports (report_id, id, chapter_num, error_code, error_label, error_notes, resolution_code, resolution_label, resolution_notes) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9) ON DUPLICATE KEY UPDATE ver_num = ?4, cat_num = ?5, current_chapter = ?6, current_page = ?7, new_chapters_tally = ?8, is_favorite = ?9, last_read_timestamp = ?10, last_modified_timestamp = ?11, is_mobile = ?12", nativeQuery = true)
    void insertOrUpdate(long reportId, long id, int chapterNum, int errorCode, String errorLabel, String errorNotes, int resolutionCode, String resolutionLabel, String resolutionNotes);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO manga_core_reports (report_id, id, chapter_num, error_code, error_label, error_notes, resolution_code, resolution_label, resolution_notes) " +
            "SELECT :reportId, :id, :chapterNum, :errorCode, :errorLabel, :errorNotes, :resolutionCode, :resolutionLabel, :resolutionNotes " +
            "WHERE NOT EXISTS (SELECT 1 FROM manga_core_reports WHERE report_id = :reportId)",
            nativeQuery = true)
    void insertIfNotExists(@Param("reportId") long reportId,
                           @Param("id") long id,
                           @Param("chapterNum") int chapterNum,
                           @Param("errorCode") int errorCode,
                           @Param("errorLabel") String errorLabel,
                           @Param("errorNotes") String errorNotes,
                           @Param("resolutionCode") int resolutionCode,
                           @Param("resolutionLabel") String resolutionLabel,
                           @Param("resolutionNotes") String resolutionNotes);

}