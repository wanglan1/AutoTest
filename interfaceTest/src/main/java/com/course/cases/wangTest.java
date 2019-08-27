package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;
import java.io.IOException;


public class wangTest {

    @Test
    public void addUser() throws IOException{
        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase", 1);
        System.out.println("测试数据为："+ addUserCase.toString());
        //验证返回结果
        User user = session.selectOne("addUser", addUserCase); //sql：查看新增加的user
        System.out.println("请求接口后，user表中新增的user为：" + user.toString());

    }

}
