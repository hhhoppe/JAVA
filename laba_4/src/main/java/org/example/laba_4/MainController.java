package org.example.laba_4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainController {
    @FXML
    Label a, b, c, d;
    @FXML
    GridPane AllInstructions, MemoryState;
    @FXML
    AnchorPane CountInstruction;
    @FXML
    GridPane MostPopularInstruction;

    private Program program = new Program();
    private Executer executer;
    private ICpu cpu;
    private int currentInstructionIndex = 0;
    private Map<Integer, Integer> previousMemoryState = new HashMap<>();
    private int lastExecutedInstructionIndex = -1;

    @FXML
    public void initialize() {
        cpu = new Cpu();
        executer = new Executer(cpu);
        resetProgram();
    }

    @FXML
    protected void addInstruction() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("addInstruction.fxml"));
        try {
            Pane pane = fxmlLoader.load();
            AddInstructionController controller = fxmlLoader.getController();
            controller.setProgram(program);
            controller.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Add instruction");
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void resetProgram() {
        currentInstructionIndex = 0;
        lastExecutedInstructionIndex = -1;
        cpu.resetMemory();
        cpu.resetRegisters(); // Добавлено: сброс регистров
        resetMemoryState();
        updateUI();
    }

    @FXML
    protected void executeInstruction() {
        if (currentInstructionIndex < program.size()) {
            Command command = program.get(currentInstructionIndex);
            cpu.exec(command);
            lastExecutedInstructionIndex = currentInstructionIndex;
            currentInstructionIndex++;
            updateUI();
        }
    }

    private void resetMemoryState() {
        previousMemoryState.clear();
        for (int i = 0; i < cpu.getMemory().length; i++) {
            previousMemoryState.put(i, cpu.getMemory()[i]);
        }
    }

    public void updateUI() {
        // Обновите интерфейс, чтобы отобразить текущее состояние регистров и памяти
        a.setText(currentInstructionIndex == 0 ? "*" : String.valueOf(cpu.getRegisterValue(0)));
        b.setText(currentInstructionIndex == 0 ? "*" : String.valueOf(cpu.getRegisterValue(1)));
        c.setText(currentInstructionIndex == 0 ? "*" : String.valueOf(cpu.getRegisterValue(2)));
        d.setText(currentInstructionIndex == 0 ? "*" : String.valueOf(cpu.getRegisterValue(3)));

        // Обновите интерфейс, чтобы отобразить текущие инструкции
        AllInstructions.getChildren().clear();
        for (int i = 0; i < program.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("instruction.fxml"));
            try {
                Pane instructionPane = fxmlLoader.load();
                InstructionController controller = fxmlLoader.getController();
                controller.setProgram(program);
                controller.setIndex(i);
                controller.setMainController(this);
                controller.setInstructionLabel(program.get(i).toString());
                if (i == lastExecutedInstructionIndex) {
                    instructionPane.setStyle("-fx-background-color: red;");
                }
                AllInstructions.add(instructionPane, 0, i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Обновите интерфейс, чтобы отобразить состояние памяти
        MemoryState.getChildren().clear();
        for (int i = 0; i < cpu.getMemory().length; i++) {
            Label memoryLabel = new Label(i + ": " + cpu.getMemory()[i]);
            if (!previousMemoryState.get(i).equals(cpu.getMemory()[i])) {
                memoryLabel.setTextFill(Color.RED);
            }
            MemoryState.add(memoryLabel, i % 5, i / 5);
        }
        resetMemoryState();

        // Обновите интерфейс, чтобы отобразить статистику
        MostPopularInstruction.getChildren().clear();
        Map<String, Long> instructionFrequency = program.instructionsByFrequency();
        Iterator<String> iterator = instructionFrequency.keySet().iterator();
        int row = 0;
        while (iterator.hasNext()) {
            String instruction = iterator.next();
            long frequency = instructionFrequency.get(instruction);
            Label instructionLabel = new Label(instruction + ": " + frequency);
            MostPopularInstruction.add(instructionLabel, 0, row);
            row++;
        }
    }
}