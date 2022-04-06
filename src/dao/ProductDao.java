package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDao {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static ProductDao dao = new ProductDao();
	
	public ProductDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB 주소 연결
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
	public ArrayList<Product> plist(){
		ArrayList<Product> productlist = new ArrayList<>(); // 리스트 선언 	
		try {
			String sql = "select * from product";	// SQL 작성
			ps = con.prepareStatement(sql);			// SQL 연결 
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
	public boolean updaete() {
		try {
			
		}
		catch(Exception e) {
			System.out.println("[SQL 제품 수정 실패 ]" + e);
		}
		return false;
	}
	
}
