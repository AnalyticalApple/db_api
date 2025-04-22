package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "apps")
public class ObjectApps {

    @Id
    private Integer id;
    private String name;
    private long version;
    private String pathServer;
    private String pathClient;
    private long lastModified;

    public ObjectApps() {

    }

    public ObjectApps(Integer id, String name, long version, String pathServer, String pathClient, long lastModified) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.pathServer = pathServer;
        this.pathClient = pathClient;
        this.lastModified = lastModified;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion() {
        this.version = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Timestamp(System.currentTimeMillis())));
    }

    public String getPathServer() {
        return pathServer;
    }

    public String getPathClient() {
        return pathClient;
    }

    public long getLastModified() {
        return lastModified;
    }
}
