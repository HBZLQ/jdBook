package jd;

import cn.jdbook.common.Constant;
import cn.jdbook.common.HttpResult;
import cn.jdbook.common.ResultCode;
import cn.jdbook.common.Tools;
import cn.jdbook.es.entry.po.Book;
import cn.jdbook.es.entry.repos.BookRepository;
import org.springframework.util.StringUtils;

public class JDRunnable implements Runnable{
    public void run() {
        Integer bookId = new Integer(Main.index);
        Main.index --;
        //获取主页面
        String mainPath = Constant.mainPath + bookId + Constant.fileType;
        //获取文本信息
        String textPath = Constant.textPath + bookId + Constant.textEnd;

        HttpResult result1 = Tools.getHttpContext(mainPath, Constant.GBK);
        Book book = new Book();
        book.setId(bookId);
        if(result1.getCode() == ResultCode.SUCCESS.getValue()){
            String str = result1.getContext();
            String skuName = StringUtils.trimAllWhitespace(Tools.getSubUtilSimple(str, Constant.SKUNAMEMACHER));
            String authorName = StringUtils.trimAllWhitespace(Tools.getSubUtilSimple(str, Constant.AUTHORMACHER));
            book.setName(skuName);
            book.setAuthor(authorName);
        }
        HttpResult result2 = Tools.getHttpContext(textPath, Constant.GBK);
        if(result2.getCode() == ResultCode.SUCCESS.getValue()) {
            String str = result2.getContext();
            book.setEditorRecommend(StringUtils.trimAllWhitespace(Tools.getBookDetailContent(str, "编辑推荐")));
            book.setAuthorDes(StringUtils.trimAllWhitespace(Tools.getBookDetailContent(str, "作者简介")));
            book.setBookDes(StringUtils.trimAllWhitespace(Tools.getBookDetailContent(str, "内容简介")));
            book.setWonderfulHighlights(StringUtils.trimAllWhitespace(Tools.getBookDetailContent(str, "精彩书摘")));
            book.setWonderfulDes(StringUtils.trimAllWhitespace(Tools.getBookDetailContent(str, "精彩书评")));
        }
        BookRepository repository =  Main.getRepository();
        repository.save(book);
        System.out.println(bookId);
    }
}
