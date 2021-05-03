/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class Calculator {

    //  private StringBuffer buffer = new StringBuffer();
    private double sum(String number, String number1) {
        return Double.valueOf(number) + Double.valueOf(number1);
    }

    private double difference(String number, String number1) {
        return Double.valueOf(number) - Double.valueOf(number1);
    }

    private double product(String number, String number1) {
        return Double.valueOf(number) * Double.valueOf(number1);
    }

    private double quotient(String number, String number1) {
        if (Double.valueOf(number1) == 0) {
            throw new IllegalArgumentException("Nulou nelze dělit");
        } else {
            return Double.valueOf(number) / Double.valueOf(number1);
        }
    }

    public String calculate(String input) {
        String vali = validateInput(input);
        List<String> inputList = new LinkedList<>(Arrays.asList(vali.split(" ")));
        double result;

        while (indexOfNextOperation(inputList) >= 0) {
            int index = indexOfNextOperation(inputList);

            switch (inputList.get(index)) {
                case "x":
                    result = product(inputList.get(index - 1), inputList.get(index + 1));
                    updateList(inputList, index, String.valueOf(result));
                    break;
                case "÷":
                    result = quotient(inputList.get(index - 1), inputList.get(index + 1));
                    updateList(inputList, index, String.valueOf(result));
                    break;
                case "+":
                    result = sum(inputList.get(index - 1), inputList.get(index + 1));
                    updateList(inputList, index, String.valueOf(result));
                    break;
                case "-":
                    result = difference(inputList.get(index - 1), inputList.get(index + 1));
                    updateList(inputList, index, String.valueOf(result));
                    break;

            }
        }

        return Output.removeDecimal(inputList.get(0));

    }

    private int lessIndex(int number, int number1) {
        if (number1 == -1 ^ number == -1) {
            return Math.max(number1, number);
        } else {
            return Math.min(number1, number);
        }

    }

    private void updateList(List<String> list, int index, String replaceWith) {

        list.set(index, replaceWith);
        list.remove(index + 1);
        list.remove(index - 1);

    }

    public int indexOfNextOperation(List<String> list) {

        if (list.contains("÷") || list.contains("x")) {
            return lessIndex(list.indexOf("÷"), list.indexOf("x"));
        } else if (list.contains("+") || list.contains("-")) {
            return lessIndex(list.indexOf("+"), list.indexOf("-"));
        }

        return -1;
    }

    private String validateInput(String input) {
        input = input.replace(" ", "");
        input = input.replace(",", ".");
        input = input.replace("*", "x");
        input = input.replace("/", "÷");
        char[] inputArray = input.toCharArray();
        List<Character> output = new ArrayList<Character>();
        List<Character> operations = new ArrayList<Character>();
        operations.add('+');
        operations.add('-');
        operations.add('x');
        operations.add('÷');

        for (int i = 0; i < inputArray.length; i++) {
            if (operations.contains(inputArray[i]) && !(i == 0 && inputArray[i] == '-')) {
                output.add(' ');
                output.add(inputArray[i]);
                output.add(' ');
            } else {
                output.add(inputArray[i]);
            }

        }
        StringBuilder builder = new StringBuilder();
        for (Character ch : output) {
            builder.append(ch);
        }
        return builder.toString();

    }

    public String backspace(String string) {
        if (string.length() != 0) {
            return string.substring(0, string.length() - 1);
        } else {
            return string;
        }

    }

}
