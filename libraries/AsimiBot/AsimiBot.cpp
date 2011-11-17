/**
    AsimiBot - Bot Base code

    Part of the Asimi project - http://sudarmuthu.com/arduino/asimi

    Copyright 2011  Sudar Muthu  (email : sudar@sudarmuthu.com)

/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <sudar@sudarmuthu.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer or coffee in return - Sudar
 * ----------------------------------------------------------------------------
 */

#include "AsimiBot.h"

/**
 * Full Constructor
 */
AsimiBot::AsimiBot(byte e1, byte e2, byte I1, byte I2, byte I3, byte I4, int delay) {
    setEnablePins(e1, e2);
    setControlPins(I1, I2, I3, I4);
    setDelay(delay);
}

/**
 * Default Constructor
 */
AsimiBot::AsimiBot() {
    setEnablePins(1, 2);
    setControlPins(3, 4, 5, 6);
    setDelay(10);
}

/**
 * Set Enable pins
 */
void AsimiBot::setEnablePins(byte e1, byte e2) {
    mE1 = e1;
    mE2 = e2;

    pinMode(mE1, OUTPUT);
    pinMode(mE2, OUTPUT);
}

/**
 * Set Control pins
 */
void AsimiBot::setControlPins(byte I1, byte I2, byte I3, byte I4) {
    mI1 = I1;
    mI2 = I2;
    mI3 = I3;
    mI4 = I4;

    pinMode(mI1, OUTPUT);
    pinMode(mI2, OUTPUT);
    pinMode(mI3, OUTPUT);
    pinMode(mI4, OUTPUT);
}

/**
 * Set delay value
 */
void AsimiBot::setDelay(int delay) {
    mDelay = delay;
}

/**
 * Start the bot and move forward
 */
void AsimiBot::start() {
    digitalWrite(mE1, HIGH);
    digitalWrite(mE2, HIGH);

    delay(mDelay);

    digitalWrite(mI1, HIGH);
    digitalWrite(mI2, LOW);
    digitalWrite(mI3, HIGH);
    digitalWrite(mI4, LOW);
}

/**
 * Move forward
 */
void AsimiBot::moveForward() {
    start();
}

/**
 * Move backward
 */
void AsimiBot::moveBackward() {
    digitalWrite(mE1, LOW);
    digitalWrite(mE2, LOW);

    delay(mDelay);

    digitalWrite(mE1, HIGH);
    digitalWrite(mE2, HIGH);

    digitalWrite(mI1, LOW);
    digitalWrite(mI2, HIGH);
    digitalWrite(mI3, LOW);
    digitalWrite(mI4, HIGH);

}

/**
 * Turn Left
 */
void AsimiBot::turnLeft() {
    digitalWrite(mE1, LOW);
    digitalWrite(mE2, LOW);

    delay(mDelay);

    digitalWrite(mE1, LOW);
    digitalWrite(mE2, HIGH);

    digitalWrite(mI1, HIGH);
    digitalWrite(mI2, LOW);
    digitalWrite(mI3, HIGH);
    digitalWrite(mI4, LOW);

}

/**
 * Turn Right
 */
void AsimiBot::turnRight() {
    digitalWrite(mE1, LOW);
    digitalWrite(mE2, LOW);

    delay(mDelay);

    digitalWrite(mE1, HIGH);
    digitalWrite(mE2, LOW);

    digitalWrite(mI1, HIGH);
    digitalWrite(mI2, LOW);
    digitalWrite(mI3, HIGH);
    digitalWrite(mI4, LOW);

}

/**
 * Stop Bot
 */
void AsimiBot::stop() {
    digitalWrite(mE1, LOW);
    digitalWrite(mE2, LOW);
}
