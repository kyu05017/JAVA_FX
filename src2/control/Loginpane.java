package control;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Loginpane implements Initializable{

	

    @FXML
    private TextField textid;

    @FXML
    private PasswordField textpw;

    @FXML
    private Button btlogin;

    @FXML
    private Button btsignup;

    @FXML
    private Button btfindpw;

    @FXML
    private Button btfindid;

    @FXML
    private Label lbnconform;

    @FXML
    void accfindip(ActionEvent event) {
    	System.out.println("아이디 찾기");
    	Login.login.loadpage("/view/findid");
    }

    @FXML
    void accfindpw(ActionEvent event) {
    	System.out.println("비밀번호 찾기");
    	Login.login.loadpage("/view/findpw");
    }

    @FXML
    void accsignup(ActionEvent event) {
    	// login 컨트롤에 존재하는 boarderpane 객체내 센터 변경
    		// * 문제 : 보더팬 객체다 다른곳에 있다
    		// * new 는 새로운 메모리 할당 [ 기존 보더가 아닌 새로운 보다 ] 
    		// * 기존이 login 클래스에서 사용중인 boaderpane 사용
    	
    	System.out.println("회원가입");
    	Login.login.loadpage("/view/signuppane");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("로그인");
    	
    }
    
   
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
