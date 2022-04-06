package controllor.board;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.home.Home;
import controllor.login.Login;
import dao.BoardDao;
import dao.MemberDao;
import dto.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardWrite implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    private Button btback;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btwrite1;

    @FXML
    private TextField txttitle;

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/board/board");
    }

    @FXML
    void addwrite(ActionEvent event) {
    	String title = txttitle.getText();
    	String contents = txtcontents.getText();
    	// 작성자 : 현재로그인된 객체는 로그인 클래스내 맴버객체에 저장되어있음
    	String writer = Login.member.getM_id();
    	if(txttitle.getText().equals("") || txtcontents.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("내용을 입력해주세요.");
    		alert.showAndWait();
    	}
    	else {
    		Board board = new Board(0, title, contents,writer, null, 0 );
    	
	    	boolean result =  BoardDao.dao.write(board);
	    	
	    	if(result) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("게시글이 작성 되었습니다. 포인트 +10점");
	    		alert.showAndWait();
	    		MemberDao.dao.todayPoint(Login.member.getM_num(),Login.member.getM_point());
	    		Home.home.loadpage("/view/board/board");
	    	}
    	}
    }
}
