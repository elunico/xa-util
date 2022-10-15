package co.eluni.xautil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;


public class AttrEditWindow extends Stage {
    protected ExtendedAttributeFile file;

    protected ObservableList<Tag> workingTags;

    // GUI
    protected TableView<Tag> tagTable;
    protected TableColumn<Tag, String> tagColumn;

    public AttrEditWindow(@NotNull ExtendedAttributeFile file) {
        this.file = file;
        workingTags = FXCollections.observableArrayList(file.attributes.getValue().stream().map(Tag::new).toList());


        Scene scene = buildGUI(file);
        setScene(scene);
        show();
        setHeight(640);
        setWidth(800);
    }

    static final double SPC = 5.0;

    private void setStyles(@NotNull VBox root) {
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(SPC);
        root.setPadding(new Insets(SPC));
        root.getChildren().forEach(c -> {
            if (c instanceof Button)
                ((Button) c).setPrefWidth(150 - SPC * 4);
        });
    }

    @NotNull
    private Scene buildGUI(@NotNull ExtendedAttributeFile file) {
        tagTable = new TableView<>(workingTags);
        tagColumn = new TableColumn<>("value");
        tagColumn.setCellValueFactory(data -> data.getValue().value);
        tagTable.getColumns().add(tagColumn);

        VBox root = new VBox();
        Label fileLabel = new Label("File: " + file.getName());

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        root.getChildren().addAll(fileLabel, tagTable, cancelButton, saveButton);

        Scene scene = new Scene(root);
        setStyles(root);
        return scene;
    }
}
