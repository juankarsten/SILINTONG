/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.extra;

/**
 *
 * @author juan.karsten
 */
public class Validator {
    public static boolean isExist(String input){
        if(input==null||input.equals(""))return false;
        return true;
    }
}
