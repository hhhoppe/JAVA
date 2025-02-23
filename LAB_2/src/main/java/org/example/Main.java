package org.example;

public class Main {
    public static void main(String[] arguments) {
        Command[] program = {
                new Command("init", "10", "5"),
                new Command("init", "11", "25"),
                new Command("init", "12", "10"),
                new Command("ld", "a", "10"),
                new Command("ld", "b", "11"),
                new Command("ld", "c", "12"),
                new Command("add"),
                new Command("print")
        };

        ICpu cpu = new Cpu();
        Executer exec = new Executer(cpu);
        exec.run(program);
    }
}