package com.yhdx.wms.utils;

import com.yhdx.wms.domain.dto.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class UrlBuildUtil {
    //读取配置文件
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CANADA);

    //拼接url
    public static String getUrl(InterfaceName name) {

        String uri = bundle.getString("uri.test");
        String path = "";
        //最终的测试地址
        String testUrl;
        //登录
        if(name == InterfaceName.LONGIN){
            path = bundle.getString("path.getLogin");
        }
        //RF-盘点任务列表（条件查询）
        if(name == InterfaceName.GETREVIEWTASK){
            path = bundle.getString("path.getReviewTask");
        }
        //RF-本地盘点任务列表
        if(name == InterfaceName.GETLOCALREVIEWTASK){
            path = bundle.getString("path.getLocalReviewTask");
        }


        //url拼接
        testUrl =uri+path;
//        System.out.println(testUrl);
        return  testUrl;
    }
}
