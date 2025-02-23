package org.example.laba_4;

import java.util.*;
import java.util.stream.Collectors;

public class Program implements Iterable<Command> {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command command) {
        commands.add(command);
    }

    public void remove(int index) {
        commands.remove(index);
    }

    public void moveUp(int index) {
        if (index > 0) {
            Collections.swap(commands, index, index - 1);
        }
    }

    public void moveDown(int index) {
        if (index < commands.size() - 1) {
            Collections.swap(commands, index, index + 1);
        }
    }

    public String mostPopularInstruction() {
        return commands.stream()
                .collect(Collectors.groupingBy(Command::getInstruction, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Нет инструкций");
    }

    public Map<String, Long> instructionsByFrequency() {
        return commands.stream()
                .collect(Collectors.groupingBy(Command::getInstruction, Collectors.counting()));
    }

    public Command get(int index) {
        return commands.get(index);
    }

    public int size() {
        return commands.size();
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}


