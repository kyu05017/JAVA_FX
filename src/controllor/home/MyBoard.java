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
		
		tc = myboard.getColumns().get(1); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		
		tc = myboard.getColumns().get(2); // ����° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));

		myboard.setItems(boardlist);

		myboard.setOnMouseClicked( e -> {
			try {
			// 0. Ŭ���� ��ü ��ü�� ����
			Board.board = myboard.getSelectionModel().getSelectedItem();
			// 1. ��ȸ�� ����
			
			MemberView view = new MemberView(Login.member.getM_id(),Board.board.getB_num(),"yyyy-MM-dd");
			Board.m_view.add(view);

			BoardDao.dao.view(Board.board.getB_num(), Board.board.getB_view(),Login.member.getM_id());
			// 2. ������ ���� 
			// 3. ������ ��ȯ
			Home.home.loadpage("/view/board/boardview");
			}
			catch(Exception e2) {
				System.out.println("�Խù� ����" + e2);
			}
		});
		
		ArrayList< Product > productlist = ProductDao.dao.my_itemlist(Login.member.getM_num());
		
		// 2. �׸��� Ŭ���� [ ��/�� ]
		GridPane gridPane = new GridPane();
			// * �׸��� ����
			gridPane.setPadding( new Insets(10) );
			// * �׸��峻 �� ���� 
			gridPane.setHgap(20); // ���ο��� 
			gridPane.setVgap(20); // ���ο���
			
		// 3. �ݺ��� 
		int i = 0 ; // �ε����� ����
		for( int row = 0 ; row < productlist.size() /3; row++ ) { // ��
			for( int col = 0 ; col<3 ; col++ ) { // ��
				// 1. �̹���
				ImageView imageView = new ImageView( new Image(productlist.get(i).getP_img()));
					// *�̹��� ������ 
					imageView.setFitWidth(250);		// �̹��� ���α��� 
					imageView.setFitHeight(200); 	// �̹��� ���α���
				// 2. ��ư ���� ( ��ư�� �̹��� �ֱ� )
				Button button = new Button( null , imageView );
					// *��ư ������� [ transparent : ����� ]
					button.setStyle("-fx-background-color:transparent");
					// *��ư id���ֱ� [ ��ǰ �ĺ� = index ]
					button.setId( i+"" );
					// *��ư Ŭ���̺�Ʈ
						//	button.setOnAction( e -> { } ); // ���ٽ� : �μ� -> { �����ڵ� }
					button.setOnAction( e -> { 
						// 1. Ŭ���� ��ư�� id ��������
						int id = Integer.parseInt(  e.toString().split(",")[0].split("=")[2] );
						// 2. Ŭ���� ��ǰ ��ȣ ���� 
						ProductControl.select = productlist.get(id);
						Home.home.loadpage("/view/product/productview");
					} );	
				gridPane.add( button  , col , row); // �׸��峻  �ش� ����ȣ , ���ȣ �� ��ư �߰�
				
				i++; //�ε��� ����
			}
		}
		// * 3����� �������� ����������������
		int row = productlist.size() / 3;		// ��
		int remain = productlist.size() % 3 ;	// ���������� ������ 
		if( remain != 0  ) { // �������� �����ϸ� 
			for( int col = 0 ; col<remain ; col++ ) { // ��
				ImageView imageView = new ImageView( new Image(productlist.get(i).getP_img() ) );
					imageView.setFitWidth(250);		// �̹��� ���α��� 
					imageView.setFitHeight(200); 	// �̹��� ���α���
				Button button = new Button( null , imageView );
					button.setStyle("-fx-background-color:transparent");
					button.setId( i+"" );
					button.setOnAction( e -> { 
						int id = Integer.parseInt(  e.toString().split(",")[0].split("=")[2] );
						ProductControl.select = productlist.get(id);
						Home.home.loadpage("/view/product/productview");
					} );	
				gridPane.add( button  , col , row+1 ); // �������࿡ �������� ��ŭ ��ư �߰��ؼ� �׸��忡 �߰�
				i++; //�ε��� ����
			}
		}
		// 4. vbox�� �׸��� �ֱ� 
		vbox.getChildren().add(gridPane);
	}
}
