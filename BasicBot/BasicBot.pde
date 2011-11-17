/**
    BasicBot - The basic working bot.

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

#define E1 2  // Enable Pin for motor 1
#define E2 3  // Enable Pin for motor 2

#define I1 4  // Control pin 1 for motor 1
#define I2 5  // Control pin 2 for motor 1
#define I3 6  // Control pin 1 for motor 2
#define I4 7  // Control pin 2 for motor 2

void setup() {

    pinMode(E1, OUTPUT);
    pinMode(E2, OUTPUT);

    pinMode(I1, OUTPUT);
    pinMode(I2, OUTPUT);
    pinMode(I3, OUTPUT);
    pinMode(I4, OUTPUT);

    digitalWrite(E1, HIGH);
    digitalWrite(E2, HIGH);

    digitalWrite(I1, HIGH);
    digitalWrite(I2, LOW);
    digitalWrite(I3, HIGH);
    digitalWrite(I4, LOW);

}

void loop() {

    digitalWrite(E1, LOW);
    digitalWrite(E2, LOW);

    delay(200);

    digitalWrite(E1, HIGH);
    digitalWrite(E2, HIGH);

    digitalWrite(I1, HIGH);
    digitalWrite(I2, LOW);
    digitalWrite(I3, HIGH);
    digitalWrite(I4, LOW);

    delay(10000);

    // change direction

    digitalWrite(E1, LOW);
    digitalWrite(E2, LOW);

    delay(200);

    digitalWrite(E1, HIGH);
    digitalWrite(E2, HIGH);

    digitalWrite(I1, LOW);
    digitalWrite(I2, HIGH);
    digitalWrite(I3, LOW);
    digitalWrite(I4, HIGH);

    delay(10000);
}
