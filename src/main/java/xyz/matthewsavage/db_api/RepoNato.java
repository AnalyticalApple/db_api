package xyz.matthewsavage.db_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RepoNato extends JpaRepository<ObjectNato, Long> {

    @Query(value = "SELECT * FROM nato WHERE id > ?1", nativeQuery = true)
    List<ObjectNato> getPostTimestampNato(long id);

    @Query(value = "SELECT * FROM nato WHERE highest_chapter > -1 AND is_downloading = 0", nativeQuery = true)
    List<ObjectNato> getAvailableNato();
}
