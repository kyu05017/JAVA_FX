package controllor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FindId implements Initializable{

	@FXML
    private TextField textemail;

    @FXML
    private Button btback;

    @FXML
    private Button btfindid;

    @FXML
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/loginpane");
    	System.out.println("�ڷΰ���");
    }

    @FXML
    void findid(ActionEvent event) {
    	System.out.println("���̵� ã��");
    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
