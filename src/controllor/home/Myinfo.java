package controllor.home;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

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
    
    @FXML
    private Button btok;

    @FXML
    void accchange(ActionEvent event) {
    	Home.home.loadpage("/view/home/changeinfo");
    }
    
    @FXML
    private MediaView homeview;

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
    	my_id.setText("ID : "+Login.member.getM_id());
    	my_email.setText("이메일 : "+Login.member.getM_email());
    	my_add.setText("주소 : "+Login.member.getM_address());
    	my_point.setText("point : " + Login.member.getM_point() + "점");
    	my_date.setText("가입일 : "+Login.member.getM_since());
    }
    
}
