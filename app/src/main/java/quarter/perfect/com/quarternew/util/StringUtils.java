package quarter.perfect.com.quarternew.util;

import android.text.TextUtils;

/**
 * Created by 小慧莹 on 2018/3/9.
 */

public class StringUtils {
    /**
     * 校验用户名是否合法,判断输入不为空
     * @param username
     * @return
     */
    public static boolean checkUsername(String username){
        if (TextUtils.isEmpty(username)){
            return false;
        }
        //首子字母,长度2到19
        return username.matches("^[a-zA-Z]\\w{2,19}$");
    }
    /**
     * 校验密码是否合法,判断输入不为空
     * @param pwd
     * @return
     */
    public static  boolean checkPwd(String pwd){
        if (TextUtils.isEmpty(pwd)){
            return false;
        }
        //密码全是数字,长度6到16
        return  pwd.matches("^[0-9]{6,16}$");
    }

    public static  String getInitial(String contact){
        if (TextUtils.isEmpty(contact)){
            return contact;
        }else {
            return contact.substring(0,1).toUpperCase();
        }
    }
}
