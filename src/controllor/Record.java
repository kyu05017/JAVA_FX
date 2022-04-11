package controllor;

import java.net.URL;
import java.util.ResourceBundle;

import dao.BoardDao;
import dao.MemberDao;
import dao.ProductDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Record implements Initializable{

	@FXML
    private Label lblmembers;

    @FXML
    private Label lblitems;

    @FXML
    private Label lblboards;

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// ��ü ȸ���� 
		int total_member  = MemberDao.dao.total_member();
		lblmembers.setText(total_member+"��");
		// ��ü ��ǰ��
		int total_product = ProductDao.dao.total_product();
		if(total_product == 0) {
			lblitems.setText("��ǰ����");
		}
		else {
			lblitems.setText(total_product+"��");
		}
		// ��ü �Խù���
		int total_board = BoardDao.dao.total_board();
		if(total_board == 0) {
			lblboards.setText("�Խù� ����.");
		}
		else {
			lblboards.setText(total_board+"��");
		}
		
	}
}
