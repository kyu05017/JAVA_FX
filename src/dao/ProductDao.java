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
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB ����̹� ��������
			con = DriverManager.getConnection("jdbc:mysql://database-1.cdocvkszhrus.us-west-2.rds.amazonaws.com:3306/javafx?serverTimezone=UTC","admin","rLARB4595!"); // 2. DB �ּ� ����
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
	public ArrayList<Product> plist(String category,String search){

			ArrayList<Product> productlist = new ArrayList<>(); // ����Ʈ ���� 	
			try {
				String sql = null;
				if(category == null) {
					if(search == null) {
						sql = "select * from product order by p_num desc";	// SQL �ۼ�
						ps = con.prepareStatement(sql);// SQL ���� 
					}
					else {
						sql = "select * from product where p_name like '%"+search+"%' order by p_num desc";	// SQL �ۼ�
						ps = con.prepareStatement(sql);// SQL ���� 
					}
				}
				else  {
					if(search ==  null) {
						sql = "select * from product where p_category = ? order by p_num desc";	// SQL �ۼ�
						ps = con.prepareStatement(sql);// SQL ���� 
						ps.setString(1, category);
					}
					else {
						sql = "select * from product where p_category = ? and p_name like '%"+search+"%' order by p_num desc";	// SQL �ۼ�
						ps = con.prepareStatement(sql);// SQL ���� 
						ps.setString(1, category);
					}
				}
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
			System.out.println("[SQL ��ǰ ���� ���� ]" + e);
		}
		return false;
	}
	public ArrayList<Product> my_itemlist(int num){
		
		ArrayList<Product> productlist = new ArrayList<>(); // ����Ʈ ���� 	
		try {
			String sql = "select * from product where m_num=?";
			ps = con.prepareStatement(sql);	
			ps.setInt(1,num);// SQL ���� 
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
	public boolean reply_write(Reply_Product reply) {
		try {
			// 1. SQL �ۼ� [ ȸ����ȣ ( �ڵ� )������ ��� �ʵ� ����  ]
			String sql = "insert into p_reply(r_content,r_write,p_num) values(?,?,?)";
			// 2. SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, reply.getR_contents());
			ps.setString(2, reply.getR_writerr());
			ps.setInt(3, reply.getP_num());
			
			// 3. SQL ����
			ps.executeUpdate(); 	
			
			// * ������ 
			return true;
		} 
		catch (SQLException e) {
			System.out.println("[SQL �Խ��� ���� ���� ]" + e);
		}
		return false;// * ���н�
	}
	public ObservableList<Reply_Product> reply_list(int p_num) {
		try {
			// *
			ObservableList<Reply_Product> replylist = FXCollections.observableArrayList();
			
			// 1. sql  �ۼ� [ ������ ȣ�� ]
				//select * (��� �ʵ�) from ���̺��
			 	// �������� by b_num asc = b_num�������� �������� ����
				// �������� by b_num desc = b_num�������� �������� ����
			String sql = "select * from p_reply where p_num=?";
			// 2. SQL ���� [ DB �� ����� ��ü�� ���ۤ��������̽� ���� ]
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_num);
			// 3. sql ���� [ ResultSet �������̽� java.sql ��Ű�� ]
			rs =  ps.executeQuery();
			
			// * ����� �ϳ��� �ƴϰ� ������ �̹Ƿ� �ݺ��� ����ؼ�
				// ���پ�[���ڵ�] ��üȭ -> ����Ʈ�� ����
			
			// rs.next() �˻������ ���� ���ڵ�
			// rs.getint() �˻������ �������� ���
			// rs.getstring() �˻������ �������� ���
			while(rs.next()) {
				// 1. ���ٽ� [ ���ڵ� ] ���� ��üȭ
				Reply_Product temp = new Reply_Product(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5)
					); 
				replylist.add(temp);
			}
			// �ݺ����� ����Ǹ� ����Ʈ ��ȯ
			// ������ ������ ��� ��ȯ
			return replylist;
		}
		catch (Exception e) {
			System.out.println("[sql ���� ����] : ���� " + e);
		}
		// ���н� 
		return null;
	}
	public boolean reply_delete(int r_num) {
		try {
			// 1. sql �ۼ�
			String sql = "delete from p_reply where r_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			ps.setInt(1, r_num);
			
			// 3. sql ����
			ps.executeUpdate();
			// 4. sql ���
			return true;
			
		} catch (SQLException e) {
			System.out.println("[SQL ��� ���� ���� ] "+ e);
			}
		
		return false;
	}
	public boolean re_updaete(int P_num,String contents) {
		try {
			// 1. SQL �ۼ�
			// select * from ���̺�� where ����=( �ʵ�� = �� )
			String sql = "UPDATE p_reply SET r_content=? where P_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			
			ps.setString(1, contents);
			ps.setInt(2, P_num);

			// 3. SQL ����
			ps.executeUpdate(); 
			
			// 4. sql ���
			return true;
		}
		catch(Exception e) {
			System.out.println("�������� " + e);
		}
		return false;
	}
	// ���º���
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
			System.out.println("[SQL ��ǰ ���� ���� ]" + e);
		}
		return false;
	}
	// 2. ��ǰ Ǯ��

	
}
