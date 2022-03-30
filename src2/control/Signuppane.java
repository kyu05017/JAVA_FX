package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signuppane implements Initializable{

	@FXML
    private TextField textid;

    @FXML
    private PasswordField textpw;

    @FXML
    private Button btback;

    @FXML
    private Button btsignup;

    @FXML
    private PasswordField textpwcheck;

    @FXML
    private TextField textaddress;

    @FXML
    private TextField textemail;

    @FXML
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/loginpane");
    	System.out.println("뒤로가기");
    }

    @FXML
    void signup(ActionEvent event) {	
    	System.out.println("회원가입");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
