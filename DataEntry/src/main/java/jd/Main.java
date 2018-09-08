package jd;

import cn.jdbook.es.entry.repos.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static BookRepository repository;
    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("run_config.xml");
        repository = (BookRepository)context.getBean("bookRepository");
    }

    public static BookRepository getRepository() {
        return repository;
    }
    public volatile static int index = 12368807;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        while(index > 10000066){
            executorService.execute(new JDRunnable());
        }
    }
}
