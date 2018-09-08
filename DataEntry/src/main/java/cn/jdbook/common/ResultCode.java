package cn.jdbook.common;

public enum ResultCode {
    SUCCESS(0, "成功"),
    FAULT(1, "失败");

    private int value;
    private String name;
    ResultCode(int value, String name){
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return value;
    }
    public String getName(){
        return name;
    }
}
