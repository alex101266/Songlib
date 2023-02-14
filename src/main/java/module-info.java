module com.assignment1.songlib {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.assignment1.songlib to javafx.fxml;
    exports com.assignment1.songlib;
}