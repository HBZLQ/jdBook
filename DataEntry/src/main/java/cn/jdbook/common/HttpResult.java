package cn.jdbook.common;

public class HttpResult {

    //状态码
    private Integer code;
    //返回内容
    private String context;

    public void setCode(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }

    public void setContext(String context){
        this.context = context;
    }
    public String getContext(){
        return context;
    }
}
