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
import javafx.scene.control.Button;
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
    		// 2. 아이디 형식
    		// 3. 비밀번호 형식 체크
    		// 4. 비밀번호 일치
    		// 5. 이메일 체크
    		// 6. 주소 체크
    	// * 모든 유효성 검사를 통과시 DB에 저장 
    		// 1. 객체화 [ 회원번호 없음, id, pw, 이메일, 주소, 포인트, 가입일 ]
    	Member member = new Member(0, id, pw, email, address, 0, since);
    		// 2. DB 객체내 메소드 호출
    	boolean result = MemberDao.dao.signup(member);
    	
    	if(result) {
    		System.out.println("회원가입 성공");
    	}
    	else {
    		System.out.println("회원가입 실패");
    	}
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
