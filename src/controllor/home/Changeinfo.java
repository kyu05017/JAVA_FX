package controllor.home;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.Main;
import controllor.login.Login;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;


public class Changeinfo implements Initializable{
	@FXML
    private TextField txtadd;
	
	@FXML
	private MediaView homeview;
	
    @FXML
    private TextField txtemail;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Media media = new Media(getClass().getResource("/img/login5.mp4").toString());
		// 2. 미디어플레이어 객체에 동영상 넣기
		MediaPlayer mediaPlayer = new MediaPlayer(media);
			// 3. 미이더 플레이어에 미디어 넣기
		homeview.setMediaPlayer(mediaPlayer); 
		// 무한반복
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
				
			}
		});
		// 미디어 플레이어 시작
		mediaPlayer.play();
		txtemail.setText(Login.member.getM_email());
		txtadd.setText(Login.member.getM_address());
		
	}
	@FXML
	private Button btok;

 
	@FXML
    void accchange(ActionEvent event) {
		
    	System.out.println("정보수정");
    	// 1. 컨트롤에 입력된 값 가져오기
    	String add = txtadd.getText(); // 해당 fxid에 입력된 값 가져오디
    	String email = txtemail.getText(); // 해당 fxid에 입력된 값 가져오디
    	Alert alert = new Alert(AlertType.INFORMATION);
   	
    	// 2. db 객체내 메소드 호출
    	boolean result = MemberDao.dao.changeInfo(Login.member.getM_num(),add, email);
    	
    	if(result) {
    		// 메세지 출력
    		System.out.println("수정완료");
    		alert.setTitle("회원정보 수정");
    		alert.setHeaderText("이메일과 주소가 수정되었습니다. [ 다시 로그인 하세요. ]");
    		alert.setContentText("완료");
    		alert.showAndWait();
    		Login.member = null;
    		Main.main.loadpage("/view/login/login");
    		System.out.println("로그아웃");
    	}
    	else {
    		System.out.println("수정실패");
    		alert.setTitle("회원정보 수정");
    		alert.setHeaderText("수정 실패. [ 관리자 문의 ]");
    		alert.setContentText("완료");
    		alert.showAndWait();
    		
    	}

    }
    
}
