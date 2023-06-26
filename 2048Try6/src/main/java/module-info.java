module com.example._2048try6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example._2048try6 to javafx.fxml;
    exports com.example._2048try6;
}