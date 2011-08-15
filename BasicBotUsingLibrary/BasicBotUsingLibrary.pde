/**
    BasicBot - The basic working bot, using library.

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
#include <SumiBot.h>

SumiBot sumiBot;
void setup() {

    // initialize Sumi bot
    sumiBot.setEnablePins(2, 3);
    sumiBot.setControlPins(4, 5, 6, 7);

    pinMode(13, OUTPUT);

}

void loop() {
    digitalWrite(13, LOW);
    sumiBot.moveForward();

    delay(10000);

    // change direction
    digitalWrite(13, HIGH);
    sumiBot.moveBackward();
    delay(10000);
}

