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
public class NeuralNet {
    
   private double BIAS;
    
   private int numOfInputs;
   private int numOfOutputs;
   private int numOfHiddenLayer;
   private int NeuronsPerHiddenLayer;
    
    //storage for each layer of neurons including the output layer
    ArrayList<Layer> allNeuronLayers = new ArrayList<>(); 
    
    NeuralNet(int inputs, int outputs, int hidden, int NperHidden){
        this.numOfInputs = inputs;
        this.numOfOutputs = outputs;
        this.numOfHiddenLayer = hidden;
        this.NeuronsPerHiddenLayer = NperHidden;
        BIAS=0;
        CreateNet();
    }
    
    public void setBIAS(double b){
        this.BIAS = b;
    }
    
   public void CreateNet(){
        
        // create layers of network
        if(numOfHiddenLayer > 0){
            //create first hidden layer
            //args number of neuron, inputsperneuron
            allNeuronLayers.add(new Layer(NeuronsPerHiddenLayer,numOfInputs)); //first hidden layer
            
            for(int i=0;i<numOfHiddenLayer-1;++i){
               
                //all hidden layers onwards hidden layer 2
                allNeuronLayers.add(new Layer(NeuronsPerHiddenLayer,NeuronsPerHiddenLayer));
            }
            
            //create output layer
            
            allNeuronLayers.add(new Layer(numOfOutputs,NeuronsPerHiddenLayer));
        }
        
        else {
            //create output layer
            allNeuronLayers.add(new Layer(numOfOutputs,numOfInputs));
        }
    }
    
    //returns a list containing all weights
    public ArrayList<Double> getWeights(){
        
        ArrayList<Double> weights = new ArrayList(); //this will hold weights 
       
        System.out.println("start");
        
        // for each layer
        for(int i=0;i<numOfHiddenLayer+1;i++){  //
            
                //for each neuron
                for(int j=0;j<allNeuronLayers.get(i).numOfNeurons;++j){ 
                        // for each weight
                        for(int k=0;k<allNeuronLayers.get(i).neuronLayer.get(j).numOfinputs;k++){ 
                       
                            weights.add(allNeuronLayers.get(i).neuronLayer.get(j).weights.get(k));
                        }
                }
        }
         return weights;
    }
   
    //given a list of weights this function will replace the weights in NN
   public void putWeights(ArrayList<Double> weights){
        
        int Weight=0;
        
        //for each layer
        for(int i=0;i<numOfHiddenLayer + 1;i++){
            
            //for each neuron
            for(int j=0;j<allNeuronLayers.get(i).numOfNeurons;j++){
                
                //for each weight
                for(int k=0;k<allNeuronLayers.get(i).neuronLayer.get(j).numOfinputs;k++){
                  
                    double temp = weights.get(Weight);
                    allNeuronLayers.get(i).neuronLayer.get(j).weights.set(k, temp);
                    Weight++;
                }
                
            }
        }
        
    }
    
   //return the total number of weights in the neural network
   
   public int getNumOfWeights() {
       int weights=0;
       
                //for each layer
        for(int i=0;i<numOfHiddenLayer + 1;i++){
            
            //for each neuron
            for(int j=0;j<allNeuronLayers.get(i).numOfNeurons;j++){
                
                //for each weight
                for(int k=0;k<allNeuronLayers.get(i).neuronLayer.get(j).numOfinputs;k++){
                    
                    weights++;
                }
                
            }
        }
        return weights;
    }
   
   public void Dsiplay(){
       
        for(int i=0;i<allNeuronLayers.size();i++){
            System.out.println("layer:" + i);
            allNeuronLayers.get(i).Display();
        }
       
       
   }
   
   private static double sigmoid(double x)
            {
                return 1 / (1 + Math.exp(-x));
            }
   
