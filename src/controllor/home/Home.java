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
import javafx.scene.image.ImageView;
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
    private ImageView bthome;
	
	@FXML
	private Label lbladdp;
	
	@FXML
	private Label bthome2;
	
	@FXML
	private Label lblproduct;
	
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
	private Label chattng;
	@FXML
    void actchat(MouseEvent event) {
		loadpage("/view/chatting");
    }
	public static String category;
	//여성의류
	@FXML
	private Label lblwc;
	@FXML
	public void accwc(MouseEvent event) {
		category = "여성의류";
		loadpage("/view/product/product");
		
	}
	// 생활용훔
	@FXML
	private Label lblli;
	@FXML
	public void accli(MouseEvent event) {
		category = "생활용품";
		loadpage("/view/product/product");
		
	}
	// 전자기기
	@FXML
	private Label lbldg;
	@FXML
	public void accdg(MouseEvent event) {
		category = "전자제품";
		loadpage("/view/product/product");
		
	}
	// 남성의류
	@FXML
	private Label lblmc;
	@FXML
	public void accid(MouseEvent event) {
		loadpage("/view/home/myinfo");
	}
	@FXML
	public void accmc(MouseEvent event) {
		category = "남성의류";
		loadpage("/view/product/product");
		
	}
    @FXML
    private MediaView homeview;
	
    @FXML
    private Label lblboard;
    @FXML
    private Label lblmyboard;
    @FXML // 
    public void myboard(MouseEvent event) {loadpage("/view/home/myboard");}
    @FXML // 
    public void addp(MouseEvent event) {loadpage("/view/product/productadd");}
    @FXML // 남자의류 이동
    public void accproduct(MouseEvent event) {
    	category = null;
    	loadpage("/view/product/product");
    }
    @FXML // 홈화면 이동
    public void tohome(MouseEvent event) {Main.main.loadpage("/view/home/home");}
    @FXML // 홈화면 이동
    public void tohome2(MouseEvent event) {Main.main.loadpage("/view/home/home");}
    @FXML // 내정보 이동
	public void myinfo(MouseEvent e ) {loadpage("/view/home/myinfo");}
	@FXML // 정보수정 이동
	public void change(MouseEvent e ) {loadpage("/view/home/changeinfo");}
	@FXML // 게시판 이동
	public void boardacc(MouseEvent e ) {loadpage("/view/board/board");}
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String since = sdf.format(new Date());
    	
		if(Login.member.getM_today().equals(since)) {
			
		}
		else {
			boolean result2 = MemberDao.dao.todayPoint(Login.member.getM_num(),Login.member.getM_point());
			if(result2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				System.out.println("로그인 포인트");
	    		alert.setTitle("방문 포인트 적립");
	    		alert.setHeaderText("오늘 처음 접속하셔서 10 포인트가 적립되었습니다.");
	    		alert.setContentText("완료");
	    		alert.showAndWait();
			}
		}
		MemberDao.dao.todaylogin(Login.member.getM_id());
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
