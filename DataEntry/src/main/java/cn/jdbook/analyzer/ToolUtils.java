package cn.jdbook.analyzer;

import java.util.ArrayList;
import java.util.List;

public class ToolUtils {

    public static void depthSearch(TreeNode node, StringBuffer ret, int deapth){
        if(node != null){
            for(int i = 0; i < deapth; i ++)
                ret.append("| ");
            ret.append("|--");
            ret.append(node.getChar() + "\n");
            for(TreeNode child : node.transitions()){
                int childDeapth = deapth + 1;
                depthSearch(child, ret, childDeapth);
            }
        }
    }

    public static List<StringSearchResult> findAll(String text, TreeNode root){
        List<StringSearchResult> result = new ArrayList<StringSearchResult>();
        TreeNode ptr = root;
        int index = 0;

        while(index < text.length()){
            TreeNode trans = null;
            while(trans == null){
                trans = ptr.getTransition(text.charAt(index));

                if(ptr == root){
                    break;
                }
                if(trans == null)
                    ptr = ptr.failure();
            }
            if(trans != null)
                ptr = trans;
            for(String found : ptr.resutls()){
                result.add(new StringSearchResult(found, index - found.length() + 1, index, 0));
            }
            index ++;
        }
        return result;
    }
}
