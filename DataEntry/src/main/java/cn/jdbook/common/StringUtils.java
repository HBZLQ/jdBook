package cn.jdbook.common;

public class StringUtils {

    public static boolean isNotEmpty(String str){
        if(null != str && !str.equals(""))
            return true;
        return false;
    }
}
