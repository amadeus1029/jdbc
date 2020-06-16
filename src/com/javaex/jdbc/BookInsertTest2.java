package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertTest2 {
    public static void main(String[] args) {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
            System.out.println("DB 접속성공");

            // 2-1. commit 설정 변경
            conn.setAutoCommit(false);

            // 3. SQL문 준비 / 바인딩 / 실행

            // -정보 등록
            String insertBookQuery1 =
                            "insert into books " +
                            "values(seq_book_id.nextVal,?,?,?,?)"; //?는 나중에 채움
            pstmt = conn.prepareStatement(insertBookQuery1); //쿼리문 준비
            pstmt.setString(1,"어쩌구 저쩌구 책");
            pstmt.setString(2,"창작과 비평");
            pstmt.setString(3, "2020-06-11");
            pstmt.setInt(4,1);

           pstmt.executeUpdate(); //쿼리문 날린다!!!!!!

            // +정보 등록
            String insertBookQuery2 =
                    "insert into books " +
                            "values(seq_book_id.nextVal,?,?,?,?)"; //?는 나중에 채움
            pstmt = conn.prepareStatement(insertBookQuery2); //쿼리문 준비
            pstmt.setString(1,"아 졸리다");
            pstmt.setString(2,"아침에 운동하지 말걸");
            pstmt.setString(3, "2020-06-16");
            pstmt.setInt(4,323123);

            pstmt.executeUpdate(); //쿼리문 날린다!!!!!!

            // 4.결과처리

        } catch (
                ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (
                SQLException e) {
            System.out.println("error:" + e);
            try {
                conn.rollback();
                System.out.println("롤백됨ㅋㅋ");
            } catch (SQLException throwables) {
                System.out.println("아시발 롤백도 안됨 암튼 오류남");
                throwables.printStackTrace();
            }
        } finally {
            // 5. 자원정리
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }

        }
    }
}
