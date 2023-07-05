package cn.campsg.practical.bubble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import cn.campsg.practical.bubble.common.StarAnimation;
import cn.campsg.practical.bubble.common.StarFormUtils;
import cn.campsg.practical.bubble.entity.Star;
import cn.campsg.practical.bubble.entity.StarList;
import cn.campsg.practical.bubble.service.StarService;

/**
 * �����Ǵ����࣬������ʾ���������С����������ǵ���¼��붯��
 * 
 * 
 * @author Frank.Chen
 * @version 1.5
 */
public class MainForm extends Application {
	
	/** �ӷ���˻�ȡ������10*10�������б�  **/
	private StarList mCurretStars = null;
	
	/** ��������ʾ�����ǵ����� **/
	private AnchorPane mStarForm = null;

	public static void show(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("/res/layout/main_layout.fxml"));

			// �������ּ��뵽��ͼ������
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			// ҳ�����ʱ�����¿�ʼ�µ����������ʱ���ʼ��������
			initGameStars(root);

			primaryStage.setTitle("����������-Popstar3");
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * ҳ�����ʱ�����¿�ʼ�µ����������ʱ���ʼ��������
	 * 
	 * @param root
	 *            �����ܲ���
	 * 
	 */
	private void initGameStars(AnchorPane root) {

		// ���ش�������ʾ�����ǵ�����
		mStarForm = (AnchorPane) root.lookup("#game_pane");

		// ��������������ҵ����
		StarService starService = getStarService();

		// �������ô��������Ǵ���
		mCurretStars = starService.createBubbleMatrix();

		// ѭ���������������ǣ��������Ƕ���Starת��Ϊ������ʾ�ؼ�Label
		for (int i = 0; i < mCurretStars.size(); i++) {
			// �������Ǽ�����ȡ��һ��������
			Star star = mCurretStars.get(i);

			// ����������������ʾ�ؼ�Label
			Label starFrame = new Label();
			starFrame.setPrefWidth(48);
			starFrame.setPrefHeight(48);

			// ��ȡ�����Ƕ���Star��������
			int row = star.getPosition().getRow();
			int col = star.getPosition().getColumn();
			
			// Ϊ��������ʾ�ؼ�Label����Ψһ��ʶID��ID����Ϊs+�к�+�кţ����磺s00,s01��
			starFrame.setId("s" + row + col);
			// �������ǵ�����λ�ñ�����������ʶ���������ڽ����е�λ�á�
			starFrame.setUserData(row + ";" + col);
			// ������������ʾ�ؼ�Label�ڽ���ĳ�������
			starFrame.setLayoutX(col * 48);
			starFrame.setLayoutY(row * 48);

			// ������������ʾ�ؼ�Label��ʾ���
			switch (star.getType().value()) {
			case 0:
				starFrame.getStyleClass().add("blue_star");
				break;
			case 1:
				starFrame.getStyleClass().add("green_star");
				break;
			case 2:
				starFrame.getStyleClass().add("yellow_star");
				break;
			case 3:
				starFrame.getStyleClass().add("red_star");
				break;
			case 4:
				starFrame.getStyleClass().add("purple_star");
				break;
			}
			
			/******************** PRJ-BU2-JAVA-007 Task1 ********************/

			StartEventHandler handler = new StartEventHandler();
			starFrame.setOnMouseClicked(handler);
			
			/******************************************************/
			
			// �������Ǽ��뵽��������ʾ�����ǵ�����
			mStarForm.getChildren().add(starFrame);
		}

	}
	
	/**
	 * �����Ǳ�������¼���������
	 */
	class StartEventHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			
			// ��ȡ���������������ͼ
	        Label starFrame = (Label) event.getTarget();
	        // ����ͼת��Ϊ������ʵ��
	        Star base = StarFormUtils.convert(starFrame);
	        System.out.println(base);

		}
		
	}

	

	
	/**
	 * ���ڽӿڶ�̬���������Ƿ����࣬��������������bean.conf�����ļ���
	 * 
	 * @return �����Ƿ�����
	 */
	private StarService getStarService() {
		//����JVM�������
		ClassLoader loader = this.getClass().getClassLoader();

		//�������ļ�bean.conf�ж�ȡ�����Ƿ�����[��ȫ��]
		String className = getClassName();

		try {
			//���������Ƿ�����
			Class<?> clazz = loader.loadClass(className);
			//��̬���������������Ƿ������ʵ��
			return (StarService) clazz.newInstance();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * �������ļ�bean.conf�ж�ȡ�����Ƿ�����[��ȫ��]
	 * 
	 * @return �����Ƿ�����[��ȫ��]
	 */
	private String getClassName() {
		// ��ȡbean.conf�����ļ�
		BufferedReader br = new BufferedReader(new InputStreamReader(getClass()
				.getClassLoader().getResourceAsStream("bean.conf")));
		
		try {
			//��ȡ��һ������
			String line = br.readLine();
			//���صȺ��Ҳ�������Ƿ�����[��ȫ��]�ַ���
			return line.split("=")[1];
		} catch (IOException e) {
			return "";
		}
	}
}