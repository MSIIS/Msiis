package com.test.design.productcustom;

import java.io.Serializable;

/**
 * 货物资源
 * Created by gzh on 2016-3-24.
 */
public class ItemRes implements Serializable {

    public static final String ITEM_TYPE="BOOK";

    private int itemId ;

    private String itemCode ;

    private String itemName;

    private String itemType;


    public ItemRes(int itemId, String itemCode, String itemName, String itemType) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        if(itemType==null){
            itemType=this.ITEM_TYPE;
        }
        this.itemType = itemType;
    }

    public ItemRes() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
