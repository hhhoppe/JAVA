module org.example.laba_4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.laba_4 to javafx.fxml;
    exports org.example.laba_4;
}