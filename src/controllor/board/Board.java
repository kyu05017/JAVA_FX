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

	public static dto.Board board; // 테이블에서 클릭한 객체를 저장한 객체
	
    @FXML
    private TableView<dto.Board> boardtable; // 테이블에 넣을 자료형 선택 [ 테이블에 게시물 표시하기 위해 ] 
    
    @FXML
    private Button btwrite;

    @FXML
    void addwrite(ActionEvent event) {
    	Home.home.loadpage("/view/board/boardwrite");
    }
	    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//ArrayList<E>가 아닌 ObservableList를 사용 하는 이유 [ TableView가 사용 ]
		// 1. DB 에서 모든 게시물 가져오기
		ObservableList<dto.Board> boardlist = BoardDao.dao.list();
		System.out.println(boardlist);
		// 2. TableView에 추가
		TableColumn<?, ?> tc = boardtable.getColumns().get(0); // 첫번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("b_num"));
		
		tc = boardtable.getColumns().get(1); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		
		tc = boardtable.getColumns().get(2); // 세번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("b_writer"));

		tc = boardtable.getColumns().get(3); // 네번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));

		tc = boardtable.getColumns().get(4); // 다섯번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));
		
		// 3. TableView에 list 연결
		boardtable.setItems(boardlist);
		// 테이블명(fxid).settime ( ObservableList )
		
		
		
		// * TableView 에서 해당 셀을 크릭 했을때 이벤트 발생 가능
		//boardtable.setOnMouseClicked( e -> { 실행 코드 });; 클릭 했을때 
		boardtable.setOnMouseClicked( e -> {
			try {
			System.out.println("클릭"+e);
			// 0. 클릭한 객체 객체로 저장
			board = boardtable.getSelectionModel().getSelectedItem();
			// 1. 조회수 증가
			BoardDao.dao.view(board.getB_num(), board.getB_view());
			// 2. 페이지 저장 
			// 3. 페이지 전환
			
			Home.home.loadpage("/view/board/boardview");
			}
			catch(Exception e2) {
				System.out.println("게시물 없음" + e2);
			}
		});
		
		// [ 일회용 ] 인수 -> 실행코드 // 람다식 ( 익명 함수 ) : 이름이 없는 함수 [ 인수와 실행코드 ]
		// VS
		// [ 재활용 ] void 함수명(인수) { 실행코드 }: 함수
	}  
}
