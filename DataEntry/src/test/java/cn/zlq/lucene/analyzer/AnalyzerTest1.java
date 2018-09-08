package cn.zlq.lucene.analyzer;

import cn.zlq.analyzer1.Dictionary;
import cn.zlq.analyzer2.CnAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.TokenStream;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerTest1 {

    public static void main(String[] args) throws IOException {

        Dictionary dic = Dictionary.getInstance();
        System.out.println(dic.matchLong("中华人民共和国成立了", 0));
        Analyzer analyzer = new CnAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("myfield", new StringReader("中华人民共和国成立了"));
        while(tokenStream.incrementToken()){
            System.out.println("token: " + tokenStream);
        }
        System.out.println("*******************");
        KeywordAnalyzer analyzer1 = new KeywordAnalyzer();
        TokenStream tokenStream1 =  analyzer1.tokenStream("myfield", new StringReader("中华人民共和国成立了"));
        while(tokenStream1.incrementToken()){
            System.out.println("token: " + tokenStream1);
        }

    }
}
