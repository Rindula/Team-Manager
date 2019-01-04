package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.omg.CORBA.portable.ValueOutputStream;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextField newTeam;

	@FXML
	private ListView<String> info_num;
	@FXML
	private ListView<String> info_t1;
	@FXML
	private ListView<String> info_t2;
	@FXML
	private ListView<String> info_feld;

	@FXML
	private Button button_add;
	@FXML
	private TextField felder;

	@FXML
	private ListView<String> list;

	public Controller() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	public void initialize() {
		felder.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue,
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	felder.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}

	@FXML
	public void work() {
		List<String> l = new ArrayList<>();
		List<String[]> t = new ArrayList<>();

		for (int i = 0; i < list.getItems().size(); i++) {
			l.add(list.getItems().get(i));
		}

		for (int i = 0; i < l.size() - 1; i++) {
			for (int j = i + 1; j < l.size(); j++) {
				t.add(new String[] { l.get(i), l.get(j) });
			}
		}

		for (int i = 0; i < (int) (Math.random() * Integer.MAX_VALUE); i++) {
			Collections.shuffle(t);
		}

		info_num.getItems().clear();
		info_t1.getItems().clear();
		info_t2.getItems().clear();
		info_feld.getItems().clear();

		info_num.getItems().add("Spiel");
		info_t1.getItems().add("Team 1");
		info_t2.getItems().add("Team 2");
		info_feld.getItems().add("Feld");

		if (felder.getText().equals("")) {
			felder.setText("1");
		}

		int cnt = 0;

		for (int i = 0; i < t.size(); i++) {
			if ((i%Integer.valueOf(felder.getText())) == 0) {
				cnt++;
			}
			info_num.getItems().add((cnt) + "");
			info_t1.getItems().add(t.get(i)[0]);
			info_t2.getItems().add(t.get(i)[1]);
			info_feld.getItems().add((i%Integer.valueOf(felder.getText()) + 1) + "");
		}

	}

	@FXML
	public void addToList() {
		if (!newTeam.getText().trim().equals("")) {
			list.getItems().add(newTeam.getText());
			newTeam.setText("");
		}
	}

	@FXML
	public void deleteFromList() {
		if (!list.getSelectionModel().isEmpty()) {
			list.getItems().remove(list.getSelectionModel().getSelectedIndex());
		}
	}

	@FXML
	public void groupSelect_1() {
		info_t1.getSelectionModel().select(info_num.getSelectionModel().getSelectedIndex());
		info_t2.getSelectionModel().select(info_num.getSelectionModel().getSelectedIndex());
		info_feld.getSelectionModel().select(info_t1.getSelectionModel().getSelectedIndex());
	}

	@FXML
	public void groupSelect_2() {
		info_num.getSelectionModel().select(info_t1.getSelectionModel().getSelectedIndex());
		info_t2.getSelectionModel().select(info_t1.getSelectionModel().getSelectedIndex());
		info_feld.getSelectionModel().select(info_t1.getSelectionModel().getSelectedIndex());
	}

	@FXML
	public void groupSelect_3() {
		info_t1.getSelectionModel().select(info_t2.getSelectionModel().getSelectedIndex());
		info_num.getSelectionModel().select(info_t2.getSelectionModel().getSelectedIndex());
		info_feld.getSelectionModel().select(info_t2.getSelectionModel().getSelectedIndex());
	}

	@FXML
	public void groupSelect_4() {
		info_t1.getSelectionModel().select(info_feld.getSelectionModel().getSelectedIndex());
		info_num.getSelectionModel().select(info_feld.getSelectionModel().getSelectedIndex());
		info_t2.getSelectionModel().select(info_feld.getSelectionModel().getSelectedIndex());
	}

}
