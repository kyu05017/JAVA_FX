package controllor.home;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

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
    private MediaView homeview;
	
	@FXML
	public void logout(MouseEvent e ) {
		// 1. �α��� ���� �����
		Login.member = null;
		Main.main.loadpage("/view/login/login");
		System.out.println("�α׾ƿ�");
	}
	
	@FXML
	public void signOut(MouseEvent e ) {
		// 1. �޼��� ����
		Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ��,��� ��ư Ÿ��
		alert.setHeaderText("���� Ż�� �Ͻðڽ��ϱ�?");
		// 2. ��ư Ȯ�� [ Optional Ŭ���� ]
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.OK) { // ok�� �÷������� ȸ�� Ż��
			System.out.println("Ż��");
			boolean result =  MemberDao.dao.signOut(Login.member.getM_num());
			
			if(result) {
				// �α׾ƿ�
				Login.member = null;
				// ������ ��Ȳ
				Main.main.loadpage("/view/login/login");
				Alert alert2 = new Alert(AlertType.INFORMATION);
	    		alert2.setTitle("ȸ��Ż��");
	    		alert2.setHeaderText("�䳢�� �߰�ŷ��� �׵��� �̿��� �ּż� �����մϴ�.");
	    		alert2.setContentText("�Ϸ�");
	    		alert2.showAndWait();
			}
			else {
				
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("���");
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
		lbloginpt.setText("point : " + Login.member.getM_point() + "��");
		Media media = new Media(getClass().getResource("/img/login5.mp4").toString());
		// 2. �̵���÷��̾� ��ü�� ������ �ֱ�
		MediaPlayer mediaPlayer = new MediaPlayer(media);
			// 3. ���̴� �÷��̾ �̵�� �ֱ�
		homeview.setMediaPlayer(mediaPlayer); 
		// ���ѹݺ�
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
				
			}
		});
		// �̵�� �÷��̾� ����
		mediaPlayer.play();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String since = sdf.format(new Date());
		if(Login.member.getM_today().equals(since)) {
			
		}
		else {
			boolean result2 = MemberDao.dao.todayPoint(Login.member.getM_num(),Login.member.getM_point());
			if(result2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				System.out.println("�α��� ����Ʈ");
	    		alert.setTitle("�湮 ����Ʈ ����");
	    		alert.setHeaderText("���� ó�� �����ϼż� 10 ����Ʈ�� �����Ǿ����ϴ�.");
	    		alert.setContentText("�Ϸ�");
	    		alert.showAndWait();
			}
		}
		MemberDao.dao.todaylogin(Login.member.getM_id());
	}
	
	public void loadpage( String page ) { // loadpage ( ���ϰ�� )
		
		try {
			// ������(fxml) ��üȭ 
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml") ); // ������ ����ó��
			mainpane.setCenter(parent); // �����̳�(fxml) ����� ������ �ֱ� 
		}
		catch( Exception e ) { // ������ �������� ���� ��� ����ó��
			System.out.println("������ ���� ����" + e);
		}
	}
}
