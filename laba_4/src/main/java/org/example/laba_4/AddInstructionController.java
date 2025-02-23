package org.example.laba_4;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AddInstructionController {
    @FXML
    private TextField inst;
    @FXML
    private TextField first;
    @FXML
    private TextField second;

    private Program program;
    private MainController mainController;

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    protected void AddNewInstruction() {
        String instruction = inst.getText().trim();
        String arg1 = first.getText().trim();
        String arg2 = second.getText().trim();

        // Проверка аргументов: команды без аргументов разрешены
        if (instruction.isEmpty() || (!requiresArguments(instruction) && (!arg1.isEmpty() || !arg2.isEmpty()))) {
            throw new IllegalArgumentException("Неправильный ввод инструкции");
        }

        Command command;
        if (requiresArguments(instruction)) {
            command = new Command(instruction, arg1, arg2);
        } else {
            command = new Command(instruction);
        }

        program.add(command);
        mainController.updateUI();
    }

    private boolean requiresArguments(String instruction) {
        return switch (instruction) {
            case "ld", "st", "mv", "init" -> true;
            default -> false;
        };
    }
}
