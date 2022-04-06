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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB �ּ� ����
			System.out.println("��ǰ DB���� ����");
		}
		catch(Exception e) {
			System.out.println("[SQL ��ǰ ��� ���� ���� ] "+ e);
		}
	}
	// 1. ��ǰ ���
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
			System.out.println("[SQL ��ǰ ���� ���� ]" + e);
		}
		return false;
	}
	// 2. ��ǰ Ǯ��
	public ArrayList<Product> plist(){
		ArrayList<Product> productlist = new ArrayList<>(); // ����Ʈ ���� 	
		try {
			String sql = "select * from product";	// SQL �ۼ�
			ps = con.prepareStatement(sql);			// SQL ���� 
			rs = ps.executeQuery();					// SQL ����  
			while( rs.next() ) {					// SQL ���[ ���ڵ� ���� ]
				Product product = new Product(  	// �ش� ���ڵ带 ��üȭ
						rs.getInt(1) ,
						rs.getString(2),
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9));
				productlist.add(product);			// ����Ʈ�� ��ü ��� 
			}	
			return productlist;						// ����Ʈ ��ȯ
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return null; // ���࿡ ���н� NULL ��ȯ
	}
	// 3. ��ǰ ��ȸ
	
	// 4. ��ǰ ����
	public boolean delete(int p_num) {
		try {
			String sql = "delete from product where p_num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_num);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			System.out.println("[SQL ��ǰ ���� ���� ]" + e);
		}
		return false;
	}
	// 5. ��ǰ ����
	public boolean updaete() {
		try {
			
		}
		catch(Exception e) {
			System.out.println("[SQL ��ǰ ���� ���� ]" + e);
		}
		return false;
	}
	
}
