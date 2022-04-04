package controllor.login;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class FindPw implements Initializable{

	@FXML
    private TextField textemail;

    @FXML
    private Button btback;

    @FXML
    private Button btfindpw;

    @FXML
    private TextField textid;
    
    @FXML
    private Label lbfindpw;

    @FXML
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/login/loginpane");
    	System.out.println("뒤로가기");
    }

    @FXML
    void findpw(ActionEvent event) {
    	
    	System.out.println("비밀번호 찾기");
    	String email = textemail.getText();
    	String id = textid.getText();

    	String result = MemberDao.dao.findpw(id,email);
    	
    	if(result == null) {
    		// 페이지 전환 [ 다음주 ]
    		// * 테스트
    		lbfindpw.setText("존재하지 않는 회원 입니다.");
    	}
    	else {
    		
    		Member.sendmail(email,result);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("비밀번호 찾기");
    		alert.setHeaderText("이메일로 비밀번호를 전송했습니다.");
    		alert.setContentText("완료");
    		alert.showAndWait();
    		
    		
    		lbfindpw.setText(result);
    	}
    
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	lbfindpw.setText("");
    	
    }
	    
	    
}
