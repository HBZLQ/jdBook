package cn.zlq.analyzer1;

public class TSTNode {
    public String data = null;

    protected TSTNode loNode; //左边节点
    protected TSTNode eqNode; //中间节点
    protected TSTNode hiNode; //右边节点

    protected char splitchar; //本节点表示的字符

    protected TSTNode(char splitchar){
        this.splitchar = splitchar;
    }
    public String toString(){
        return "splitchar:" + splitchar;
    }
}
