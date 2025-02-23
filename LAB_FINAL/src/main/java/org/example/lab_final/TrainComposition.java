package org.example.lab_final;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrainComposition implements Iterable<Wagon> {
    private List<Locomotive> locomotives = new ArrayList<>();
    private List<Wagon> wagons = new ArrayList<>();

    public  void addLocomotive(Locomotive locomotive) {
        locomotives.add(locomotive);
    }

    public void addWagon(Wagon wagon) throws Exception {
        int maxWagons = locomotives.stream().mapToInt(Locomotive::getMaxWagons).sum();
        if (wagons.size() >= maxWagons) {
            throw new Exception("Превышен лимит вагонов");
        }
        wagons.add(wagon);
    }

    public void removeLastWagon() {
        if (!wagons.isEmpty()) {
            wagons.remove(wagons.size() - 1);
        }
    }

    public String getCompositionDescription(java.util.function.Function<Cargo, String> wagonDescriptionFunction) {
        StringBuilder description = new StringBuilder();
        for(int i = 0; i < locomotives.size(); i++) {
            Locomotive locomotive = locomotives.get(i);
            description.append("Локомотив ").append(i + 1)
                    .append(" (тяга: ").append(locomotive.getMaxWagons()).append(" вагонов)\n");
        }
        for(int i = 0; i < wagons.size(); i++) {
            Wagon wagon = wagons.get(i);
            description.append("Вагон №").append(i + 1)
                    .append(": ").append(wagon.getCargoDescription(wagonDescriptionFunction)).append("\n");
        }
        return description.toString();
    }

    @Override
    public Iterator<Wagon> iterator() {return wagons.iterator();}
}