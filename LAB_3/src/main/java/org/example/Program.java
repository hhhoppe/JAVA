package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Program implements Iterable<Command> {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command command) {
        commands.add(command);
    }

    public String mostPopularInstruction() {
        return String.valueOf(String.valueOf(commands
                .stream()
                .collect(Collectors.groupingBy(c -> c.instruction, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Ноль инструкций")));
    }

    public int[] memoryRange() {
        OptionalInt min = commands
                .stream()
                .flatMapToInt(c -> Arrays.stream(c.arguments)
                        .filter(arg -> arg.matches("\\d+"))
                        .mapToInt(Integer::parseInt))
                .min();

        OptionalInt max = commands
                .stream()
                .flatMapToInt(c -> Arrays.stream(c.arguments)
                        .filter(arg -> arg.matches("\\d+"))
                        .mapToInt(Integer::parseInt))
                .max();

        return new int[]{min.orElse(0), max.orElse(0)};
    }

    public List<String> instructionsByFrequency() {
        return commands
                .stream()
                .collect(Collectors.groupingBy(c -> c.instruction, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override // iterable
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}