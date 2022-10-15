module co.eluni.xautil {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.jetbrains.annotations;

    opens co.eluni.xautil to javafx.fxml;
    exports co.eluni.xautil;
}