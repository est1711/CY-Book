module com.example.cybookfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cybookfx to javafx.fxml;
    exports com.example.cybookfx;
}