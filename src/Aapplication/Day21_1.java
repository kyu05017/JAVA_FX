package Aapplication;

// javafx만 사용 [ awt. swing 이전 버전 사용 x ]
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Day21_1 extends Application{
					// extends : 상속 [ Application : JAVAFX GUI 관련 클래스 => start 메소드 제공]
								// 상속 ㅂ다으면 슈퍼클래스 내에 맴버를 사용가능
	
	
	@Override
	public void start(Stage stage) throws Exception {
		// javafx를 실행하는 메소드 [ 추상 => 구현 ]
			// Start ( Stage 객체이름 )
		
		// 1. 컨테이너 맨둘기 [ 여러개의 컨트롤을 세로로 저장 ]
		VBox box = new VBox();// V 버티컬 세로
		
		// 2. 컨트롤 만들기
		Button button = new Button();
		button.setText("닫기");
		button.setOnAction( e -> Platform.exit() );
						// 값 -> 인수  람다식 ( 이름이 없는 메소드 )
		// 3. 컨테이너에 컨트롤 넣기	
		box.getChildren().add(button);
		
		// 4. 신 만들시
		Scene scene = new Scene(box,500 ,500);// 컨테이너 이름, 가로 길이. 세로 길이
		
		// 5. 스테이지에 씬 넣기
		stage.setScene(scene);
		
		stage.show();	// 스테이지 열기
			// * 순서 : 스테이지 -> 씬 -> 컨테이너 -> 여러개의 컨트롤 ( 버튼, 입력상자, 표 등)
	}
	
	public static void main(String[] args) {	// 메인 스래드
	
		launch(args);	// start 메소드 호출
	}
	
}
