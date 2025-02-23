package org.example.laba_4;

public class Executer {
    private final ICpu cpu;

    public Executer(ICpu cpu) {
        this.cpu = cpu;
    }

    public void run(Program program) {
        for (Command command : program) {
            cpu.exec(command);
        }
    }
}
