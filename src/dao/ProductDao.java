package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dto.Board;
import dto.Product;
import dto.Reply;
import dto.Reply_Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDao {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static ProductDao dao = new ProductDao();
	
	public ProductDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://database-1.cdocvkszhrus.us-west-2.rds.amazonaws.com:3306/javafx?serverTimezone=UTC","admin","rLARB4595!"); // 2. DB 주소 연결
			System.out.println("상품 DB연동 성공");
		}
		catch(Exception e) {
			System.out.println("[SQL 제품 모드 연동 실패 ] "+ e);
		}
	}
	// 1. 제품 등록
	public boolean addproduct(Product product) {
		try {
			String sql = "insert into product(p_name,p_img,p_contents,p_category,p_money,p_condition,m_num) values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getP_name());
			ps.setString(2, product.getP_img());
			ps.setString(3, product.getP_contents());
			ps.setString(4, product.getP_category());
			ps.setInt(5, product.getP_money());
			ps.setInt(6, product.getP_condition());
			ps.setInt(7, product.getM_num());
			ps.executeUpdate(); 	
			return true;
		}
		catch(Exception e) {
			System.out.println("[SQL 제품 저장 실패 ]" + e);
		}
		return false;
	}
	// 2. 제품 풀력
	public ArrayList<Product> plist(String category,String search){

			ArrayList<Product> productlist = new ArrayList<>(); // 리스트 선언 	
			try {
				String sql = null;
				if(category == null) {
					if(search == null) {
						sql = "select * from product order by p_num desc";	// SQL 작성
						ps = con.prepareStatement(sql);// SQL 연결 
					}
					else {
						sql = "select * from product where p_name like '%"+search+"%' order by p_num desc";	// SQL 작성
						ps = con.prepareStatement(sql);// SQL 연결 
					}
				}
				else  {
					if(search ==  null) {
						sql = "select * from product where p_category = ? order by p_num desc";	// SQL 작성
						ps = con.prepareStatement(sql);// SQL 연결 
						ps.setString(1, category);
					}
					else {
						sql = "select * from product where p_category = ? and p_name like '%"+search+"%' order by p_num desc";	// SQL 작성
						ps = con.prepareStatement(sql);// SQL 연결 
						ps.setString(1, category);
					}
				}
				rs = ps.executeQuery();					// SQL 실행  
				while( rs.next() ) {					// SQL 결과[ 레코드 단위 ]
					Product product = new Product(  	// 해당 레코드를 객체화
							rs.getInt(1) ,
							rs.getString(2),
							rs.getString(3), 
							rs.getString(4), 
							rs.getString(5),
							rs.getInt(6),
							rs.getInt(7),
							rs.getString(8),
							rs.getInt(9));
					productlist.add(product);			// 리스트에 객체 담기 
				}	
				return productlist;						// 리스트 반환
			}catch(Exception e ) { System.out.println( "[SQL 오류]"+e  ); }
		return null; // 만약에 실패시 NULL 반환
	}
	// 3. 제품 조회
	
	// 4. 제품 삭제
	public boolean delete(int p_num) {
		try {
			String sql = "delete from product where p_num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_num);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			System.out.println("[SQL 제품 삭제 실패 ]" + e);
		}
		return false;
	}
	// 5. 제품 수정
	public boolean updaete(Product product) {
		try {
			String sql = "update product set p_name=? , p_img=? , p_contents=?,p_category=? , p_money=? where p_num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getP_name());
			ps.setString(2, product.getP_img());
			ps.setString(3, product.getP_contents());
			ps.setString(4, product.getP_category());
			ps.setInt(5, product.getP_money());
			ps.setInt(6, product.getP_num());
			ps.executeUpdate();
			return true;
			
		}
		catch(Exception e) {
			System.out.println("[SQL 제품 수정 실패 ]" + e);
		}
		return false;
	}
	public ArrayList<Product> my_itemlist(int num){
		
		ArrayList<Product> productlist = new ArrayList<>(); // 리스트 선언 	
		try {
			String sql = "select * from product where m_num=?";
			ps = con.prepareStatement(sql);	
			ps.setInt(1,num);// SQL 연결 
			rs = ps.executeQuery();					// SQL 실행  
			while( rs.next() ) {					// SQL 결과[ 레코드 단위 ]
				Product product = new Product(  	// 해당 레코드를 객체화
						rs.getInt(1) ,
						rs.getString(2),
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9));
				productlist.add(product);			// 리스트에 객체 담기 
			}	
			return productlist;						// 리스트 반환
		}catch(Exception e ) { System.out.println( "[SQL 오류]"+e  ); }
		return null; // 만약에 실패시 NULL 반환
	}
	public boolean reply_write(Reply_Product reply) {
		try {
			// 1. SQL 작성 [ 회원번호 ( 자동 )제외한 모든 필드 삽입  ]
			String sql = "insert into p_reply(r_content,r_write,p_num) values(?,?,?)";
			// 2. SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, reply.getR_contents());
			ps.setString(2, reply.getR_writerr());
			ps.setInt(3, reply.getP_num());
			
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
	public ObservableList<Reply_Product> reply_list(int p_num) {
		try {
			// *
			ObservableList<Reply_Product> replylist = FXCollections.observableArrayList();
			
			// 1. sql  작성 [ 데이터 호출 ]
				//select * (모든 필드) from 테이블명
			 	// 오름차순 by b_num asc = b_num기준으로 오름차순 정렬
				// 내림차순 by b_num desc = b_num기준으로 내림차순 정렬
			String sql = "select * from p_reply where p_num=?";
			// 2. SQL 조작 [ DB 와 연결된 객체와 조작ㄱ인터페이스 연결 ]
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_num);
			// 3. sql 실행 [ ResultSet 인터페이스 java.sql 패키지 ]
			rs =  ps.executeQuery();
			
			// * 결과물 하나가 아니고 여러개 이므로 반복문 사용해서
				// 한줄씩[레코드] 객체화 -> 리스트에 저장
			
			// rs.next() 검색결과의 다음 레코드
			// rs.getint() 검색결과를 정수형일 경우
			// rs.getstring() 검색결과를 문자형일 경우
			while(rs.next()) {
				// 1. 한줄식 [ 레코드 ] 단위 객체화
				Reply_Product temp = new Reply_Product(
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
	public boolean reply_delete(int r_num) {
		try {
			// 1. sql 작성
			String sql = "delete from p_reply where r_num=?";
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
	public boolean re_updaete(int P_num,String contents) {
		try {
			// 1. SQL 작성
			// select * from 테이블명 where 조건=( 필드명 = 값 )
			String sql = "UPDATE p_reply SET r_content=? where P_num=?";
			// 2. sql 조작
			ps = con.prepareStatement(sql);
			
			ps.setString(1, contents);
			ps.setInt(2, P_num);

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
	// 상태변경
	public boolean change_condition(int p_num) {
		try {
			String sql = "select p_condition from product where p_num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String upsql = null;
				if(rs.getInt(1) == 1) {
					upsql = "UPDATE product SET p_condition=2 where p_num=?";
				}
				else if(rs.getInt(1) == 2) {
					upsql = "UPDATE product SET p_condition=3 where p_num=?";
				}
				else if(rs.getInt(1) == 3) {
					upsql = "UPDATE product SET p_condition=1 where p_num=?";
				}
				ps = con.prepareStatement(upsql);
				ps.setInt(1, p_num);
				ps.executeUpdate(); 
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("[SQL 제품 수정 실패 ]" + e);
		}
		return false;
	}
	// 2. 제품 풀력

	
}
