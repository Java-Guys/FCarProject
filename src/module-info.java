module FCar.Project.Git {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires java.sql;
    requires jlfgr;

    opens model;
    opens store;
    opens UI.carUI;
    opens UI.customerUI;
    opens UI.rentalUI;
    opens UI;
}
