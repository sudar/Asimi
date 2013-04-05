/**
    WiiButtonsBot - The basic bot that can be controlled by Wii Remote.
    Part of the Asimi project - http://hardwarefun.com/projects/asimi

    Requires the following Arduino libraries
    - WiiRemote - http://hardwarefun.com/projects/wiiremote
    - USB Host Shield Library - https://github.com/felis/USB_Host_Shield

    Copyright 2011  Sudar Muthu  (email : sudar@sudarmuthu.com)

/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <sudar@sudarmuthu.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer or coffee in return - Sudar
 * ----------------------------------------------------------------------------
 */

// TODO: Update the code to use the DCMotorBot library available at http://hardwarefun.com/projects/dc-motor-bot
// TODO: Add schematics and photos

#include <Usb.h>
#include <WiiRemote.h>

WiiRemote wiiremote;

#define E1 1  // Enable Pin for motor 1
#define E2 2  // Enable Pin for motor 2

#define I1 3  // Control pin 1 for motor 1
#define I2 4  // Control pin 2 for motor 1
#define I3 5  // Control pin 1 for motor 2
#define I4 6  // Control pin 2 for motor 2

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

    wiiremote.init();

    // This is the bluetooth address of your Wii remote. You need to find it and change here
    unsigned char wiiremote_bdaddr[6] = {0x00, 0x24, 0x1e, 0x7e, 0x93, 0x3e};
    wiiremote.setBDAddress(wiiremote_bdaddr, 6);
    wiiremote.setBDAddressMode(BD_ADDR_FIXED);

    Serial.begin(9600);
}

void loop() {

    wiiremote.task(&myapp);
    if (!(wiiremote.getStatus() & WIIREMOTE_STATE_RUNNING)) {
        Serial.print("\r\nlost Bluetooth connection");
    }
}

// Call back which is executed by wiiremote.task() method
void myapp(void) {

    if (wiiremote.buttonClicked(WIIREMOTE_LEFT)) {
        Serial.println("Left Button clicked");

        digitalWrite(E1, LOW);
        digitalWrite(E2, LOW);

        delay(200);

        digitalWrite(E1, LOW);
        digitalWrite(E2, HIGH);

        digitalWrite(I1, HIGH);
        digitalWrite(I2, LOW);
        digitalWrite(I3, HIGH);
        digitalWrite(I4, LOW);

    }

    if (wiiremote.buttonClicked(WIIREMOTE_RIGHT)) {
        Serial.println("Right Button clicked");

        digitalWrite(E1, LOW);
        digitalWrite(E2, LOW);

        delay(200);

        digitalWrite(E1, HIGH);
        digitalWrite(E2, LOW);

        digitalWrite(I1, HIGH);
        digitalWrite(I2, LOW);
        digitalWrite(I3, HIGH);
        digitalWrite(I4, LOW);

    }

    if (wiiremote.buttonClicked(WIIREMOTE_UP)) {
        Serial.println("Up Button clicked");

        digitalWrite(E1, LOW);
        digitalWrite(E2, LOW);

        delay(200);

        digitalWrite(E1, HIGH);
        digitalWrite(E2, HIGH);

        digitalWrite(I1, HIGH);
        digitalWrite(I2, LOW);
        digitalWrite(I3, HIGH);
        digitalWrite(I4, LOW);

    }

    if (wiiremote.buttonClicked(WIIREMOTE_DOWN)) {
        Serial.println("Down Button clicked");

        digitalWrite(E1, LOW);
        digitalWrite(E2, LOW);

        delay(200);

        digitalWrite(E1, HIGH);
        digitalWrite(E2, HIGH);

        digitalWrite(I1, LOW);
        digitalWrite(I2, HIGH);
        digitalWrite(I3, LOW);
        digitalWrite(I4, HIGH);

    }
}
