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
import javafx.scene.control.cell.PropertyValueFactory;

public class Board implements Initializable{

    @FXML
    private TableView<dto.Board> boardtable; // ���̺� ���� �ڷ��� ���� [ ���̺� �Խù� ǥ���ϱ� ���� ] 
    
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
		TableColumn tc = boardtable.getColumns().get(0); // ù��° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("b_num"));
		
		tc = boardtable.getColumns().get(1); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		
		tc = boardtable.getColumns().get(2); // ����° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("b_writer"));

		tc = boardtable.getColumns().get(3); // �׹�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));

		tc = boardtable.getColumns().get(4); // �ټ���° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));
		
		// 3. TableView�� list ����
		boardtable.setItems(boardlist);
		// ���̺��(fxid).settime ( ObservableList )
	}  
}
