package application;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

//client object with all important information
class Client {

	// Client info
	private String clientName;
	private int ID;
	protected double checkings = 0;
	protected double savings = 0;
	protected double networth;

	// getters and setters
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public double getCheckings() {
		return checkings;
	}

	public void depositCheckings(double deposit) {
		this.checkings += deposit;
	}

	public double getSavings() {
		return savings;
	}

	public void depositSavings(double deposit) {
		this.savings += deposit;
	}

	public double getNetworth() {
		networth = this.checkings + this.savings;
		return networth;
	}

	public void setNetworth(double networth) {
		networth = this.checkings + this.savings;
		this.networth = networth;
	}

	// constructor
	public Client(String name, int ID) {
		this.setClientName(name);
		this.setID(ID);
	}

	public String toString() {
		return this.clientName;

	}

}

class businessClient extends Client {
	double taxWithholdings = 0;
	double taxPercentage = 0.065;

	// same constructor
	public businessClient(String name, int ID) {
		super(name, ID);
	}

	// this deposit method takes a percentage of income and deposits it in a tax
	// withholdings account
	@Override
	public void depositCheckings(double deposit) {
		double taxWithheld = deposit * taxPercentage;
		taxWithholdings += taxWithheld;
		this.checkings += (deposit - taxWithheld);

	}

