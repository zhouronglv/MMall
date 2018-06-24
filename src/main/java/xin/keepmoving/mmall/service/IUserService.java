package xin.keepmoving.mmall.service;

import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.pojo.User;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/23
 **/
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String val, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String quesion, String answer);
}
