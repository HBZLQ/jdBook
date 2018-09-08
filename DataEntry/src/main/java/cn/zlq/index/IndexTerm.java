package cn.zlq.index;

import cn.jdbook.common.StringUtils;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermEnum;

import java.io.IOException;

public class IndexTerm {

    public static TermInfo[] getHighFreqTerms(IndexReader reader, int numTerms, String field) throws IOException {
        TermInfoQueue tiq = new TermInfoQueue();
        TermEnum terms = reader.terms();
        int minFreq = 0;
        while(terms.next()){
            String fields = terms.term().field();
            if(StringUtils.isNotEmpty(fields)){
                boolean skip = true;
            }
        }

        return null;
    }
}
