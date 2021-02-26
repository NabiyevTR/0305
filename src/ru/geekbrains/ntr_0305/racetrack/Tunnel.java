package ru.geekbrains.ntr_0305.racetrack;

import ru.geekbrains.ntr_0305.Car;
import ru.geekbrains.ntr_0305.Main;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore smfTunnel;
    private static final int MAX_CARS_IN_TUNNEL = Main.CARS_COUNT < 2 ? 1 : Main.CARS_COUNT / 2;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        smfTunnel = new Semaphore(MAX_CARS_IN_TUNNEL);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smfTunnel.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smfTunnel.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
