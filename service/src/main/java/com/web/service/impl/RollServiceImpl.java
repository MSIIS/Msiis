package com.web.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.util.model.DataType;
import com.util.model.QueryRule;
import com.util.tools.JSonUtils;
import com.web.service.BaserService;
import com.web.service.RollService;
import com.web.soupe.roll.OokerData;
import com.web.soupe.roll.OokerKillNum;
import com.web.soupe.roll.Roll;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("rollServiceImpl")
@Transactional(readOnly = true)
public class RollServiceImpl extends BaserService implements RollService {
    @Override
    public List<OokerData> getOokerData2(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        List<OokerData> ookerDatas = new LinkedList<OokerData>();
        try {
            // 执行get请求.
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
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
                            OokerData ookerData = new OokerData();
                            ookerData.setFieldNum(key);
                            ookerData.setLotteyResult(value.getString("r"));
                            JSONObject killnumObj = value.getJSONObject("kn");
                            List<OokerKillNum> killNums = new LinkedList<OokerKillNum>();
                            for (Map.Entry<String, Object> entry : killnumObj.entrySet()) {
                                JSONObject obj = (JSONObject) entry.getValue();
                                OokerKillNum killNum = new OokerKillNum();
                                killNum.setCheck(obj.getInteger("Check"));
                                killNum.setNum(obj.getString("n"));
                                killNums.add(killNum);
                                ookerData.setKillNums(killNums);
                            }
                            ookerDatas.add(ookerData);
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
        return ookerDatas;
    }

    @Override
    public List<OokerData> getOokerData(String url) { //创建客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        List<OokerData> ookerDatas = new LinkedList<OokerData>();
        try {
            // 执行get请求.
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
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
                            OokerData ookerData = new OokerData();
                            ookerData.setFieldNum(key);
                            ookerData.setLotteyResult(value.getString("LotteryResult"));
                            JSONObject killnumObj = value.getJSONObject("KillNum");
                            List<OokerKillNum> killNums = new LinkedList<OokerKillNum>();
                            for (Map.Entry<String, Object> entry : killnumObj.entrySet()) {
                                JSONObject obj = (JSONObject) entry.getValue();
                                OokerKillNum killNum = new OokerKillNum();
                                killNum.setCheck(obj.getInteger("Check"));
                                killNum.setNum(obj.getString("Num"));
                                killNums.add(killNum);
                                ookerData.setKillNums(killNums);
                            }
                            ookerDatas.add(ookerData);
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
        return ookerDatas;
    }

    @Override
    @Transactional(readOnly = false)
    public List<Roll> getNumbersOfKill(String[] reds, String[] blues, int num, int sum1, int sum2, String sort) {
        List<Roll> rolls = new LinkedList<Roll>();
        List<Integer> redTotal = this.getTotle(33);
        List<Integer> blueTotal = this.getTotle(16);

        int jj = 1;

        if (reds != null) {
            for (String red : reds) {
                Integer i = Integer.parseInt(red);
                redTotal.remove(i);
            }
        }
        if (blues != null) {
            for (String blue : blues) {
                Integer i = Integer.parseInt(blue);
                blueTotal.remove(i);
            }
        }
        while (jj <= num) {
            List<Integer> r1 = new LinkedList<Integer>();
            List<Integer> b1 = new LinkedList<Integer>();
            Set<Integer> redIndexSet = this.getIndex(redTotal, 6);
            Set<Integer> blueIndexSet = this.getIndex(blueTotal, 1);
            for (Integer inte : blueIndexSet) {
                b1.add(blueTotal.get(inte));
            }

            int sum = 0;
            for (Integer inte : redIndexSet) {
                r1.add(redTotal.get(inte));
                sum += redTotal.get(inte);
            }
            if (sum > sum1 && sum < sum2) {
                Roll roll = new Roll();
                Collections.sort(r1);
                roll.setRed(this.convertToString(r1));
                roll.setBlue(this.convertToString(b1));
                roll.setIssueNum(sort);
                roll.setRedSum(sum);
                roll.setDataSourceType(DataType.SSQ_FOR_SOURE_TYPE_KILL);
                rolls.add(roll);
                jj++;
            }
        }
        daoManager.getRollDaoH4().saveAll(rolls);
        return rolls;
    }

    public List<Integer> getTotle(int num) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= num; i++) {
            list.add(i);
        }
        return list;
    }

    public Set<Integer> getIndex(Collection<Integer> coll, int k) {
        Set<Integer> redIndexSet = new HashSet<Integer>();
        Random r = new Random();
        while (redIndexSet.size() < k) {
            int j = r.nextInt(coll.size());
            redIndexSet.add(j);
        }

        return redIndexSet;
    }

    public String convertToString(List<Integer> list) {
        String s = "";
        for (Integer i : list) {
            s = s + i + "  ";
        }
        return s;
    }

    @Override
    public List<Roll> findNums(String sort, String dataType) {
        QueryRule queryRule = QueryRule.getInstance();
        if (StringUtils.isNotEmpty(dataType)) {
            queryRule.addEqual("dataSourceType", dataType);
        }
        if (StringUtils.isNotEmpty(sort)) {
            queryRule.addLike("numberFiel", sort + "%");
        }
        return daoManager.getRollDaoH4().find(queryRule);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteNums(String ids) {
        List<Long> list = new ArrayList<Long>();
        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            for (String id : array) {
                list.add(Long.parseLong(id));
            }
            daoManager.getRollDaoH4().deleteByPks(list);
        }

    }

    @Override
    public List<Roll> findListByJdbcSql(Long id) {

        return this.getDaoManager().getRollDaoH4().findObjectByJdbc(id);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Roll> getNumbersOfChoose(String[] reds, String[] blues, int num, int sum1, int sum2, String sort) {
        List<Roll> rolls = new LinkedList<Roll>();
        Set<Integer> reds_a = new HashSet<Integer>();
        Set<Integer> blus_a = new HashSet<Integer>();
        int jj = 1;
        if (reds != null) {
            for (String red : reds) {
                Integer i = Integer.parseInt(red);
                reds_a.add(i);
            }
        }
        if (blues != null) {
            for (String blue : blues) {
                Integer i = Integer.parseInt(blue);
                blus_a.add(i);
            }
        }
        List<Integer> r = new LinkedList<Integer>();
        List<Integer> b = new LinkedList<Integer>();

        b.addAll(blus_a);
        r.addAll(reds_a);

        while (jj <= num) {
            List<Integer> r1 = new LinkedList<Integer>();
            List<Integer> b1 = new LinkedList<Integer>();
            Set<Integer> redIndexSet = this.getIndex(r, 6);
            Set<Integer> blueIndexSet = this.getIndex(b, 1);
            for (Integer inte : blueIndexSet) {
                b1.add(b.get(inte));
            }

            int sum = 0;
            for (Integer inte : redIndexSet) {
                r1.add(r.get(inte));
                sum += r.get(inte);
            }
            if (sum > sum1 && sum < sum2) {
                Roll roll = new Roll();
                Collections.sort(r1);
                roll.setRed(this.convertToString(r1));
                roll.setBlue(this.convertToString(b1));
                roll.setIssueNum(sort);
                roll.setRedSum(sum);
                roll.setDataSourceType(DataType.SSQ_FOR_SOURE_TYPE_CHOOSE);
                rolls.add(roll);
                jj++;
            }
        }
        daoManager.getRollDaoH4().saveAll(rolls);
        return rolls;
    }

}
