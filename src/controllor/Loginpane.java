package controllor;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    	System.out.println("���̵� ã��");
    }

    @FXML
    void accfindpw(ActionEvent event) {
    	System.out.println("��й�ȣ ã��");
    }

    @FXML
    void accsignup(ActionEvent event) {
    	System.out.println("ȸ������");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("�α���");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
