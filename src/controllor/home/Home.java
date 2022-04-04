package controllor.home;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.Main;
import controllor.login.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Home implements Initializable{
	
	
	@FXML
	private Label lbloginid;
	
	@FXML
	private Label lbloginpt;
	
	@FXML
	private Label lblogout;
	
	@FXML
	private Label lblsignOut;
	
	@FXML
	public void logout(MouseEvent e ) {
		// 1. 로그인 정보 지우기
		Login.member = null;
		Main.main.loadpage("/view/login/login");
	}
	
	@FXML
	public void signOut(MouseEvent e ) {
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbloginid.setText("ID : "+Login.member.getM_id());
		lbloginpt.setText("point : " + Login.member.getM_point() + "점");
		
	}
}
