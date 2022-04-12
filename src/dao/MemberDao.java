package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import controllor.login.Login;
import dto.Member;

public class MemberDao {	// DB 접근 객체

	private Connection con; // DB 연동시 사용되는 클래스 : DB 연동클래스
	private PreparedStatement ps; // 연결된 DB내 SQL 조작 할때 사용하는 인터페이스 : DB 조작
	private ResultSet rs; // 검색 [ select ]
	
	// * DB 연동할때마다 객체 선언시 불필요한 코드를 방지
	
	public static MemberDao dao = new MemberDao(); // DB 연동 객체;
	
	public MemberDao() {
		
		try {
			// DB 연동 
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://database-1.cdocvkszhrus.us-west-2.rds.amazonaws.com:3306/javafx?serverTimezone=UTC","admin","rLARB4595!"); // 2. DB 주소 연결
			System.out.println("DB연동 성공");
		} catch (Exception e) {
			System.out.println("DB연동 실패" + e);
		}

	}
	// 메소드
		// * 아이디 중복 체크 메소드 ( 인수 : 아이디를 인수로 받아 db에 존재하지지체크 ]
	public boolean idcheck(String id) {
		try {
			// 1. SQL 작성
				// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "select * from member where m_id=?";
			
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			// 3. SQL 실행
			rs = ps.executeQuery();//  select 실행은 결과물이 존재 -> resulitSet O
			// ResultSet 처음 결과물이 null -- next > 결과 레코드
			// 4. SQL 결과
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				return true; // 해당 아이디는 중복이 존재
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL 오류 ]" + e);
		}
		return false; // 해당아이디 중복 없음
	}
	
		// 1. 회원가입 메소드 ( 인수를 DB에 넣을 아이디 비밀번호 이메일 주소 )
	public boolean signup(Member member){
		
		try {
		// 1. SQL 작성 [ 회원번호 ( 자동 )제외한 모든 필드 삽입  ]
		String sql = "insert into member(m_id,m_pw,m_email,m_address,m_point,m_since,m_today) values(?,?,?,?,?,?,?)";
		
		
		// 2. SQL 조작
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getM_id());
		ps.setString(2, member.getM_pw());
		ps.setString(3, member.getM_email());
		ps.setString(4, member.getM_address());
		ps.setInt(5, member.getM_point());
		ps.setString(6, member.getM_since());
		ps.setString(7, member.getM_today());
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
		try {
			// 1. SQL 작성 
					// and : 조건1 && 조건2
					// or  : 조건1 || 조건2
			String sql = "select * from member where m_id=? and m_pw=?";
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			// 3. SQL 실행
			rs = ps.executeQuery();
			// 4. 결과
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				return true; // 해당 아이디는 중복이 존재
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL 오류]" + e);
		}
		return false;
	}
		// 3. 아이디 찾기 메소드 ( 아이디 찾기에 필요한 이메일 )
	public String findid(String email) {
		try {
			// 1. SQL 작성
				// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "select * from member where m_email=?";
			
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			// 3. SQL 실행
			rs = ps.executeQuery();//  select 실행은 결과물이 존재 -> resulitSet O
			// ResultSet 처음 결과물이 null -- next > 결과 레코드
			// 4. SQL 결과
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				return rs.getString(2); // 해당 아이디는 중복이 존재
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL 오류 ]" + e);
		}
		return null; // 해당아이디 중복 없음
	}
		// 4. 비밀번호 찾기 메소드 ( 비밀번호 찾기에 필요한 아이디 이메일 ) 
	public String findpw(String id ,String email) {
		try {
			// 1. SQL 작성
				// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "select * from member where m_id=? and m_email=?";
			
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, email);
			// 3. SQL 실행
			rs = ps.executeQuery();//  select 실행은 결과물이 존재 -> resulitSet O
			// ResultSet 처음 결과물이 null -- next > 결과 레코드
			// 4. SQL 결과
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				return rs.getString(3); // 해당 아이디는 중복이 존재
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL 오류 ]" + e);
		}
		return null; // 해당아이디 중복 없음
	}
	
	// 5. 아이디로 회원정보 호출
	public Member getmember (String id) {
		try {
			// 1. sql 작성
			String sql = "select * from member where m_id=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			// 3. sql 실행
			rs = ps.executeQuery();
			// 4. sql 결과
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				Member member = new Member(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getString(7),
					rs.getString(8)
					//rs.next() : 결과내 다음 레코드 ( 줄, 가로 )
					//rs.getint : 해당 필드의 자료형이 정수형으로 가져옴
					//rs.getsttring : 해당필드의 자료형을 문자열로 가져옴
				);
				return member;
				
			}
			
		}
		catch(Exception e ) {
			System.out.println(e);
		}
		return null;
		 
	}
	// 6. 회원탈퇴 = 회원번호를 입력받아 인수로 받아 해당 회원번호의 레코드를 삭제
	public boolean signOut(int num) {
		try {
			// 1. sql 작성
			String sql = "delete from member where m_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			
			// 3. sql 실행
			ps.executeUpdate();
			// 4. sql 결과
			return true;
			
		} catch (SQLException e) {System.out.println("탈퇴오류"+e);}
		
		
		
		return false;
	}
	// 7. 회원수정
	public boolean changeInfo(int num,String add,String email) {
		
		try {
			// 1. SQL 작성
			// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "UPDATE member SET m_email=?,m_address=? where m_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, email);
			ps.setString(2, add);
			ps.setInt(3, num);

			// 3. SQL 실행
			ps.executeUpdate(); 
			
			// 4. sql 결과
			return true;
		}
		catch(Exception e) {
			System.out.println("수정오료 " + e);
		}
		return false;
	}
	public boolean todayPoint(int num,int point) {
		try {
			// 1. SQL 작성
			// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "UPDATE member SET m_point=? where m_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			int new_point = point + 10;
			Login.member.setM_point(new_point);
			ps.setInt(1, new_point);
			System.out.println(new_point);
			ps.setInt(2, num);
			// 3. SQL 실행
			ps.executeUpdate(); 
			
			return true;
		}
		catch(Exception e) {
			System.out.println("수정오료 " + e);
		}
		return false;
	}
	public void todaylogin(String id) {
		try {
			String sql2 = "UPDATE member SET m_today=? where m_id=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql2);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String since = sdf.format(new Date());
			ps.setString(1, since);
			ps.setString(2, id);
	
			// 3. SQL 실행
			ps.executeUpdate(); 
		}
		catch(Exception e) {
			System.out.println("투데이 수정오료 " + e);
		}
	}
	// 
	public String getM_id(int m_num) {
		try {
			String sql = "select m_id from member where m_num=?";
			
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setInt(1, m_num);
			// 3. SQL 실행
			rs = ps.executeQuery();//  select 실행은 결과물이 존재 -> resulitSet O
			// ResultSet 처음 결과물이 null -- next > 결과 레코드
			// 4. SQL 결과
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				return rs.getString(1); // 해당 아이디는 중복이 존재
			}
		}
		catch(Exception e) {
			System.out.println("아이디 찾기 오류 " + e);
		}
		return null;
	}
	
	// 9. 전체회쉉수를 반환하는 메소드
	
	public int total_member(String name) {
		try {
			String sql = null;
			if(name.equals("member")) {
				sql = "select count(m_id) from member";
			}else if(name.equals("board")) {
				sql = "select count(*) from board";
			}else if(name.equals("product")) {
				sql = "select count(*) from product";
			}			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				return rs.getInt(1); // 해당 아이디는 중복이 존재
			}
		}
		catch (Exception e) {
			System.out.println("회원수 불러오기 오류 " + e);
		}
		return 0;
	}
	
	// 10. 날짜별로 회원가입순을 반환
	public Map<String, Integer> date_total(String name) {
		try {
			Map<String ,Integer> btotal = new HashMap<>();
			String sql = null;
			
			if(name.equals("member")) {
				sql = "SELECT substring_index(m_since,' ', 1), count(*) FROM "+name+" group by substring_index(m_since, ' ' , 1)  ";
			}
			else if(name.equals("board")) {
				sql = "SELECT substring_index(b_date,' ', 1), count(*) FROM "+name+" group by substring_index(b_date, ' ' , 1)  ";
			}
			else if(name.equals("product")){
				sql = "SELECT substring_index(p_date,' ', 1), count(*) FROM "+name+" group by substring_index(p_date, ' ' , 1)  ";
			}
			else if(name.equals("category")){
				sql =  "select p_category,count(*) from product group by p_category";
			}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				btotal.put(rs.getString(1), rs.getInt(2));
			}
			return btotal;
		}
		catch (Exception e) {
			System.out.println("날짜 회원수 조회 실패" + e);
		}
		return null;
	}
}
