package cn.logicalthinking.common.base.constant;


/**
 * @note
 * @auhtor 卢祖飞
 * @date 2018/12/28,16:53
 */
public class YunXinConstant {

    public static String ACCID = "accid";
    public static String ICON = "icon";
    public static String NAME = "name";


    /**
    *@note 云信注册地址
    *@auhtor 卢祖飞
    *@date 2018/12/28,16:58
    *@version 1.0
    */
    public static String REGISTER_URL = "https://api.netease.im/nimserver/user/create.action";

    /**
    *@note 判断房间是否存在
    *@auhtor 卢祖飞
    *@date 2018/12/28,17:00
    *@version 1.0
    */
    public static String IS_EXISTENCE_ROOM = "https://roomserver-dev.netease.im/v1/api/rooms/{id}";

    /**
    *@note 修改云信用户信息
    *@auhtor 卢祖飞
    *@date 2018/12/28,17:01
    *@version 1.0
    */
    public static String UPDATE_USER_INFO = "https://api.netease.im/nimserver/user/updateUinfo.action";

    /**
    *@note 一对一推送
    *@auhtor 卢祖飞
    *@date 2018/12/28,17:03
    *@version 1.0
    */
    public static String MESSAGE_PUSH = "https://api.netease.im/nimserver/msg/sendMsg.action";

    /**
    *@note 群发消息
    *@auhtor 卢祖飞
    *@date 2018/12/29,17:23
    *@version 1.0
    */
    public static String MESSAGE_PUSH_BAT = "https://api.netease.im/nimserver/msg/sendBatchMsg.action";



}
