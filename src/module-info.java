module lab04_pop {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens Application to javafx.graphics, javafx.fxml;
}