package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Product;

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
	
	// 3. ��ǰ ��ȸ
	
	// 4. ��ǰ ����
	
	// 5. ��ǰ ����
}
