package org.example;

public class Cpu implements ICpu {
    private final int[] registers = new int[4]; // для 4х регистров a,b,c,d
    private final int[] memory = new int[1024]; // для 1024 эл-ов памяти

    @Override
    public void exec(Command command) {
        switch (command.instruction) {
            case "ld":
                ld(command.arguments);
                break;
            case "st":
                st(command.arguments);
                break;
            case "mv":
                mv(command.arguments);
                break;
            case "init":
                init(command.arguments);
                break;
            case "print":
                print();
                break;
            case "add":
                add();
                break;
            case "sub":
                sub();
                break;
            case "mult":
                mult();
                break;
            case "div":
                div();
                break;
            default:
                System.out.println("Нет такой функции ( " + command.instruction + " )");
        }
    }

    private int getRegisterIndex(String register) {
        switch (register) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            default:
                throw new IllegalArgumentException("Нет такого регистра ( " + register + " )");
        }
    }

    private void ld(String[] arguments) { // из памяти в рег (load)
        int register = getRegisterIndex(arguments[0]);
        int address = Integer.parseInt(arguments[1]);
        if (address >= 0 && address < memory.length) {
            registers[register] = memory[address];
        } else {
            System.out.println("Неверный адрес памяти: " + address);
        }
    }

    private void st(String[] arguments) { // из рег в память (store)
        int register = getRegisterIndex(arguments[0]);
        int address = Integer.parseInt(arguments[1]);
        if (address >= 0 && address < memory.length) {
            memory[address] = registers[register];
        } else {
            System.out.println("Неверный адрес памяти: " + address);
        }
    }

    private void mv(String[] arguments) {
        int register1 = getRegisterIndex(arguments[0]);
        int register2 = getRegisterIndex(arguments[1]);
        registers[register1] = registers[register2];
    }

    private void init(String[] arguments) {
        int address = Integer.parseInt(arguments[0]);
        int value = Integer.parseInt(arguments[1]);
        if (address >= 0 && address < memory.length) {
            memory[address] = value;
        } else {
            System.out.println("Неверный адрес памяти: " + address);
        }
    }

    private void print() {
        System.out.println("Регистры:");
        System.out.println("a = " + registers[0]);
        System.out.println("b = " + registers[1]);
        System.out.println("c = " + registers[2]);
        System.out.println("d = " + registers[3]);
    }

    private void add() {
        registers[3] = registers[0] + registers[1];
    }

    private void sub() {
        registers[3] = registers[0] + registers[1];
    }

    private void mult() {
        registers[3] = registers[0] * registers[1];
    }

    private void div() {
        if (registers[1] != 0) {
            registers[3] = registers[0] / registers[1];
        } else {
            System.out.println("Нельзя делить на ноль");
        }
    }
}