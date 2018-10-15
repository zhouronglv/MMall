package xin.keepmoving.mmall.dao;

import org.apache.ibatis.annotations.Param;
import xin.keepmoving.mmall.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkEmail(String email);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question,@Param("answer") String answer);

    int checkPassword(@Param("id") Integer id, @Param("oldPassword") String oldPassword);

    int checkEmailByUserId(@Param("email") String email, @Param("id") Integer id);
}