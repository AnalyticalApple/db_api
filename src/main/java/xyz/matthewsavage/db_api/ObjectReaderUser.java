package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reader_users")
public class ObjectReaderUser {

    @Id
    private Integer clientId;
    private String login;
    private long bookmark;
    private long bookmarkCreatedTimestamp;
    private long lease;

    public ObjectReaderUser() {
    }

    public ObjectReaderUser(Integer clientId, String login, long bookmark, long bookmarkCreatedTimestamp, long lease) {
        this.clientId = clientId;
        this.login = login;
        this.bookmark = bookmark;
        this.bookmarkCreatedTimestamp = bookmarkCreatedTimestamp;
        this.lease = lease;
    }

    public ObjectReaderUser(long bookmarkCreatedTimestamp, long bookmark) {
        this.bookmarkCreatedTimestamp = bookmarkCreatedTimestamp;
        this.bookmark = bookmark;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getBookmark() {
        return bookmark;
    }

    public void setBookmark(long bookmark) {
        this.bookmark = bookmark;
    }

    public long getBookmarkCreatedTimestamp() {
        return bookmarkCreatedTimestamp;
    }

    public void setBookmarkCreatedTimestamp(long bookmarkCreatedTimestamp) {
        this.bookmarkCreatedTimestamp = bookmarkCreatedTimestamp;
    }

    public long getLease() {
        return lease;
    }

    public void setLease(long lease) {
        this.lease = lease;
    }
}
