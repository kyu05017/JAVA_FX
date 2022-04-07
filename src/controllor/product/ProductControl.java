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
    	
    	if( vbox.getChildren().isEmpty() == false) { //isEmpty()  ������� ��츦 Ȯ�� [ vbox�� ����ִ��� Ȯ�� ]
    		// vbox �� ��ü�� ������� ������
    		vbox.getChildren().remove(0); // vbox
    	}
    	// 1. ��� ��ǰ ��������
		ArrayList< Product > productlist = ProductDao.dao.plist(Home.category,search);
		
		// 2. �׸��� Ŭ���� [ ��/�� ]
		GridPane gridPane = new GridPane();
			// * �׸��� ����
			gridPane.setPadding( new Insets(10) );
			// * �׸��峻 �� ���� 
			gridPane.setHgap(20); // ���ο��� 
			gridPane.setVgap(20); // ���ο���
		if(Home.category != null) {
			if(Home.category.equals("�����Ƿ�")) {
				txtmenu.setText("�߰���ǰ : �����Ƿ�");
			}
			if(Home.category.equals("��Ȱ��ǰ")) {
				txtmenu.setText("�߰���ǰ : ��Ȱ��ǰ");
			}
			if(Home.category.equals("������ǰ")) {
				txtmenu.setText("�߰���ǰ : ������ǰ");
			}
			if(Home.category.equals("�����Ƿ�")) {
				txtmenu.setText("�߰���ǰ : �����Ƿ�");
			}
		}
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
						select = productlist.get(id);
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
						select = productlist.get(id);
						Home.home.loadpage("/view/product/productview");
					} );	
				gridPane.add( button  , col , row+1 ); // �������࿡ �������� ��ŭ ��ư �߰��ؼ� �׸��忡 �߰�
				i++; //�ε��� ����
			}
		}
		// 4. vbox�� �׸��� �ֱ� 
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


