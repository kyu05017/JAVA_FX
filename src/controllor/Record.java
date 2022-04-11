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
		// 전체 회원수 
		int total_member  = MemberDao.dao.total_member();
		lblmembers.setText(total_member+"명");
		// 전체 제품수
		int total_product = ProductDao.dao.total_product();
		if(total_product == 0) {
			lblitems.setText("제품없음");
		}
		else {
			lblitems.setText(total_product+"개");
		}
		// 전체 게시물수
		int total_board = BoardDao.dao.total_board();
		if(total_board == 0) {
			lblboards.setText("게시물 없음.");
		}
		else {
			lblboards.setText(total_board+"개");
		}
		
	}
}
