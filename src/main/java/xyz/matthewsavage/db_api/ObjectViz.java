package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "viz")
public class ObjectViz {

    @Id
    private Long id;
    private long natoId;
    private long linkNum;
    private String title;
    private String url;
    private int highestChapter;
    private int highestChapterDownloaded;
    private String highestChapterListed;

    public ObjectViz() {

    }

    public Long getId() {
        return id;
    }

    public long getNatoId() {
        return natoId;
    }

    public long getLinkNum() {
        return linkNum;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getHighestChapter() {
        return highestChapter;
    }

    public int getHighestChapterDownloaded() {
        return highestChapterDownloaded;
    }

    public String getHighestChapterListed() {
        return highestChapterListed;
    }

}
