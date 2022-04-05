package controllor.board;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.home.Home;
import dao.BoardDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Board implements Initializable{

    @FXML
    private TableView<dto.Board> boardtable;
    
    @FXML
    private TableColumn<dto.Board, Integer> bnum;

    @FXML
    private TableColumn<?, ?> btitle;

    @FXML
    private TableColumn<dto.Board, String> bwriter;

    @FXML
    private TableColumn<dto.Board, String> bdate;

    @FXML
    private TableColumn<dto.Board, Integer> bview;
    
    @FXML
    private Button btwrite;

    @FXML
    void addwrite(ActionEvent event) {
    	Home.home.loadpage("/view/board/boardwrite");
    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//ArrayList<E>�� �ƴ� ObservableList�� ��� �ϴ� ���� [ TableView�� ��� ]
		// 1. DB ���� ��� �Խù� ��������
		ObservableList<dto.Board> boardlist = BoardDao.dao.list();
		System.out.println(boardlist);
		// 2. TableView�� �߰�

	}  
}
