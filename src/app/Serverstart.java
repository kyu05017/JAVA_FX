package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Serverstart extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/view/server.fxml"));
			Scene scene = new Scene(parent);
			Font.loadFont(getClass().getResourceAsStream("SuncheonB.ttf"), 15);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image image = new Image("img/tree.png"); 
			stage.getIcons().add(image);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("서버");
			stage.show();
			
		}
		catch (Exception e) {
			System.out.println(" 서버 실행 오류 " + e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
