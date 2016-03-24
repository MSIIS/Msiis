package com.test.design.productcustom;

import com.test.common.MyTestException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 物品仓库,单例模式。
 * Created by gzh on 2016-3-24.
 */
public class StoreHouse {

    /**
     * 货物清单
     */
    private static Map<Integer, ItemRes> itemMap;

    private static final Integer maxeSize =30;

    /**
     * 单例模式创建仓库
     * 1.私有的构造器,拥有自身的成员变量
     * 2.对外公开的静态方法
     * 3.饱汉和饿汉模式
     * 4.线程安全问题
     */
    //饿汉模式，即不初始化  饱汉模式直接初始化一个返回
    private static StoreHouse single = null;

    private StoreHouse() {
        this.itemMap = new LinkedHashMap<Integer, ItemRes>();
    }

    //第一种  使用的是同步的模式
    public static synchronized StoreHouse getSingleInstance() {
        if (single == null) {
            single = new StoreHouse();
        }
        return single;
    }
    //第二种 内部类创建一个单例 ==============================================

    /**
     * 好处在于 ：既实现了线程安全，又避免了同步带来的性能影响。比第一种好点
     */
    private static class LazyHolder {
        private static final StoreHouse INSTANCE = new StoreHouse();
    }

    public static final StoreHouse getOneStoreHouse() {
        return LazyHolder.INSTANCE;
    }

    //=======================================================

    /**
     * 放入物品清单
     *
     * @param item
     * @throws MyTestException
     */
    public void setItem(ItemRes item) throws MyTestException {
        if (this.itemMap != null) {
            if(maxeSize<=this.itemMap.size()){
                throw  new MyTestException("仓库只能存放【"+maxeSize+"】件物品");
            }
            this.itemMap.put(item.getItemId(), item);
        } else {
            throw new MyTestException("放入物品失败，物品清单没有初始化",1);
        }

    }

    /**
     * 出库一件物品
     *
     * @return
     */
    public void removeItem(Integer itemId) {
        if (this.itemMap != null) {
            itemMap.remove(itemId);
        }
    }

    /**
     * 返回现有物品的数量
     * @return
     */
    public int getCurrentItemSize(){
        if(this.itemMap!=null){
            return  this.itemMap.size();
        }
        return  0;
    }


}
