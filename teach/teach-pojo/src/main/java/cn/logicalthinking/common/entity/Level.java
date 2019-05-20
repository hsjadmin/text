package cn.logicalthinking.common.entity;

import java.util.List;

/**
 * 级别年级科目
 */
public class Level {

    private String levelId;
    private String levelName;

    private List<Grade> grades;

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }


    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
