module com.example.dresscasualrents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.dresscasualrents to javafx.fxml;
    exports com.example.dresscasualrents;
}