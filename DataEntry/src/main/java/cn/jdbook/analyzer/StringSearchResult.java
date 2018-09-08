package cn.jdbook.analyzer;

public class StringSearchResult {
    private String keyword;
    private Integer start;
    private Integer end;
    private Integer offset;

    public StringSearchResult(String keyword, Integer start, Integer end, Integer offset){
        this.keyword = keyword;
        this.start = start;
        this.end = end;
        this.offset = offset;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("keyword=[").append(keyword)
                .append("] start=[").append(start)
                .append("]end=[").append(end)
                .append("]offset=[").append(offset)
                .append("]");
        return sb.toString();
    }
}
