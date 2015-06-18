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
public class NeuralNetwork1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        //args input,output,hidden,numperhidden
       // neural network defined
        NeuralNet N = new NeuralNet(2,1,1,2);
      N.Dsiplay();
        //adding new weights, 
        // first 3 are of first neuron of first layer next 3 of next neuron and so on
       ArrayList<Double> w2 = new ArrayList();
            w2.add(.1);
            w2.add(.8);
            w2.add(0.4);
            w2.add(0.6);
            w2.add(.3);
            w2.add(.9);
            
        
       //adding new weights
            N.putWeights(w2);
          
       
      //adding inputs
        ArrayList<Double> input  = new ArrayList<>();
        input.add(.35);
        input.add(.9);
        
        
        //desired outputs
        ArrayList<Double> DesiredOutput = new ArrayList<>();
        DesiredOutput.add(.5);
       
     
        ArrayList<Double> output  = N.forwardPass(input);
        ArrayList<Double> error = N.Error(output, DesiredOutput);
     
        ArrayList<Double> eachOutput = N.outputPerNeuron(input);
     
       
       for(int i=0;i<output.size();i++){
           System.out.println("Output" + output.get(i) + ", error" + error.get(i));
           
       } 
        for(int i=0;i<eachOutput.size();i++){
           System.out.println("^^Output " + i + " :" + eachOutput.get(i) );
           
       } 
        
       
        N.Dsiplay();
       
        
        
        ArrayList <Double> temp = new ArrayList<>();
        
       temp = N.getWeights();
       for(int i=temp.size()-1;i>=0.;i--){
            System.out.println("weight :" + i + " " + temp.get(i));
            
        }
     
       
       N.updateWeights(error, output, 1);
         
       temp = N.getWeights();
       for(int i=temp.size()-1;i>=0.;i--){
            System.out.println("weight :" + i + " " + temp.get(i));
            
        }
     
        
        
    }
    
}
