package ru.geekbrains.ntr_0305.racetrack;

import ru.geekbrains.ntr_0305.Car;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
