package cn.jdbook.analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        List<String> keywords = new ArrayList<String>();
        File file = new File("E:\\gwssi\\IPlanguage\\src\\main\\resources\\config\\qk_keywords_cn.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null){
                keywords.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeNode root = tree.StringSearch(keywords);
        //深度优先遍历所有关键词
        StringBuffer sb = new StringBuffer();
        ToolUtils.depthSearch(root, sb, 0);
        System.out.println(sb.toString());
        List<StringSearchResult> results = ToolUtils.findAll("相比于懒汉以及饿汉模式，静态内部类模式没有一个较为官方的名称，这只是我个人的称谓。但是却是许多人更为推荐的一种形式", root);
        if(results != null){
            int index = 0;
            for (StringSearchResult result : results){
                System.out.println(index + " " + result.toString());
            }
        }
    }
}
