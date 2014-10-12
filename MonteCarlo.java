package com.company;

/**
 * Created by sophialin on 10/11/14.
 */
import java.util.Scanner;

public class MonteCarlo
{
    public static void main (String args [])
    {
        Scanner scan = new Scanner (System.in);

        System.out.println("How many times would you like to run the program?");
        int runs = scan.nextInt();
        int hit = 0;
        int miss = 0;

        for(int i= 0; i < runs; i++)
        {
            double randX = (Math.random() * 2) - 1;
            double randY = (Math.random() * 2) - 1;
            double dist = Math.sqrt(randX * randX + randY * randY);

            if (dist < 1)
            {
                hit++;
            }
            else
            {
                miss++;
            }
        }

        double approx_pi = (double) (4.0 * hit / runs);

        System.out.println("Number of hits:" + hit);
        System.out.println("Number of misses:" + miss);

        System.out.println(approx_pi);
    }
}