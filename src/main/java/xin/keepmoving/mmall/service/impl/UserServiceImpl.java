package xin.keepmoving.mmall.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.keepmoving.mmall.common.Constants;
import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.common.TokenCache;
import xin.keepmoving.mmall.dao.UserMapper;
import xin.keepmoving.mmall.pojo.User;
import xin.keepmoving.mmall.service.IUserService;
import xin.keepmoving.mmall.util.Md5Util;

import java.util.UUID;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/23
 **/
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public ServerResponse<User> login(String username, String password) {
        int count = userMapper.checkUsername(username);

        if (count == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = Md5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse<String> validResp = this.checkValid(user.getUsername(), Constants.USERNAME);
        if (!validResp.isSuccess()) {
            return validResp;
        }

        validResp = this.checkValid(user.getEmail(), Constants.EMAIL);

        if (!validResp.isSuccess()) {
            return validResp;
        }

        user.setRole(Constants.Role.ROLE_CUSTOMER);

        user.setPassword(Md5Util.MD5EncodeUtf8(user.getPassword()));

        int insertCount = userMapper.insert(user);

        if (insertCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String val, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Constants.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(val);
                if (resultCount == 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }

            if (Constants.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(val);

                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("Email已存在");
                }
            }

        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse<String> validResp = this.checkValid(username, Constants.USERNAME);
        if (validResp.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey("token_" + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }
}
