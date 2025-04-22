package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reader_users_mangas")
public class ObjectReaderUserMangas {

    @Id
    private Long uniqueId;
    private int clientId;
    private long mangaId;
    private long verNum;
    private int catNum;
    private int currentChapter;
    private int currentPage;
    private int newChaptersTally;
    private int isFavorite;
    private long lastReadTimestamp;
    private long lastModifiedTimestamp;
    private int isMobile;

    public ObjectReaderUserMangas() {
    }

    public ObjectReaderUserMangas(int clientId, long mangaId, long verNum, int catNum, int currentChapter, int currentPage, int newChaptersTally, int isFavorite, long lastReadTimestamp, long lastModifiedTimestamp, int isMobile) {
        this.clientId = clientId;
        this.mangaId = mangaId;
        this.verNum = verNum;
        this.catNum = catNum;
        this.currentChapter = currentChapter;
        this.currentPage = currentPage;
        this.newChaptersTally = newChaptersTally;
        this.isFavorite = isFavorite;
        this.lastReadTimestamp = lastReadTimestamp;
        this.lastModifiedTimestamp = lastModifiedTimestamp;
        this.isMobile = isMobile;
    }

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(Long lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public long getMangaId() {
        return mangaId;
    }

    public void setMangaId(long mangaId) {
        this.mangaId = mangaId;
    }

    public long getVerNum() {
        return verNum;
    }

    public void setVerNum(long verNum) {
        this.verNum = verNum;
    }

    public int getCatNum() {
        return catNum;
    }

    public void setCatNum(int catNum) {
        this.catNum = catNum;
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNewChaptersTally() {
        return newChaptersTally;
    }

    public void setNewChaptersTally(int newChaptersTally) {
        this.newChaptersTally = newChaptersTally;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public long getLastReadTimestamp() {
        return lastReadTimestamp;
    }

    public void setLastReadTimestamp(long lastReadTimestamp) {
        this.lastReadTimestamp = lastReadTimestamp;
    }

    public int getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(int isMobile) {
        this.isMobile = isMobile;
    }
}
