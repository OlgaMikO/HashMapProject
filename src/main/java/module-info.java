module com.example.hashmapproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hashmapproject to javafx.fxml;
    exports com.example.hashmapproject;
}