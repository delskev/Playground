package be.civadis.gpdoc.solid.lsp;

public class MotorCar implements Car {
    private Engine engine;

    public void turnOnEngine(){
        engine.turnOn();
    }

    public void accelerate(){
        engine.accelerate(5);
    }
}
