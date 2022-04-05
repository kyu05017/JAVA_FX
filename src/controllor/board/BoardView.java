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

    // * ��� ���̺� 
    public void replyshow() {
    	
    	ObservableList<Reply> replylist = BoardDao.dao.reply_list(controllor.board.Board.board.getB_num());
		
		TableColumn<?, ?> tc = retalbe.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_num"));
		
		tc = retalbe.getColumns().get(1); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("r_writerr"));
		
		tc = retalbe.getColumns().get(2); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("r_contents"));
		
		tc = retalbe.getColumns().get(3); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("r_date"));
		retalbe.setItems(replylist);
		
		
    }
    
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
		//Optional Ŭ���� == null���� ��ü�� �����ϴ� Ŭ����
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
				System.out.println("���� �����ߵ�");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("���");
		}
    }
    @FXML
    void redel(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ��,��� ��ư Ÿ��
		alert.setHeaderText("���� ���� �Ͻðڽ��ϱ�?");
		// 2. ��ư Ȯ�� [ Optional Ŭ���� ]
		Optional<ButtonType> optional = alert.showAndWait();
		//Optional Ŭ���� == null���� ��ü�� �����ϴ� Ŭ����
		if(optional.get() == ButtonType.OK) { // ok�� �÷������� ȸ�� Ż��
			System.out.println("����");
			boolean result =  BoardDao.dao.reply_delete(reply.getR_num());
			
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
				System.out.println("���� �����ߵ�");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("���");
		}
    }

    @FXML
    void rewrite(ActionEvent event) {
    	
    	String reply_contents = txtrecontents.getText();
    	
    	if(txtrecontents.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("������ �Է����ּ���.");
    		alert.showAndWait();
    	}
    	else {
    		Reply reply = new Reply(0, reply_contents, Login.member.getM_id(), null,controllor.board.Board.board.getB_num());
        	
        	boolean result =  BoardDao.dao.reply_write(reply);
        	
        	if(result) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("��� �ۼ��� �Ϸ� �Ǿ����ϴ�.");
        		alert.showAndWait();
        		replyshow();
        	}
    	}
    }
    
    boolean upcheck = true; // ���� ��ư ����ġ ��
    @FXML
    void update(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION); // 1. �޼��� ���
    	
    	if(upcheck) {
    		alert.setHeaderText("�Խñ� ������ ���� �Ϸ� ��ư�� �����ּ���.");
    		alert.showAndWait();
    		txttitle.setEditable(true);
			txtcontents.setEditable(true);
			btupdate.setText("�����Ϸ�");
	    	upcheck = false;
    	}
    	else {

	    	boolean result = BoardDao.dao.updaete(controllor.board.Board.board.getB_num(),txttitle.getText(),txtcontents.getText());
	    	
	    	if(result) {
	    		alert.setHeaderText("������ �Ϸ� �Ǿ����ϴ�.");
				alert.showAndWait();
				txttitle.setEditable(false);
				txtcontents.setEditable(false);
				Home.home.loadpage("/view/board/board");
				upcheck = true;
	    	}
    	}
    }
    boolean re_upcheck = true; // ���� ��ư ����ġ ��
    @FXML
    void reupdate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION); // 1. �޼��� ���
    	
    	if(re_upcheck) {
    		alert.setHeaderText("��� ������ ���� �Ϸ� ��ư�� �����ּ���.");
    		alert.showAndWait();
			re_upcheck = false;
    	}
    	else {

	    	boolean result = BoardDao.dao.re_updaete(reply.getR_num(),txtrecontents.getText());
	    	
	    	if(result) {
	    		alert.setHeaderText("������ �Ϸ� �Ǿ����ϴ�.");
				alert.showAndWait();
				re_upcheck = true;
	    	}
    	}
    }
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	replyshow();
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
		
		// �ؽ�Ʈ ���� ���ϰ� ��� ó��
		txttitle.setEditable(false);
		txtcontents.setEditable(false);
		
		

	}
}
