package cn.logicalthinking.common.base.enums;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程大纲状态
 * @date 2018-12-19
 */
public enum CourseOutlineStatus {

    /**
     * 课程大纲状态
     */

    NO_CLASS(0, "0未上课"),
    HAVE_CLASS(1, "1已上课"),
    IN_CLASS(2, "2上课中"),
    ;

    private Integer key;
    private String value;

    private CourseOutlineStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueBykey(Integer key) {
        for (CourseOutlineStatus code : CourseOutlineStatus.values()) {
            if (code.getKey().equals(key)) {
                return code.getValue();
            }
        }
        return "";
    }

}
