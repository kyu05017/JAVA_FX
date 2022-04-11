package controllor;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import dao.BoardDao;
import dao.MemberDao;
import dao.ProductDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class Record implements Initializable{

	@FXML
    private Label lblmembers;

    @FXML
    private Label lblitems;

    @FXML
    private Label lblboards;

    @FXML
    private BarChart<?, ?> mbc;
	
    @FXML
    private BarChart<?, ?> pbc;

    @FXML
    private BarChart<?, ?> bbc;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 전체 회원수 
		int total_member  = MemberDao.dao.total_member();
		lblmembers.setText(total_member+"명");
		// 전체 제품수
		int total_product = ProductDao.dao.total_product();
		if(total_product == 0) {
			lblitems.setText("제품없음");
		}
		else {
			lblitems.setText(total_product+"개");
		}
		// 전체 게시물수
		int total_board = BoardDao.dao.total_board();
		if(total_board == 0) {
			lblboards.setText("게시물 없음.");
		}
		else {
			lblboards.setText(total_board+"개");
		}

		// 날짜별 회원 가입수'
		XYChart.Series series = new XYChart.Series<>();
		// XYChart.Series = 계열 클래스
			//  XYChart.Data  = 계열 데이터 클래스 [ x 축의 값 , y 축의 값 ] 
			// 날짜별로 회원가입수 수 체크 [ 2022-04-11 , 3 ]
			// map 컬렉션 => 키와 값으로 하나의 엔트리 구성
			// 키 = 날짜 \\ 값 가입수
			
		Map<String, Integer> total = MemberDao.dao.date_total("member");
		
		for(String temp : total.keySet()) {
			XYChart.Data data = new XYChart.Data(temp,total.get(temp));
			series.getData().add(data);
		}
		mbc.getData().add(series);
		
		XYChart.Series series2 = new XYChart.Series<>();
		Map<String, Integer> b_total = MemberDao.dao.date_total("board");
		for(String temp : b_total.keySet()) {
			XYChart.Data data = new XYChart.Data(temp,b_total.get(temp));
			series2.getData().add(data);
		}
		bbc.getData().add(series2);
		
		XYChart.Series series3 = new XYChart.Series<>();
		Map<String, Integer> p_total = MemberDao.dao.date_total("product");
		for(String temp : p_total.keySet()) {
			XYChart.Data data = new XYChart.Data(temp,p_total.get(temp));
			series3.getData().add(data);
		}
		pbc.getData().add(series3);
	}
}
