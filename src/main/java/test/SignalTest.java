package test;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class SignalTest implements SignalHandler {

    @Override
    public void handle(Signal signal) {
        System.out.println("handling signal " + signal.getName());
    }


    public static void main(String[] args) {

        SignalTest signalTest = new SignalTest();
        Signal.handle(new Signal("TERM"), signalTest);
        Signal.handle(new Signal("INT"), signalTest);
        Signal.handle(new Signal("USR2"), signalTest);

        while(true) {
            System.out.println("do something...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
