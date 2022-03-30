package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/loginpane");
    	System.out.println("뒤로가기");
    }

    @FXML
    void findpw(ActionEvent event) {
    	System.out.println("비밀번호 찾기");
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    }
	    
	    
}
