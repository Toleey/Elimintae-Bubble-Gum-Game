package cn.campsg.practical.bubble.service;



import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StarServiceImpl implements StarService {

	public void drawOneBubble(AnchorPane root, int row, int col, String type) {

		// 创建泡泡糖控件
		Label starFrame = new Label();
		// 设置泡泡糖的大小
		starFrame.setPrefHeight(48);
		starFrame.setPrefWidth(48);
		// 设置泡泡糖的位置
		starFrame.setLayoutX(col * 48);// 列
		starFrame.setLayoutY(row * 48);// 行
		// 设置泡泡糖的样式
		// skin2.css中已定义5种泡泡糖样式
		starFrame.getStyleClass().add(type);
		
		//添加泡泡糖点击事件 【START】
		starFrame.setOnMouseClicked(new EventHandler<Event>() {
			 
			public void handle(Event event) {

			System.out.println("你触发了点击事件。");
			}

		});
		//添加泡泡糖点击事件 【END】
		// 获得泡泡糖布局对象
		AnchorPane anchorPane = (AnchorPane) root.lookup("#game_pane");
		// 将泡泡糖控件加入父容器中
		anchorPane.getChildren().add(starFrame);
	}

	@Override
	public void drawBubbles(AnchorPane root) {
		// 创建泡泡糖控件
		// 泡泡糖样式集合
		String[] styles = { "purple_star", "red_star", "green_star", "yellow_star", "blue_star" };
		// 遍历10*10的泡泡糖阵列
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				// 设置泡泡糖样式
				int typeIndex = (int) (Math.random() * 5);
				String type = styles[typeIndex];
				this.drawOneBubble(root, row, col, type);
			}
		}
	}
	

}
