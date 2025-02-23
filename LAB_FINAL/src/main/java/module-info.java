module org.example.lab_final {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.lab_final to javafx.fxml;
    exports org.example.lab_final;
}