package cn.zlq.analyzer1;

import cn.zlq.analyzer2.AdjList;
import cn.zlq.analyzer2.CnToken;
import cn.zlq.analyzer2.PrefixRet;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dictionary dic = Dictionary.getInstance();
        String sentence = "Query123大学生活动中心在中华人民共和国成立后迅速发展";
        int length = sentence.length();
        TSTNode node = dic.getNode("大学");
        if(node != null){
            String data = node.data;
            System.out.println(data);
        }else{
            System.out.println("null");
        }

        int offset = 0;
        String ret = dic.matchLong(sentence, offset);
        System.out.println(sentence + " matcht: " + ret);

        List<String> keyWords = dic.wordSegment(sentence);
        StringBuffer stringBuffer = new StringBuffer();
        for (String keyWord : keyWords){
            stringBuffer.append(keyWord).append(",");
        }
        System.out.println(stringBuffer.toString());

        AdjList adjList = new AdjList(length + 1);
        int[] offsets = new int[length + 1];
        for (int i = 0; i < length + 1; i ++){
            offsets[i] = 0;
        }
        int j;
        for (int i = 0; i < length;){
            if(i == 0 || offsets[i] == 1){
                offsets[i] = 0;
                PrefixRet prefixRet = new PrefixRet();
                boolean match = dic.getMatch(sentence, i, prefixRet);
                if(match){
                    for (String word : prefixRet.values){
                        j = i + word.length();
                        adjList.addEdge(new CnToken(i, j, 10, word));
                        offsets[i + word.length()] = 1;
                    }
                    for (int index = i; index < length + 1; index ++){
                        if (offsets[index] < prefixRet.end && offsets[index] == 1){
                            i = index;
                            break;
                        }
                        if (index == prefixRet.end){
                            i = prefixRet.end;
                            break;
                        }
                    }
                }else{
                    j = i + 1;
                    adjList.addEdge(new CnToken(i, j, 1, sentence.substring(i, j)));
                    i = j;
                    offsets[j] = 1;
                }
            }
        }
        System.out.println("size =" + adjList.getVerticesNum() + adjList.toString());

        adjList.printDepthLink();
        System.out.println("一共发现了[" + adjList.allWays.size() + "]");

    }
}
