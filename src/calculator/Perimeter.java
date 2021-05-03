/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author jakub
 */
public class Perimeter {

    public double roundPerimeter(double r) {
        return 2 * r * Math.PI;
    }

    public double rectangelPerimetr(double a, double b) {
        return 2 * a + 2 * b;
    }

    public double squerPerimeter(double a) {
        return rectangelPerimetr(a, a);
    }

    public double triangelPerimeter(double a, double b, double c) {
        return a + b + c;
    }
    
}
