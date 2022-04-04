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
    	System.out.println("DB내 데이터를 호출합니다.");
    	
    	//1. DB 연동 객체 만들기
    	BoardDao dbcon = new BoardDao();
    	// 2. DB 객체내 메소드 호출 후 리스트로 받기
    	ArrayList<dto.Data> datalist =dbcon.get();
    	// 3. 리스트를 컨트롤에 넣어주기
    	for(dto.Data temp : datalist) {
    		// txtcontentlist.appendText : 해당 컨트롤에 내용 추가
    		txtcontentlist.appendText(
				temp.getNumber()+". " +
				temp.getWriter()+" : "+
				temp.getContents()+"\n"
			);
    	}
    }

    @FXML
    void write(ActionEvent event) {
    	System.out.println("DB내 데이터를 저장합니다.");
    	String content = txtcontent.getText(); 
    	// 1. DB 얀덩 객체 만들기
    	BoardDao dbcon = new BoardDao();
    	boolean result = dbcon.write(content);
    	if(result) {
    		System.out.println("DB 저장 성공");
    		
    		// 성공시 컨트롤에 입력된 데이터 지워주기 
    			// txtid명.setText() : 해당 컨트롤에 값 넣기
    		
    		txtcontent.setText("");
    	}
    	else {
    		System.out.println("DB 저장 실패");
    	}
    }
	    
}
