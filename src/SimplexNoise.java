/*******************************************************************************
 * file: SimplexNoise.java
 * author: Josue Miramontes, Anthony Guzman, Gerret Kubota
 *                              
 * class: CS 445
 * 
 * assignment: Checkpoint 2 
 * date last modified: 11/19/2015
 * 
 * purpose: The purpose of this class is to help render/genereate heights of terrain
 *          of an environment and it can also extend to multiple dimensions. 
 *          This particular is actually faster than its predecessor
 *          (perlin noise), as the time complexity of the simplex noise is o(n^2),
 *          whereas perlin noise is o(2^n). This class will also randomize the height
 *          of the chunk. In essence, the environment/map of the program will not look
 *          the same when it is ran each time.
 *
 ******************************************************************************/
import java.util.Random;

public class SimplexNoise {

    private SimplexNoise_octave[] octaves;
    private double[] frequencys;
    private double[] amplitudes;
    private int largestFeature;
    private double persistence;
    private int seed;

    // constuctor: SimplexNoise
    // purpose: Pass 3 parameters; largestFeature, persistence, and seed.
    // For the largestFeature, it will create octaves that will provide
    // features of the size between 1 & largestFeature value.
    // For the value of persistence, it is used to render different heights
    // of the terrain. The seed is to help generate a random value.
    public SimplexNoise(int largestFeature,double persistence, int seed){
        this.largestFeature=largestFeature;
        this.persistence=persistence;
        this.seed=seed;

        //recieves a number (eg 128) and calculates what power of 2 it is (eg 2^7)
        int numberOfOctaves=(int)Math.ceil(Math.log10(largestFeature)/Math.log10(2));

        octaves=new SimplexNoise_octave[numberOfOctaves];
        frequencys=new double[numberOfOctaves];
        amplitudes=new double[numberOfOctaves];

        Random rnd=new Random(seed);

        for(int i=0;i<numberOfOctaves;i++){
            octaves[i]=new SimplexNoise_octave(rnd.nextInt());

            frequencys[i] = Math.pow(2,i);
            amplitudes[i] = Math.pow(persistence,octaves.length-i);
        }

    }

    // method: getNoise
    // purpose: This is for a 2D simplex noise, the purpose of having this 
    // method is to assist in generating a random max height for each of the
    // locations in the chunk. This method will return a value between -1 & 1
    // and it will be scaled as needed in the chunk.
    public double getNoise(int x, int y){

        double result=0;

        for(int i=0;i<octaves.length;i++){
          //double frequency = Math.pow(2,i);
          //double amplitude = Math.pow(persistence,octaves.length-i);

          result=result+octaves[i].noise(x/frequencys[i], y/frequencys[i])* amplitudes[i];
        }
        return result;
    }

    // method: getNoise
    // purpose: This is for a 3D simplex noise, the purpose of having this 
    // method is to assist in generating a random max height for each of the 
    // locations in the chunk. This method will return a value between -1 & 1
    // and it will be scaled as needed in the chunk.
    public double getNoise(int x,int y, int z){

        double result=0;

        for(int i=0;i<octaves.length;i++){
          double frequency = Math.pow(2,i);
          double amplitude = Math.pow(persistence,octaves.length-i);

          result=result+octaves[i].noise(x/frequency, y/frequency,z/frequency)* amplitude;
        }
        return result;
    }
} 