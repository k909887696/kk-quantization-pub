package com.kk.quantizationapi.utils;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author: kk
 * @Date: 2021/11/18 17:46
 */
public class JsonUtil {

    /**
     * 转对象为JSON字符串
     * @param obj 对象
     * @return String JSON字符串
     */
    public static String getJSONString(Object obj){
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析JSON字符串为对象
     * @param jsonString JSON
     * @param clazz 类
     * @return Object 对象
     */
    public static Object parseObject(String jsonString, Class clazz){
        try {
            return JSON.parseObject(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析JSON字符串为对象
     * @param jsonString JSON
     * @param clazz 类
     * @return Object 对象
     */
    public static Object parseObject(String jsonString, Type clazz){
        /* new TypeReference<List<xx>>(){}.getType() */
        try {
            return JSON.parseObject(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将jsonString转化为hashmap
     * @param jsonString JSON字符串
     * @return Map 对象
     */
    public static HashMap<String, Object> fromJson2Map(String jsonString) {
        HashMap jsonMap = JSON.parseObject(jsonString, HashMap.class);

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        for(Iterator iter = jsonMap.keySet().iterator(); iter.hasNext();){
            String key = (String)iter.next();
            if(jsonMap.get(key) instanceof JSONArray){
                JSONArray jsonArray = (JSONArray)jsonMap.get(key);
                List list = handleJSONArray(jsonArray);
                resultMap.put(key, list);
            }else{
                resultMap.put(key, jsonMap.get(key));
            }
        }
        return resultMap;
    }

    /**
     * 将jsonString转化为hashmap
     * 不处理List中的数据
     * @param jsonString JSON字符串
     * @return Map 对象
     */
    public static HashMap<String, Object> fromJson2Map2(String jsonString) {
        HashMap jsonMap = JSON.parseObject(jsonString, HashMap.class);

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        for(Iterator iter = jsonMap.keySet().iterator(); iter.hasNext();){
            String key = (String)iter.next();
            if(jsonMap.get(key) instanceof JSONArray){
                //JSONArray jsonArray = (JSONArray)jsonMap.get(key);
                //List list = handleJSONArray(jsonArray);
                resultMap.put(key, jsonMap.get(key));
            }else{
                resultMap.put(key, jsonMap.get(key));
            }
        }
        return resultMap;
    }

    private static List<HashMap<String, Object>> handleJSONArray(JSONArray jsonArray){
        List list = new ArrayList();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            HashMap map = new HashMap<String, Object>();
            for (Map.Entry entry : jsonObject.entrySet()) {
                if(entry.getValue() instanceof JSONArray){
                    map.put((String)entry.getKey(), handleJSONArray((JSONArray)entry.getValue()));
                }else{
                    map.put((String)entry.getKey(), entry.getValue());
                }
            }
            list.add(map);
        }
        return list;
    }

    public static void main(String[] args){
        String shardParam="{\"listCityCode\":[\"c11127\",\"c10750\"],\"threadNum\":1,\"refreshType\":1}";
        //HashMap<String, Object> shardParamMap = JsonUtil.fromJson2Map(shardParam);//(HashMap<String, Object>)
        Map<?, ?> shardParamMap = JsonUtil.fromJson2Map2(shardParam);//(HashMap<String, Object>)

        System.out.print(shardParamMap.toString());
    }

}
