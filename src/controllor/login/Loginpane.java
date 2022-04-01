package controllor.login;


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
    	Login.login.loadpage("/view/login/findid"); 
    }

    @FXML
    void accfindpw(ActionEvent event) {
    	System.out.println("비밀번호 찾기");
    	Login.login.loadpage("/view/login/findpw");
    }

    @FXML
    void accsignup(ActionEvent event) {
    	// login 컨트롤에 존재하는 boarderpane 객체내 센터 변경
    		// * 문제 : 보더팬 객체다 다른곳에 있다
    		// * new 는 새로운 메모리 할당 [ 기존 보더가 아닌 새로운 보다 ] 
    		// * 기존이 login 클래스에서 사용중인 boaderpane 사용
    	
    	System.out.println("회원가입");
    	Login.login.loadpage("/view/login/signuppane");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("로그인");
    	String id = textid.getText(); // 해당 fxid에 입력된 값 가져오디
    	String pw = textpw.getText(); // 해당 fxid에 입력된 값 가져오디
    	
    	if(id.equals("admin") && pw.equals("1234")) {
    		System.out.println("관리자");
    		lbnconform.setText("관리자 입장");
    	}
    	else if(id.equals("1234") && pw.equals("1234")){
    		System.out.println("일반회원");
    		lbnconform.setText("일반회원 입장");
    	}
    	System.out.println( textid.getText() + "아이디 인식");
    	System.out.println( textpw.getText() + "비밀번호 인식");
    	
    }
    
   
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbnconform.setText("");
		
	}
	
	
}
