package xin.keepmoving.mmall.common;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/24
 **/
public final class Constants {
    public static final String CURRENT_USER = "current_user";

    public  static final String USERNAME = "username";

    public static final String EMAIL = "email";

    public interface Role {
        // 普通用户
        int ROLE_CUSTOMER = 0;
        // 管理员
        int ROLE_ADMIN = 1;
    }
}
