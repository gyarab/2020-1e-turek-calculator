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
public class Volume {
    private final Surface surface = new Surface();
    public double blockVolume(double a, double b, double c) {
        return a * b * c;

    }

    public double cubeVolume(double a) {
        return Math.pow(a, 3);
    }

    public double sphereVolume(double r) {
       
      return (4*Math.PI*Math.pow(r, 3))/3;
    }
    

   
    public double cylinderVolume(double v, double r)
    {
        return surface.roundSurface(r)*v;
    
    }
    public double pyramidVolume(double a, double v)
    {
        return surface.squerContent(a)*v*(1/3);
    
    }
    
   
    

}
