package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户信息")
    public void getUserListInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //第1步：发送请求，获取结果
        //实际结果
        JSONArray resultJSON = getJsonResult(getUserListCase);

        //第2步：验证
        List<User> userList = session.selectList(getUserListCase.getExpected(), getUserListCase);
        for (User u: userList){
            System.out.println("获取的user是" + u.toString());
        }

        //预期结果
        JSONArray userListJson = new JSONArray(userList);

        //判断获取用户的个数与预期是否相同
        Assert.assertEquals(userListJson.length(), resultJSON.length());

        //判断请求获取的每个用户信息与预期是否相同
        for (int i = 0; i < resultJSON.length(); i++){
            JSONObject expect = (JSONObject) resultJSON.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());

        }
    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject(); //设置json数据
        param.put("userName", getUserListCase.getUserName());
        param.put("sex", getUserListCase.getSex());
        param.put("age", getUserListCase.getAge());

        post.setHeader("content-type", "application/json"); //设置header

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity); //设置entity

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store); //设置cookie

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONArray jsonArray = new JSONArray(result);

        return jsonArray;
    }
}
