package com.web.soupe.web;

/**
 * 树形组织结构排序，先有父节点，才有子节点，默认根是0。
 * 那么 ，排序方法就可以是：查询出来的数据，按照父节点排序排下来
 * parentId asc,id asc
 * ,那么，按照从parentId=0开始取数据，因为是有序的，所以遇到不是0的。说明第一层取完。第一层获取到的，下一个的父节点ID一定是存在这几个中。
 * Created by lenovo on 2015/7/14.
 */
public interface TreeEntity {
    public int getId();

    public int getParentId();

    public String getSortCode();

    public boolean isLeaf();

    public void setId(int id);

    public void setParentId(int parentId);

    public void setLeaf(boolean b);

    public void setSortCode(String sortCode);


}
