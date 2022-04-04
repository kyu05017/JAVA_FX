package controllor.home;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.login.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Myinfo implements Initializable{

    @FXML
    private Label my_id;

    @FXML
    private Label my_date;

    @FXML
    private Label my_add;

    @FXML
    private Label my_point;

    @FXML
    private Label my_email;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	my_id.setText("ID : "+Login.member.getM_id());
    	my_email.setText("이메일 : "+Login.member.getM_email());
    	my_add.setText("주소 : "+Login.member.getM_address());
    	my_point.setText("point : " + Login.member.getM_point() + "점");
    	my_date.setText("가입일 : "+Login.member.getM_since());
    }
    
}
