/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5n;

import java.awt.image.BufferedImage;

/**
 *
 * @author Matthew Martinez
 */
public class Project5N {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        float eMask1[][] = {
            {0.25f, 0.25f},
            {0.25f, 0.25f},};
        
        float eMask2[][] = {
            {0.0625f, 0.0625f, 0.0625f, 0.0625f},
            {0.0625f, 0.0625f, 0.0625f, 0.0625f},
            {0.0625f, 0.0625f, 0.0625f, 0.0625f},
            {0.0625f, 0.0625f, 0.0625f, 0.0625f},};
        
        float eMask3[][] = {
            {0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f},
            {0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f},
            {0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f},
            {0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f},
            {0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f},
            {0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f, 0.0277777778f},};
        
        //1- Read an Image
        BufferedImage inImage = ImageIo.readImage("utb.jpg");
        ImageIo.getBufferedImageType(inImage,"Main-Example-01: InImage");

        //2- Extract the 2-d data
        Object[] byteArrays = ImageIo.getColorByteImageArray2DFromBufferedImage(inImage);
        byte[][] byteData= (byte[][]) byteArrays[0];//Use for length and height
        
        //Create an array with noise
        byte[][] gausianNoise = Noise.createGaussionNoise(0, 500, byteData[0].length, byteData.length);
        
        //Add Noise to the Image
        Object[] byteArrayswithGaussianNoise = Noise.addGaussianNoise_3(byteArrays,gausianNoise);
        byte[][] rByteData= (byte[][]) byteArrayswithGaussianNoise[0];
        byte[][] gByteData= (byte[][]) byteArrayswithGaussianNoise[1];
        byte[][] bByteData= (byte[][]) byteArrayswithGaussianNoise[2];

        //Write out the noisy image as a file
        BufferedImage outImage = ImageIo.setColorByteImageArray2DToBufferedImage(rByteData, gByteData, bByteData);
        ImageIo.writeImage(outImage, "jpg", "noise.jpg");
        
        //The convoluted image arrays
        byte[][] rByteDataNew = new byte[byteData.length] [byteData[0].length];
        byte[][] gByteDataNew = new byte[byteData.length] [byteData[0].length];
        byte[][] bByteDataNew = new byte[byteData.length] [byteData[0].length];
        
        Convolution cnv = new Convolution();
        cnv.convolveEvenMask(rByteData, gByteData, bByteData, rByteDataNew, gByteDataNew, bByteDataNew, eMask3);
      
        //Write out the noisy image as a file
        BufferedImage outImage2 = ImageIo.setColorByteImageArray2DToBufferedImage(rByteDataNew, gByteDataNew, bByteDataNew);
        ImageIo.writeImage(outImage2, "jpg", "convolved.jpg");
        
    }
    
}
