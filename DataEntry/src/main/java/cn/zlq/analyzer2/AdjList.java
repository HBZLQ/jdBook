package cn.zlq.analyzer2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdjList {
    private TokenLinkedList list[];
    public List<String> allWays = new ArrayList<String>();

    public AdjList(int verticesNum){
        list = new TokenLinkedList[verticesNum];
        for(int index = 0; index < verticesNum; index ++){
            list[index] = new TokenLinkedList();
        }
    }
    public int getVerticesNum(){
        return list.length;
    }
    /**
     * 增加一个边到图中
     */
    public void addEdge(CnToken cnToken){
        list[cnToken.start].put(cnToken);
    }

    /**
     * 返回一个迭代器，包含以指定点结束的所有的边
     */
    public Iterator<CnToken> getAdjacencies(int vertex){
        TokenLinkedList ll = list[vertex];
        if(ll == null)return null;
        return ll.iterator();
    }

    /**
     *  打印每一条到达中带你的路径
     */
    public void printDepthLink(){
        TokenLinkedList linkedList = list[0];
        Iterator<CnToken> cnTokenIterator = linkedList.iterator();
        while (cnTokenIterator.hasNext()){
            StringBuffer sb = new StringBuffer();
            CnToken cnToken = cnTokenIterator.next();
            getDepthCnToken(cnToken, sb);
        }
    }

    public void getDepthCnToken(CnToken cnToken, StringBuffer stringBuffer){
        StringBuffer sb = new StringBuffer(stringBuffer);
        sb.append(cnToken.termText).append(", ");
        if (cnToken.end >= list.length - 1){
            System.out.println(sb.toString());
            allWays.add(sb.toString());
        }else{
            TokenLinkedList linkedList = list[cnToken.end];
            Iterator<CnToken>  cnTokenIterator = linkedList.iterator();
            while (cnTokenIterator.hasNext()){
                CnToken cnToken1 = cnTokenIterator.next();
                getDepthCnToken(cnToken1, sb);
            }
        }
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        if (list.length > 0){
            int index = 0;
            while (index < list.length){
                TokenLinkedList tokenList = list[index];
                stringBuffer.append(tokenList.toString()).append("\n");
                index ++;
            }
        }
        return stringBuffer.toString();
    }
}
