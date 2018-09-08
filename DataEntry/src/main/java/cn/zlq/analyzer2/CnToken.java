package cn.zlq.analyzer2;

public class CnToken {

    public String termText;
    public int start;
    public int end;
    public int freq;
    public String type;
    public double logProb;
    public CnToken(int vertexFrom, int vertexTo, String word, String type, double logProb){
        this.termText = word;
        this.start = vertexFrom;
        this.end = vertexTo;
        this.type = type;
        this.logProb = logProb;
    }
    public CnToken(int start, int end, int freq, String termText){
        this.start = start;
        this.end = end;
        this.freq = freq;
        this.termText = termText;
    }
}
