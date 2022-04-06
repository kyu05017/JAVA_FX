package controllor.product;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductUpdate implements Initializable{
	
	Product product = ProductControl.select;
	
	@FXML
    private Button btback;

    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontents;

    @FXML
    private TextField txtpprise;

    @FXML
    private Button btimg;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt4;

    @FXML
    private RadioButton opt2;

    @FXML
    private RadioButton opt3;

    @FXML
    private ImageView img;

    @FXML
    private Button btaddp;

    @FXML
    private Label txtpath;

    @FXML
    void accadd(ActionEvent event) {

    }

    @FXML
    void accback(ActionEvent event) {

    }

    @FXML
    void imgadd(ActionEvent event) {

    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		img.setImage(new Image(product.getP_img()) );
    	txtpname.setText(product.getP_name());
    	txtpcontents.setText(product.getP_contents());
    	DecimalFormat df2 = new DecimalFormat("#,##0¿ø");
    	String aa = df2.format(product.getP_money());
    	txtpprise.setText(aa);
		
	}
	
}
