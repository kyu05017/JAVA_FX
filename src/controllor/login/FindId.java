package controllor.login;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindId implements Initializable{

	@FXML
    private TextField textemail;

    @FXML
    private Button btback;

    @FXML
    private Button btfindid;
    
    @FXML 
    private Label lbfindid;

    @FXML
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/login/loginpane");
    	System.out.println("뒤로가기"); 
    }

    @FXML
    void findid(ActionEvent event) {
    	System.out.println("아이디 찾기");
    	String email = textemail.getText();
    	
    	String result = MemberDao.dao.findid(email);
    	
    	if(result == null) {
    		// 페이지 전환 [ 다음주 ]
    		// * 테스트
    		lbfindid.setText("존재하지 않는 아이디 입니다.");
    	}
    	else {
    		lbfindid.setText(result);
    	}
    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbfindid.setText("");
		
	}
}
