module com.example.fileclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fileclient to javafx.fxml;
    exports com.example.fileclient;
}