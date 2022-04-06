package controllor.board;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.home.Home;
import controllor.login.Login;
import dao.BoardDao;
import dao.MemberDao;
import dto.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardWrite implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    private Button btback;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btwrite1;

    @FXML
    private TextField txttitle;

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/board/board");
    }

    @FXML
    void addwrite(ActionEvent event) {
    	String title = txttitle.getText();
    	String contents = txtcontents.getText();
    	// �ۼ��� : ����α��ε� ��ü�� �α��� Ŭ������ �ɹ���ü�� ����Ǿ�����
    	String writer = Login.member.getM_id();
    	if(txttitle.getText().equals("") || txtcontents.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("������ �Է����ּ���.");
    		alert.showAndWait();
    	}
    	else {
    		Board board = new Board(0, title, contents,writer, null, 0 );
    	
	    	boolean result =  BoardDao.dao.write(board);
	    	
	    	if(result) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("�Խñ��� �ۼ� �Ǿ����ϴ�. ����Ʈ +10��");
	    		alert.showAndWait();
	    		MemberDao.dao.todayPoint(Login.member.getM_num(),Login.member.getM_point());
	    		Home.home.loadpage("/view/board/board");
	    	}
    	}
    }
}
