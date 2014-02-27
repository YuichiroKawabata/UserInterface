package org.kyutech.kawabata.UserInterface.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MainApp extends Application {

	String inputPath;
	String outputPath;

	@Override
    public void start(final Stage primaryStage) {
    	 Button inputFileSelectBtn = new Button();
         inputFileSelectBtn.setText("変換するファイルを選択してください"); //$NON-NLS-1$
         final DirectoryChooser fc = new DirectoryChooser();
         fc.setTitle("変換するファイルを選択してください"); //$NON-NLS-1$

         final Label inputFilePathLabel = new Label();

         inputFileSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 File importFile = fc.showDialog(primaryStage);
                 if (importFile != null) {
                     inputFilePathLabel.setText(importFile.getPath().toString());
                     MainApp.this.inputPath=importFile.getPath().toString();
                 }
             }
         });
         
         Button outputFileSelectBtn = new Button();
         outputFileSelectBtn.setText("変換先のファイルを選択してください"); //$NON-NLS-1$
         final DirectoryChooser outFc = new DirectoryChooser();
         fc.setTitle("変換先のファイルを選択してください"); //$NON-NLS-1$

         final Label outputFilePathLabel = new Label();

         outputFileSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 File importFile = outFc.showDialog(primaryStage);
                 if (importFile != null) {
                     outputFilePathLabel.setText(importFile.getPath().toString());
                     MainApp.this.outputPath=importFile.getPath().toString();
                 }
             }
         });
         ImageView imageView = null;
        
			Image image = new Image((new File("yajirushi.png")).toURI().toString());
			imageView.setImage(image);
		
         HBox hbox1 = new HBox(30d);
         HBox hbox2 = new HBox(30d);
         HBox hbox3 = new HBox(30d);
         hbox1.setAlignment(Pos.CENTER);
         hbox2.setAlignment(Pos.CENTER);
         hbox3.setAlignment(Pos.CENTER);
         hbox1.getChildren().addAll(inputFileSelectBtn, inputFilePathLabel);
         hbox2.getChildren().addAll(imageView);
         hbox3.getChildren().addAll(outputFileSelectBtn, outputFilePathLabel);
        VBox vbox = new VBox(30d);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hbox1,hbox2,hbox3);

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