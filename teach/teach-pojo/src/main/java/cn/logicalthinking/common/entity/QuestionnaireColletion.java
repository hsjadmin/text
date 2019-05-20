package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 问卷情况汇总表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "问卷情况汇总表",description="问卷情况汇总表") 
public class QuestionnaireColletion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "用户名")
    private String name;
	@ApiModelProperty(value = "账号")
    private String account;
	@ApiModelProperty(value = "满意度(0-3不满意，一般，满意，非常满意)")
    private String satisfied;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question1;
	@ApiModelProperty(value = "")
    private String answer1;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question2;
	@ApiModelProperty(value = "")
    private String answer2;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question3;
	@ApiModelProperty(value = "")
    private String answer3;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question4;
	@ApiModelProperty(value = "")
    private String answer4;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question5;
	@ApiModelProperty(value = "")
    private String answer5;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question6;
	@ApiModelProperty(value = "")
    private String answer6;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question7;
	@ApiModelProperty(value = "")
    private String answer7;
	@ApiModelProperty(value = "八个问题，八个回答")
    private String question8;
	@ApiModelProperty(value = "")
    private String answer8;
	@ApiModelProperty(value = "建议内容")
    private String advise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSatisfied() {
        return satisfied;
    }

    public void setSatisfied(String satisfied) {
        this.satisfied = satisfied;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    public String getQuestion6() {
        return question6;
    }

    public void setQuestion6(String question6) {
        this.question6 = question6;
    }

    public String getAnswer6() {
        return answer6;
    }

    public void setAnswer6(String answer6) {
        this.answer6 = answer6;
    }

    public String getQuestion7() {
        return question7;
    }

    public void setQuestion7(String question7) {
        this.question7 = question7;
    }

    public String getAnswer7() {
        return answer7;
    }

    public void setAnswer7(String answer7) {
        this.answer7 = answer7;
    }

    public String getQuestion8() {
        return question8;
    }

    public void setQuestion8(String question8) {
        this.question8 = question8;
    }

    public String getAnswer8() {
        return answer8;
    }

    public void setAnswer8(String answer8) {
        this.answer8 = answer8;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    @Override
	public String toString() {
		return "QuestionnaireColletion [id="+id+",createTime="+createTime+",updateTime="+updateTime+",name="+name+",account="+account+",satisfied="+satisfied+",question1="+question1+",answer1="+answer1+",question2="+question2+",answer2="+answer2+",question3="+question3+",answer3="+answer3+",question4="+question4+",answer4="+answer4+",question5="+question5+",answer5="+answer5+",question6="+question6+",answer6="+answer6+",question7="+question7+",answer7="+answer7+",question8="+question8+",answer8="+answer8+",advise="+advise+"]";
	}
    
}