package hello.com.sourse.server;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public String getCookies(){
        return "获取到了cookies信息";
    }
}
