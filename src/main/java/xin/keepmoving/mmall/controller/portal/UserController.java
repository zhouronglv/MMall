package xin.keepmoving.mmall.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.keepmoving.mmall.common.Constants;
import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.pojo.User;
import xin.keepmoving.mmall.service.IUserService;

import javax.servlet.http.HttpSession;

/**
 * <p>@author: zhourl
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/23
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> resp = userService.login(username, password);

        if (resp.isSuccess()) {
            session.setAttribute(Constants.CURRENT_USER, resp.getData());
        }
        return resp;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Constants.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/checkValid.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String val, String type) {
        return userService.checkValid(val, type);
    }

    @RequestMapping(value = "/get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录");
    }

    @RequestMapping(value = "/forget_get_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        ServerResponse<String> resp = userService.selectQuestion(username);
        return resp;
    }

    @RequestMapping(value = "/forget_check_answer.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        ServerResponse<String> resp = userService.checkAnswer(username, question, answer);
        return resp;
    }
}
