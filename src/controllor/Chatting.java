package controllor;

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
    
    
    @FXML
    void acsend(ActionEvent event) {

    }

    @FXML
    void actcon(ActionEvent event) {

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    }
}
