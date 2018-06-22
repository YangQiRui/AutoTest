package com.yhdx.wms.cases;

import com.yhdx.wms.config.TestReview2UrlConfig;
import com.yhdx.wms.domain.dto.InterfaceName;
import com.yhdx.wms.domain.LoginBean;
import com.yhdx.wms.client.ClientBuild;
import com.yhdx.wms.utils.ContantUtil;
import com.yhdx.wms.utils.DatabaseUtil;
import com.yhdx.wms.utils.UrlBuildUtil;
import org.apache.ibatis.session.SqlSession;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginCase {
    private static Logger logger = LoggerFactory.getLogger(LoginCase.class);

    @BeforeClass(description = "测试准备工作")
    public void  beforeTest(){
        logger.info("开始进行初始化URL工作的准备####");
        TestReview2UrlConfig.loginUrl = UrlBuildUtil.getUrl(InterfaceName.LONGIN);
        TestReview2UrlConfig.getReviewTask = UrlBuildUtil.getUrl(InterfaceName.GETREVIEWTASK);
        TestReview2UrlConfig.getLocalReviewTask = UrlBuildUtil.getUrl(InterfaceName.GETLOCALREVIEWTASK);
        logger.info("结束初始化URL工作的准备####");
    }
    @Test(groups = "loginTrue")
    public static void loginTrue() throws IOException {
        //读取参数赋值给对象
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginBean loginbean = session.selectOne("loginData",1);
        //调用客户端发送请求且接受响应  这里参数直接tostringjson类型
        JSONObject result = ClientBuild.doPost(TestReview2UrlConfig.loginUrl,loginbean.toString());
        logger.info("响应实体:{}",result.toJSONString());
        //进行token提取
        String token = result.getJSONObject("result").getString("token");
        //将toke赋值给application.properties
        ContantUtil.setProperties("token",token);

        //断言判断响应体中code与预期结果是否一致  ok
        Assert.assertEquals(result.getString("code"),loginbean.getExpected());
        logger.info("断言状态码:Success~");
        logger.info("实际结果：\t{}",result.getString("code"));
        logger.info("预期结果：\t{}",loginbean.getExpected());

    }



}
