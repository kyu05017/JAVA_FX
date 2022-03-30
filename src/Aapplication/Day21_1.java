package Aapplication;

// javafx�� ��� [ awt. swing ���� ���� ��� x ]
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Day21_1 extends Application{
					// extends : ��� [ Application : JAVAFX GUI ���� Ŭ���� => start �޼ҵ� ����]
								// ��� �������� ����Ŭ���� ���� �ɹ��� ��밡��
	
	
	@Override
	public void start(Stage stage) throws Exception {
		// javafx�� �����ϴ� �޼ҵ� [ �߻� => ���� ]
			// Start ( Stage ��ü�̸� )
		
		// 1. �����̳� �ǵѱ� [ �������� ��Ʈ���� ���η� ���� ]
		VBox box = new VBox();// V ��Ƽ�� ����
		
		// 2. ��Ʈ�� �����
		Button button = new Button();
		button.setText("�ݱ�");
		button.setOnAction( e -> Platform.exit() );
						// �� -> �μ�  ���ٽ� ( �̸��� ���� �޼ҵ� )
		// 3. �����̳ʿ� ��Ʈ�� �ֱ�	
		box.getChildren().add(button);
		
		// 4. �� �����
		Scene scene = new Scene(box,500 ,500);// �����̳� �̸�, ���� ����. ���� ����
		
		// 5. ���������� �� �ֱ�
		stage.setScene(scene);
		
		stage.show();	// �������� ����
			// * ���� : �������� -> �� -> �����̳� -> �������� ��Ʈ�� ( ��ư, �Է»���, ǥ ��)
	}
	
	public static void main(String[] args) {	// ���� ������
	
		launch(args);	// start �޼ҵ� ȣ��
	}
	
}
