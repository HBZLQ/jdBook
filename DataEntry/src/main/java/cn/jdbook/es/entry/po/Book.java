package cn.jdbook.es.entry.po;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "library",type = "novels",shards = 5,replicas = 0, createIndex = false)
public class Book extends ESDocument {
    @Field(type = FieldType.Text,analyzer = "english")
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String author;
    //编辑推荐
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String editorRecommend;
    //内容简介
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String bookDes;
    //作者简介
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String authorDes;
    //精彩书摘
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String wonderfulHighlights;
    //精彩简介
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String wonderfulDes;
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

    public String getWonderfulDes() {
        return wonderfulDes;
    }

    public void setWonderfulDes(String wonderfulDes) {
        this.wonderfulDes = wonderfulDes;
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
