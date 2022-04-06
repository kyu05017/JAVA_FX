package controllor.home;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.board.Board;
import controllor.login.Login;
import dao.BoardDao;
import dao.ProductDao;
import dto.MemberView;
import dto.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyBoard implements Initializable{
	
	@FXML
    private TableView<dto.Board> myboard;
	
	@FXML
    private TableView<Product> myitem;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<dto.Board> boardlist = BoardDao.dao.my_boardlist(Login.member.getM_id());
		
		TableColumn<?, ?> tc = myboard.getColumns().get(0); 
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));
		
		tc = myboard.getColumns().get(1); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		
		tc = myboard.getColumns().get(2); // 세번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));

		myboard.setItems(boardlist);

		myboard.setOnMouseClicked( e -> {
			try {
			// 0. 클릭한 객체 객체로 저장
			Board.board = myboard.getSelectionModel().getSelectedItem();
			// 1. 조회수 증가
			
			MemberView view = new MemberView(Login.member.getM_id(),Board.board.getB_num(),"yyyy-MM-dd");
			Board.m_view.add(view);

			BoardDao.dao.view(Board.board.getB_num(), Board.board.getB_view(),Login.member.getM_id());
			// 2. 페이지 저장 
			// 3. 페이지 전환
			Home.home.loadpage("/view/board/boardview");
			}
			catch(Exception e2) {
				System.out.println("게시물 없음" + e2);
			}
		});
		
		ObservableList<Product> my_boardlist = ProductDao.dao.my_itemlist(Login.member.getM_num());
		
		TableColumn<?, ?> tc2 = myitem.getColumns().get(0); 
		tc2.setCellValueFactory(new PropertyValueFactory<>("p_date"));
		
		tc2 = myitem.getColumns().get(1); // 두번째 열 호출
		tc2.setCellValueFactory(new PropertyValueFactory<>("p_name"));
		
		tc2 = myitem.getColumns().get(2); // 세번째 열 호출
		tc2.setCellValueFactory(new PropertyValueFactory<>("p_money"));
		
		tc2 = myitem.getColumns().get(3); // 세번째 열 호출
		tc2.setCellValueFactory(new PropertyValueFactory<>("p_condition"));
		
		myitem.setItems(my_boardlist);

	}
}
