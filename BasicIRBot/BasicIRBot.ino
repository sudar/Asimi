/**
    BasicIRBot - The basic working bot with IR Obstacle Detection.

    Part of the Asimi project - http://sudarmuthu.com/arduino/asimi

   Copyright 2011  Sudar Muthu  (email : sudar@sudarmuthu.com)
  
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <sudar@sudarmuthu.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer or coffee in return - Sudar
 * ----------------------------------------------------------------------------
 */
// Pin Change Int Library optimizations
#define       NO_PORTC_PINCHANGES
#define       NO_PORTD_PINCHANGES
#define       NO_PIN_ARDUINO
#define       NO_PIN_STATE

#include <AsimiBot.h>
#include <PinChangeInt.h> // For handling Pin change Interrupts

#define E1 2  // Enable Pin for motor 1
#define E2 3  // Enable Pin for motor 2

#define I1 4  // Control pin 1 for motor 1
#define I2 5  // Control pin 2 for motor 1
#define I3 6  // Control pin 1 for motor 2
#define I4 7  // Control pin 2 for motor 2

#define IR_PIN 13 // IR signal pin

AsimiBot asimiBot; // create an instance of the bot
volatile bool moving_forward = true; // Move forward initially

void changeDirection() {
    moving_forward = false;
}

void setup() {

    // initialize Asimi bot
    asimiBot.setEnablePins(E1, E2);
    asimiBot.setControlPins(I1, I2, I3, I4);

    pinMode(IR_PIN, INPUT);
    digitalWrite(IR_PIN, HIGH);

    PCintPort::attachInterrupt(IR_PIN, &changeDirection, FALLING);

}

void loop() {
   if (moving_forward) {
        asimiBot.moveForward();
   } else {
        asimiBot.moveBackward();
   }
}
