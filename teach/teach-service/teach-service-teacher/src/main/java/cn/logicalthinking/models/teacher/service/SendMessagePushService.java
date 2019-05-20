package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.YunXinConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.util.HttpRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2018/12/29,18:02
 */
@Service
public class SendMessagePushService extends AbstractService {

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        //发送类型(0:发送普通消息,1:批量发送点对点普通消息)
        String sendType = data.getParameter("sendType");

        Map<String, Object> map = getMap(sendType);

        String url = "";

        if("0".equals(sendType)){
            url = YunXinConstant.MESSAGE_PUSH;
        }else if("1".equals(sendType)){
            url = YunXinConstant.MESSAGE_PUSH_BAT;
        }

        String push = HttpRequest.sendMessagePush(url, map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),push);
    }

    public Map<String,Object> getMap(String sendType){
        Map<String, Object> map = new HashMap<>();

        if("0".equals(sendType)){
            //普通消息参数
            String from = data.getParameter("from");
            String ope = data.getParameter("ope");
            String to = data.getParameter("to");

            if(StringUtils.isNotBlank(from)){
                map.put("form",from);
            }
            if(StringUtils.isNotBlank(ope)){
                map.put("ope",ope);
            }
            if(StringUtils.isNotBlank(to)){
                map.put("to",to);
            }

        }else if("1".equals(sendType)){
            //群发参数
            String fromAccid = data.getParameter("fromAccid");
            String toAccids = data.getParameter("toAccids");
            String returnMsgid = data.getParameter("returnMsgid");
            if(StringUtils.isNotBlank(fromAccid)){
                map.put("fromAccid",fromAccid);
            }
            if(StringUtils.isNotBlank(toAccids)){
                map.put("toAccids",toAccids);
            }if(StringUtils.isNotBlank(returnMsgid)){
                map.put("returnMsgid",returnMsgid);
            }
        }
        //公共参数
        String type = data.getParameter("type");
        String body = data.getParameter("body");

        if(StringUtils.isNotBlank(type)){
            map.put("type",type);
        }
        if(StringUtils.isNotBlank(body)){
            map.put("body",body);
        }

        return map;
    }
}
