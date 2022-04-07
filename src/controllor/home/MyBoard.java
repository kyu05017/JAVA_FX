package controllor.home;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import controllor.board.Board;
import controllor.login.Login;
import controllor.product.ProductControl;
import dao.BoardDao;
import dao.ProductDao;
import dto.MemberView;
import dto.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MyBoard implements Initializable{
	
	@FXML
    private TableView<dto.Board> myboard;
	
	@FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;
	
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
		
		ArrayList< Product > productlist = ProductDao.dao.my_itemlist(Login.member.getM_num());
		
		// 2. 그리드 클래스 [ 행/열 ]
		GridPane gridPane = new GridPane();
			// * 그리드 여백
			gridPane.setPadding( new Insets(10) );
			// * 그리드내 셀 여백 
			gridPane.setHgap(20); // 세로여백 
			gridPane.setVgap(20); // 가로여백
			
		// 3. 반복문 
		int i = 0 ; // 인덱스용 변수
		for( int row = 0 ; row < productlist.size() /3; row++ ) { // 행
			for( int col = 0 ; col<3 ; col++ ) { // 열
				// 1. 이미지
				ImageView imageView = new ImageView( new Image(productlist.get(i).getP_img()));
					// *이미지 사이즈 
					imageView.setFitWidth(250);		// 이미지 가로길이 
					imageView.setFitHeight(200); 	// 이미지 세로길이
				// 2. 버튼 생성 ( 버튼에 이미지 넣기 )
				Button button = new Button( null , imageView );
					// *버튼 배경제거 [ transparent : 투명색 ]
					button.setStyle("-fx-background-color:transparent");
					// *버튼 id값넣기 [ 제품 식별 = index ]
					button.setId( i+"" );
					// *버튼 클릭이벤트
						//	button.setOnAction( e -> { } ); // 람다식 : 인수 -> { 실행코드 }
					button.setOnAction( e -> { 
						// 1. 클릭한 버튼의 id 가져오기
						int id = Integer.parseInt(  e.toString().split(",")[0].split("=")[2] );
						// 2. 클릭한 제품 번호 저장 
						ProductControl.select = productlist.get(id);
						Home.home.loadpage("/view/product/productview");
					} );	
				gridPane.add( button  , col , row); // 그리드내  해당 열번호 , 행번호 에 버튼 추가
				
				i++; //인덱스 증가
			}
		}
		// * 3배수의 나머지값 ㅇㄹㄴㅇㄹㄴㄴㄴ
		int row = productlist.size() / 3;		// 행
		int remain = productlist.size() % 3 ;	// 마지막행의 나머지 
		if( remain != 0  ) { // 나머지가 존재하면 
			for( int col = 0 ; col<remain ; col++ ) { // 열
				ImageView imageView = new ImageView( new Image(productlist.get(i).getP_img() ) );
					imageView.setFitWidth(250);		// 이미지 가로길이 
					imageView.setFitHeight(200); 	// 이미지 세로길이
				Button button = new Button( null , imageView );
					button.setStyle("-fx-background-color:transparent");
					button.setId( i+"" );
					button.setOnAction( e -> { 
						int id = Integer.parseInt(  e.toString().split(",")[0].split("=")[2] );
						ProductControl.select = productlist.get(id);
						Home.home.loadpage("/view/product/productview");
					} );	
				gridPane.add( button  , col , row+1 ); // 마지막행에 나머지값 만큼 버튼 추가해서 그리드에 추가
				i++; //인덱스 증가
			}
		}
		// 4. vbox에 그리도 넣기 
		vbox.getChildren().add(gridPane);
	}
}
