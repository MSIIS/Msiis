package com.test.design.productcustom;

import com.test.common.MyTestException;
import com.util.tools.JSonUtils;
import org.apache.log4j.Logger;

/**
 * 工作任务接口
 * 假设物品仓库的数量少于10个了，就要进行生成任务，以保障可以提供正常的消费
 * Created by gzh on 2016-3-24.
 */
public class WorkTask implements  Runnable {

    private Logger logger = Logger.getLogger(WorkTask.class);

    /**
     * 工作类型  1-去生成物品 其他-去消费物品
     */
    private  int  operationType ;

    public StoreHouse currentStoreHouse = StoreHouse.getOneStoreHouse();

    @Override
    public void run() {
        try{
            //当前的任务为生产操作
            if(this.operationType==1){
                //执行生产的操作
                this.productItem();
            }else{
                //执行消费的操作
                this.customItem();
            }
        }catch (MyTestException my){
            this.logger.error(my.getMessage(),my);
        }catch (Exception ex){
            this.logger.error("工作出错",ex);
        }
    }

    public WorkTask(int operationType) {
        this.operationType = operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    /**
     * 生成并存放一件物品
     * @return
     */
    private  void productItem()  throws MyTestException{
        ItemRes itemRes = new ItemRes();
        itemRes.setItemId(currentStoreHouse.getCurrentItemSize()+1);
        currentStoreHouse.setItem(itemRes);
        this.logger.info("生成了一件物品【"+ JSonUtils.toJSONString(itemRes)+"】仓库目前库存 【"+currentStoreHouse.getCurrentItemSize()+"】");
    }


    /**
     * 消费一件物品
     */
    private void customItem() throws  MyTestException{
       currentStoreHouse.removeItem(currentStoreHouse.getCurrentItemSize());
       this.logger.info("消费了一件物品,itemId :【"+ currentStoreHouse.getCurrentItemSize()+"】仓库目前库存 【"+currentStoreHouse.getCurrentItemSize()+"】");
    }
}
