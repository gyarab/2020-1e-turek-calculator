/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jakub
 */
public class Conventor {

    private List<String> unitsSI = new ArrayList<>();
    private List<String> unitsImperial = new ArrayList<>();
    private List<String> unitsVolSI = new ArrayList<>();
    private List<String> unitsVolImperial = new ArrayList<>();

    public Conventor() {
        Collections.addAll(unitsSI, "mm", "cm", "dm", "m", "km");
        Collections.addAll(unitsImperial, "in", "ft", "yd", "mi");
        Collections.addAll(unitsVolSI, "ml", "cl", "dl", "l", "hl");
        Collections.addAll(unitsVolImperial, "čajové lžičky", "polévkové lžíce",
                "pinty", "galony");
    }

    private String convertInSI(String from, String to, double value) {

        int exp = unitsSI.indexOf(from) - unitsSI.indexOf(to);

        return String.valueOf(value * Math.pow(10, exp));

    }

    private String convertInImp(String from, String to, double value) {
        double[] chart = new double[]{2.54, 3.048, 0.9114, 1.609344};
        int index = unitsImperial.indexOf(from);
        int index1 = unitsImperial.indexOf(to);
        int exp = index - index1;
        double result = value * chart[index] * Math.pow(10, exp) / chart[index1];
        return String.valueOf(result);

    }

    private String convertFromSIToImp(String from, String to, double value) {
        if (unitsSI.contains(from)) {

            return convertInImp("in", to, Double.valueOf(convertInSI(from, "cm", value)) / 2.54);
        } else {

            return convertInSI("cm", to, Double.valueOf(convertInImp(from, "in", value)) * 2.54);
        }
    }

    private String convertFlatInSI(String from, String to, double value) {
        from = removeExp(from);
        to = removeExp(to);
        return convertInSI(from, to, Double.valueOf(convertInSI(from, to, value)));

    }

    private String convertFlatInImp(String from, String to, double value) {
        from = removeExp(from);
        to = removeExp(to);
        return convertInImp(from, to, Double.valueOf(convertInImp(from, to, value)));

    }

    private String convertFlatFromSIToImp(String from, String to, double value) {
        if (unitsSI.contains(removeExp(from))) {

            return convertFlatInImp("in²", to, Double.valueOf(convertFlatInSI(from, "cm²", value)) / 6.4516);
        } else {

            return convertFlatInSI("cm²", to, Double.valueOf(convertFlatInImp(from, "in²", value)) * 6.4516);
        }
    }

    private String convertVolumeInSI(String from, String to, double value) {
        int exp = unitsVolSI.indexOf(from) - unitsVolSI.indexOf(to);
        return String.valueOf(value * Math.pow(10, exp));

    }

    private String convertVolumeInImp(String from, String to, double value) {
        double[] chart = new double[]{4.92892, 1.478676, 2.365882, 0.473176, 0.03785412};
        int index = unitsVolImperial.indexOf(from);
        int index1 = unitsVolImperial.indexOf(to);
        int exp = index - index1;
        double result = value * chart[index] * Math.pow(10, exp) / chart[index1];
        return String.valueOf(result);

    }

    private String convertVolumeFromSIToImp(String from, String to, double value) {
        double d;
        if (unitsSI.contains(removeExp(from))) {
            d = Double.valueOf(convertVolumeInSI(from, "ml", value));
            return convertVolumeInImp("čajové lžičky", to, d / 4.928622);
        } else {
            d = Double.valueOf(convertVolumeInImp(from, "čajové lžičky", value));
            return convertVolumeInSI("ml", to, d * 4.928622);
        }
    }

    private String removeExp(String string) {
        return string = string.substring(0, string.length() - 1);

    }

    public String convert(String from, String to, String in) {
        in = in.replace(",", ".");
        double value;
        try{
        value = Double.valueOf(in);}
        catch(NumberFormatException e)
        { throw e;
        }
        String result;
        if (from.contains("²")) {
            if (unitsSI.contains(removeExp(from)) && unitsSI.contains(removeExp(to))) {
                result = convertFlatInSI(from, to, value);
            } else if (unitsImperial.contains(removeExp(from)) && unitsImperial.contains(removeExp(to))) {
                result = convertFlatInImp(from, to, value);
            } else {
                result = convertFlatFromSIToImp(from, to, value);
            }

        } else if (unitsSI.contains(from) || unitsImperial.contains(from)) {
            if (unitsSI.contains(from) && unitsSI.contains(to)) {
                result = convertInSI(from, to, value);
            } else if (unitsImperial.contains(from) && unitsImperial.contains(to)) {
                result = convertInImp(from, to, value);
            } else {
                result = convertFromSIToImp(from, to, value);
            }
        } else {
            if (unitsVolSI.contains(from) && unitsVolSI.contains(to)) {
                result = convertVolumeInSI(from, to, value);
            } else if (unitsVolImperial.contains(from) && unitsVolImperial.contains(to)) {
                result = convertVolumeInImp(from, to, value);
            } else {
                result = convertVolumeFromSIToImp(from, to, value);
            }

        }
        return Output.removeDecimal(result);
    }

}
