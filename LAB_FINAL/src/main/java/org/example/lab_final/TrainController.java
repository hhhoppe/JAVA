package org.example.lab_final;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TrainController {
    @FXML
    private TextField locomotivePower;
    @FXML
    private TextField cargoName;
    @FXML
    private ComboBox<Integer> compositionSelector;
    @FXML
    private ListView<String> trainList;

    private List<TrainComposition> compositions = new ArrayList<>();
    private int currentCompositionIndex = 0;

    @FXML
    public void initialize() {
        compositions.add(new TrainComposition());
        compositionSelector.getItems().add(1);
        compositionSelector.getSelectionModel().selectFirst();
        updateTrainList();
    }

    @FXML
    public void addLocomotive() {
        try {
            int power = Integer.parseInt(locomotivePower.getText());
            Locomotive locomotive = new Locomotive(power);
            getCurrentComposition().addLocomotive(locomotive);
            updateTrainList();
        } catch (NumberFormatException exception) {
           showError("Ошибка", "Введите число вагонов");
        }
        locomotivePower.clear();
    }

    @FXML
    public void addWagon() {
        String name = cargoName.getText();
        if(name.isEmpty()) { name = "Пустой"; }
        Cargo cargo = new Cargo(name);
        Wagon wagon = new Wagon(cargo);
        try {
            getCurrentComposition().addWagon(wagon);
            updateTrainList();
        } catch (Exception exception) {
            showError("Ошибка", exception.getMessage());
        }
        cargoName.clear();
    }

    @FXML
    public void switchComposition() {
        currentCompositionIndex = compositionSelector.getSelectionModel().getSelectedIndex();
        updateTrainList();
    }

    @FXML
    public void createNewComposition() {
        TrainComposition newComposition = new TrainComposition();
        compositions.add(newComposition);
        compositionSelector.getItems().add(compositions.size());
        compositionSelector.getSelectionModel().selectLast();
        updateTrainList();
    }

    @FXML
    public void removeLastWagon() {
        TrainComposition currentComposition = getCurrentComposition();
        currentComposition.removeLastWagon();
        updateTrainList();
    }

    @FXML
    public void removeSelectComposition() {
        if (compositions.size() <= 1) {
            showError("Ошибка", "Нельзя удалить единственный состав");
        }
        compositions.remove(currentCompositionIndex);
        compositionSelector.getItems().remove(currentCompositionIndex);
        if (currentCompositionIndex >= compositions.size()) {
            currentCompositionIndex = compositions.size() - 1;
        }
        updateCompositionNumber();
        compositionSelector.getSelectionModel().select(currentCompositionIndex);
        updateTrainList();
    }

    private void updateCompositionNumber() {
        compositionSelector.getItems().clear();
        for (int i = 0; i < compositions.size(); i++) {
            compositionSelector.getItems().add(i + 1);
        }
    }

    private TrainComposition getCurrentComposition() {
        return compositions.get(currentCompositionIndex);
    }

    private void updateTrainList() {
        trainList.getItems().clear();
        TrainComposition currentComposition = getCurrentComposition();
        Function<Cargo, String> wagonDescriptionFunction = cargo -> "Товар: " + cargo.getName();
        trainList.getItems().add("Состав поезда " + (currentCompositionIndex + 1) + ":");
        trainList.getItems().add(currentComposition.getCompositionDescription(wagonDescriptionFunction));
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}