package controllor.board;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.home.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardWrite implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    private Button btback;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btwrite1;

    @FXML
    private TextField txttitle;

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/board/board");
    }

    @FXML
    void addwrite(ActionEvent event) {
    	String title = txttitle.getText();
    	String contents = txtcontents.getText();
    	
    	
    }
}
