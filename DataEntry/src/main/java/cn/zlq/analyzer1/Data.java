package cn.zlq.analyzer1;

public class Data {
    private String word;//原词
    private String wordType;//词性
    private int wordRef; //词频

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public int getWordRef() {
        return wordRef;
    }

    public void setWordRef(int wordRef) {
        this.wordRef = wordRef;
    }

    public int addWordRef(int ref){
        this.wordRef += ref;
        return wordRef;
    }

    @Override
    public String toString(){

        StringBuffer data = new StringBuffer();
        data.append("word=").append(word).append("\nwordType=").append(wordType).append("\nwordRef").append(wordRef);
        return data.toString();
    }
}
