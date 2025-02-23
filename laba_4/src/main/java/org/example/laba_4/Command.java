package org.example.laba_4;

public class Command {
    String instruction;
    String[] arguments;

    public Command(String instruction, String... arguments) {
        this.instruction = instruction;
        this.arguments = arguments;
    }

    public String getInstruction() {
        return instruction;
    }

    public String[] getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return instruction + " " + String.join(" ", arguments);
    }
}
