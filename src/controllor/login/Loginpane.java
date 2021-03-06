package controllor.login;


import java.net.URL;
import java.util.ResourceBundle;
import controllor.Main;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Loginpane implements Initializable{
	
	public static Loginpane loginpane;
	
	public Loginpane() {
		loginpane = this; 
	}
	
    @FXML
    private TextField textid;

    @FXML
    private PasswordField textpw;

    @FXML
    private Button btlogin;

    @FXML
    private Button btsignup;

    @FXML
    private Button btfindpw;

    @FXML
    private Button btfindid;

    @FXML
    private Label lbnconform;

    @FXML
    void accfindip(ActionEvent event) {
    	System.out.println("아이디 찾기");
    	Login.login.loadpage("/view/login/findid"); 
    }

    @FXML
    void accfindpw(ActionEvent event) {
    	System.out.println("비밀번호 찾기"); 
    	Login.login.loadpage("/view/login/findpw");
    }

    @FXML
    void accsignup(ActionEvent event) {
    	// login 컨트롤에 존재하는 boarderpane 객체내 센터 변경
    		// * 문제 : 보더팬 객체다 다른곳에 있다
    		// * new 는 새로운 메모리 할당 [ 기존 보더가 아닌 새로운 보다 ] 
    		// * 기존이 login 클래스에서 사용중인 boaderpane 사용
    	
    	System.out.println("회원가입");
    	Login.login.loadpage("/view/login/signuppane");
    }
    @FXML
    void actlogin(ActionEvent event) {
    	System.out.println("로그인");
    	// 1. 컨트롤에 입력된 값 가져오기
    	String id = textid.getText(); // 해당 fxid에 입력된 값 가져오디
    	String pw = textpw.getText(); // 해당 fxid에 입력된 값 가져오디
    	
   	
    	// 2. db 객체내 메소드 호출
    	boolean result = MemberDao.dao.login(id, pw);
    	
 
    
    	// 3. 결과확인
    	if(result) {
    		// 페이지 전환 [ 다음주 ]
    		// * 테스트
    		
    		// 로그인 성공시 성공한 회원정보 저장 / 로그아웃시 초기화
    		Login.member = MemberDao.dao.getmember(id);
    		
    		lbnconform.setText("로그인성공");
    		Main.main.loadpage("/view/home/home");
    		System.out.println(Login.member.getM_today());
    		System.out.println(Login.member.getM_since());

    	}
    	else {
    		lbnconform.setText("로그인실패");
    	}
    }
    @FXML
    void login(ActionEvent event) {
    	System.out.println("로그인");
    	// 1. 컨트롤에 입력된 값 가져오기
    	String id = textid.getText(); // 해당 fxid에 입력된 값 가져오디
    	String pw = textpw.getText(); // 해당 fxid에 입력된 값 가져오디
    	
   	
    	// 2. db 객체내 메소드 호출
    	boolean result = MemberDao.dao.login(id, pw);
    	
 
    
    	// 3. 결과확인
    	if(result) {
    		// 페이지 전환 [ 다음주 ]
    		// * 테스트
    		
    		// 로그인 성공시 성공한 회원정보 저장 / 로그아웃시 초기화
    		Login.member = MemberDao.dao.getmember(id);
    		
    		lbnconform.setText("로그인성공");
    		Main.main.loadpage("/view/home/home");
    		System.out.println(Login.member.getM_today());
    		System.out.println(Login.member.getM_since());

    	}
    	else {
    		lbnconform.setText("로그인실패");
    	}
    	
    }

    public String getid() {
    	return textid.getText();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbnconform.setText("");
	}
	
	
}
