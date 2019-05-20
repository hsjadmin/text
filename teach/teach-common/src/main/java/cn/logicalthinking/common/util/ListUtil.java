package cn.logicalthinking.common.util;

import java.util.List;

/**
 * List
 *
 * @author XHX
 * @date 2018/7/17
 */
public class ListUtil {

    /**
     * 判断list为空
     *
     * @param list
     * @return
     */
    public static boolean isBlank(List list) {
        return list == null || list.size() == 0 || list.isEmpty();
    }

}
