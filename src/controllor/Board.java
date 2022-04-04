package controllor;

import java.util.ArrayList;

import dao.BoardDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Board {

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
    	BoardDao dbcon = new BoardDao();
    	// 2. DB ��ü�� �޼ҵ� ȣ�� �� ����Ʈ�� �ޱ�
    	ArrayList<dto.Data> datalist =dbcon.get();
    	// 3. ����Ʈ�� ��Ʈ�ѿ� �־��ֱ�
    	for(dto.Data temp : datalist) {
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
    	String content = txtcontent.getText(); 
    	// 1. DB �ᵢ ��ü �����
    	BoardDao dbcon = new BoardDao();
    	boolean result = dbcon.write(content);
    	if(result) {
    		System.out.println("DB ���� ����");
    		
    		// ������ ��Ʈ�ѿ� �Էµ� ������ �����ֱ� 
    			// txtid��.setText() : �ش� ��Ʈ�ѿ� �� �ֱ�
    		
    		txtcontent.setText("");
    	}
    	else {
    		System.out.println("DB ���� ����");
    	}
    }
	    
}
