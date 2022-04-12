package controllor.product;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import controllor.home.Home;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProductControl implements Initializable{
	
	@FXML
    private Button btaddp;
	
	@FXML
    private Text txtmenu;
	
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;
    
    @FXML
    private TextField txtsearch;

    @FXML
    private Button btsearch;


    @FXML
    void accsearch(ActionEvent event) {
    	String search = txtsearch.getText();
    	show(search);
    	
    }
    void show(String search) {
    	
    	if( vbox.getChildren().isEmpty() == false) { //isEmpty()  비어있을 경우를 확인 [ vbox내 비어있느지 확인 ]
    		// vbox 내 객체가 비어있지 않으면
    		vbox.getChildren().remove(0); // vbox
    	}
    	// 1. 모든 제품 가져오기
		ArrayList< Product > productlist = ProductDao.dao.plist(Home.category,search);
		
		// 2. 그리드 클래스 [ 행/열 ]
		GridPane gridPane = new GridPane();
			// * 그리드 여백
			gridPane.setPadding( new Insets(10) );
			// * 그리드내 셀 여백 
			gridPane.setHgap(20); // 세로여백 
			gridPane.setVgap(20); // 가로여백
		if(Home.category != null) {
			if(Home.category.equals("여성의류")) {
				txtmenu.setText("중고제품 : 여성의류");
			}
			if(Home.category.equals("생활용품")) {
				txtmenu.setText("중고제품 : 생활용품");
			}
			if(Home.category.equals("전자제품")) {
				txtmenu.setText("중고제품 : 전자제품");
			}
			if(Home.category.equals("남성의류")) {
				txtmenu.setText("중고제품 : 남성의류");
			}
		}
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
						select = productlist.get(id);
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
						select = productlist.get(id);
						Home.home.loadpage("/view/product/productview");
					} );	
				gridPane.add( button  , col , row+1 ); // 마지막행에 나머지값 만큼 버튼 추가해서 그리드에 추가
				i++; //인덱스 증가
			}
		}
		// 4. vbox에 그리도 넣기 
		vbox.getChildren().add(gridPane);
    		
    }
    
    @FXML
    void accadd(ActionEvent event) {
    	Home.home.loadpage("/view/product/productadd");
    }
    
    public static Product select;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	show(null);
    }
		
		
}


