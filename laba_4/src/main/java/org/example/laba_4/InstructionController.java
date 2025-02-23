package org.example.laba_4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class InstructionController {
    @FXML
    private Label inst;
    @FXML
    private Label first;
    @FXML
    private Label second;
    @FXML
    private Button deleteButton;
    @FXML
    private Button moveUpButton;
    @FXML
    private Button moveDownButton;

    private Program program;
    private int index;
    private MainController mainController;

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setInstructionLabel(String text) {
        inst.setText(text);
        String[] parts = text.split(" ");
        if (parts.length > 1) {
            first.setText(parts[1]);
        }
        if (parts.length > 2) {
            second.setText(parts[2]);
        }
    }

    @FXML
    protected void DeleteInstruction() {
        program.remove(index);
        mainController.updateUI();
    }

    @FXML
    protected void SwapUp() {
        program.moveUp(index);
        mainController.updateUI();
    }

    @FXML
    protected void SwapDown() {
        program.moveDown(index);
        mainController.updateUI();
    }
}
