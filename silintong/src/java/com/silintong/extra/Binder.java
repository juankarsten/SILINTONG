/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.extra;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan.karsten
 */
public class Binder {
    Class modelClass;
    Map<String,String> params;

    public Binder(Class modelClass, Map<String, String> params) {
        this.modelClass = modelClass;
        this.params = params;
    }
    
    public void bindRequest(){
        try {
            Object ob=modelClass.newInstance();
            
            Field []field=modelClass.getDeclaredFields();
            for(int ii=0;ii<field.length;ii++){
                
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Binder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Binder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
