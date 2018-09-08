package cn.jdbook.es.entry.repos;

import cn.jdbook.es.entry.po.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository("bookRepository")
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

}
