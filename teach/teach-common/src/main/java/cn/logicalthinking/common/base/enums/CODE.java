package cn.logicalthinking.common.base.enums;

public enum  CODE {

	CODE_200_SELECT("200", "请求成功"),
	CODE_200_ADD("200", "添加成功"),
	CODE_200_UPDATE("200", "修改成功"),
	CODE_200_DELETE("200", "删除成功"),
	CODE_201_LOGIN("201", "登录成功"),

	CODE_300("300", "登录超时,请重新登录"),
	CODE_301("300", "您没有操作权限"),
	CODE_302("302", "待完善"),
	CODE_303("303", "审核未通过/审核中"),
	CODE_304("304", "未注册"),
	CODE_305("305", "被禁用"),

	CODE_400("400", "校验不通过"),
	CODE_401("401", "余额不足"),

	CODE_500("500", "系统不明异常"),
	CODE_501("501", "空指针异常"),
	CODE_502("502", "指定的类不存在"),
	CODE_503("503", "数学运算异常"),
	CODE_504("504", "数组下标越界"),
	CODE_505("505", "下标越界"),
	CODE_506("506", "方法的参数错误"),
	CODE_507("507", "类型强制转换错误"),
	CODE_508("508", "违背安全原则异常"),
	CODE_509("509", "操作数据库异常"),
	CODE_510("510", "方法末找到异常"),
	CODE_511("511", "数字类型格式化异常"),
	CODE_512("512", "上传文件超过限制大小"),
	CODE_513("513", "系统找不到指定文件"),
	CODE_514("514", "网络异常"),
	CODE_515("515", "IO异常"),
	CODE_516("516", "上传文件异常"),
	CODE_517("517", "事物回滚失败"),
	CODE_518("518", "提交事物失败异常"),
	CODE_519("519", "执行SQL语句错误");

	private String key;
	private String value;

	private CODE(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getValueBykey(String key) {
		for (CODE code : CODE.values()) {
			if (code.getKey().equals(key)) {
				return code.getValue();
			}
		}
		return "";
	}
}

