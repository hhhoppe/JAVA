package org.example.laba_4;

public interface ICpu {
    void exec(Command command);
    int getRegisterValue(int index);
    int[] getMemory();
    void resetMemory();
    public void resetRegisters();
}
