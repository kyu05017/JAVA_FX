package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDao {
	
	private Connection con; // 1 .DB 연동시 사용되는 클래스 : DB 연동클래스
	private PreparedStatement ps; // 2. 연결된 DB내 SQL 조작 할때 사용하는 인터페이스 : DB 조작
	private ResultSet rs; // 3. sql 결과 
	
	public static BoardDao dao = new BoardDao();
	
	public BoardDao() { // 4. 생성자에서 연동하는 이유 : 객체 생성시 바로 연동
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB 주소 연결
			System.out.println("보드 DB연동 성공");
		} 
		catch (Exception e) {System.out.println("보드 DB연동 실패" + e);}
	}
	
	
}
