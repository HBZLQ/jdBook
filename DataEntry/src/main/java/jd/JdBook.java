package jd;

public class JdBook {

    private Integer id;
    //名称
    private String name;
    //作者
    private String author;
    //编辑推荐
    private String editorRecommend;
    //内容简介
    private String bookDes;
    //作者简介
    private String authorDes;
    //精彩书摘
    private String wonderfulHighlights;
    //精彩简介
    private String wonderfulDes;


    public String getWonderfulDes() {
        return wonderfulDes;
    }

    public void setWonderfulDes(String wonderfulDes) {
        this.wonderfulDes = wonderfulDes;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorRecommend() {
        return editorRecommend;
    }

    public void setEditorRecommend(String editorRecommend) {
        this.editorRecommend = editorRecommend;
    }

    public String getBookDes() {
        return bookDes;
    }

    public void setBookDes(String bookDes) {
        this.bookDes = bookDes;
    }

    public String getAuthorDes() {
        return authorDes;
    }

    public void setAuthorDes(String authorDes) {
        this.authorDes = authorDes;
    }

    public String getWonderfulHighlights() {
        return wonderfulHighlights;
    }

    public void setWonderfulHighlights(String wonderfulHighlights) {
        this.wonderfulHighlights = wonderfulHighlights;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("name=[")
                .append(name)
                .append("]author=[")
                .append(author)
                .append("]editorRecommend=[")
                .append(editorRecommend)
                .append("]bookDes=[")
                .append(bookDes)
                .append("]authorDes=[")
                .append(authorDes)
                .append("]wonderfulHighlights=[")
                .append("]");
        return sb.toString();
    }
}
