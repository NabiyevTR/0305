package ru.geekbrains.ntr_0305;

import ru.geekbrains.ntr_0305.racetrack.Road;
import ru.geekbrains.ntr_0305.racetrack.Tunnel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier sbPrepare = new CyclicBarrier(CARS_COUNT+1);
    public static final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
    public static final Lock lockWinner = new ReentrantLock();

    public static void main(String[] args) {


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            sbPrepare.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            cdlFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");


    }
}