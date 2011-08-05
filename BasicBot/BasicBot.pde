#include <WiiRemote.h>

WiiRemote wiiremote;

#define E1 1
#define E2 2

#define I1 3
#define I2 4
#define I3 5
#define I4 6

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
    
//    Serial.begin(9600);
  wiiremote.init();

  unsigned char wiiremote_bdaddr[6] = {0x00, 0x24, 0x1e, 0x7e, 0x93, 0x3e};
  wiiremote.setBDAddress(wiiremote_bdaddr, 6);
  wiiremote.setBDAddressMode(BD_ADDR_FIXED);
    
}

void loop() {
    
  wiiremote.task(&myapp);
  if (!(wiiremote.getStatus() & WIIREMOTE_STATE_RUNNING)) {
//    Serial.print("\r\nlost Bluetooth connection");
  }
}

// Call back which is executed by wiiremote.task() method
void myapp(void) {

  if (wiiremote.buttonClicked(WIIREMOTE_LEFT)) {
//      Serial.println("Left Button clicked");

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
//      Serial.println("Right Button clicked");

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
//      Serial.println("Up Button clicked");
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
//      Serial.println("Down Button clicked");
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
//  if (wiiremote.buttonClicked(WIIREMOTE_PLUS)) {
//      Serial.println("Plus Button clicked");
//  }
//  if (wiiremote.buttonClicked(WIIREMOTE_TWO)) {
//      Serial.println("Button Two clicked");
//  }
//  if (wiiremote.buttonClicked(WIIREMOTE_ONE)) {
//      Serial.println("Button One clicked");
//  }
//  if (wiiremote.buttonClicked(WIIREMOTE_B)) {
//      Serial.println("Button B clicked");
//  }
//  if (wiiremote.buttonClicked(WIIREMOTE_A)) {
//      Serial.println("Button A clicked");
//  }
//  if (wiiremote.buttonClicked(WIIREMOTE_MINUS)) {
//      Serial.println("Minus Button clicked");
//  }
//  if (wiiremote.buttonClicked(WIIREMOTE_HOME)) {
//      Serial.println("Home Button clicked");
//  }
//  
//    digitalWrite(E1, HIGH);
//    digitalWrite(E2, HIGH);
//  
//    digitalWrite(I1, LOW);
//    digitalWrite(I2, HIGH);
//    digitalWrite(I3, HIGH);
//    digitalWrite(I4, LOW);
//        
//    delay (10000);
//
//    digitalWrite(E1, LOW);
//    digitalWrite(E2, LOW);
//
//    delay (10000);
//
//    digitalWrite(E1, HIGH);
//    digitalWrite(E2, HIGH);
//    
//    digitalWrite(I1, HIGH);
//    digitalWrite(I2, LOW);
//    digitalWrite(I3, HIGH);
//    digitalWrite(I4, LOW);
//    
//    delay (10000);
//
//    digitalWrite(E1, LOW);
//    digitalWrite(E2, LOW);

    
}
