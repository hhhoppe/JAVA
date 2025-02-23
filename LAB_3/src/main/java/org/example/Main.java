package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] arguments) {
        Program program = new Program();
        program.add(new Command("init", "10", "5"));
        program.add(new Command("init", "11", "25"));
        program.add(new Command("init", "12", "10"));
        program.add(new Command("ld", "a", "10"));
        program.add(new Command("ld", "b", "11"));
        program.add(new Command("ld", "c", "12"));
        program.add(new Command("add"));
        program.add(new Command("print"));

        for (Command c : program) {
            System.out.println(c);
        }

        System.out.println("Наиболее популярная инструкция: " + program.mostPopularInstruction());
        System.out.println("Диапазон памяти: " + Arrays.toString(program.memoryRange()));
        System.out.println("Инструкции по частоте: " + program.instructionsByFrequency());
        System.out.println("Команды программы:");

        ICpu cpu = new Cpu();
        Executer exec = new Executer(cpu);
        exec.run(program);
    }
}