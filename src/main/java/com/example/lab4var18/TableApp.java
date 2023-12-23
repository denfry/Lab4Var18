package com.example.lab4var18;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;

public class TableApp extends Application {
    private final TableView<Buildingmaterials> table = new TableView<>();
    private final ObservableList<Buildingmaterials> buildingMaterials = FXCollections.observableArrayList();

    private boolean ascendingSort = true;

    private final TextField typeTextField = new TextField();
    private final TextField priceTextField = new TextField();
    private final TextField sumTextField = new TextField();
    private final TextField brandTextField = new TextField();
    private final CheckBox isFireproofCheckBox = new CheckBox("Is Fireproof");
    private final ComboBox<String> buildingMaterialsTypeComboBox = new ComboBox<>();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Building Materials Table App");

        TableColumn<Buildingmaterials, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Buildingmaterials, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Buildingmaterials, Integer> sumColumn = getSumColumn();

        TableColumn<Buildingmaterials, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        TableColumn<Buildingmaterials, Boolean> isFireproofColumn = getIsFireproofColumn();
        TableColumn<Buildingmaterials, String> buildingMaterialsTypeTableColumn = new TableColumn<>("Building Materials Type");
        buildingMaterialsTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("buildingMaterialsType"));

        isFireproofColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        buildingMaterialsTypeTableColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));


        table.getColumns().addAll(typeColumn, priceColumn, sumColumn, brandColumn, isFireproofColumn, buildingMaterialsTypeTableColumn);
        table.setItems(buildingMaterials);

        Button addButton = new Button("Add Building Material");
        addButton.setOnAction(e -> addSportEquipment());

        Button addFromArrayButton = new Button("Add Building Materials from Array");
        addFromArrayButton.setOnAction(e -> addAllSportEquipmentFromArray());

        Button sortButton = new Button("Sort by Price");
        sortButton.setOnAction(e -> sortSportEquipmentByPrice());


        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(sortButton);
        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(
                new Label("Type:"),
                typeTextField,
                new Label("Price:"),
                priceTextField,
                new Label("Brand:"),
                brandTextField,
                new Label("Sum:"),
                sumTextField,
                new Label("Is Fireproof:"),
                isFireproofCheckBox,
                new Label("Building Materials Type:"),
                buildingMaterialsTypeComboBox,
                addButton
        );


        buildingMaterialsTypeComboBox.getItems().addAll("Wallpaper", "Paint");
        buildingMaterialsTypeComboBox.setValue("Wallpaper");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(table, inputBox, addFromArrayButton, buttonBox);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableColumn<Buildingmaterials, Integer> getSumColumn() {
        TableColumn<Buildingmaterials, Integer> sumColumn = new TableColumn<>("Sum");
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));

        sumColumn.setCellFactory(column -> new TableCell<Buildingmaterials, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item));
                    int sumValue = item;

                    if (sumValue >= 20) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else {
                        setStyle("-fx-background-color: lightcoral;");
                    }
                }
            }
        });

        return sumColumn;
    }


    private static TableColumn<Buildingmaterials, Boolean> getIsFireproofColumn() {
        TableColumn<Buildingmaterials, Boolean> isFireproofColumn = new TableColumn<>("Is Fireproof");
        isFireproofColumn.setCellValueFactory(cellData -> {
            Buildingmaterials buildingMaterial = cellData.getValue();
            if (buildingMaterial instanceof Paint) {
                return new SimpleBooleanProperty(((Paint) cellData.getValue()).isFireproof());
            }
            return new SimpleBooleanProperty().asObject();
        });
        isFireproofColumn.setCellFactory(column -> new CheckBoxTableCell<Buildingmaterials, Boolean>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setAlignment(Pos.CENTER);
                setAlignment(Pos.CENTER);

                checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                    if (isSelected) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else {
                        setStyle("-fx-background-color: lightcoral;");
                    }
                });
            }

            @Override
            public void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                    setStyle("");
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                    if (!item) {
                        setStyle("-fx-background-color: lightcoral;");
                    } else {
                        setStyle("-fx-background-color: lightgreen;");
                    }
                }
            }
        });
        return isFireproofColumn;
    }


    private void addSportEquipment() {
        try {
            String type = typeTextField.getText();
            String brand = brandTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int sum = Integer.parseInt(sumTextField.getText());
            boolean isFireproof = isFireproofCheckBox.isSelected();
            String buildingMaterialsType = buildingMaterialsTypeComboBox.getValue();


            if ("Paint".equals(buildingMaterialsType)) {
                if (isFireproof) {
                    buildingMaterials.add(new Paint(type, brand, price, sum, 30, isFireproof, "Paint"));
                } else {
                    buildingMaterials.add(new Paint(type, brand, price, sum, 30, isFireproof, "Paint"));
                }
            } else if ("Wallpaper".equals(buildingMaterialsType) && !isFireproof) {
                buildingMaterials.add(new Wallpaper(type, brand, price, sum, "бумажные", "Wallpaper"));
            } else {
                // Обработка некорректного выбора типа материала
                throw new IllegalArgumentException("Invalid material type selected");
            }

            typeTextField.clear();
            priceTextField.clear();
            sumTextField.clear();
            brandTextField.clear();
            isFireproofCheckBox.setSelected(false);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter a valid number.");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid combination");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    private void addAllSportEquipmentFromArray() {
        buildingMaterials.addAll(getBuildingMaterialsFromArray());
    }

    private ObservableList<Buildingmaterials> getBuildingMaterialsFromArray() {
        ObservableList<Buildingmaterials> buildingMaterialsArray = FXCollections.observableArrayList();
        buildingMaterialsArray.add(new Paint("краска", "kudo", 10000, 9, 3, true,
                "Paint"));
        buildingMaterialsArray.add(new Paint("краска", "startone", 11000, 1, 4, true,
                "Paint"));
        buildingMaterialsArray.add(new Paint("краска", "marshall", 10000, 3, 5, true,
                "Paint"));
        buildingMaterialsArray.add(new Paint("краска", "sitprof", 23000, 4, 6, true,
                "Paint"));
        buildingMaterialsArray.add(new Paint("краска", "kudo", 3000, 1, 10, true,
                "Paint"));
        buildingMaterialsArray.add(new Paint("краска", "artway", 30000, 8, 15, true,
                "Paint"));
        buildingMaterialsArray.add(new Paint("краска", "artway", 20000, 1, 20, true,
                "Paint"));
        buildingMaterialsArray.add(new Wallpaper("обои", "erismann", 7300, 2, "флизелиновые",
                "Wallpaper"));
        buildingMaterialsArray.add(new Wallpaper("обои", "elysium", 71000, 1, "бумажные",
                "Wallpaper"));
        buildingMaterialsArray.add(new Wallpaper("обои", "victoria", 10000, 2, "виниловые",
                "Wallpaper"));


        return buildingMaterialsArray;
    }

    private void sortSportEquipmentByPrice() {
        ascendingSort = !ascendingSort;
        Comparator<Buildingmaterials> comparator = ascendingSort
                ? Comparator.comparingDouble(Buildingmaterials::getPrice)
                : (r1, r2) -> Double.compare(r2.getPrice(), r1.getPrice());

        buildingMaterials.sort(comparator);
    }
}