package com.javaex.author04;

import java.util.List;

public class AuthorApp {
    public static void main(String[] args) {
        AuthorDao authorDao = new AuthorDao();
        List<AuthorVo> authorList = authorDao.getAuthorList();

        AuthorVo vo01 = new AuthorVo("이웅희","양아치");
        authorDao.authorInsert(vo01);
        authorList.add(vo01);
        AuthorVo vo02 = new AuthorVo("김은지","꿀꿀꿀");
        authorDao.authorInsert(vo02);
        authorList.add(vo02);
        AuthorVo vo03 = new AuthorVo("이소은","미개또");
        authorDao.authorInsert(vo03);
        authorList.add(vo03);

        /*AuthorVo vo04 = new AuthorVo(3,"양회강","뭐라고할까");
        authorDao.authorUpdate(vo04);


        for(AuthorVo vo : authorList) {
            System.out.println("====================");
            System.out.println(vo.getAuthorId()+", "+vo.getAuthorName()+", "+vo.getAuthorDesc());
        }*/
    }
}
