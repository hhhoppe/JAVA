package org.example;

public class Command {
    String instruction;
    String[] arguments;

    public Command(String instruction, String... arguments) {
        this.instruction = instruction;
        this.arguments = arguments;
    }
}