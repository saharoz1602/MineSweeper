package mines;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mines.Mines.Point;

public class MineFX extends Application {

	private Button btnReset;
	private Button btnExit;

	private TextField txtWidth;
	private TextField txtHeight;
	private TextField txtMines;

	private GridPane boardGrid;
	private Pane topPane;

	private GridPane gloabalGrid;

	private MineController mineController;

	private Mines mine;
	private Button[][] buttonsBoard;

	private int width;
	private int height;
	private int minesNumber;

	private boolean yes_no_FLAG;

	@SuppressWarnings("unused")
	private final int maxWidth = 600;

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		stage.initStyle(StageStyle.UNDECORATED);

		StackPane root1;
		Scene scene;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("backup_gameGui.fxml"));
			root1 = loader.load();
			mineController = loader.getController();

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		yes_no_FLAG = false;
		// set pointers to all of my controller data.
		btnReset = mineController.getBtnReset();

		txtWidth = mineController.getTxtWidth();
		txtHeight = mineController.getTxtHeight();
		txtMines = mineController.getTxtMines();

		gloabalGrid = mineController.getGlobalGrid();
		boardGrid = mineController.getBoardGrid();
		topPane = mineController.getDataPane();

		// TO DELETE //
		txtWidth.setText("11");
		txtHeight.setText("11");
		txtMines.setText("11");

		/*************** set exit button *****************/
		btnExit = new Button();
		btnExit.setPrefSize(32, 32);
		btnExit.relocate(10, 10);

		// click to exit form.
		btnExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
			}
		});

		// set image for exit button.
		BackgroundImage myBI = new BackgroundImage(new Image("images/exit.png", 32, 32, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		btnExit.setBackground(new Background(myBI));

		topPane.getChildren().add(btnExit);
		gloabalGrid.setHalignment(boardGrid, HPos.CENTER);

		/*************** set reset button *****************/

		btnReset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtWidth.getText().equals("") || txtHeight.getText().equals("") || txtMines.getText().equals("")) {
					// bad input , set pop up window to the user.
					createPopUpMessage("Sorry , first fill all text fields..", "wrong");
				} else {
					// all good set the mine.
					createNewGame();
					setOnClickBoard();
				}

			}
		});

		scene = new Scene(root1);
		scene.getStylesheets().add("style.css");
		stage.setScene(scene);
		stage.show();
	}

	public void toggleFlag(int i, int j) {
		mine.toggleFlag(i, j);
		buttonsBoard[i][j].setText(mine.getBoard()[i][j].toString());
	}

	public void setButtonsOpen() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Point currentPoint = mine.getBoard()[i][j];
				if (currentPoint.isOpen()) {
					buttonsBoard[i][j].setText(currentPoint.toString());
					switch (currentPoint.getCountOfMinesArround()) {
					case 0: {
						buttonsBoard[i][j].setStyle("" + "-fx-background-color:white");
					}
						break;
					case 1: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#8ACAFE");
					}
						break;
					case 2: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#00ff00");
					}
						break;
					case 3: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#2e8b57");
					}
						break;
					case 4: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#bd3b3b");
					}
						break;
					case 5: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#377fc3");
					}
						break;
					case 6: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#55ff55");
					}
						break;
					case 7: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#ffbf23");
					}
						break;
					case 8: {
						buttonsBoard[i][j].setStyle("" + "-fx-color:#ccccff");
					}
						break;
					}
					buttonsBoard[i][j].setDisable(true);
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void showAll(boolean b) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Point currentPoint = mine.getBoard()[i][j];
				if (currentPoint.toString().equals(" ")) {
					buttonsBoard[i][j].setText("");
				} else {
					buttonsBoard[i][j].setText(currentPoint.toString());
				}
			}
		}
	}

	private void createNewGame() {
		boardGrid.getChildren().clear(); // clear all children before set new game.
		boardGrid.setStyle("" + "-fx-padding:5px");
//		boardGrid.setStyle("" + "-fx-background-color:red;");
		width = Integer.parseInt(txtWidth.getText());
		height = Integer.parseInt(txtHeight.getText());
		boardGrid.setPrefWidth(850);
		boardGrid.setPrefHeight(650);
		minesNumber = Integer.parseInt(txtMines.getText());
		mine = new Mines(width, height, minesNumber);
		mine.setShowAll(true);
		mine.setShowAll(false);
		buttonsBoard = new Button[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				buttonsBoard[i][j] = new Button();
				double sizeWidth = boardGrid.getPrefWidth() / width;// > 30 ? 30 : (int)boardGrid.getWidth() / width;
				double sizeHeight = boardGrid.getPrefHeight() / height;
				buttonsBoard[i][j].setPrefSize(sizeWidth, sizeHeight);
				buttonsBoard[i][j].setId(i + "," + j);
				boardGrid.add(buttonsBoard[i][j], i + 1, j + 1);
			}

		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				buttonsBoard[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						MouseButton btnClick = event.getButton();
						Button currentButton = (Button) event.getSource();
						int i = Integer.parseInt(currentButton.getId().split(",")[0] + "");
						int j = Integer.parseInt(currentButton.getId().split(",")[1] + "");

						if (btnClick == MouseButton.PRIMARY) {
							// LEFT CLICK - regular click
							if (mine.open(i, j)) {
								setButtonsOpen();
								if (mine.isDone()) {
									gameOver("win");
								}
							} else {
								openAllMines();
								gameOver("lose");
							}
						}
						if (btnClick == MouseButton.SECONDARY) {
							// RIGHT CLICK - to toggle flags.
							toggleFlag(i, j);
						}
					}
				});
			}
		}

	}

	private void setOnClickBoard() {

	}

	private void gameOver(String result) {
		switch (result) {
		case "win": {
			createPopUpMessage("Your are the winner !!!", "win");
		}
			break;
		case "lose": {
			createPopUpMessage("Your are a loser !!! go home..", "lose");
		}
			break;
		}
		if (yes_no_FLAG) {
			createNewGame();
		}

	}

	private void createPopUpMessage(String messege, String type) {
		// return true to create new game , and false to back.
		// type = 'wrong' for empty values , 'win' for winner image , 'lose' for losser
		// image.
		yes_no_FLAG = false;

		final Stage dialog = new Stage();
		dialog.initStyle(StageStyle.UNDECORATED);

		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.setId("dialog");

		Label lblImage = new Label();
		lblImage.setPrefSize(50, 50);
		BackgroundImage myBI = new BackgroundImage(new Image("images/" + type + ".png", 50, 50, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		lblImage.setBackground(new Background(myBI));
		dialogVbox.getChildren().add(lblImage);

		dialogVbox.getChildren().add(new Text(messege));

		Button btnYes = new Button("Ok");
		btnYes.setPrefSize(70, 40);
		btnYes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				yes_no_FLAG = true;
				dialog.close();
			}
		});

		dialogVbox.getChildren().add(btnYes);

		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();

	}

	private void openAllMines() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Point currentPoint = mine.getBoard()[i][j];
				if (currentPoint.isMine()) {
					buttonsBoard[i][j].setText("X");
					buttonsBoard[i][j].setStyle("" + "-fx-background-color:red");
				}
			}
		}
	}

}
