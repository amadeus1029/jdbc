package com.javaex.author03;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    //필드
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String id = "webdb";
    private String pw = "webdb";


    //생성자

    public AuthorDao() {
    }

    //일반메소드
    public List<AuthorVo> getAuthorList() {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<AuthorVo> authorList = new ArrayList<AuthorVo>();

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName(driver);
            // 2. Connection 얻어오기
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("DB 접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            String selectAuthorQuery =
                    "select  author_id," +
                            "        author_name," +
                            "        author_desc " +
                            "from    author";
            pstmt = conn.prepareStatement(selectAuthorQuery);
            rs = pstmt.executeQuery();
            // 4.결과처리
            while(rs.next()) {
                int authorId = rs.getInt("author_id");
                String authorName = rs.getString("author_name");
                String authorDesc = rs.getString("author_desc");

                AuthorVo authorVo = new AuthorVo(authorId,authorName,authorDesc);
                authorList.add(authorVo);
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

        return authorList;
    }

    public void authorInsert(AuthorVo authorVo) {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName(driver);
            // 2. Connection 얻어오기
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("DB 접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            String authorInsertQuery =
                    "insert into author " +
                            "values(seq_author_id.nextVal,?,?)"; //?는 나중에 채움
            pstmt = conn.prepareStatement(authorInsertQuery); //쿼리문 준비
            pstmt.setString(1, authorVo.getAuthorName());
            pstmt.setString(2, authorVo.getAuthorDesc());

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

    public void authorUpdate(AuthorVo authorVo) {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try     {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName(driver);
            // 2. Connection 얻어오기
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("DB 접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            String updateAuthorQuery =
                    "update author " +
                            "set    author_name = ?," +
                            "       author_desc = ? " +
                            "where  author_id = ?"; //?는 나중에 채움
            pstmt = conn.prepareStatement(updateAuthorQuery); //쿼리문 준비
            pstmt.setString(1,authorVo.getAuthorName());
            pstmt.setString(2,authorVo.getAuthorDesc());
            pstmt.setInt(3,authorVo.getAuthorId());

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

    public void authorDelete(int authorId) {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName(driver);
            // 2. Connection 얻어오기
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("DB 접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            String deleteAuthorQuery =
                    "delete author " +
                            "where author_id = ? "; //?는 나중에 채움
            pstmt = conn.prepareStatement(deleteAuthorQuery); //쿼리문 준비
            pstmt.setInt(1, authorId);
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
