/**
    AsimiBot - Bot Base code

    Part of the Asimi project - http://sudarmuthu.com/arduino/sumi

   Copyright 2011  Sudar Muthu  (email : sudar@sudarmuthu.com)

/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <sudar@sudarmuthu.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer or coffee in return - Sudar
 * ----------------------------------------------------------------------------
 */

//TODO: Add speed control using PWM
#ifndef AsimiBot_H
#define AsimiBot_H

// Compatibility for Arduino 1.0

#if ARDUINO >= 100
    #include "Arduino.h"
#else    
    #include "WProgram.h"
#endif

class AsimiBot {

public:

    // constructor
    AsimiBot(); // empty constructor
    AsimiBot(byte el, byte e2, byte I1, byte I2, byte I3, byte I4, int delay); // full constructor

    void setEnablePins(byte el, byte e2);
    void setControlPins(byte I1, byte I2, byte I3, byte I4);
    void setDelay(int delay);

    // movement functions
    void start();
    void moveForward();
    void moveBackward();
    void turnLeft();
    void turnRight();
    void stop();

private:

    int mDelay; // delay while switching

    // enable pins
    byte mE1;
    byte mE2;

    // Control Pins
    byte mI1;
    byte mI2;
    byte mI3;
    byte mI4;
};

#endif
