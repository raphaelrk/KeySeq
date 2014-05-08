package com.raphaelrk.keyalgorithm;


import java.awt.Robot;

/**
 * Take all of KeyEvent's static members and import them, eliminating the need for "KeyEvent."
 */
import static java.awt.event.KeyEvent.*;

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

    private int[] keySequence; // keySequence that robot will perform. indices are keys. Keys come from KeyEvent's static final ints
    private Robot r; // Robot that will perform your keySequence
    
    private static final int DELAY = 1; // delay between each key event in milliseconds
    
    /**
     * A few common key sequences one might use
     */
    private static final int[] LDDRDD = {VK_LEFT, VK_DOWN, VK_DOWN, VK_RIGHT, VK_DOWN, VK_DOWN},
                               ABCS = {VK_A, VK_B, VK_C, VK_D, VK_E, VK_F, VK_G, VK_H, VK_I, VK_J, VK_K, VK_L, VK_M, 
                                       VK_N, VK_O, VK_P, VK_Q, VK_R, VK_S, VK_T, VK_U, VK_V, VK_W, VK_X, VK_Y, VK_Z},
                               CW = {VK_UP, VK_RIGHT, VK_DOWN, VK_LEFT},
                               CCW = {VK_UP, VK_LEFT, VK_DOWN, VK_RIGHT};
    
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
        
        // example custom sequence:
//        int[] alg = {VK_LEFT, VK_DOWN, VK_DOWN, 
//                     VK_RIGHT, VK_DOWN, VK_DOWN};
        
        int[] alg = {VK_DOWN, VK_RIGHT};
        keyAlg.setAlgorithm(alg);
        
        keyAlg.performAlgorithm();
    }
}
