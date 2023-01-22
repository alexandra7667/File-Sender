module com.example.fileserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fileserver to javafx.fxml;
    exports com.example.fileserver;
}