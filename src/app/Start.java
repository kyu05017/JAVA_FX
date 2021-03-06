package app;

import controllor.Chatting;
import controllor.login.Login;
import dao.RoomDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Start extends Application{ // 20220408 1019

	@Override
	public void start(Stage stage) throws Exception {
		
		// 5. 컨테이너 불러오기
		Parent parent = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
		// 6. 신 객체 -> 컨테이너 연결
		Scene scene = new Scene(parent);
		// 7. 씬 -> 스테이지
		stage.setScene(scene);
		// 스테이지 로고 설정ㄷ
			// 1. 이미지 불러오기 
		Image image = new Image("img/logo.png"); // 상대 경로
		//Image image2 = new Image("//C:\\Users\\504\\git\\JAVA_FX\\src\\img"); 절대경로
			// 절대 경로 VS 상대 경로
			// 절대 경로 : 모든 경로
				// C:\Users\504\git\JAVA_FX\src\img
			// 상대 경로 : 현위치 [ 프로젝트 기준 src ] 기준 경로
				// 생략 [ src폴더부터 ] img/파일명.확장자
			// 2. 스테이지 설정 
		stage.getIcons().add(image);
		// 외부 폰트 설정
		// 1. 폰트 가져오기
		Font.loadFont(getClass().getResourceAsStream("SuncheonB.ttf"), 15);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setResizable(false);	// 3. 스테이지 크기 고정
		stage.setTitle("토끼 중고나라 "); // 2. 스테이지 창 이름
		stage.show(); // 스테이지 열기
		
		// * 윈도우 창에 x버튼 눌렀을때
		stage.setOnCloseRequest(e -> {
			if(Login.member != null) {
				RoomDao.dao.deletelist(Login.member.getM_id());
				if(Chatting.selectRoom != null) {
					RoomDao.dao.deleteroom(Chatting.selectRoom.getRo_num());
				}
				Chatting.selectRoom = null;
			}
		});
	}
	public static void main(String[] args) {
		
		launch(args);
	}
}
