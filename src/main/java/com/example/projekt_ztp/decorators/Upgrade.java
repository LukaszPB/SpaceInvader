package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;

public interface Upgrade {
    double move(double x);
    Bullet shot(Bullet bullet);
}
