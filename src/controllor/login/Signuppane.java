package controllor.login;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signuppane implements Initializable{

	@FXML
    private TextField textid;

    @FXML
    private PasswordField textpw;

    @FXML
    private Button btback;

    @FXML
    private Button btsignup;

    @FXML
    private PasswordField textpwcheck;

    @FXML
    private TextField textaddress;

    @FXML
    private TextField textemail;

    @FXML
    private Label lbltext;

    @FXML
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/login/loginpane");
    	System.out.println("뒤로가기");
    }

    @FXML
    void signup(ActionEvent event) {	
    	System.out.println("회원가입");
    	// * 컨트롤에 입력된 데이터 가져오기 [ fxid명.gettext ] 
    	String id = textid.getText();
    	String pw = textpw.getText();
    	String pwcheck = textpwcheck.getText();
    	String address = textaddress.getText();
    	String email = textemail.getText();
    	
    	// 현재 날짜 가져오기
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String since = sdf.format(new Date());
    	
    	// * 유효성 검사 [ 제한 ]
    		// 1. 아이디 중복체크
    	Boolean result2 = MemberDao.dao.idcheck(id);
    	if(result2) {
    		lbltext.setText("[알림] 사용중인 아이디 입니다.");
    		return;
    	}
    		// 2. 아이디 형식
    	if(id.length() < 4 || id.length() > 10) {
    		lbltext.setText("[알림] 4~10자리만 가능합니다.");
    		return;
    	}
    		// 3. 비밀번호 형식 체크
    	if(pw.length() < 4 || pw.length() > 10 || pwcheck.length() < 4 || pwcheck.length() > 10) {
    		lbltext.setText("[알림] 4~10자리만 가능합니다.");
    		return;
    	}
    		// 4. 비밀번호 일치
    	if(!pw.equals(pwcheck)) { // 패스워드랑 패스워드검사가 일하지 않으면
    		lbltext.setText("[알림] 비밀번호가 일치하지 않습니다.");
    		return;
    	}
    		// 5. 이메일 체크
    	if(email.indexOf("@") == -1) {
    		lbltext.setText("[알림] 올바르지 않는 이메일 형식입니다.");
    		return;
    	}
    		// 6. 주소 체크
    	if(!address.contains("시") && address.contains("구") &&address.contains("동")) {
    		lbltext.setText("[알림] 시 , 구 ,도가 포함 되어있어야 합니다.");
    		return;
    	}
    	// * 모든 유효성 검사를 통과시 DB에 저장 
    		// 1. 객체화 [ 회원번호 없음, id, pw, 이메일, 주소, 포인트, 가입일 ]
    	Member member = new Member(0, id, pw, email, address, 0, since,"yyyy-MM-dd");
    		// 2. DB 객체내 메소드 호출
    	boolean result = MemberDao.dao.signup(member);
    	
    	if(result) {
    		// 1. 메세지 창 [ Alert : 메세지 클래스 ]
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("회원가입");
    		alert.setHeaderText("토끼 중고나라에 가입했습니다.");
    		alert.setContentText("완료");
    		alert.showAndWait();
    		// 2. 화면 전환
    		
    		Login.login.loadpage("/view/login/loginpane");
    	}
    	else {
    		lbltext.setText("[알림] DB 오류 관리자 문의");
    		return;
    	}
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbltext.setText("");
	}
}
