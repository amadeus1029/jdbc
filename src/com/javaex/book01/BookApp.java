package com.javaex.book01;


import com.javaex.author02.AuthorVo;

import java.util.List;

public class BookApp {
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        List<BookVo> bookList = bookDao.getBookList();

        BookVo vo01 = new BookVo("책이름1","출판사1","2010-06-01",1);
        bookDao.bookInsert(vo01);
        bookList.add(vo01);
        BookVo vo02 = new BookVo("책이름2","출판사2","2010-06-02",2);
        bookDao.bookInsert(vo02);
        bookList.add(vo02);
        BookVo vo03 = new BookVo("책이름3","출판사3","2010-06-03",2);
        bookDao.bookInsert(vo03);
        bookList.add(vo03);


        BookVo vo04 = new BookVo(3, "책이름4","출판사4","2010-06-04",3);
        bookDao.bookUpdate(vo04);

        for(BookVo vo : bookList) {
            System.out.println("====================");
            System.out.println(vo.getBookId()+", "+vo.getTitle()+", "+vo.getPubs()+", "+vo.getPubDate()+", "+vo.getAuthorId());
        }
    }
}
