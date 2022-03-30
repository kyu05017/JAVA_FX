package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		// 스테이지 로고 설정ㄷ
			// 1. 이미지 불러오기
		Image image = new Image("img/a.png"); // 상대 경로
		//Image image2 = new Image("//C:\\Users\\504\\git\\JAVA_FX\\src\\img"); 절대경로
			// 절대 경로 VS 상대 경로
			// 절대 경로 : 모든 경로
				// C:\Users\504\git\JAVA_FX\src\img
			// 상대 경로 : 현위치 [ 프로젝트 기준 src ] 기준 경로
				// 생략 [ src폴더부터 ] img/파일명.확장자
			// 2. 스테이지 설정
		stage.getIcons().add(image);
		stage.setResizable(false);	// 3. 스테이지 크기 고정
		
		stage.setTitle("햄버거 먹고싶은 사람의 FX프로그래밍"); // 2. 스테이지 창 이름
		stage.show(); // 스테이지 열기
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	
}
