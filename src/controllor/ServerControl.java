package controllor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerControl implements Initializable{
	
	@FXML
    private Button btserver;

    @FXML
    private TextArea txtserver;

    @FXML
    void server(ActionEvent event) { // 서버 실행 버튼
    	if(btserver.getText().equals("서버 실행")) {
    		
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
