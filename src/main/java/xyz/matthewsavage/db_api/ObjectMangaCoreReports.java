package xyz.matthewsavage.db_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "manga_core_reports")
public class ObjectMangaCoreReports {

    @Id
    private long reportId;
    private long id;
    private int chapterNum;
    private int errorCode;
    private String errorLabel;
    private String errorNotes;
    private int resolutionCode;
    private String resolutionLabel;
    private String resolutionNotes;

    public ObjectMangaCoreReports() {

    }

    public ObjectMangaCoreReports(long reportId, long id, int chapterNum, int errorCode, String errorLabel, String errorNotes, int resolutionCode, String resolutionLabel, String resolutionNotes) {
        this.reportId = reportId;
        this.id = id;
        this.chapterNum = chapterNum;
        this.errorCode = errorCode;
        this.errorLabel = errorLabel;
        this.errorNotes = errorNotes;
        this.resolutionCode = resolutionCode;
        this.resolutionLabel = resolutionLabel;
        this.resolutionNotes = resolutionNotes;
    }

    public long getReportId() {
        return reportId;
    }

    public long getId() {
        return id;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorLabel() {
        return errorLabel;
    }

    public String getErrorNotes() {
        return errorNotes;
    }

    public int getResolutionCode() {
        return resolutionCode;
    }

    public String getResolutionLabel() {
        return resolutionLabel;
    }

    public String getResolutionNotes() {
        return resolutionNotes;
    }
}
