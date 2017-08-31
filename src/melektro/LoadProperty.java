/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melektro;

import java.util.Properties;

/**
 *
 * @author Marius
 */
public class LoadProperty {
    public static String LoadProperty(Properties prop, String property, String defaultvalue) {
        String PropertyValue = prop.getProperty(property);
        if (PropertyValue != null) {
            return PropertyValue;
        } else {
            return defaultvalue;
        }
    }
}
