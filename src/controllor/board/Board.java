package controllor.board;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;


public class Board implements Initializable{

	
    @FXML
    private TableView<?> boardtable;

    @FXML
    private ButtonType btwrite;

    @FXML
    void addwrite(ActionEvent event) {

    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}  
}
