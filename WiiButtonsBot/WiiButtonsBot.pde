/**
    WiiButtonsBot - The basic bot that can be controlled by Wii Remote.

    Part of the Sumi project - http://sudarmuthu.com/arduino/sumi

   Copyright 2011  Sudar Muthu  (email : sudar@sudarmuthu.com)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License, version 2, as
    published by the Free Software Foundation.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

/**
 * Requres the following Arduino libraries
 *
 * WiiRemote - http://sudarmuthu.com/arduino/wiiremote
 * USB Host Shield Library - https://github.com/felis/USB_Host_Shield
 *
 */

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
