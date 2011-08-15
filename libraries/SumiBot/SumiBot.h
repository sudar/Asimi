/**
    SumiBot - Bot Base code

    Part of the Sumi project - http://sudarmuthu.com/arduino/sumi

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
#ifndef SumiBot_H
#define SumiBot_H

#include <WProgram.h>
class SumiBot {

public:

    // constructor
    SumiBot(); // empty constructor
    SumiBot(byte el, byte e2, byte I1, byte I2, byte I3, byte I4, int delay); // full constructor

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
