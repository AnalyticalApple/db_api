package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "manga_core_replacements")
public class ObjectMangaCoreReplacements {

    @Id
    private Integer instanceId;
    private long id;
    private int sourceNum;
    private String urlParent;
    private int chapterNumber;
    private int processed;

    public ObjectMangaCoreReplacements() {
    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public long getId() {
        return id;
    }

    public int getSourceNum() {
        return sourceNum;
    }

    public String getUrlParent() {
        return urlParent;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public int getProcessed() {
        return processed;
    }
}
