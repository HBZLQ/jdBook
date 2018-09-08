package cn.jdbook.analyzer;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private TreeNode _root = null;
    private void buildTree(List<String> keywords){
        _root = new TreeNode(null , ' ');
        for(String keyWord : keywords) {
            TreeNode nd = _root;
            for (char c : keyWord.toCharArray()) {
                TreeNode ndNew = null;
                for (TreeNode trans : nd.transitions()) {
                    if (trans.getChar() == c) {
                        ndNew = trans;
                        break;
                    }
                }
                if (ndNew == null) {
                    ndNew = new TreeNode(nd, c);
                    nd.addTransition(ndNew);
                }
                nd = ndNew;
            }
            nd.addResult(keyWord);
        }
    }
    private void addFailure(){
        //所有词的第n个子节点的集合，n从2开始
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        for (TreeNode nd : _root.transitions()){
            nd.failure(_root);
            for(TreeNode trans : nd.transitions()){
                nodes.add(trans);
            }
        }
        while(nodes.size() != 0){
            ArrayList<TreeNode> newNodes = new ArrayList<TreeNode>();
            for(TreeNode nd : nodes){
                TreeNode r = nd.parent().failure();
                char c = nd.getChar();
                while(r != null && !r.containsTransition(c))
                    r = r.failure();
                if(r == null)
                    nd.failure(_root);
                else{
                    nd.failure(r.getTransition(c));
                    for(String result : nd.failure().resutls()){
                        nd.addResult(result);
                    }
                }

                for(TreeNode child : nd.transitions()){
                    newNodes.add(child);
                }
            }
            nodes = newNodes;
        }
        _root.failure(_root);
    }

    public TreeNode StringSearch(List<String> keywords){
        buildTree(keywords);
        addFailure();
        return this._root;
    }
}

