package org.kyutech.kawabata.UserInterface;

import java.io.File;

import org.kyutech.kawabata.UserInterface.xml.DictionaryClass;
import org.kyutech.kawabata.UserInterface.xml.ReadXML;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author kawabata
 * javafxフレームワークのメインクラスです
 */
public class MainApp extends Application {

	String inputPath = "sampledoc/input.edm"; //$NON-NLS-1$
	String outputPath = "output.edm"; //$NON-NLS-1$

	@Override
	public void start(final Stage primaryStage) {
		Button inputFileSelectBtn = new Button();
		inputFileSelectBtn.setText("変換するファイルを選択してください"); //$NON-NLS-1$
		
		final FileChooser fc = new FileChooser();
		fc.setTitle("変換するファイルを選択してください"); //$NON-NLS-1$

		final Label inputFilePathLabel = new Label();

		inputFileSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				File importFile = fc.showOpenDialog(primaryStage);
				if (importFile != null) {
					inputFilePathLabel.setText(importFile.getPath().toString());
					MainApp.this.inputPath = importFile.getPath().toString();
				}
			}
		});

		Button outputFileSelectBtn = new Button();
		outputFileSelectBtn.setText("変換先のフォルダを選択してください"); //$NON-NLS-1$
		final DirectoryChooser outFc = new DirectoryChooser();
		fc.setTitle("変換先のフォルダを選択してください"); //$NON-NLS-1$

		final Label outputFilePathLabel = new Label();

		outputFileSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				File importFile = outFc.showDialog(primaryStage);
				if (importFile != null) {
					outputFilePathLabel
							.setText(importFile.getPath().toString()+"/output.edm"); //$NON-NLS-1$
					MainApp.this.outputPath = importFile.getPath().toString()+"/output.edm"; //$NON-NLS-1$
				}
			}
		});

		Button changeBtn = new Button();
		final Label resultLabel = new Label();
		changeBtn.setText("上記のパスで変換します"); //$NON-NLS-1$
		changeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ReadXML readXml = new ReadXML();
				DictionaryClass dic =new DictionaryClass();
				resultLabel.setText("ボタンが押されました "+ DictionaryClass.class.getName().toString() + "   " + ReadXML.class.getName().toString()+"\n"+ readXml.toString() + dic.toString() );
				
				//resultLabel.setText(new DictionaryClass().keyList.toString());
				//resultLabel.setText(readXml.encodeXML(MainApp.this.inputPath,
				//		MainApp.this.outputPath));

			}
		});

		HBox hbox1 = new HBox(30d);
		HBox hbox2 = new HBox(30d);
		HBox hbox3 = new HBox(30d);
		hbox1.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		hbox3.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(inputFileSelectBtn, inputFilePathLabel);
		hbox3.getChildren().addAll(changeBtn);
		hbox2.getChildren().addAll(outputFileSelectBtn, outputFilePathLabel);
		VBox vbox = new VBox(30d);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(hbox1, hbox2, hbox3,resultLabel);

		StackPane root = new StackPane();
		root.getChildren().addAll(vbox);

		Scene scene = new Scene(root, 500, 250);

		primaryStage.setTitle("ER_EXCHANGE"); //$NON-NLS-1$
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}