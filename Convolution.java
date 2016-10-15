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
public class Convolution {
    

    public void convolveEvenMask(byte[][]rByteData,byte[][]gByteData,byte[][]bByteData, byte[][] rByteDataNew, byte[][] gByteDataNew,byte[][] bByteDataNew, float[][] eMask) 
    {
 
        //Check your mask
        for (int i = 0; i < eMask.length; i++) {
            for (int j = 0; j < eMask[0].length; j++) {
                System.out.print(" " + eMask[i][j]);
            }
            System.out.println("\n------------------------------");
        }

        // Convolution
        int m2 = (int) (eMask.length / 2);//Top to Bottom
        int n2 = (int) (eMask[0].length / 2);//Left to Right
        System.out.println("m2 = :" + m2 + " n2 = " + n2);
       
        float sumR, sumG, sumB;
        for(int i = 0; i < rByteDataNew.length - eMask.length; i++)
        {
            for(int j = 0; j < rByteDataNew[0].length - eMask[0].length; j++)
            {
                sumR = 0.0f;
                sumG = 0.0f;
                sumB = 0.0f;

                for(int x = 0; x < eMask.length; x++)
                    for(int y = 0; y < eMask[0].length; y++)
                    {
                        sumR += eMask[x][y] * (rByteData[x+i][y+j] & 0xff);
                        sumG += eMask[x][y] * (gByteData[x+i][y+j] & 0xff);
                        sumB += eMask[x][y] * (bByteData[x+i][y+j] & 0xff);
                    }
                
                sumR = ImageIo.clip(Math.abs(sumR));
                sumG = ImageIo.clip(Math.abs(sumG));
                sumB = ImageIo.clip(Math.abs(sumB));
                
                rByteDataNew[i][j]= (byte) sumR;
                gByteDataNew[i][j]= (byte) sumG;
                bByteDataNew[i][j]= (byte) sumB;
            }
        }
        
        //HANDLE BORDERS
        //Top to Bottom
        int arrayHeight = rByteDataNew.length;
        for(int i = 0; i < arrayHeight; i++)
        {
            for(int j = (rByteDataNew[0].length - eMask[0].length); j < rByteDataNew[0].length; j++)
            {
                rByteDataNew[i][j] = rByteData[i][j];
                gByteDataNew[i][j] = gByteData[i][j];
                bByteDataNew[i][j] = bByteData[i][j];
                //System.out.println("Coordinates ("+i+","+j+")");

            }
        }

        //Left to Right
        for(int i = rByteDataNew.length - eMask.length; i < rByteDataNew.length; i++)
        {
            for(int j = 0; j < rByteDataNew[0].length; j++)
            {
                rByteDataNew[i][j] = rByteData[i][j];
                gByteDataNew[i][j] = gByteData[i][j];
                bByteDataNew[i][j] = bByteData[i][j];
            }
        }
      
    }
}
