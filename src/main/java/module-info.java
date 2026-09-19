module com.loancalc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.loancalc to javafx.fxml;
    exports com.loancalc;
}