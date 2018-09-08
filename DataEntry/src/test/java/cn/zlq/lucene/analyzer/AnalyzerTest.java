package cn.zlq.lucene.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.*;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerTest {

    public static void main(String[] args) throws IOException {
        String text = "The door has been opened for us";

        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
        TokenStream stream = analyzer.tokenStream("", new StringReader(text));

        CharTermAttributeImpl term = (CharTermAttributeImpl)stream.addAttribute(CharTermAttribute.class);
        OffsetAttributeImpl attribute = (OffsetAttributeImpl) stream.addAttribute(OffsetAttribute.class);
        PositionIncrementAttributeImpl positionIncrementAttribute = (PositionIncrementAttributeImpl)stream.addAttribute(PositionIncrementAttribute.class);
        while(stream.incrementToken()){
            System.out.println("word=[" + term.term() + "] startOffset=[" + attribute.startOffset() + "] endOffset=[" + attribute.endOffset() + "]" + positionIncrementAttribute.getPositionIncrement());
        }

    }
}
