package tn.telnet.workflow.payload.request;

public class CompetencyCategoryAverageRequest {

    private Long categoryId;
    private String categoryName;
    private Long competencyId;
    private String competencyName;
    private Double averageMark;

    public CompetencyCategoryAverageRequest(Long categoryId, String categoryName, Long competencyId, String competencyName, Double averageMark) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.competencyId = competencyId;
        this.competencyName = competencyName;
        this.averageMark = averageMark;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(Long competencyId) {
        this.competencyId = competencyId;
    }

    public String getCompetencyName() {
        return competencyName;
    }

    public void setCompetencyName(String competencyName) {
        this.competencyName = competencyName;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }
}
