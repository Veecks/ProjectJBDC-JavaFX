package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	
	public static Boolean showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
		System.out.println(alert.getResult().getText().toUpperCase() == "OK");
		return alert.getResult().getText().toUpperCase().contains("OK");
	}
	
}
