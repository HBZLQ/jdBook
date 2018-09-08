package cn.zlq.test3;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

public class LuceneAnalyzerTest {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new CJKAnalyzer();
        TokenStream ts = analyzer.tokenStream("myFirend", new StringReader("明天的太阳"));
        CharTermAttribute charTermAttribute = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while(ts.incrementToken()){
            System.out.println("token: " + charTermAttribute);
        }
    }
}
