package com.javaex.author01;

import java.util.List;

public class AuthorApp {
    public static void main(String[] args) {
        AuthorDao authorDao = new AuthorDao();
        List<AuthorVo> authorList = authorDao.getAuthorList();


        authorDao.authorInsert("이웅희","정신병");
        authorDao.authorInsert("이소은","멍청이");
        authorDao.authorInsert("김은지","꿀꿀꿀");

        for(AuthorVo vo : authorList) {
            System.out.println("====================");
            System.out.println(vo.getAuthorId()+", "+vo.getAuthorName()+", "+vo.getAuthorDesc());
        }

        authorDao.authorUpdate(1, "홍길동","ㅋㅋㅋㅋㅋ");


        for(AuthorVo vo : authorList) {
            System.out.println("====================");
            System.out.println(vo.getAuthorId()+", "+vo.getAuthorName()+", "+vo.getAuthorDesc());
        }
        System.out.println("====================");

        authorDao.authorDelete(3);

        for(AuthorVo vo : authorList) {
            System.out.println("====================");
            System.out.println(vo.getAuthorId()+", "+vo.getAuthorName()+", "+vo.getAuthorDesc());
        }
    }
}
