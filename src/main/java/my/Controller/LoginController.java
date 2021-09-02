package my.Controller;

import my.util.JsonMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/my")
public class LoginController {
    //登录：跳转到登录页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    //对登录页面输入的用户名和密码做简单的判断
    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg dologin(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username+password);
        if(!"admin1234".equals(username+password)){
            return  JsonMsg.fail().addInfo("login_error","输入用户名与密码不匹配，请重新输入！");
        }
        return  JsonMsg.success();
    }

    //跳转到主页面
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    //退出登录：从主页面跳转到登录页面
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        return "login";
    }

}
