/**
    AndroidControlledBot - The bot that can be controlled by Android phone
    Part of the Asimi project - http://hardwarefun.com/projects/asimi

    Requires the following Arduino libraries
    - MissileLauncher - http://hardwarefun.com/projects/missilelauncher
    - USB Host Shield Library v2.0 - https://github.com/felis/USB_Host_Shield_2.0
    - MeetAndroid (Amarino) - http://amarino.googlecode.com/svn/trunk/arduino_library/MeetAndroid
    - DCMotorBot - http://hardwarefun.com/projects/dc-motor-bot

    Copyright 2011  Sudar Muthu  (email : sudar@sudarmuthu.com)

/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <sudar@sudarmuthu.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer or coffee in return - Sudar
 * ----------------------------------------------------------------------------
 */

#include <DCMotorBot.h>
#include <MeetAndroid.h>

// declare MeetAndroid so that you can call functions with it
MeetAndroid meetAndroid;
DCMotorBot bot;

void setup() {
    // use the baud rate your bluetooth module is configured to
    // not all baud rates are working well, i.e. ATMEGA168 works best with 57600
    Serial.begin(115200);

    // initialize Asimi bot
    bot.setEnablePins(2, 3);
    bot.setControlPins(4, 5, 6, 7);

    // register callback functions, which will be called when an associated event occurs.
    meetAndroid.registerFunction(handleBot, 'b');
//    meetAndroid.registerFunction(handleMissile, 'm'); //TODO: Enable missile as well

}

void loop() {
    meetAndroid.receive(); // you need to keep this in your loop() to receive events
}

void handleBot(byte flag, byte num) {
    int direction[1];

    meetAndroid.getIntValues(direction);

    switch (direction[0]) {
        case 0: // LEFT
            bot.turnLeft();
            break;

        case 1: // Right
            bot.turnRight();
            break;

        case 2:  // Up
            bot.moveForward();
            break;

        case 3:  // down
            bot.moveBackward();
            break;

        case 4:  // stop
            bot.stop();
            break;

    }
}
