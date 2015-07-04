package com.util.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 联想下拉列表选项Vo类
 * 
 * @author liangzh
 *
 * 2013年9月16日 上午10:31:52
 *
 */
public class SelectVo {

    //ID
    private String id;
    //中文显示
    private String name;
    //其他信息
    private String otherInfo;

    public SelectVo() {
        super();
    }

    /**
     * 构造函数
     * 
     * @param id
     * @param name
     */
    public SelectVo(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * map转换为List<SelectVo>
     *
     * @param selectVos
     * @param paramMap
     * @return
     */
    public static List<SelectVo> addList(List<SelectVo> selectVos, Map<?, ?> paramMap) {
        if (selectVos == null) {
            selectVos = new ArrayList<SelectVo>();
        }
        if (paramMap != null && paramMap.size() > 0) {
            Set<?> keys = paramMap.keySet();
            for (Object key : keys) {
                SelectVo selectVo = new SelectVo();
                if (key != null) {
                    selectVo.setId(key + "");
                }
                if (paramMap.get(key) != null) {
                    selectVo.setName(paramMap.get(key) + "");
                }
            }
        }
        return selectVos;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherInfo() {
        return this.otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
