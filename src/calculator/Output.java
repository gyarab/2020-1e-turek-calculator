/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.text.DecimalFormat;

/**
 *
 * @author jakub
 */
public class Output {

    public static String removeDecimal(String string) {
        if (Double.valueOf(string) == Math.floor(Double.valueOf(string))) {

            return new DecimalFormat("#").format(Double.valueOf(string));
        } else {
            return string;
        }

    }
}
