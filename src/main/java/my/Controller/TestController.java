package my.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class TestController {
    //requestMapping作用是指定URI和哪个类或方法作为一个请求的处理器
//在controller中加入一个index方法
    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String index(){
        System.out.println("作为测试部分");
        return "main";
    }
}
