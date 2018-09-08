package cn.jdbook.analyzer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class TreeNode {

    private char _char; //节点代表的字符
    private TreeNode _parent;//该节点的父节点
    private TreeNode _failure;//匹配失败后跳转的节点

    private ArrayList<String> _results;//存储模式串的数组变量
    private TreeNode[] _transitionsAr;

    private Hashtable<Character, TreeNode> _transHash;

    public TreeNode(TreeNode parent, char c){
        this._char = c;
        _parent = parent;
        _results = new ArrayList<String>();//储存所有没有重复的模式串的数组
        _transitionsAr = new TreeNode[]{};
        _transHash = new Hashtable<Character, TreeNode>();
    }

    //将模式传中不在_results中的模式串添加进来
    public void addResult(String result){
        if(_results.contains(result)){
            return ;
        }
        _results.add(result);
    }
    //增加一个孩子节点
    public void addTransition(TreeNode node){
        _transHash.put(node._char, node);
        TreeNode[] ar = new TreeNode[_transHash.size()];
        Iterator<TreeNode> it = _transHash.values().iterator();
        for(int i = 0; i < ar.length; i ++){
            if(it.hasNext()){
                ar[i] = it.next();
            }
        }
        _transitionsAr = ar;
    }

    public TreeNode getTransition(char c){
        return _transHash.get(c);
    }
    public boolean containsTransition(char c){
        return getTransition(c) != null;
    }

    public char getChar(){
        return _char;
    }

    public TreeNode parent(){
        return _parent;
    }
    public TreeNode failure(TreeNode value){
        _failure = value;
        return _failure;
    }
    public TreeNode[] transitions(){
        return _transitionsAr;
    }
    public ArrayList<String> resutls(){
        return _results;
    }

    public TreeNode failure(){
        return _failure;
    }
}
