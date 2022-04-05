package controllor.board;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import controllor.home.Home;
import controllor.login.Login;
import dao.BoardDao;
import dto.Board;
import dto.Reply;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Reply> retalbe;
    
    @FXML
    private Button btredelete;
    
    @FXML
    private Button btreupdate;
    
    
    
    public static Reply reply;

    // * 댓글 테이블 
    public void replyshow() {
    	
    	ObservableList<Reply> replylist = BoardDao.dao.reply_list(controllor.board.Board.board.getB_num());
		
		TableColumn<?, ?> tc = retalbe.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_num"));
		
		tc = retalbe.getColumns().get(1); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("r_writerr"));
		
		tc = retalbe.getColumns().get(2); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("r_contents"));
		
		tc = retalbe.getColumns().get(3); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("r_date"));
		retalbe.setItems(replylist);
		
		
    }
    
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
		//Optional 클래스 == null값을 객체로 저장하는 클래스
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
				System.out.println("삭제 실패했데");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("취소");
		}
    }
    @FXML
    void redel(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION); // 확인,취소 버튼 타입
		alert.setHeaderText("정말 삭제 하시겠습니까?");
		// 2. 버튼 확인 [ Optional 클래스 ]
		Optional<ButtonType> optional = alert.showAndWait();
		//Optional 클래스 == null값을 객체로 저장하는 클래스
		if(optional.get() == ButtonType.OK) { // ok를 늘렀을때ㅑ 회원 탈퇴
			System.out.println("삭제");
			boolean result =  BoardDao.dao.reply_delete(reply.getR_num());
			
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
				System.out.println("삭제 실패했데");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("취소");
		}
    }

    @FXML
    void rewrite(ActionEvent event) {
    	
    	String reply_contents = txtrecontents.getText();
    	
    	if(txtrecontents.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("내용을 입력해주세요.");
    		alert.showAndWait();
    	}
    	else {
    		Reply reply = new Reply(0, reply_contents, Login.member.getM_id(), null,controllor.board.Board.board.getB_num());
        	
        	boolean result =  BoardDao.dao.reply_write(reply);
        	
        	if(result) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("댓글 작성이 완료 되었습니다.");
        		alert.showAndWait();
        		replyshow();
        	}
    	}
    }
    
    boolean upcheck = true; // 수정 버튼 스위치 변
    @FXML
    void update(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION); // 1. 메세지 출력
    	
    	if(upcheck) {
    		alert.setHeaderText("게시글 수정후 수정 완료 버튼을 눌러주세요.");
    		alert.showAndWait();
    		txttitle.setEditable(true);
			txtcontents.setEditable(true);
			btupdate.setText("수정완료");
	    	upcheck = false;
    	}
    	else {

	    	boolean result = BoardDao.dao.updaete(controllor.board.Board.board.getB_num(),txttitle.getText(),txtcontents.getText());
	    	
	    	if(result) {
	    		alert.setHeaderText("수정이 완료 되었습니다.");
				alert.showAndWait();
				txttitle.setEditable(false);
				txtcontents.setEditable(false);
				Home.home.loadpage("/view/board/board");
				upcheck = true;
	    	}
    	}
    }
    boolean re_upcheck = true; // 수정 버튼 스위치 변
    @FXML
    void reupdate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION); // 1. 메세지 출력
    	
    	if(re_upcheck) {
    		alert.setHeaderText("댓글 수정후 수정 완료 버튼을 눌러주세요.");
    		alert.showAndWait();
			re_upcheck = false;
    	}
    	else {

	    	boolean result = BoardDao.dao.re_updaete(reply.getR_num(),txtrecontents.getText());
	    	
	    	if(result) {
	    		alert.setHeaderText("수정이 완료 되었습니다.");
				alert.showAndWait();
				re_upcheck = true;
	    	}
    	}
    }
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	replyshow();
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
		
		btredelete.setVisible(false);
		
		retalbe.setOnMouseClicked( e -> {
			
			reply = retalbe.getSelectionModel().getSelectedItem();
			if(reply.getR_writerr().equals(Login.member.getM_id())) {
				btredelete.setVisible(true);
				btreupdate.setVisible(true);
				btrewrite1.setVisible(false);
				
			}
			else {
				btreupdate.setVisible(false);
				btredelete.setVisible(false);
				btrewrite1.setVisible(true);
			}

			
		});
		
		// 텍스트 수정 못하게 잠금 처리
		txttitle.setEditable(false);
		txtcontents.setEditable(false);
		
		

	}
}
