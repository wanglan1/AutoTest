package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    //执行这个用例，前提需要登录成功！
    //依赖登录成功那个组
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase", 1);
        System.out.println("测试数据为："+ addUserCase.toString());
        System.out.println( "addUser的uri为：" + TestConfig.addUserUrl);

        //发请求，获取结果
        String result = getResult(addUserCase);
        Thread.sleep(6000);

        //验证返回结果
//        session.commit();
//        User user = session.selectOne("addUser", addUserCase); //sql：查看新增加的user
//        System.out.println("请求接口后，user表中新增的user为：" + user.toString());

        //处理结果，判断返回结果是否符合预期
        Assert.assertEquals(addUserCase.getExpected(), result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName", addUserCase.getUserName());
        param.put("password", addUserCase.getPassword());
        param.put("sex", addUserCase.getSex());
        param.put("age", addUserCase.getAge());
        param.put("permission", addUserCase.getPermission());
        param.put("isDelete", addUserCase.getIsDelete());
        //设置头信息
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result; //存放返回结果
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("请求响应结果为：" + result);
        return result;
    }
}
