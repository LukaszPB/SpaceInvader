package com.example.projekt_ztp.observer;

import java.util.ArrayList;

public class GameObserver {
    private ArrayList<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notify(Event event) {
        observers.forEach(o->o.update(event));
    }
}
