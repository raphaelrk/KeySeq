package com.raphaelrk.keyalgorithm;

import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * This program continuously presses a given keySequence,
 * in this case Left Down Down Right Down Down,
 * In its focused window
 * 
 * Created for 2048: 2^53 edition 
 * Available at 
 * @url http://www.csie.ntu.edu.tw/~b01902112/9007199254740992/
 * 
 * @author RAPHAEL
 * @date May 7, 2014
 */
public class KeyAlgorithm {

    private int[] keySequence; // keySequence that robot will perform. indices are keys. Keys come from KeyEvent's final ints
    private Robot r; // Robot that will perform your keySequence
    
    private static final int DELAY = 1; // delay between each key event in milliseconds
    
    public void setAlgorithm(int[] keys) {
        keySequence = keys;
        System.out.println("Successfully set key sequence");
    }
    
    private void pressAndRelease(int keyEvent) {
        r.keyPress(keyEvent);
        r.keyRelease(keyEvent);
    }
    
    /**
     * Runs the given keySequence through the robot forever
     */
    private void performAlgorithm() {
        System.out.println("Starting sequence");
        while(true) {
            for(int i = 0; i < keySequence.length; i++) {
                int key = keySequence[i];
                pressAndRelease(key);
            }
        }
    }
    
    /**
     * Instantiates a robot object and sets its autoDelay
     */
    public KeyAlgorithm() {
        try {
            r = new Robot();
            r.setAutoDelay(DELAY);
            System.out.println("Successfully created robot");
        } catch(Exception e) {
            System.out.println("ABORT! Error in creating robot");
            e.printStackTrace();
        }
    }
    
    /**
     * Creates an instance of this class and sets/runs its keySequence
     */
    public static void main(String[] args) {
        KeyAlgorithm keyAlg = new KeyAlgorithm();
        
        int[] alg = {KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_DOWN, 
                     KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_DOWN};
        keyAlg.setAlgorithm(alg);
        
        keyAlg.performAlgorithm();
    }
}
