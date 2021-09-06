module projeto_jdbc_javafx {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	exports gui;
	opens gui to javafx.fxml;
	
	exports model.entities;
	opens model.entities to javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