	@Override
	public void depositSavings(double deposit) {
		double taxWithheld = deposit * taxPercentage;
		taxWithholdings += taxWithheld;
		this.savings += (deposit - taxWithheld);
	}

}

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
			AnchorPane root = new AnchorPane();
			root.backgroundProperty()
					.set(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
			primaryStage.setTitle("Bank Management System");

			// Set up Password Scene
			Label label1 = new Label("Enter Employee Password:");
			AnchorPane.setTopAnchor(label1, 172.0);
			AnchorPane.setLeftAnchor(label1, 75.0);
			TextField password = new TextField();
			AnchorPane.setTopAnchor(password, 168.0);
			AnchorPane.setLeftAnchor(password, 230.0);
			AnchorPane layout1 = new AnchorPane();
			Button submitButton = new Button("Enter");
			AnchorPane.setTopAnchor(submitButton, 168.0);
			AnchorPane.setLeftAnchor(submitButton, 380.0);
			submitButton.setPrefSize(60.0, 20.0);
			layout1.getChildren().addAll(label1, password, submitButton);

			passwordScene = new Scene(layout1, 600, 500, Color.BLANCHEDALMOND);
			passwordScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Note for Group - This is how you make a button do something
			submitButton.setOnAction(e -> {
				if (!(password.getText().equals("a"))) {
					Alert errorAlert = new Alert(AlertType.ERROR);
					errorAlert.setHeaderText("Invalid Password");
					errorAlert.setContentText("This password is not valid. Please try again.");
					errorAlert.showAndWait();
				} else {
					primaryStage.setScene(menuScene);
				}
			});

			double buttonSetWidth = 220;
			double buttonSetHeight = 30;

			// Set up the Menu Scene

			// Set all buttons to their positions on the scene and what size they should be
			Button button1 = new Button("Add New Member");
			AnchorPane.setTopAnchor(button1, 30.0);
			AnchorPane.setRightAnchor(button1, 30.0);
			button1.setPrefWidth(buttonSetWidth);
			button1.setPrefHeight(buttonSetHeight);

			Button button2 = new Button("Delete Member");
			AnchorPane.setTopAnchor(button2, 70.0);
			AnchorPane.setRightAnchor(button2, 30.0);
			button2.setPrefWidth(buttonSetWidth);
			button2.setPrefHeight(buttonSetHeight);

			Button button3 = new Button("Update Member");
			AnchorPane.setTopAnchor(button3, 110.0);
			AnchorPane.setRightAnchor(button3, 30.0);
			button3.setPrefWidth(buttonSetWidth);
			button3.setPrefHeight(buttonSetHeight);

			Button button4 = new Button("Print Member Information");
			AnchorPane.setTopAnchor(button4, 150.0);
			AnchorPane.setRightAnchor(button4, 30.0);
			button4.setPrefWidth(buttonSetWidth);
			button4.setPrefHeight(buttonSetHeight);

			Button button5 = new Button("Deposit into account");
			AnchorPane.setTopAnchor(button5, 190.0);
			AnchorPane.setRightAnchor(button5, 30.0);
			button5.setPrefWidth(buttonSetWidth);
			button5.setPrefHeight(buttonSetHeight);

			Button button6 = new Button("Withdraw from account");
			AnchorPane.setTopAnchor(button6, 230.0);
			AnchorPane.setRightAnchor(button6, 30.0);
			button6.setPrefWidth(buttonSetWidth);
			button6.setPrefHeight(buttonSetHeight);

			Button button7 = new Button("Transfer money between accounts");
			AnchorPane.setTopAnchor(button7, 270.0);
			AnchorPane.setRightAnchor(button7, 30.0);
			button7.setPrefWidth(buttonSetWidth);
			button7.setPrefHeight(buttonSetHeight);

			Button button8 = new Button("Save and Exit");
			AnchorPane.setTopAnchor(button8, 310.0);
			AnchorPane.setRightAnchor(button8, 30.0);
			button8.setPrefWidth(buttonSetWidth);
			button8.setPrefHeight(buttonSetHeight);

			//
			ObservableList<Client> clients = FXCollections.observableArrayList();
			ListView<Client> clientList = new ListView<>(clients);
			AnchorPane.setTopAnchor(clientList, 10.0);
			AnchorPane.setLeftAnchor(clientList, 10.0);
			AnchorPane.setRightAnchor(clientList, 300.0);
			AnchorPane.setBottomAnchor(clientList, 10.0);

			clientList.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
				@Override
				public ListCell<Client> call(ListView<Client> listView) {
					return new ListCell<Client>() {
						@Override
						protected void updateItem(Client client, boolean empty) {
							super.updateItem(client, empty);
							if (client == null || empty) {
								setText(null);
							} else {
								String bankAccountType;
								if (client instanceof businessClient) {
									bankAccountType = "Business Account";
								} else {
									bankAccountType = "Personal Account";
								}
								setText(String.format("%-20s %-15s %s", client.getClientName(), client.getID(),
										bankAccountType));
							}
						}
					};
				}
			});

			AnchorPane layout2 = new AnchorPane();
			layout2.setStyle("-fx-background: #DFFDFF;");
			layout2.getChildren().addAll(clientList, button1, button2, button3, button4, button5, button6, button7,
					button8);
			menuScene = new Scene(layout2, 600, 500);
			menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			

			// Each button sends the user to another scene
			button1.setOnAction(e -> primaryStage.setScene(addScene));
			button2.setOnAction(e -> primaryStage.setScene(deleteScene));
			button3.setOnAction(e -> primaryStage.setScene(updateScene));
			button4.setOnAction(e -> primaryStage.setScene(printMemberScene));
			button5.setOnAction(e -> primaryStage.setScene(depositScene));
			button6.setOnAction(e -> primaryStage.setScene(withdrawScene));
			button7.setOnAction(e -> primaryStage.setScene(transferScene));
			button8.setOnAction(e -> primaryStage.setScene(exitScene));

			// Setup addScene FIXME
			AnchorPane layout3 = new AnchorPane();
			layout3.setStyle("-fx-background: #DFFDFF;");
			Label label3 = new Label("Fill out client information");

			ToggleButton personalSwitch = new ToggleButton("Personal");
			AnchorPane.setTopAnchor(personalSwitch, 80.0);
			AnchorPane.setLeftAnchor(personalSwitch, 160.0);

			ToggleButton businessSwitch = new ToggleButton("Business");
			AnchorPane.setTopAnchor(businessSwitch, 80.0);
			AnchorPane.setLeftAnchor(businessSwitch, 290.0);

			// toggle switch to dictate what kind of account you are adding. Default is set
			// to personal
			ToggleGroup group = new ToggleGroup();
			personalSwitch.setToggleGroup(group);
			businessSwitch.setToggleGroup(group);
			personalSwitch.setSelected(true);

			Label nameLabel = new Label("Name:");
			AnchorPane.setTopAnchor(nameLabel, 172.0);
			AnchorPane.setLeftAnchor(nameLabel, 120.0);

			TextField nameField = new TextField();
			AnchorPane.setTopAnchor(nameField, 170.0);
			AnchorPane.setLeftAnchor(nameField, 180.0);
			// Allowing only letters to be input into the name textfield
			nameField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\\\sa-zA-Z*")) {
						nameField.setText(newValue.replaceAll("[^\\\\sa-zA-Z]", ""));
					}
				}
			});

			Label idLabel = new Label("ID:");
			AnchorPane.setTopAnchor(idLabel, 233.0);
			AnchorPane.setLeftAnchor(idLabel, 140.0);

			TextField idField = new TextField();
			AnchorPane.setTopAnchor(idField, 230.0);
			AnchorPane.setLeftAnchor(idField, 180.0);
			// Allowing only numbers to be input into the ID textfield
			idField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						idField.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});

			Button addingMember = new Button("Add Member");
			AnchorPane.setTopAnchor(addingMember, 290.0);
			AnchorPane.setLeftAnchor(addingMember, 220.0);

			addingMember.setOnAction(e -> {
				if (nameField.getText().isEmpty() || idField.getText().isEmpty()) {
					Alert empty = new Alert(AlertType.ERROR);
					empty.setHeaderText("Empty Field(s)");
					empty.setContentText("Please fill out both fields");
					empty.showAndWait();

				} else {

					if (businessSwitch.isSelected()) {
						clients.add(new businessClient(nameField.getText(), Integer.parseInt(idField.getText())));
					} else {
						clients.add(new Client(nameField.getText(), Integer.parseInt(idField.getText())));
					}
					primaryStage.setScene(menuScene);
					nameField.clear();
					idField.clear();
				}
			});

			// return to main menu button
			Button returnToMenu1 = new Button("Main Menu");
			AnchorPane.setBottomAnchor(returnToMenu1, 10.0);
			AnchorPane.setRightAnchor(returnToMenu1, 10.0);
			returnToMenu1.setOnAction(e -> {
				primaryStage.setScene(menuScene);
				nameField.clear();
				idField.clear();
			});

			layout3.getChildren().addAll(returnToMenu1, personalSwitch, businessSwitch, nameLabel, nameField, idLabel,
					idField, addingMember);
			addScene = new Scene(layout3, 500, 500);
			addScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Setup deleteScene FIXME
			AnchorPane layout4 = new AnchorPane();
			layout4.setStyle("-fx-background: #DFFDFF;");

			Label deletingMember = new Label("Enter Client ID:");
			AnchorPane.setTopAnchor(deletingMember, 183.0);
			AnchorPane.setLeftAnchor(deletingMember, 120.0);

			TextField deleteMemberidField = new TextField();
			AnchorPane.setTopAnchor(deleteMemberidField, 180.0);
			AnchorPane.setLeftAnchor(deleteMemberidField, 220.0);
			// Allowing only numbers to be input into the ID textfield
			deleteMemberidField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						deleteMemberidField.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});

			Button confirmDelete = new Button("Delete Member");
			AnchorPane.setTopAnchor(confirmDelete, 290.0);
			AnchorPane.setLeftAnchor(confirmDelete, 210.0);
			confirmDelete.setOnAction(e -> {
				int checkID = Integer.parseInt(deleteMemberidField.getText());

				for (int i = 0; i < clients.size(); i++) {
					if (checkID == clients.get(i).getID()) {
						clients.remove(i);
					}

				}

				primaryStage.setScene(menuScene);
				deleteMemberidField.clear();
			});

			// adding the main menu button to bottom right corner
			Button returnToMenu2 = new Button("Main Menu");
			AnchorPane.setBottomAnchor(returnToMenu2, 10.0);
			AnchorPane.setRightAnchor(returnToMenu2, 10.0);
			returnToMenu2.setOnAction(e -> {
				primaryStage.setScene(menuScene);
				deleteMemberidField.clear();
			});

			layout4.getChildren().addAll(returnToMenu2, deletingMember, deleteMemberidField, confirmDelete);
			deleteScene = new Scene(layout4, 500, 500);
			deleteScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Setup updateScene FIXME
			AnchorPane layout5 = new AnchorPane();
			layout5.setStyle("-fx-background: #DFFDFF;");
			Label label5 = new Label(" Update (UNFINISHED)");
			layout5.getChildren().addAll(label5);
			updateScene = new Scene(layout5, 300, 250);
			updateScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Setup printMemberScene FIXME
			AnchorPane layout6 = new AnchorPane();
			layout6.setStyle("-fx-background: #DFFDFF;");
			Label label6 = new Label(" Print Member (UNFINISHED)");
			layout6.getChildren().addAll(label6);
			printMemberScene = new Scene(layout6, 300, 250);
			printMemberScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Setup depositScene FIXME
			AnchorPane layout7 = new AnchorPane();
			layout7.setStyle("-fx-background: #DFFDFF;");
			Label label7 = new Label(" Deposit (UNFINISHED)");
			layout7.getChildren().addAll(label7);
			depositScene = new Scene(layout7, 300, 250);
			depositScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Setup withdrawScene FIXME
			AnchorPane layout8 = new AnchorPane();
			layout8.setStyle("-fx-background: #DFFDFF;");
			Label label8 = new Label(" Withdraw (UNFINISHED)");
			layout8.getChildren().addAll(label6);
			withdrawScene = new Scene(layout8, 300, 250);
			withdrawScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Setup transferScene FIXME
			AnchorPane layout10 = new AnchorPane();
			layout10.setStyle("-fx-background: #DFFDFF;");
			Label label10 = new Label(" Transfer (UNFINISHED)");
			layout10.getChildren().addAll(label10);
			transferScene = new Scene(layout10, 300, 250);
			transferScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Setup exitScene FIXME
			AnchorPane layout11 = new AnchorPane();
			layout11.setStyle("-fx-background: #DFFDFF;");
			Label label11 = new Label(
					" This probably shouldn't actually even go to a scene, and just close the program");
			layout11.getChildren().addAll(label11);
			exitScene = new Scene(layout11, 300, 250);
			exitScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Starts the Program (Begins at Password Scene)
			primaryStage.setScene(passwordScene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
