/**
 * Sample Skeleton for 'gameGui.fxml' Controller Class
 */

package mines;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MineController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="globalGrid"
	private GridPane globalGrid; // Value injected by FXMLLoader

	@FXML // fx:id="boardGrid"
	private GridPane boardGrid; // Value injected by FXMLLoader

	@FXML // fx:id="dataPane"
	private Pane dataPane; // Value injected by FXMLLoader

	@FXML // fx:id="btnReset"
	private Button btnReset; // Value injected by FXMLLoader

	@FXML // fx:id="lblHeight"
	private Label lblHeight; // Value injected by FXMLLoader

	@FXML // fx:id="lblMines"
	private Label lblMines; // Value injected by FXMLLoader

	@FXML // fx:id="txtWidth"
	private TextField txtWidth; // Value injected by FXMLLoader

	@FXML // fx:id="txtHeight"
	private TextField txtHeight; // Value injected by FXMLLoader

	@FXML // fx:id="txtMines"
	private TextField txtMines; // Value injected by FXMLLoader

	@FXML // fx:id="lblWidth"
	private Label lblWidth; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert globalGrid != null : "fx:id=\"globalGrid\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert boardGrid != null : "fx:id=\"boardGrid\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert dataPane != null : "fx:id=\"dataPane\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert lblHeight != null : "fx:id=\"lblHeight\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert lblMines != null : "fx:id=\"lblMines\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert txtWidth != null : "fx:id=\"txtWidth\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert txtHeight != null : "fx:id=\"txtHeight\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert txtMines != null : "fx:id=\"txtMines\" was not injected: check your FXML file 'gameGui.fxml'.";
		assert lblWidth != null : "fx:id=\"lblWidth\" was not injected: check your FXML file 'gameGui.fxml'.";

	}

	public Pane getDataPane() {
		return dataPane;
	}
	
	public GridPane getGlobalGrid() {
		return globalGrid;
	}

	public GridPane getBoardGrid() {
		return boardGrid;
	}

	public Button getBtnReset() {
		return btnReset;
	}

	public TextField getTxtWidth() {
		return txtWidth;
	}

	public TextField getTxtHeight() {
		return txtHeight;
	}

	public TextField getTxtMines() {
		return txtMines;
	}

}
