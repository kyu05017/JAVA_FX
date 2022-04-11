package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dto.Board;
import dto.MemberView;
import dto.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BoardDao { // 2022 04 06 06 12
	
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
		catch (Exception e) {
			System.out.println("[SQL 모드 연동 실패 ] "+ e);
		}
	}
	
	// 메소드
	public static void viewSave() {
		try {
			FileOutputStream outputStream = new FileOutputStream("D:/viewlist.txt");
			for(MemberView temp : controllor.board.Board.m_view) {
				String Save = temp.getId()+","+temp.getB_num()+","+temp.getDate()+"\n";
				outputStream.write(Save.getBytes());
			}
		}
		catch(Exception e) {
			
		}
	}
	public static void viewLoad() {
		try {
			FileInputStream inputStream = new FileInputStream("D:/viewlist.txt");
			byte[] bytes = new byte[1024]; 
			inputStream.read(bytes); 
			String file = new String(bytes); 
			String[]list = file.split("\n"); 
			int i = 0;
			for(String temp : list) {
				if( i+1 == list.length ) {
					break; 
				}
				String[] filed = temp.split(",");
				MemberView temp2 = new MemberView(filed[0],Integer.parseInt(filed[1]),filed[2]);
				controllor.board.Board.m_view.add(temp2);
				i++;
			}
			
		}
		catch(Exception e) {
		}
	}
	
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
			System.out.println("[SQL 글목록 로드 실패 ] "+ e);
		}
		// 실패시 
		return null;
	}

	// 3. 글삭제
	public boolean delete(int b_num) {
		try {
			// 1. sql 작성
			String sql = "delete from board where b_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			ps.setInt(1, b_num);
			
			// 3. sql 실행
			ps.executeUpdate();
			// 4. sql 결과
			return true;
			
		} catch (SQLException e) {
			System.out.println("[SQL 게시글 삭제 실패 ] "+ e);
			}
		
		return false;
	}
	// 4. 글수정
	public boolean updaete(int b_num, String title, String contents) {
		try {
			// 1. SQL 작성
			// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "UPDATE board SET b_title=?,b_contents=? where b_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, title);
			ps.setString(2, contents);
			ps.setInt(3, b_num);

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
	public boolean view(int num,int view,String id) {
		BoardDao.viewLoad();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String today = sdf.format(new Date());
			for(MemberView temp : controllor.board.Board.m_view) {
				if(temp.getId().equals(id) && temp.getB_num() == num) {
					if(temp.getDate().equals(today)) {
						System.out.println("조회수 미증가");
						break;
					}
					else if((!temp.getDate().equals(today))){
						System.out.println("조회수 증가");
						temp.setDate(today);
						String sql = "UPDATE board SET b_view=? where b_num=?";
						// 2. sql 조작
						ps = con.prepareStatement(sql);
						int new_view = view + 1;
						controllor.board.Board.board.setB_view(new_view);
						ps.setInt(1, new_view);
						System.out.println(new_view);
						ps.setInt(2, num);
						// 3. SQL 실행
						ps.executeUpdate();
						BoardDao.viewSave();
						break;
					}
				}
			}
			// 1. SQL 작성
			// select * from 테이블명 where 조건=( 필드명 = 값 )
			
			
			return true;
		}
		catch(Exception e) {
			System.out.println("[SQL 조회수 저장 ] "+ e);
		}
		return false;
	}
	// 6. 댓글 작성
	public boolean reply_write(Reply reply) {
		try {
			// 1. SQL 작성 [ 회원번호 ( 자동 )제외한 모든 필드 삽입  ]
			String sql = "insert into reply(r_contents,r_writerr,b_num) values(?,?,?)";
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, reply.getR_contents());
			ps.setString(2, reply.getR_writerr());
			ps.setInt(3, reply.getB_num());
			
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
	// 7 . 불러오시
	public ObservableList<Reply> reply_list(int b_num) {
		
		try {
			// *
			ObservableList<Reply> replylist = FXCollections.observableArrayList();
			
			// 1. sql  작성 [ 데이터 호출 ]
				//select * (모든 필드) from 테이블명
			 	// 오름차순 by b_num asc = b_num기준으로 오름차순 정렬
				// 내림차순 by b_num desc = b_num기준으로 내림차순 정렬
			String sql = "select * from reply where b_num=?";
			// 2. SQL 조작 [ DB 와 연결된 객체와 조작ㄱ인터페이스 연결 ]
			ps = con.prepareStatement(sql);
			ps.setInt(1, b_num);
			// 3. sql 실행 [ ResultSet 인터페이스 java.sql 패키지 ]
			rs =  ps.executeQuery();
			
			// * 결과물 하나가 아니고 여러개 이므로 반복문 사용해서
				// 한줄씩[레코드] 객체화 -> 리스트에 저장
			
			// rs.next() 검색결과의 다음 레코드
			// rs.getint() 검색결과를 정수형일 경우
			// rs.getstring() 검색결과를 문자형일 경우
			while(rs.next()) {
				// 1. 한줄식 [ 레코드 ] 단위 객체화
				Reply temp = new Reply(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5)
					); 
				replylist.add(temp);
			}
			// 반복문이 종료되면 리스트 반환
			// 성공시 데이터 목록 반환
			return replylist;
		}
		catch (Exception e) {
			System.out.println("[sql 연결 실패] : 사유 " + e);
		}
		// 실패시 
		return null;
	}
	// 8. 댓글 삭제
	public boolean reply_delete(int r_num) {
		try {
			// 1. sql 작성
			String sql = "delete from reply where r_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			ps.setInt(1, r_num);
			
			// 3. sql 실행
			ps.executeUpdate();
			// 4. sql 결과
			return true;
			
		} catch (SQLException e) {
			System.out.println("[SQL 댓글 삭제 실패 ] "+ e);
			}
		
		return false;
	}
	// 9. 댓글 수정
	public boolean re_updaete(int r_num,String contents) {
		try {
			// 1. SQL 작성
			// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "UPDATE reply SET r_contents=? where r_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, contents);
			ps.setInt(2, r_num);

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
	// 9.내 글확인
	public ObservableList<Board> my_boardlist(String id) {
		
		try {
			// *
			ObservableList<Board> myboardlist = FXCollections.observableArrayList();

			String sql = "select * from board where b_writer=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs =  ps.executeQuery();

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
				myboardlist.add(temp);
			}
			// 반복문이 종료되면 리스트 반환
			// 성공시 데이터 목록 반환
			return myboardlist;
		}
		catch (Exception e) {
			System.out.println("[sql 연결 실패] : 사유 " + e);
		}
		// 실패시 
		return null;
	}
	public int total_board() {
		try {
			String sql = "select count(*) from board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) { // 만약에 다음 결과물이 존재하면 => 해당아이디가 존재 => 중복
				return rs.getInt(1); // 해당 아이디는 중복이 존재
			}
		}
		catch (Exception e) {
			System.out.println("게시물수 불러오기 오류 " + e);
		}
		return 0;
	}
	public Map<String, Integer> date_Btotal(String name) {
		try {
			Map<String ,Integer> btotal = new HashMap<>();
			String sql = null;
			if(name.equals("board")) {
				sql = "SELECT substring_index(b_date,' ', 1), count(*) FROM "+name+" group by substring_index(b_date, ' ' , 1)  ";
			}
			else if(name.equals("product")){
				sql = "SELECT substring_index(p_date,' ', 1), count(*) FROM "+name+" group by substring_index(p_date, ' ' , 1)  ";
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