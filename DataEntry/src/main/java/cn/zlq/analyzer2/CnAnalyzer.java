package cn.zlq.analyzer2;

import org.apache.lucene.analysis.ReusableAnalyzerBase;

import java.io.Reader;

public class CnAnalyzer extends ReusableAnalyzerBase {

    public CnAnalyzer(){

    }
    protected TokenStreamComponents createComponents(String s, Reader reader) {
        return new TokenStreamComponents(new CnTokenizer(reader));
    }
}
