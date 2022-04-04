package controllor.home;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controllor.Main;
import controllor.login.Login;
import dao.MemberDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Home implements Initializable{
	
	public static Home home;
	
	public Home() {
		home = this; 
	}
	@FXML
	private BorderPane mainpane;
	
	@FXML
	private Label lbloginid;
	
	@FXML
	private Label lbloginpt;
	
	@FXML
	private Label lblogout;
	
	@FXML
	private Label lblsignOut;
	
	@FXML
	private Label lblInfo;
	
	@FXML
	private Label lblInfoChange;
	
	@FXML
	public void logout(MouseEvent e ) {
		// 1. 로그인 정보 지우기
		Login.member = null;
		Main.main.loadpage("/view/login/login");
		System.out.println("로그아웃");
	}
	
	@FXML
	public void signOut(MouseEvent e ) {
		// 1. 메세지 설정
		Alert alert = new Alert(AlertType.CONFIRMATION); // 확인,취소 버튼 타입
		alert.setHeaderText("정말 탈퇴 하시겠습니까?");
		// 2. 버튼 확인 [ Optional 클래스 ]
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.OK) { // ok를 늘렀을때ㅑ 회원 탈퇴
			System.out.println("탈퇴");
			boolean result =  MemberDao.dao.signOut(Login.member.getM_num());
			
			if(result) {
				// 로그아웃
				Login.member = null;
				// 페이지 전황
				Main.main.loadpage("/view/login/login");
				Alert alert2 = new Alert(AlertType.INFORMATION);
	    		alert2.setTitle("회원탈퇴");
	    		alert2.setHeaderText("토끼의 중고거래를 그동안 이용해 주셔서 감사합니다.");
	    		alert2.setContentText("완료");
	    		alert2.showAndWait();
			}
			else {
				
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("취소");
		}
		
		
	}
	@FXML
	public void myinfo(MouseEvent e ) {
		loadpage("/view/home/myinfo");
	}

	@FXML
	public void change(MouseEvent e ) {
		loadpage("/view/home/changeinfo");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbloginid.setText("ID : "+Login.member.getM_id());
		lbloginpt.setText("point : " + Login.member.getM_point() + "점");
		
	}
	
	public void loadpage( String page ) { // loadpage ( 파일경로 )
		
		try {
			// 페이지(fxml) 객체화 
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml") ); // 무조건 예외처리
			mainpane.setCenter(parent); // 컨테이너(fxml) 가운데에 페이지 넣기 
		}
		catch( Exception e ) { // 파일이 존재하지 않을 경우 예외처리
			System.out.println("페이지 연결 실패" + e);
		}
	}
}
