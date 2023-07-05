package cn.campsg.practical.bubble.service;




import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class StarServiceImpl implements StarService {

	public void drawOneBubble(AnchorPane root, int row, int col, String type) {

		// ���������ǿؼ�
		Label starFrame = new Label();
		// ���������ǵĴ�С
		starFrame.setPrefHeight(48);
		starFrame.setPrefWidth(48);
		// ���������ǵ�λ��
		starFrame.setLayoutX(col * 48);// ��
		starFrame.setLayoutY(row * 48);// ��
		// ���������ǵ���ʽ
		// skin2.css���Ѷ���5����������ʽ
		starFrame.getStyleClass().add(type);
		
		//���������ǵ���¼� ��START��
		
		starFrame.setOnMouseClicked(new StartEventHandler());
		
//		starFrame.setOnMouseClicked(new EventHandler<Event>() {
//			 
//			public void handle(Event event) {
//
//			System.out.println("�㴥���˵���¼���");
//			}
//
//		});
		//���������ǵ���¼� ��END��
		// ��������ǲ��ֶ���
		AnchorPane anchorPane = (AnchorPane) root.lookup("#game_pane");
		// �������ǿؼ����븸������
		anchorPane.getChildren().add(starFrame);
	}

	@Override
	public void drawBubbles(AnchorPane root) {
		// ���������ǿؼ�
		// ��������ʽ����
		String[] styles = { "purple_star", "red_star", "green_star", "yellow_star", "blue_star" };
		// ����10*10������������
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				// ������������ʽ
				int typeIndex = (int) (Math.random() * 5);
				String type = styles[typeIndex];
				this.drawOneBubble(root, row, col, type);
			}
		}
	}
	


class StartEventHandler implements EventHandler<MouseEvent>{

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		//��ñ������������
		Label label = (Label) event.getTarget();
		int col = (int) (label.getLayoutX()/48);
		int row = (int) (label.getLayoutY()/48);
		List<String> styles = label.getStyleClass();
		String style = styles.get(styles.size()-1);
		System.out.println("�����˵�"+row+"�У���"+col+"�е������ǣ�����Ϊ��"+style);

	}
	
}

}
