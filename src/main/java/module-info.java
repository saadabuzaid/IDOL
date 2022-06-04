module com.example.idol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.idol to javafx.fxml;
    exports com.example.idol;
    exports com.example.idol.exceptions;
}
