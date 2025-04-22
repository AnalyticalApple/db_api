package xyz.matthewsavage.db_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepoReaderUsers extends JpaRepository<ObjectReaderUser, Integer> {

    @Query(value = "SELECT MAX(client_id) FROM reader_users", nativeQuery = true)
    Integer getNewestUser();

    @Query(value = "INSERT INTO reader_users (client_id, login, bookmark, bookmark_created_timestamp, lease) VALUES (?1, ?2, ?3, ?4, ?5) ON DUPLICATE KEY UPDATE lease = ?5", nativeQuery = true)
    void insertOrUpdate(int clientId, String login, long bookmark, long bookmarkCreatedTimestamp, long lease);

    @Query(value = "SELECT client_id FROM reader_users WHERE login = ?1", nativeQuery = true)
    int getUserId(String login);

}
