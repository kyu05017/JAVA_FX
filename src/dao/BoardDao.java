package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllor.login.Login;
import controllor.login.Loginpane;


public class BoardDao {
	
	private Connection con; // DB 연동객체
	private PreparedStatement ps; // 연결된 DB내 SQL 조작 할때 사용하는 인터페이스 : DB 조작
	private ResultSet rs; // 검색 [ select ]
	
	
	// 2. 생성자
	public BoardDao() {
		
		try { // 자바외 연결시 무조건 일반 예외 발생 
			// 1. DB 드라이브 클래스 호출 [ DB 회사 마다 다르기 때문에 암기X 정리O ]
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. DB 연결
			con = DriverManager.getConnection(
					// JDBC : JAVA DATABASE CONNECTE
					// jdbc:mysql://IP주소( 로컬[본인PC] 이면 localhost):port번호/DB이름?시간설정
					// , 계정명 , 비밀번호
					"jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC",
					"root","1234");
			System.out.println("DB연동 성공");
		}
		catch(Exception e) {
			System.out.println("DB연동 실패");
		}
	}
	// 3. 메소드
	
	
	
	// 1. 삽입
	public boolean write(String content) {
		
		try {
			
			
		// 1. SQL 작성 [ DB내 테이블에 데이터 삽입 ]
			// db에 테이블에 데이터 저장 : insert into 테이블명1 ( 필드명1 , 필드명2 ) values ( 필드명1의 값 , 필드명2의 값) 
		String sql = "insert into test(writer,content) values(?,?)";
		// 2. SQL 설정 [ 연결된 DB에 SQL 설정 ] 
			// PreparableStatement : 연결된 DB에ㅐ서 테이블 조작 ( 삽입, 삭제 수정 생성 ) 할때 쓰는 인터페이스
		PreparedStatement ps = con.prepareStatement(sql);
		
		//ps.setString(1 , id2);	// sql내 작성한 첫번째 ? 에 변수 넣기 [ 1 : 첫번째 ]
		
		ps.setString(1 ,Loginpane.loginpane.getid() );
		ps.setString(2 , content);	//  [ 2 : 두번째 ]
		
		// 3. sql 실행
		ps.executeUpdate(); // sql 삽입
		
		// 성공시 true
		return true;
		}
		catch (Exception e) {
			System.out.println("[sql 연결 실패] : 사유 " + e);
		}
		
		// 실패시 false
		return false;
	}
	
	


	// 2. 데이터 호출
	public ArrayList<dto.Data> get() {
		
		try {
			// *
			ArrayList<dto.Data> datelsit = new ArrayList<>();
			
			// 1. sql  작성 [ 데이터 호출 ]
				//select * (모든 필드) from 테이블명
			String sql = "select * from test";
			
			// 2. SQL 조작 [ DB 와 연결된 객체와 조작ㄱ인터페이스 연결 ]
			PreparedStatement ps = con.prepareStatement(sql);
			
			// 3. sql 실행 [ ResultSet 인터페이스 java.sql 패키지 ]
			ResultSet rs =  ps.executeQuery();
			
			// * 결과물 하나가 아니고 여러개 이므로 반복문 사용해서
				// 한줄씩[레코드] 객체화 -> 리스트에 저장
			while(rs.next()) {
				// 1. 한줄식 [ 레코드 ] 단위 객체화
				dto.Data temp = new dto.Data(
					rs.getInt(1), // 해당 줄 [ 레코드 ]의 첫번째 필드 [ 세로 ] 값을 가져오기
					rs.getString(2), // 해당 줄 [ 레코드 ]의 첫번째 필드 [ 세로 ] 값을 가져오기
					rs.getString(3)); // 해당 줄 [ 레코드 ]의 첫번째 필드 [ 세로 ] 값을 가져오기
				datelsit.add(temp);
			}
			// 반복문이 종료되면 리스트 반환
			// 성공시 데이터 목록 반환
			return datelsit;
		}
		catch (Exception e) {
			System.out.println("[sql 연결 실패] : 사유 " + e);
		}
		// 실패시 
		return null;
	}
	
	
}
