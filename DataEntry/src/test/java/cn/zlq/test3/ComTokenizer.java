package cn.zlq.test3;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.search.suggest.jaspell.JaspellTernarySearchTrie;

import java.io.IOException;

public class ComTokenizer extends Tokenizer {

 //   private static TernarySearchTrie dic = new JaspellTernarySearchTrie();

    public boolean incrementToken() throws IOException {
        return false;
    }

    protected ComTokenizer(TokenStream input){

    }
}
