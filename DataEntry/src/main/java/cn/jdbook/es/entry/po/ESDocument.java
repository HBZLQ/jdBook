package cn.jdbook.es.entry.po;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class ESDocument implements Serializable {
    @Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
