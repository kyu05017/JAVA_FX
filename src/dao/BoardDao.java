package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {
	
	private Connection con; // 1 .DB 연동시 사용되는 클래스 : DB 연동클래스
	private PreparedStatement ps; // 2. 연결된 DB내 SQL 조작 할때 사용하는 인터페이스 : DB 조작
	private ResultSet rs; // 3. sql 결과 
	
	public static BoardDao dao = new BoardDao();	// db 연동 객체
	
	public BoardDao() { // 4. 생성자에서 연동하는 이유 : 객체 생성시 바로 연동
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB 주소 연결
			System.out.println("보드 DB연동 성공");
		} 
		catch (Exception e) {System.out.println("보드 DB연동 실패" + e);}
	}
	
	// 메소드
	//1. 글쓰기
	public boolean write(Board board) {
		try {
			// 1. SQL 작성 [ 회원번호 ( 자동 )제외한 모든 필드 삽입  ]
			String sql = "insert into board(b_title,b_contents,b_writer) values(?,?,?)";
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getB_title());
			ps.setString(2, board.getB_contents());
			ps.setString(3, board.getB_writer());
			
			// 3. SQL 실행
			ps.executeUpdate(); 	
			
			// * 성공시 
			return true;
		} 
		catch (SQLException e) {
			System.out.println("[SQL 게시판 저장 실패 ]" + e);
		}
		return false;// * 실패시
	}
	
	// 2. 글목록 
	public ObservableList<Board> list() {
		
		try {
			// *
			ObservableList<Board> boardlist = FXCollections.observableArrayList();
			
			// 1. sql  작성 [ 데이터 호출 ]
				//select * (모든 필드) from 테이블명
			 	// 오름차순 by b_num asc = b_num기준으로 오름차순 정렬
				// 내림차순 by b_num desc = b_num기준으로 내림차순 정렬
			String sql = "select * from board order by b_num desc";
			// 2. SQL 조작 [ DB 와 연결된 객체와 조작ㄱ인터페이스 연결 ]
			ps = con.prepareStatement(sql);
			
			// 3. sql 실행 [ ResultSet 인터페이스 java.sql 패키지 ]
			rs =  ps.executeQuery();
			
			// * 결과물 하나가 아니고 여러개 이므로 반복문 사용해서
				// 한줄씩[레코드] 객체화 -> 리스트에 저장
			
			// rs.next() 검색결과의 다음 레코드
			// rs.getint() 검색결과를 정수형일 경우
			// rs.getstring() 검색결과를 문자형일 경우
			while(rs.next()) {
				// 1. 한줄식 [ 레코드 ] 단위 객체화
				Board temp = new Board(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6)
					); 
				boardlist.add(temp);
			}
			// 반복문이 종료되면 리스트 반환
			// 성공시 데이터 목록 반환
			return boardlist;
		}
		catch (Exception e) {
			System.out.println("[sql 연결 실패] : 사유 " + e);
		}
		// 실패시 
		return null;
	}

	// 3. 글삭제
	public boolean delete(int b_num) {
		return false;
	}
	// 4. 글수정
	public boolean updaete(int b_num, String title, String contents) {
		return false;
	}
}
