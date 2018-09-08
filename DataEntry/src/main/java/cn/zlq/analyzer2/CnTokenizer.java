package cn.zlq.analyzer2;

import cn.zlq.analyzer1.Dictionary;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.*;

import java.io.IOException;
import java.io.Reader;

public class CnTokenizer extends Tokenizer {
    Dictionary dic = Dictionary.getInstance();
    private CharTermAttributeImpl termAtt;
    private OffsetAttributeImpl offsetAtt;
    private PositionIncrementAttributeImpl positionIncrementAtt;
    private static final int IO_BUFFER_SIZE = 4096;
    private char[] ioBuffer = new char[IO_BUFFER_SIZE];

    private boolean done;
    private int i = 0;
    private int upto = 0;

    protected CnTokenizer(Reader input) {
        super(input);
        this.termAtt = (CharTermAttributeImpl)addAttribute(CharTermAttribute.class);
        this.offsetAtt  = (OffsetAttributeImpl)addAttribute(OffsetAttribute.class);
        this.positionIncrementAtt = (PositionIncrementAttributeImpl)addAttribute(PositionIncrementAttribute.class);
        this.done = false;
    }

    public void resizeIOBuffer(int newSize){
        if(ioBuffer.length < newSize){
            final char[] newCharBuffer = new char[newSize];
            System.arraycopy(ioBuffer, 0, newCharBuffer, 0, ioBuffer.length);
            ioBuffer = newCharBuffer;
        }
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (!done){
            clearAttributes();
            done = true;
            upto = 0;
            i = 0;
            while(true){
                final int length = input.read(ioBuffer, upto, ioBuffer.length - upto);
                if(length == -1)
                    break;
                upto += length;
                if(upto == ioBuffer.length)
                    resizeIOBuffer(upto * 2);
            }
        }
        if(i < upto){
            String word = dic.matchLong(ioBuffer, i);
            if(word != null){
                termAtt.setTermBuffer(word, 0, word.length());
                offsetAtt.setOffset(i, i + word.length());
                i += word.length();
            }else {
                termAtt.setTermBuffer(ioBuffer, i, 1);
                offsetAtt.setOffset(i, i + 1);
                ++i;
            }
            return true;
        }

        return false;
    }
}
