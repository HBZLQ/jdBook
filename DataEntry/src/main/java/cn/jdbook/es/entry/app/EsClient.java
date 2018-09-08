package cn.jdbook.es.entry.app;

import cn.jdbook.es.entry.po.Book;
import cn.jdbook.es.entry.repos.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EsClient {
    private static final Logger log = LoggerFactory.getLogger(EsClient.class);
    private static BookRepository repository;
    @Before
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext("run_config.xml");
        repository = (BookRepository)context.getBean("bookRepository");
    }
    @Test
    public void test(){
        Book book = new Book();
        book.setId(1);
        book.setName("Head first");
        book.setAuthor("freeman");
        repository.save(book);
    }
    @After
    public void after(){
        log.info("save book success ...");
    }

}
