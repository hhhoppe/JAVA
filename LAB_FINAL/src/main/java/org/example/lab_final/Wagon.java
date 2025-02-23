package org.example.lab_final;

public class Wagon {
    private Cargo cargo;

    public Wagon(Cargo cargo) {this.cargo = cargo;}

    //Описание через лямбда-выражение
    public String getCargoDescription(java.util.function.Function<Cargo, String> descriptionFunction) {
        return descriptionFunction.apply(cargo);
    }
}