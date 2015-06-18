/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork1;

import java.util.ArrayList;

/**
 *
 * @author asimadnan
 */
public class Layer {
    
    int numOfNeurons;  // number of neurons
    
    // list of neurons in this layer
    ArrayList<Neuron> neuronLayer = new ArrayList<>(); 
    
    Layer(int numOfNeurons, int NumInputsPerNeuron){
        
        this.numOfNeurons = numOfNeurons;   // number of neuron
        
        // for loop assigning num of inputs to each neuron in this layer
        for(int i=0;i<numOfNeurons;i++){
            Neuron Temp = new Neuron(NumInputsPerNeuron);
            neuronLayer.add(Temp);
        }
    }
    
    public void Display(){
        System.out.println("------------------Layer-------------------");
         for(int i=0;i<numOfNeurons;i++){
             
             neuronLayer.get(i).Display();
         }
         System.out.println("-----------------Layer--------------------");
    }
    
}
