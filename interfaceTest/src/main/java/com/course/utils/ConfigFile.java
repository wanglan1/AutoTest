package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 读取application.properties文件，拼接测试接口URL
 */
public class ConfigFile {

    private static ResourceBundle bundle= ResourceBundle.getBundle("application", Locale.CHINA);;

    //传进来的name，必须是我们设计的InterfaceName类中枚举的接口名，不能瞎传
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri = ""; //获取application.perperties中的uri
        String testUrl;  //最终的测试地址，address+uri
        if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");

        }

        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }

        if(name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }

        if(name == InterfaceName.ADDUSERINFO){
            uri = bundle.getString("addUser.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
}
