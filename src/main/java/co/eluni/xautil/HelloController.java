package co.eluni.xautil;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HelloController {
    public TableView<ExtendedAttributeFile> mainTable;
    @FXML
    private Label cwdLabel;

    public HelloController() {

    }

    public void chooseDirectory(ActionEvent actionEvent) throws IOException {
        var chooser = new DirectoryChooser();
        var dir = chooser.showDialog(null);

        cwdLabel.setText(dir.getAbsolutePath());

        mainTable.setRowFactory(view -> {
            var row = new TableRow<ExtendedAttributeFile>();
            row.setOnMouseClicked(event -> new AttrEditWindow(view.getItems().get(row.getIndex())));
            return row;
        });

        var nameColumn = new TableColumn<ExtendedAttributeFile, String>();
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        mainTable.getColumns().add(nameColumn);

        var attrsColumn = new TableColumn<ExtendedAttributeFile, List<String>>();
        attrsColumn.setCellValueFactory(data -> data.getValue().attributesProperty());
        mainTable.getColumns().add(attrsColumn);


        List<ExtendedAttributeFile> list = Files.list(Path.of(dir.toURI())).map(f -> new ExtendedAttributeFile(f.toFile())).toList();
        mainTable.setItems(FXCollections.observableList(list));
    }
}