/**
    BasicBot - The basic working bot, using library.

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
#include <AsimiBot.h>

AsimiBot asimiBot;
void setup() {

    // initialize Asimi bot
    asimiBot.setEnablePins(2, 3);
    asimiBot.setControlPins(4, 5, 6, 7);

    pinMode(13, OUTPUT);

}

void loop() {
    digitalWrite(13, LOW);
    asimiBot.moveForward();

    delay(10000);

    // change direction
    digitalWrite(13, HIGH);
    asimiBot.moveBackward();
    delay(10000);
}

