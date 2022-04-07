package controllor;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Chatting implements Initializable{
	@FXML
    private TextArea txtcontents;

    @FXML
    private Button btcon;

    @FXML
    private TextField txt;

    @FXML
    private Button btsend;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtipadd;

    @FXML
    private TextField txtport;
    
    Socket socket;
    
    
    public void clientstart() {
    	
    }
	public void clientstop() {
	    	
	}
	public void send(String msg) {
		
	}
	public void receive() {
		
	}
    
    @FXML
    void acsend(ActionEvent event) {
    	if(btcon.getText().equals("채팅방 입장")){
    		
    		
    		txtcontents.appendText("---채팅방 입장 ---\n");
    		btcon.setText("채팅방 나가기");
    	}
    	else {
    		
    	}
    }

    @FXML
    void actcon(ActionEvent event) {

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    }
}
