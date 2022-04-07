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
	
	// *  �ش� Ŭ������ ��ȯ �޸𸮸� ��ȯ ���ִ� �޼ҵ� 
	public static Login login;
	// * ������
	public Login() {login = this;}

	// �α��� ������ ��ü [ �ٸ� Ŭ�������� ȣ���ϱ� ���� ]
	public static Member member;
	
	
	@FXML 
	private MediaView mediaview;
	 
	@FXML
    private BorderPane boaderpane; 
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 1. ������ �����ϱ�
			// 1. ������ ���� ��üȭ
		Media media = new Media(getClass().getResource("/img/login5.mp4").toString());
			// 2. �̵���÷��̾� ��ü�� ������ �ֱ�
		MediaPlayer mediaPlayer = new MediaPlayer(media);
			// 3. ���̴� �÷��̾ �̵�� �ֱ�
		mediaview.setMediaPlayer(mediaPlayer); 
		// ���ѹݺ� [ �̵� �������� ] 
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
				
			}
		});
		// �̵�� �÷��̾� ����
		mediaPlayer.play();
		
		 
		loadpage("/view/login/loginpane");
		
	}
	
	
	public void loadpage( String page ) { // loadpage ( ���ϰ�� )
		
		try {
			// ������(fxml) ��üȭ 
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml") ); // ������ ����ó��
			boaderpane.setCenter(parent); // �����̳�(fxml) ����� ������ �ֱ� 
		}
		catch( Exception e ) { // ������ �������� ���� ��� ����ó��
			System.out.println("[�α��� ȭ�� �ε� ����] ���� :  " + e);
		}
	}
}