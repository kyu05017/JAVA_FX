package controllor.product;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import controllor.home.Home;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductView implements Initializable{
	@FXML
    private Button btback;

    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontents;

    @FXML
    private ImageView img;

    @FXML
    private Button btdelete;

    @FXML
    private Button btupdate;

    @FXML
    private Label txtcondition;

    @FXML
    private Label txtdate;

    @FXML
    private Label txtmid;

    @FXML
    private Label txtprise;

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/product/product");
    }

    @FXML
    void accdelete(ActionEvent event) {
    	
    }

    @FXML
    void accupdate(ActionEvent event) {

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Product product = ProductControl.select;
    	
    	img.setImage(new Image(product.getP_img()) );
    	txtpname.setText(product.getP_name());
    	txtpcontents.setText(product.getP_contents());
    	txtdate.setText(product.getP_date());
    	DecimalFormat df2 = new DecimalFormat("#,##0¿ø");
    	String aa = df2.format(product.getP_money());
    	txtprise.setText(aa);
    	
    }
}
