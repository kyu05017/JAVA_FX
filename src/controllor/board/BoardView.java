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
    	// 1. �޼��� ����
    			Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ��,��� ��ư Ÿ��
    			alert.setHeaderText("���� ���� �Ͻðڽ��ϱ�?");
    			// 2. ��ư Ȯ�� [ Optional Ŭ���� ]
    			Optional<ButtonType> optional = alert.showAndWait();
    			if(optional.get() == ButtonType.OK) { // ok�� �÷������� ȸ�� Ż��
    				System.out.println("����");
    				boolean result =  BoardDao.dao.delete(controllor.board.Board.board.getB_num());
    				
    				if(result) {
    					// ������ ��Ȳ
    					Home.home.loadpage("/view/board/board");
    					Alert alert2 = new Alert(AlertType.INFORMATION);
    		    		alert2.setTitle("�ۻ���");
    		    		alert2.setHeaderText("�Խñ� ������ �Ϸ� �Ǿ����ϴ�.");
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
    void rewrite(ActionEvent event) {
    	
    }

    @FXML
    void update(ActionEvent event) {
    	
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		Board board = controllor.board.Board.board; // ���� ��Ʈ�ѷ��� ���̺��� ���õ� ��ü�� ȣ��
		
		lblwriter.setText("�ۼ��� : "+board.getB_writer());
		blbdate.setText("�ۼ��� : "+board.getB_date());
		lblview.setText("��ȸ�� : "+board.getB_view());
		
		txttitle.setText(board.getB_title());
		txtcontents.setText(board.getB_contents());
		
		if(!board.getB_writer().equals(Login.member.getM_id())) {
			btdelete.setVisible(false); // ��ư �����
			btupdate.setVisible(false); // true == ���̱�
		}
		// �ؽ�Ʈ ���� ���ϰ� ��� ó��
		txttitle.setEditable(false);
		txtcontents.setEditable(false);
		
		
	}
}
