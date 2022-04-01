package Aapplication.Day22;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DB implements Initializable{
			// Initializable : fxml�� �������� �ʱⰪ [ ó���� ] ���� �޼ҵ� ����
    @FXML
    private TextField txtwriter;

    @FXML
    private TextField txtcontent;

    @FXML
    private Button btnget;

    @FXML
    private Button btnwrite;

    @FXML
    private TextArea txtcontentlist;

    @FXML
    void get(ActionEvent event) {
    	System.out.println("DB�� �����͸� ȣ���մϴ�.");
    	
    	//1. DB ���� ��ü �����
    	Day22_2 dbcon = new Day22_2();
    	// 2. DB ��ü�� �޼ҵ� ȣ�� �� ����Ʈ�� �ޱ�
    	ArrayList<Data> datalist =dbcon.get();
    	// 3. ����Ʈ�� ��Ʈ�ѿ� �־��ֱ�
    	for(Data temp : datalist) {
    		// txtcontentlist.appendText : �ش� ��Ʈ�ѿ� ���� �߰�
    		txtcontentlist.appendText(
				temp.getNumber()+". " +
				temp.getWriter()+" : "+
				temp.getContents()+"\n"
			);
    	}
    }

    @FXML
    void write(ActionEvent event) {
    	System.out.println("DB�� �����͸� �����մϴ�.");
    	
    	String writer = txtwriter.getText(); 
    	String content = txtcontent.getText(); 
    	// 1. DB �ᵢ ��ü �����
    	Day22_2 dbcon = new Day22_2();
    	boolean result = dbcon.write(writer, content);
    	if(result) {
    		System.out.println("DB ���� ����");
    		
    		// ������ ��Ʈ�ѿ� �Էµ� ������ �����ֱ� 
    			// txtid��.setText() : �ش� ��Ʈ�ѿ� �� �ֱ�
    		txtwriter.setText("");
    		txtcontent.setText("");
    	}
    	else {
    		System.out.println("DB ���� ����");
    	}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { // �ʱⰪ [ ó���� ] ���� �޼ҵ�
    	
    	Day22_2 day22_2 = new Day22_2(); // �����ڿ� DB ���� �ڵ带 �־��� ������ ��ü ����� ����
    	
    }


}