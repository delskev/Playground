package be.civadis.gpdoc.solid.lsp;

public interface Engine {
    boolean turnOn();
    boolean turnOff();
    void accelerate(double velocity);
}
