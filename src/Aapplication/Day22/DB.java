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
			// Initializable : fxml이 열렸을때 초기값 [ 처음값 ] 설정 메소드 제공
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
    	System.out.println("DB내 데이터를 호출합니다.");
    	
    	//1. DB 연동 객체 만들기
    	Day22_2 dbcon = new Day22_2();
    	// 2. DB 객체내 메소드 호출 후 리스트로 받기
    	ArrayList<Data> datalist =dbcon.get();
    	// 3. 리스트를 컨트롤에 넣어주기
    	for(Data temp : datalist) {
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
    	
    	String writer = txtwriter.getText(); 
    	String content = txtcontent.getText(); 
    	// 1. DB 얀덩 객체 만들기
    	Day22_2 dbcon = new Day22_2();
    	boolean result = dbcon.write(writer, content);
    	if(result) {
    		System.out.println("DB 저장 성공");
    		
    		// 성공시 컨트롤에 입력된 데이터 지워주기 
    			// txtid명.setText() : 해당 컨트롤에 값 넣기
    		txtwriter.setText("");
    		txtcontent.setText("");
    	}
    	else {
    		System.out.println("DB 저장 실패");
    	}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { // 초기값 [ 처음값 ] 설정 메소드
    	
    	Day22_2 day22_2 = new Day22_2(); // 생성자에 DB 연동 코드를 넣었기 때문에 객체 선언시 연동
    	
    }


}