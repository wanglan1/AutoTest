package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {


    @Test(dependsOnGroups = "loginTrue", description = "获取userId为1的用户信息")
    public void getUerInfoTest() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        //发送请求
        //实际结果
        JSONArray resultJSON = getJsonResult(getUserInfoCase);

        //mapper id取getUserInfoCase中的expected字段，即获取预期的用户信息
        User user = session.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);

        List userList = new ArrayList();
        userList.add(user);
        //预期结果
        JSONArray jsonArray = new JSONArray(userList);

        JSONArray jsonArray_res = new JSONArray(resultJSON.getString(0));

        //jsonArray与jsonArray_res内元素顺序可能不同，不能这样断言；可以将里面每个元素取出，逐个断言
        Assert.assertEquals(jsonArray.toString(), jsonArray_res.toString());


    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id", getUserInfoCase.getUserId());

        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(resultList);
        return array;
    }
}
