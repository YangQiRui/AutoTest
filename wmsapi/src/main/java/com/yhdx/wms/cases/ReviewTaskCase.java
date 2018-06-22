package com.yhdx.wms.cases;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhdx.wms.client.ClientBuild;
import com.yhdx.wms.config.TestReview2UrlConfig;
import com.yhdx.wms.domain.ReviewTaskBean;
import com.yhdx.wms.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ReviewTaskCase {
    private static Logger logger = LoggerFactory.getLogger(ReviewTaskCase.class);

    @Test(dependsOnGroups = "loginTrue")
    public  void  reviewTask() throws IOException {
        //读取参数
        SqlSession session = DatabaseUtil.getSqlSession();
        ReviewTaskBean reviewTaskBean = session.selectOne("reviewTaskData",1);

        //调用客户端发送请求
        JSONObject result = ClientBuild.doPost(TestReview2UrlConfig.getReviewTask, reviewTaskBean.toString());
        logger.info("响应：{}",result.toString());

        //断言状态码
        Assert.assertEquals(result.getString("code"),"ok");
        logger.info("断言状态码:Success~");
        //断言响应数据的状态码(只查询状态为100的)
        JSONArray jsonArray = result.getJSONObject("result").getJSONArray("rows");
        if (jsonArray.size()>0){
            logger.info("开始进行响应数据校验~");
            String status = jsonArray.getJSONObject(0).getString("status");
            Assert.assertEquals(status,reviewTaskBean.getExpected());
            logger.info("实际结果：\t{}",status);
            logger.info("预期结果：\t{}",reviewTaskBean.getExpected());
        }else {
            logger.info("返回数据为空~end！");
        }
    }
}