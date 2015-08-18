package com.test.demo;

import com.alibaba.fastjson.JSONObject;
import com.util.model.RollDataOutUrl;
import com.util.tools.JSonUtils;
import com.web.soupe.roll.OokerData;
import com.web.soupe.roll.OokerKillNum;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by nlf on 2015-8-17.
 */
public class OutData {
    public static void main(String[] args) {
        //创建客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget
        HttpGet httpget = new HttpGet(RollDataOutUrl.f2);
        CloseableHttpResponse response = null;
        try {
            // 执行get请求.
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                List<OokerData> ookerDatas = new LinkedList<OokerData>();
                // 打印响应内容长度
                System.out.println("Response content length: " + entity.getContentLength());
                // 打印响应内容
                List<String> l1 = new LinkedList<String>();
                String context = EntityUtils.toString(entity);
                if (StringUtils.isNotEmpty(context)) {
                    LinkedHashMap<String, JSONObject> m1 = JSonUtils.parseSimpleLinkedHashMap(context);
                    if (m1.size() > 0) {
                        for (String key : m1.keySet()) {
                            JSONObject value = m1.get(key);
                            for(Map.Entry<String, Object> entry : value.entrySet()){
                                OokerData ookerData = new OokerData();
                                JSONObject obj = (JSONObject) entry.getValue();
                                ookerData.setFieldNum(entry.getKey());
                                ookerData.setLotteyResult(obj.getString("r"));
                                JSONObject killnumObj = obj.getJSONObject("kn");
                                List<OokerKillNum> killNums = new LinkedList<OokerKillNum>();
                                for (Map.Entry<String, Object> ee : killnumObj.entrySet()) {
                                    JSONObject obj1 = (JSONObject) ee.getValue();
                                    String  n =obj1.getString("n");
                                    OokerKillNum killNum = new OokerKillNum();
                                    killNum.setNum(obj1.getString("n"));
                                    killNums.add(killNum);
                                    ookerData.setKillNums(killNums);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
