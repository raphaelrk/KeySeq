package com.raphaelrk.keyalgorithm;


import java.awt.Robot;

/**
 * Take all of KeyEvent's static members and import them, eliminating the need for "KeyEvent."
 */
import static java.awt.event.KeyEvent.*;
import java.util.Arrays;

/**
 * This program continuously presses a given keySequence in the user's focused window
 * 
 * Use examples available in the main class at the bottom of the code.
 * 
 * Originally created for use in 2048: 2^53 edition, available at 
 * @url http://www.csie.ntu.edu.tw/~b01902112/9007199254740992/
 * 
 * @author RAPHAEL ROUVINOV-KATS
 */
public class KeyAlgorithm {

    private int[] keySequence; // keySequence that robot will perform. indices are keys. Keys come from KeyEvent's static final ints
    private Robot r; // Robot that will perform your keySequence
    
    private static final int DELAY = 1, // delay between each key event in milliseconds
                            HOLD = 0xffff, // Add this to a key in your keySequence to hold it. None of the KeyEvent values are higher than 0xffff
                            RELEASE = -HOLD; // Add this to a key in your keySequence to release it
    
    /**
     * A few common key sequences one might use
     */
    private static final int[] LDDRDD = {VK_LEFT, VK_DOWN, VK_DOWN, VK_RIGHT, VK_DOWN, VK_DOWN}, // arrow keys- left, down, down, right, down, down
                               ABCS = {VK_A, VK_B, VK_C, VK_D, VK_E, VK_F, VK_G, VK_H, VK_I, VK_J, VK_K, VK_L, VK_M, // abc's, lowercase
                                       VK_N, VK_O, VK_P, VK_Q, VK_R, VK_S, VK_T, VK_U, VK_V, VK_W, VK_X, VK_Y, VK_Z},
                               NUMS = {VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9, VK_0}, // 0-9 above homerow
                               PUNC = {VK_SPACE, VK_COMMA, VK_PERIOD, VK_SLASH, VK_SEMICOLON, VK_QUOTE, VK_OPEN_BRACKET, VK_CLOSE_BRACKET, VK_BACK_SLASH, VK_EQUALS, VK_MINUS}, // Non-shift non-number punctuation keys. grave accent/tilde key (KeyEvent.VK_DEAD_GRAVE) doesn't seem to work
                               CW = {VK_UP, VK_RIGHT, VK_DOWN, VK_LEFT}, // arrow keys, clockwise
                               CCW = {VK_UP, VK_LEFT, VK_DOWN, VK_RIGHT}, // arrow keys, counter-clockwise
                               SHIFT_HOLD = {VK_SHIFT + HOLD}, // for join()ing with other sequences to capitalize them or get their other key
                               SHIFT_RELEASE = {VK_SHIFT + RELEASE}; // for use with HOLD_SHIFT to stop capitalizing them
    
    /**
     * Sets your sequence of keys
     * @param keys key sequence
     */
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
            for(Integer key : keySequence) {
                // System.out.println(KeyEvent.getKeyText(keyEvent)); // for debugging
                if(key > HOLD) {
                    r.keyPress(key - HOLD); // hold key
                } else if(key < 0) {
                    r.keyRelease(key - RELEASE); // release key
                } else {
                    pressAndRelease(key); // click key
                }
            }
        }
    }
    
    /**
     * Recieves indefinite amount of int[]'s as parameters and joins them into one int[]
     * @param arrs int[]'s to concatenate
     * @return concatenated int[]
     */
    public static int[] join(int[]... arrs) {
        int length = 0;
        for(int[] arr : arrs) {
            length += arr.length;
        }
        
        int[] dest = new int[length];
        
        int destIndex = 0;
        for(int[] arr : arrs) {
            System.arraycopy(arr, 0, dest, destIndex, arr.length);
            destIndex += arr.length;
        }
        
        return dest;
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
        
        /**
         * Example sequences:
         * 
         * Arrow key sequence
         * int[] alg = {VK_LEFT, VK_DOWN, VK_DOWN, VK_RIGHT, VK_DOWN, VK_DOWN};
         * 
         * Premade sequence
         * int[] alg = LDDRDD;
         * 
         * Sequence using shift and hold/release
         * int[] alg = {VK_A, VK_SHIFT + HOLD, VK_A, VK_SHIFT + RELEASE};
         * 
         * Sequence joining. This goes through most of your keys: abcs, numbers, and punctuation without shift and then with shift
         * int[] alg = join(ABCS, NUMS, PUNC, SHIFT_HOLD, ABCS, NUMS, PUNC, SHIFT_RELEASE);
         * tested on: http://www.goodtyping.com/
         * Could also use Arrays.asList(T... a) instead of join(int[]... arrs) but you'll need to change source code to use Integer[]'s instead of int[]'s.
         */
        
        int[] alg = join(ABCS, NUMS, PUNC, SHIFT_HOLD, ABCS, NUMS, PUNC, SHIFT_RELEASE);
        keyAlg.setAlgorithm(alg);
        keyAlg.performAlgorithm();
    }
}
