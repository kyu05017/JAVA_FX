package controllor.board;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controllor.Main;
import controllor.home.Home;
import controllor.login.Login;
import dao.BoardDao;
import dao.MemberDao;
import dto.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class BoardView implements Initializable{
	
	
	
	@FXML
    private Button btback;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btrewrite1;

    @FXML
    private TextField txttitle;

    @FXML
    private Button btdelete;

    @FXML
    private Button btupdate;

    @FXML
    private Label lblwriter;

    @FXML
    private Label blbdate;

    @FXML
    private Label lblview;

    @FXML
    private TextArea txtrecontents;

    @FXML
    private TableView<?> retalbe;

    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/board/board");
    }

    @FXML
    void delete(ActionEvent event) {
    	// 1. 메세지 설정
    			Alert alert = new Alert(AlertType.CONFIRMATION); // 확인,취소 버튼 타입
    			alert.setHeaderText("정말 삭제 하시겠습니까?");
    			// 2. 버튼 확인 [ Optional 클래스 ]
    			Optional<ButtonType> optional = alert.showAndWait();
    			if(optional.get() == ButtonType.OK) { // ok를 늘렀을때ㅑ 회원 탈퇴
    				System.out.println("삭제");
    				boolean result =  BoardDao.dao.delete(controllor.board.Board.board.getB_num());
    				
    				if(result) {
    					// 페이지 전황
    					Home.home.loadpage("/view/board/board");
    					Alert alert2 = new Alert(AlertType.INFORMATION);
    		    		alert2.setTitle("글삭제");
    		    		alert2.setHeaderText("게시글 삭제가 완료 되었습니다.");
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

    @FXML
    void rewrite(ActionEvent event) {
    	
    }

    @FXML
    void update(ActionEvent event) {
    	
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		Board board = controllor.board.Board.board; // 보드 컨트롤러내 테이블에서 선택된 객체를 호출
		
		lblwriter.setText("작성자 : "+board.getB_writer());
		blbdate.setText("작성일 : "+board.getB_date());
		lblview.setText("조회수 : "+board.getB_view());
		
		txttitle.setText(board.getB_title());
		txtcontents.setText(board.getB_contents());
		
		if(!board.getB_writer().equals(Login.member.getM_id())) {
			btdelete.setVisible(false); // 버튼 숨기기
			btupdate.setVisible(false); // true == 보이기
		}
		// 텍스트 수정 못하게 잠금 처리
		txttitle.setEditable(false);
		txtcontents.setEditable(false);
		
		
	}
}
