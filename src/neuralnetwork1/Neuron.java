/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author asimadnan
 */
public class Neuron  {
    
    int numOfinputs; // number of inputs;
    
    ArrayList<Double> weights = new ArrayList<>(); //list of weights 
    
    
    // constructer for this class
    Neuron(int N){ 
        
       Random r = new Random();
       int rangeMin=-1;
       int rangeMax=1;
       
        numOfinputs = N; //extra 1 is the bias
        
        //assigning random weights
        for(int i=0;i<numOfinputs;i++){
            weights.add(rangeMin + (rangeMax - rangeMin) * r.nextDouble());
        }
    }
    
    public void Display(){
         System.out.println("------------------Neuron-------------------");
        System.out.println("Number of inputs in me: " + numOfinputs);
         for(int i=0;i<numOfinputs;i++){
              System.out.println("Weight " + (i+1) + " : " + weights.get(i));
         }
         System.out.println("--------------------Neuron-----------------");
    }
    
}
