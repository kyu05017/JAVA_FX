package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;

public class MemberDao {	// DB 접근 객체

	private Connection con; // SB 연동시 사용되는 클래스 : DB 연동클래스
	private PreparedStatement ps; // 연결된 DB내 SQL 조작 할때 사용하는 인터페이스 : DB 조작
	private ResultSet rs; // 검색 [ select ]
	
	// * DB 연동할때마다 객체 선언시 불필요한 코드를 방지
	
	public static MemberDao dao = new MemberDao(); // DB 연동 객체;
	
	public MemberDao() {
		
		try {
			// DB 연동 
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB 주소 연결
			System.out.println("DB연동 성공");
		} catch (Exception e) {
			System.out.println("DB연동 실패" + e);
		}

	}
	// 메소드
		// 1. 회원가입 메소드 ( 인수를 DB에 넣을 아이디 비밀번호 이메일 주소 )
	public boolean signup(Member member){
		
		try {
		// 1. SQL 작성 [ 회원번호 ( 자동 )제외한 모든 필드 삽입  ]
		String sql = "insert into member(m_id,m_pw,m_email,m_address,m_point,m_since) values(?,?,?,?,?,?)";
		
		
		// 2. SQL 조작
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getM_id());
		ps.setString(2, member.getM_pw());
		ps.setString(3, member.getM_email());
		ps.setString(4, member.getM_address());
		ps.setInt(5, member.getM_point());
		ps.setString(6, member.getM_since());
		
		// 3. SQL 실행
		ps.executeUpdate(); 	
		
		// * 성공시 
		return true;
		
		} catch (SQLException e) {
			System.out.println("[SQL 저장 실패 ]" + e);
		}
		// * 실패시
		return false;
	}
		// 2. 로그인 메소드 ( 로그인에 필요한 아이디 비밀번호 )
	public boolean login(String id, String pw) {
		return false;
	}
		// 3. 아이디 찾기 메소드 ( 아이디 찾기에 필요한 이메일 )
	public String findid(String email) {
		return null;
	}
		// 4. 비밀번호 찾기 메소드 ( 비밀번호 찾기에 필요한 아이디 이메일 ) 
	public String findpw(String id ,String email) {
		return null;
	}
}
