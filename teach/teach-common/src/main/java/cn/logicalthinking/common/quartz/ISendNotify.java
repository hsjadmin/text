package cn.logicalthinking.common.quartz;

import java.util.Map;

public interface ISendNotify {

    public abstract void send(Map<String, String> param) throws Exception;

}
