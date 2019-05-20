package cn.logicalthinking.common.entity;

import java.util.List;

/**
 * 级别年级科目
 */
public class Grade {

    private String gradeId;
    private String gradeName;

    List<Subject> subjects;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
