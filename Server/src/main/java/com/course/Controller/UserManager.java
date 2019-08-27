package com.course.Controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "v1", description = "用户管理系统")
@RequestMapping("/v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template; //访问数据库的对象

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    //@requestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
    // 比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
    //通过@requestBody可以将请求体中的JSON字符串绑定到相应的bean上，当然，也可以将其分别绑定到对应的字符串上。
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i = template.selectOne("login", user); //查看数据库中是否有这个用户
        Cookie cookie =new Cookie("login", "true");
        response.addCookie(cookie);
        log.info("查询到的结果是" + i);
        if (i == 1){
            log.info("登录的用户是：" + user.getUserName());
            return true;
        }
        return false;
    }


    /**
     * 添加用户
     */
    @ApiOperation(value = "添加用户接口", httpMethod = "POST")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user){
        Boolean x = verifyCookies(request); //验证cookie
        int result = 0;
        if (x != null){
            result = template.insert("addUser", user);
        }
        if (result > 0){
            log.info("添加用户的数量是：" + result);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "获取用户（列表）信息接口", httpMethod = "POST")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user){
        Boolean x = verifyCookies(request);
        if (x == true){
            List<User> users = template.selectList("getUserInfo", user); //实现单个或多个用户查询
            log.info("getUserInfo获取到的用户数量是" + users.size());
            return users;
        }else {
            return null;
        }
    }

    //注意：这里的删除为逻辑删除，即把isDelete置为1
    @ApiOperation(value = "更新/删除用户接口", httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request, @RequestBody User user){
        System.out.println(user);
        Boolean x = verifyCookies(request);
        //若不传id或id非法，则不更新
        if (user.getId() == 0){
            return 0;
        }
        int i = 0;
        if (x == true){
            i = template.update("updateUserInfo", user);
        }
        log.info("更新数据的条目数为：" + i);
        return i;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("cookies为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
