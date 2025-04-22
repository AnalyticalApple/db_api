package xyz.matthewsavage.db_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoMangaCore extends JpaRepository<ObjectMangaCore, Long> {

    @Query(value = "SELECT * FROM manga_core WHERE id > ?1", nativeQuery = true)
    List<ObjectMangaCore> getNewMangaTitles(long id);

    @Query(value = "SELECT * FROM manga_core WHERE highest_chapter > -1 AND is_downloading = 0", nativeQuery = true)
    List<ObjectMangaCore> getAvailableForDownload();

}