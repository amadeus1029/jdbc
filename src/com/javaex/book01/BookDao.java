package com.javaex.book01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public BookDao() {
    }

    public List<BookVo> getBookList() {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<BookVo> bookList = new ArrayList<BookVo>();

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
            System.out.println("DB 접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            String bookSelectQuery =
                    "select  book_id," +
                            "        title," +
                            "        pubs," +
                            "        pub_date," +
                            "        author_id " +
                            "from    books";
            pstmt = conn.prepareStatement(bookSelectQuery);
            rs = pstmt.executeQuery();
            // 4.결과처리
            while(rs.next()) {
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String pubs = rs.getString("pubs");
                String pubDate = rs.getString("pub_date");
                int authorId = rs.getInt("author_id");

                BookVo bookVo = new BookVo(bookId,title,pubs,pubDate,authorId);
                bookList.add(bookVo);
            }

        } catch (
                ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (
                SQLException e) {
            System.out.println("error:" + e);
        } finally {
            // 5. 자원정리
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return bookList;
    }

    public void bookInsert(BookVo bookVo) {
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

            // 3. SQL문 준비 / 바인딩 / 실행
            String bookInserQuery =
                    "insert into books " +
                            "values(seq_book_id.nextVal,?,?,?,?)"; //?는 나중에 채움
            pstmt = conn.prepareStatement(bookInserQuery); //쿼리문 준비
            pstmt.setString(1, bookVo.getTitle());
            pstmt.setString(2, bookVo.getPubs());
            pstmt.setString(3, bookVo.getPubDate());
            pstmt.setInt(4, bookVo.getAuthorId());

            int count = pstmt.executeUpdate(); //쿼리문 날린다!!!!!!
            // 4.결과처리
            System.out.println(count+"건 처리되었습니다.");

        } catch (
                ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (
                SQLException e) {
            System.out.println("error:" + e);
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

    public void bookUpdate(BookVo bookVo) {
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

            // 3. SQL문 준비 / 바인딩 / 실행
            String bookUpdateQuery =
                    "update books " +
                            "set title = ?, " +
                            "    pubs = ?,  " +
                            "    pub_date = ?,  " +
                            "    author_id = ?  " +
                            "where book_id = ?  "; //?는 나중에 채움
            pstmt = conn.prepareStatement(bookUpdateQuery); //쿼리문 준비
            pstmt.setString(1, bookVo.getTitle());
            pstmt.setString(2, bookVo.getPubs());
            pstmt.setString(3, bookVo.getPubDate());
            pstmt.setInt(4, bookVo.getAuthorId());
            pstmt.setInt(5, bookVo.getBookId());

            int count = pstmt.executeUpdate(); //쿼리문 날린다!!!!!!
            // 4.결과처리
            System.out.println(count+"건 처리되었습니다.");

        } catch (
                ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (
                SQLException e) {
            System.out.println("error:" + e);
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

    public void bookDelete(int bookId) {
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

            // 3. SQL문 준비 / 바인딩 / 실행
            String deleteBookQuery =
                    "delete books " +
                            "where book_id = ? "; //?는 나중에 채움
            pstmt = conn.prepareStatement(deleteBookQuery); //쿼리문 준비
            pstmt.setInt(1, bookId); //4번을 지운다
            int count = pstmt.executeUpdate(); //쿼리문 날린다!!!!!!
            // 4.결과처리
            System.out.println(count + "건 처리되었습니다.");

        } catch (
                ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (
                SQLException e) {
            System.out.println("error:" + e);
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
