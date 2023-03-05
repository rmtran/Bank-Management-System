package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {
	private Scene passwordScene; // 1st Scene Displayed
	private Scene menuScene; // 2nd Scene Displayed
	private Scene addScene; // Each of these scenes is connected to a button on the menu scene
	private Scene deleteScene;
	private Scene updateScene;
	private Scene printMemberScene;
	private Scene depositScene;
	private Scene withdrawScene;
	private Scene printListScene;
	private Scene transferScene;
	private Scene exitScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			  BorderPane root = new BorderPane();
			  root.backgroundProperty().set(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
			  primaryStage.setTitle("Bank Management System"); 
			   
			  //Set up Password Scene 
			  Label label1 = new Label("Enter Password:");
			  TextField password = new TextField();
			  VBox layout1 = new VBox(10); 
			  Button submitButton = new Button("Submit");
			  layout1.getChildren().addAll(label1, password, submitButton);
			  
			  passwordScene = new Scene(layout1, 300, 250, Color.BLANCHEDALMOND);
			  passwordScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			  
			  // Note for Group - This is how you make a button do something
			  submitButton.setOnAction(e -> {
			  if(!(password.getText().equals("secureforsure"))) {
				  Alert errorAlert = new Alert(AlertType.ERROR);
				  errorAlert.setHeaderText("Invalid Password");
				  errorAlert.setContentText("This password is not valid. Please try again.");
				  errorAlert.showAndWait();
				}
			  else {
				  primaryStage.setScene(menuScene);
			  }
			  });
		
			  
			  
			  // Set up the Menu Scene
			  Label label2 = new Label(" -- Welcome To The Bank Management System -- "); 
			  Button button1 = new Button("Add New Member"); 
			  Button button2 = new Button("Delete Member");
			  Button button3 = new Button("Update Member");
			  Button button4 = new Button("Print All Member Information");
			  Button button5 = new Button("Deposit into account");
			  Button button6 = new Button("Withdraw from account");
			  Button button7 = new Button("Print List of Clients and their ID's");
			  Button button8 = new Button("Transfer money between accounts");
			  Button button9 = new Button("Save and Exit");
		
			  
			  VBox layout2 = new VBox(20);
			  layout2.setStyle("-fx-background: #DFFDFF;");
			  layout2.getChildren().addAll(label2, button1, button2, button3, button4, button5, button6, button7, button8, button9); 
			  menuScene = new Scene(layout2,300, 250);
			  menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Each button sends the user to another scene
			  button1.setOnAction(e ->primaryStage.setScene(addScene)); 
			  button2.setOnAction(e ->primaryStage.setScene(deleteScene));
			  button3.setOnAction(e ->primaryStage.setScene(updateScene));
			  button4.setOnAction(e ->primaryStage.setScene(printMemberScene));
			  button5.setOnAction(e ->primaryStage.setScene(depositScene));
			  button6.setOnAction(e ->primaryStage.setScene(withdrawScene));
			  button7.setOnAction(e ->primaryStage.setScene(printListScene));
			  button8.setOnAction(e ->primaryStage.setScene(transferScene));
			  button9.setOnAction(e ->primaryStage.setScene(exitScene));
			  
			  // Setup addScene FIXME
			  VBox layout3 = new VBox(20);
			  layout3.setStyle("-fx-background: #DFFDFF;");
			  Label label3 = new Label(" Add Member (UNFINISHED)"); 
			  layout3.getChildren().addAll(label3); 
			  addScene = new Scene(layout3,300, 250);
			  addScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Setup deleteScene FIXME
			  VBox layout4 = new VBox(20);
			  layout4.setStyle("-fx-background: #DFFDFF;");
			  Label label4 = new Label(" Delete Member (UNFINISHED)"); 
			  layout4.getChildren().addAll(label4); 
			  deleteScene = new Scene(layout4,300, 250);
			  deleteScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Setup updateScene FIXME
			  VBox layout5 = new VBox(20);
			  layout5.setStyle("-fx-background: #DFFDFF;");
			  Label label5 = new Label(" Update (UNFINISHED)"); 
			  layout5.getChildren().addAll(label5); 
			  updateScene = new Scene(layout5,300, 250);
			  updateScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Setup printMemberScene FIXME
			  VBox layout6 = new VBox(20);
			  layout6.setStyle("-fx-background: #DFFDFF;");
			  Label label6 = new Label(" Print Member (UNFINISHED)"); 
			  layout6.getChildren().addAll(label6); 
			  printMemberScene = new Scene(layout6,300, 250);
			  printMemberScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  // Setup depositScene FIXME
			  VBox layout7 = new VBox(20);
			  layout7.setStyle("-fx-background: #DFFDFF;");
			  Label label7 = new Label(" Deposit (UNFINISHED)"); 
			  layout7.getChildren().addAll(label7); 
			  depositScene = new Scene(layout7,300, 250);
			  depositScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Setup withdrawScene FIXME
			  VBox layout8 = new VBox(20);
			  layout8.setStyle("-fx-background: #DFFDFF;");
			  Label label8 = new Label(" Withdraw (UNFINISHED)"); 
			  layout8.getChildren().addAll(label6); 
			  withdrawScene = new Scene(layout8,300, 250);
			  withdrawScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Setup printListScene FIXME
			  VBox layout9 = new VBox(20);
			  layout9.setStyle("-fx-background: #DFFDFF;");
			  Label label9 = new Label(" Print List (UNFINISHED)"); 
			  layout9.getChildren().addAll(label9); 
			  printListScene = new Scene(layout9,300, 250);
			  printListScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Setup transferScene FIXME
			  VBox layout10 = new VBox(20);
			  layout10.setStyle("-fx-background: #DFFDFF;");
			  Label label10 = new Label(" Transfer (UNFINISHED)"); 
			  layout10.getChildren().addAll(label10); 
			  transferScene = new Scene(layout10,300, 250);
			  transferScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  // Setup exitScene FIXME
			  VBox layout11 = new VBox(20);
			  layout11.setStyle("-fx-background: #DFFDFF;");
			  Label label11 = new Label(" This probably shouldn't actually even go to a scene, and just close the program"); 
			  layout11.getChildren().addAll(label11); 
			  exitScene = new Scene(layout11,300, 250);
			  exitScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			  
			  
			  
			  
			  // Starts the Program (Begins at Password Scene)
			  primaryStage.setScene(passwordScene);
			  primaryStage.show();
	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
