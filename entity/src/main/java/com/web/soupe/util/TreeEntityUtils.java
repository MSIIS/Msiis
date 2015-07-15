package com.web.soupe.util;

import com.web.soupe.web.TreeEntity;
import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 2015/7/14.
 */
public final class TreeEntityUtils {

    public static List<? extends TreeEntity > sortTreeEntity(List<? extends TreeEntity>  entities){
        List<TreeEntity>  pList = new LinkedList<TreeEntity>();
        int pid = 0;
        if(CollectionUtils.isNotEmpty(entities)){
            int index = 0;
            int level =1;
            String pSortCode ="";
            for(TreeEntity o :entities){
                if(pList==null){
                    pList= new LinkedList<TreeEntity>();
                }
                if(o.getParentId()==pid){
                   index++;
                   if(index/1000>level){
                       level++;
                   }
                   String sortCode=pSortCode+level+String.format("%03d",index);
                   o.setSortCode(sortCode);
                   pList.add(o);

                }else{
                    index=1;
                    for(TreeEntity p:pList){
                        if(p.getId()==o.getParentId()){
                            pSortCode=p.getSortCode();
                            p.setLeaf(false);
                            pList.remove(p);
                            break;
                        }
                    }
                    pList.add(o);
                    String sortCode = pSortCode+level+String.format("%03d",index);
                    o.setSortCode(sortCode);
                    pid=o.getParentId();
                }
            }
        }
        return entities;
    }

}
