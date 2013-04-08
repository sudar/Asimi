# Asimi

Asimi is a bot based on [Arduino](http://arduino.cc), which I created to experiment with robotics and different ways to connect to external interfaces from Arduino.

## The name Asimi

I choose the name Asimi, in honor of [Isaac Asimov](http://en.wikipedia.org/wiki/Isaac_Asimov), who coined the term 'robotics'. Also 'A' is the starting letter of the name of a very special person in my life ;) 

## Modules

Each module is a separate Arduino sketch(program) which does a particular functionality. Right now the following modules are available.

*   BasicBot - A very basic bot which can run. It uses two DC motors.
*   BasicBotUsingLibrary - A very basic bot, which is coded using the [DCMotorBot library](http://hardwarefun.com/projects/dc-motor-bot)
*   WiiBottonsBot - A bot that can be controlled using the buttons of the Wii remote.
*   WiiAcclerometerBot - A bot that can be controlled using the accelerometer of the Wii remove.
*   AndroidControlledBot - A bot that can be controlled using an Android phone.

## Libraries

Almost all modules using the [DCMotorBot Arduino library](http://hardwarefun.com/projects/dc-motor-bot), that I created which allows you to control bots created with 2 DC Motors and H-Bridge IC like L293D.

## Installation

Download the project and copy the files into your Arduino sketch folder. Open the projects in Arduino IDE and you should be able to compile them. You might also want to install the dependency libraries in the `libraries` folder of Arduino.

The Android project can be opened in Eclipse.

## Explanation

Explanation/documentation of this project can be found in its [homepage](http://hardwarefun.com/projects/asimi)

## Demo Video

Video of my demo of Asimi at [DroidCon](http://sudarmuthu.com/blog/building-robots-using-arduino-and-android-at-droidcon) can be found at http://www.youtube.com/watch?v=ZT9r6KhI32k

## Talks

I have talked about this project in the following conferences/events

- [Droidcon 2011](http://sudarmuthu.com/blog/building-robots-using-arduino-and-android-at-droidcon)
- [Google Devfestx](sudarmuthu.com/blog/building-robots-using-android-and-arduino-in-google-devfestx)
- [Droidcon 2012](http://sudarmuthu.com/blog/using-websockets-with-android-to-control-arduino-robots)
- [JSFoo 2012](http://sudarmuthu.com/blog/controlling-robots-using-javascript)
- [Arduino Robotic Workshop](hardwarefun.com/arduino-workshop)

## License

The source code and schematics are released under "THE BEER-WARE" license.

I would, however, consider it a great courtesy if you could email me and tell me about your project and how this code was used, just for my own continued personal gratification :)

You can also find other ways to [make me happy](http://sudarmuthu.com/if-you-wanna-thank-me), if you liked this project.

##Support 

More details about this project and the support for this project can be found at its [homepage](http://hardwarefun.com/projects/asimi).

## Coming soon

More modules, better documentation, demo videos, photos, building instructions etc..
