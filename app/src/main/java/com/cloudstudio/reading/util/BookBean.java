package com.cloudstudio.reading.util;

import java.util.List;

/**
 * @ClassName Book
 * @Author Create By matrix
 * @Date 2024/8/16 20:48
 */
public class BookBean {
    private String id;
    private String name;
    private String pic;
    private String writer;
    private String type;
    private String hot;
    private String des;
    private List<BookBean> Book;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public List<BookBean> getBook() {
        return Book;
    }

    public void setBook(List<BookBean> book) {
        Book = book;
    }
}
