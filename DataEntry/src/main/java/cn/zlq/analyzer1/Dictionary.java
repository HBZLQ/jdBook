package cn.zlq.analyzer1;

import cn.jdbook.common.StringUtils;
import cn.zlq.analyzer2.PrefixRet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private static TSTNode root = null;
    private Dictionary(){
        init();
    }

    private static class Instance{
        private static Dictionary instance = new Dictionary();
    }

    public static Dictionary getInstance(){
        return Instance.instance;
    }

    /**
     * 初始化加载词典
     */
    public void init(){
        root = new TSTNode('面');
        BufferedReader br = null;
        try {
            String s;
            br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\gwssi\\IPlanguage\\src\\main\\resources\\config\\qk_keywords_cn.txt"), "GBK"));
            while ((s = br.readLine()) != null){
                addWord(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查看该key是否在字典中存在
     * @param key
     * @return
     */
    public TSTNode getNode(String key){
        if (key == null)return null;
        int len = key.length();
        if (len == 0)return null;
        TSTNode currentNode = root;
        int charIndex = 0;
        char comChar = key.charAt(charIndex);
        int charComp;
        while(true){
            if (currentNode == null)return null;
            charComp = comChar - currentNode.splitchar;
            if (charComp == 0){
                charIndex ++;
                if (charIndex == len){
                    return currentNode;
                }else{
                    comChar = key.charAt(charIndex);
                }
                currentNode = currentNode.eqNode;
            }else if(charComp < 0){
                currentNode = currentNode.loNode;
            }else{
                currentNode = currentNode.hiNode;
            }
        }
    }

    /**
     * 往字典中添加关键词
     * @param key
     * @return
     */
    public TSTNode addWord(String key){
        TSTNode currentNode = root;
        int charIndex = 0;
        while(true){
            int charComp = key.charAt(charIndex) - currentNode.splitchar;
            if(charComp == 0){
                charIndex ++;
                if(charIndex == key.length()){
                    currentNode.data= key;
                    return currentNode;
                }
                if (currentNode.eqNode == null){
                    currentNode.eqNode = new TSTNode(key.charAt(charIndex));
                }
                currentNode = currentNode.eqNode;
            }else if(charComp < 0){
                if (currentNode.loNode == null){
                    currentNode.loNode = new TSTNode(key.charAt(charIndex));
                }
                currentNode = currentNode.loNode;
            }else{
                if (currentNode.hiNode == null){
                    currentNode.hiNode = new TSTNode(key.charAt(charIndex));
                }
                currentNode = currentNode.hiNode;
            }
        }
    }

    /**
     * 最大前缀匹配
     * @param key
     * @param offset
     * @return
     */
    public String matchLong(String key, int offset){
        String ret = null;
        if(key == null || root == null || "".equals(key)){
            return null;
        }
        TSTNode currentNode = root;
        int charIndex = offset;
        while(true){
            if (currentNode == null){
                return ret;
            }
            int charComp = key.charAt(charIndex) - currentNode.splitchar;

            if(charComp == 0){
                charIndex ++;
                if(currentNode.data != null){
                    ret = currentNode.data;
                }
                if (charIndex == key.length()){
                    return ret;
                }
                currentNode = currentNode.eqNode;
            }else if(charComp < 0){
                currentNode = currentNode.loNode;
            }else {
                currentNode = currentNode.hiNode;
            }
        }
    }

    /**
     * 最大前缀匹配
     * @param key
     * @param offset
     * @return
     */
    public String matchLong(char[] key, int offset){
        String ret = null;
        if(key == null || root == null || "".equals(key)){
            return null;
        }
        TSTNode currentNode = root;
        int charIndex = offset;
        while(true){
            if (currentNode == null){
                return ret;
            }
            int charComp = key[charIndex] - currentNode.splitchar;

            if(charComp == 0){
                charIndex ++;
                if(currentNode.data != null){
                    ret = currentNode.data;
                }
                if (charIndex == key.length){
                    return ret;
                }
                currentNode = currentNode.eqNode;
            }else if(charComp < 0){
                currentNode = currentNode.loNode;
            }else {
                currentNode = currentNode.hiNode;
            }
        }
    }

    /**
     * 最大前缀匹配分词算法
     * @param sentence
     * @return
     */
    public List<String> wordSegment(String sentence){
        List<String> keyWords = new ArrayList<String>();
        int senLen = sentence.length();
        int i = 0;

        while (i < senLen){
            String word = matchLong(sentence, i);
            if (word != null){
                i += word.length();
                keyWords.add(word);
            }
            else{
                word = sentence.substring(i, i+1);
                keyWords.add(word);
                ++i;
            }
        }
        return keyWords;
    }

    /**
     * 匹配所有前缀
     * @param sentence
     * @param offset
     * @param prefix
     * @return
     */
    public boolean getMatch(String sentence, int offset, PrefixRet prefix){
        if (sentence == null || root == null || "".equals(sentence)){
            return false;
        }
        boolean match = matchEnglish(offset, sentence, prefix);
        if (match){
            return true;
        }
        match = matchNum(offset, sentence, prefix);
        if(match){
            return true;
        }
        prefix.end = offset;
        ArrayList<String> ret = new ArrayList<String>();
        TSTNode currentNode = root;
        int charIndex = offset;
        int minLen = sentence.length();
        while(true){
            if (currentNode == null){
                prefix.values = ret;
                prefix.end += minLen;
                if (ret.size() > 0){
                    return true;
                }
                return false;
            }
            int charComp = sentence.charAt(charIndex) - currentNode.splitchar;
            if (charComp == 0){
                charIndex ++;
                if (currentNode.data != null){
                    ret.add(currentNode.data);
                    if (currentNode.data.length() < minLen){
                        minLen = currentNode.data.length();
                    }
                }
                if (charIndex == sentence.length()){
                    prefix.values = ret;
                    prefix.end += minLen;
                    if (ret.size() > 0){
                        return true;
                    }
                    return false;
                }
                currentNode = currentNode.eqNode;
            }else if(charComp < 0){
                currentNode = currentNode.loNode;
            }else {
                currentNode = currentNode.hiNode;
            }
        }
    }

    /**
     * 英文匹配
     * @param offset
     * @param sentence
     * @param prefix
     * @return
     */
    public boolean matchEnglish(int offset, String sentence, PrefixRet prefix){
        if (!StringUtils.isNotEmpty(sentence)){
            return false;
        }
        int charIndex = offset;
        boolean flag = false;
        char charComp ;
        StringBuffer stringBuffer = new StringBuffer();
        while(charIndex < sentence.length()){
            charComp = sentence.charAt(charIndex);
            if ((charComp >= 'a' && charComp <= 'z') || (charComp >= 'A' && charComp <= 'Z')){
                stringBuffer.append(charComp);
                charIndex ++ ;
                flag = true;
            }else{
                break;
            }
        }
        if (flag){
            if (prefix.values == null){
                prefix.values = new ArrayList<String>();
            }
            prefix.values.add(stringBuffer.toString());
            prefix.end = charIndex;
        }
        return flag;
    }

    /**
     * 字母匹配
     * @param offset
     * @param sentence
     * @param prefix
     * @return
     */
    public boolean matchNum(int offset, String sentence, PrefixRet prefix){
        if (!StringUtils.isNotEmpty(sentence)){
            return false;
        }
        int charIndex = offset;
        boolean flag = false;
        char charComp;
        StringBuffer stringBuffer = new StringBuffer();
        while(charIndex < sentence.length()){
            charComp = sentence.charAt(charIndex);
            if (charComp >= '0' && charComp <= '9'){
                stringBuffer.append(charComp);
                charIndex ++ ;
                flag = true;
            }else{
                break;
            }
        }
        if (flag){
            if (prefix.values == null){
                prefix.values = new ArrayList<String>();
            }
            prefix.values.add(stringBuffer.toString());
            prefix.end = charIndex;
        }
        return flag;
    }
}