   //given an input list this calculates the outputs
   public ArrayList<Double> forwardPass(ArrayList<Double> inputs){
       
       ArrayList<Double> outputs = new ArrayList();
       int weights=0;
       
       
       //check if num of inputs in right
      if(inputs.size() != numOfInputs){
           return outputs; //return empty list
       }
       
       //for each layer
       for(int i=0;i<numOfHiddenLayer + 1;i++){
                      
           if (i > 0){
                // each layers output become input of next after the first layer
               inputs.clear();
               for(int l=0;l<outputs.size();l++){
                   inputs.add(outputs.get(l));
               }
            
            }
           
           outputs.clear(); // empty the list for computation of next layer
           weights =0;
           
            //for each neuron summing the (input * its corresponding weights)
           //then take sigmoid of that value to get output
           
           for(int j=0;j<allNeuronLayers.get(i).numOfNeurons;j++){ 
               
                    double netInput=0;
                int numInputs = allNeuronLayers.get(i).neuronLayer.get(j).numOfinputs;//for each weight
               
                        for(int k=0;k<numInputs ;k++){
                            // sum the weights*inputs
                            netInput += allNeuronLayers.get(i).neuronLayer.get(j).weights.get(k) * inputs.get(weights);
                            
                            weights++;
                    
                        }
                outputs.add(sigmoid(netInput));
                weights=0;
                
                //inputs will be stored as genretd by each layer
                //combined output is genrated by passing it through sigmoid function
                
                
               
           }
           
       }
       return outputs;
   }
   
   public ArrayList<Double> Error(ArrayList<Double> outputs , ArrayList<Double> DesiredOutput){
       
       ArrayList<Double> errors = new ArrayList(); //arraylist for storing each output's erro
  
      
       for(int i=0;i< outputs.size();i++){
           //error for each output
           double temp = outputs.get(i) * (1 - outputs.get(i)) * (DesiredOutput.get(i) - outputs.get(i));
           
           errors.add(temp);
       }
       
       return errors;
       }
   
   public void updateWeights(ArrayList<Double> errors, ArrayList<Double> output, double learningRate){
       
        boolean hiddenlayer = true;
        //for each layer
        for(int i=numOfHiddenLayer ;i >= 0 ;i--){
            //System.out.println("``````````" + i);
        
            //for each neuron
            for(int j=allNeuronLayers.get(i).numOfNeurons-1;j>=0;j--){
              //  System.out.println("^^^^^^^^^" + j);
                
                
                //for each weight
            for(int k=allNeuronLayers.get(i).neuronLayer.get(j).numOfinputs-1;k>=0;k--){
                //  System.out.println("*******" + k);
                if(hiddenlayer){
                   
                        for(int l=0;l<errors.size();l++){
                            double temp = allNeuronLayers.get(i).neuronLayer.get(j).weights.get(k);
                            temp = temp + errors.get(l)*output.get(l)*learningRate; 
                            allNeuronLayers.get(i).neuronLayer.get(j).weights.set(k, temp);
                        }       
                }
                
                   
                }
            hiddenlayer=false;
                
            }
        }
        
    }
   
   public ArrayList<Double> outputPerNeuron(ArrayList<Double> inputs){
       
       ArrayList<Double> outputs = new ArrayList();
       ArrayList<Double> eachOut = new ArrayList();
       int weights=0;
       
       
       //check if num of inputs in right
      if(inputs.size() != numOfInputs){
           return outputs; //return empty list
       }
       
       //for each layer
       for(int i=0;i<numOfHiddenLayer + 1;i++){
                      
           if (i > 0){
                // each layers output become input of next after the first layer
               inputs.clear();
               for(int l=0;l<outputs.size();l++){
                   inputs.add(outputs.get(l));
               }
            
            }
           
           outputs.clear(); // empty the list for computation of next layer
           weights =0;
           
            //for each neuron summing the (input * its corresponding weights)
           //then take sigmoid of that value to get output
           
           for(int j=0;j<allNeuronLayers.get(i).numOfNeurons;j++){ 
               
                    double netInput=0;
                int numInputs = allNeuronLayers.get(i).neuronLayer.get(j).numOfinputs;//for each weight
               
                        for(int k=0;k<numInputs ;k++){
                            // sum the weights*inputs
                            netInput += allNeuronLayers.get(i).neuronLayer.get(j).weights.get(k) * inputs.get(weights);
                            
                            weights++;
                    
                        }
                outputs.add(sigmoid(netInput));
                eachOut.add(sigmoid(netInput));
                weights=0;
                
                //inputs will be stored as genretd by each layer
                //combined output is genrated by passing it through sigmoid function
                
                
               
           }
           
       }
       return eachOut;
   }
   
   

      
       
       
       
   
       
   
}
