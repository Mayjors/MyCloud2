package com.example.algorithm.pattern.factory.abstracts;

public interface CarFactory {
    Engine createEngine();
    Seat createSeat();
    Tyre createTyre();
}
