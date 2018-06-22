package com.yhdx.wms.client;

import com.alibaba.fastjson.JSONObject;
import com.yhdx.wms.utils.ContantUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ClientBuild {
    private static Logger logger = LoggerFactory.getLogger(ClientBuild.class);


    private ClientBuild() {
    }
//    private static CloseableHttpClient HttpClient = null;

    private static HttpPost post = null;
    private static StringEntity requstEntity = null;
    private static CloseableHttpResponse response = null;
    private static HttpEntity responsEentity = null;
    private static JSONObject jsonResponsEentity = null;

    public static JSONObject doPost(String url, String bodyParams)throws ClientProtocolException, IOException{

            // 创建客户端
            CloseableHttpClient HttpClient = HttpClients.createDefault();
            // 请求行
            post = new HttpPost(url);

            // 请求头
            post.addHeader("token", ContantUtil.getProperties().getProperty("token"));
//            logger.info("在配置文件中获取到的token:{}",ContantUtil.getProperties().getProperty("token"));

            // 请求实体
            requstEntity = new StringEntity(bodyParams);
            requstEntity.setContentEncoding("UTF-8");
            requstEntity.setContentType("application/json");// 发送json数据需要设置contentType
            post.setEntity(requstEntity);
            logger.info("这里是请求行 {}",post);
            logger.info("这里是请求实体 {}",bodyParams);
            // 响应
            response = HttpClient.execute(post);
//            logger.info("响应行:\t{}",response.toString());
            if (response.getStatusLine().getStatusCode() == 200) {
                responsEentity = response.getEntity();
                if(responsEentity != null) {
                    String stringResponsEentity = EntityUtils.toString(responsEentity, "UTF-8");
                    jsonResponsEentity = JSONObject.parseObject(stringResponsEentity);
                }
            } else {
                JSONObject reqFail = new JSONObject();
                reqFail.put("code", response.getStatusLine().getStatusCode());
                reqFail.put("failbody", response.getStatusLine());
                logger.debug("关闭会话：" + reqFail);
                return reqFail;
            }

        return jsonResponsEentity;
    }

    }



