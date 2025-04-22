package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nato")
public class ObjectNato {

    @Id
    private Long id;
    private int sourceNum;
    private Long linkNum;
    private Long verNum;
    private String title;
    private String titleAlt;
    private String genres;
    private String genresJson;
    private String authors;
    private String summary;
    private int isRetailRelease;
    private String muAddress;
    private int highestChapter;
    private int checkForUpdates;
    private String urlParent;
    private String urlLastDownloaded;
    private int isDownloading;

    public ObjectNato() {

    }

    public Long getId() {
        return id;
    }

    public int getSourceNum() {
        return sourceNum;
    }

    public Long getLinkNum() {
        return linkNum;
    }

    public Long getVerNum() {
        return verNum;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleAlt() {
        return titleAlt;
    }

    public String getGenres() {
        return genres;
    }

    public String getGenresJson() {
        return genresJson;
    }

    public String getAuthors() {
        return authors;
    }

    public String getSummary() {
        return summary;
    }

    public int getIsRetailRelease() {
        return isRetailRelease;
    }

    public String getMuAddress() {
        return muAddress;
    }

    public int getHighestChapter() {
        return highestChapter;
    }

    public int getCheckForUpdates() {
        return checkForUpdates;
    }

    public String getUrlParent() {
        return urlParent;
    }

    public String getUrlLastDownloaded() {
        return urlLastDownloaded;
    }

    public int getIsDownloading() {
        return isDownloading;
    }

    public void setCheckForUpdates(int checkForUpdates) {
        this.checkForUpdates = checkForUpdates;
    }

    public void setIsDownloading(int isDownloading) {
        this.isDownloading = isDownloading;
    }

}
