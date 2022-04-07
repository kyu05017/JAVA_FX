package controllor.login;

import java.net.URL;
import java.util.ResourceBundle;

import dto.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Login implements Initializable{
	
	// *  해당 클래스를 반환 메모리를 반환 해주는 메소드 
	public static Login login;
	// * 생성자
	public Login() {login = this;}

	// 로그인 성공한 객체 [ 다른 클래스에서 호출하기 위해 ]
	public static Member member;
	
	
	@FXML 
	private MediaView mediaview;
	 
	@FXML
    private BorderPane boaderpane; 
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 1. 동영상 삽입하기
			// 1. 동영상 파일 객체화
		Media media = new Media(getClass().getResource("/img/login5.mp4").toString());
			// 2. 미디어플레이어 객체에 동영상 넣기
		MediaPlayer mediaPlayer = new MediaPlayer(media);
			// 3. 미이더 플레이어에 미디어 넣기
		mediaview.setMediaPlayer(mediaPlayer); 
		// 무한반복 [ 미디어가 끝났을때 ] 
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
				
			}
		});
		// 미디어 플레이어 시작
		mediaPlayer.play();
		
		 
		loadpage("/view/login/loginpane");
		
	}
	
	
	public void loadpage( String page ) { // loadpage ( 파일경로 )
		
		try {
			// 페이지(fxml) 객체화 
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml") ); // 무조건 예외처리
			boaderpane.setCenter(parent); // 컨테이너(fxml) 가운데에 페이지 넣기 
		}
		catch( Exception e ) { // 파일이 존재하지 않을 경우 예외처리
			System.out.println("[로그인 화면 로딩 실패] 사유 :  " + e);
		}
	}
}