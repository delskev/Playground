package be.civadis.gpdoc.solid.lsp;

public class ElectricCar implements Car {
    Engine electricEngine;

    @Override
    public void turnOnEngine() {
        electricEngine.turnOn();
    }

    @Override
    public void accelerate() {
        electricEngine.accelerate(2.0);
    }
}
