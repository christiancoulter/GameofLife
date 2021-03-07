package com.gameoflife;

import java.io.*;
import java.util.*;

public class Game {
	//to store grid
    private char grid[][]=new char[20][20];

    //to store intermediate values for next generation
    private char inter[][]=new char[20][20];

    //read file into grid and generation value
    public int readFile()
    {
        int index=0,gen=1;
        try
        {
            File file=new File("C:\\Users\\chris\\eclipse-workspace\\Game\\src\\com\\gameoflife\\startinggrid.txt");

            Scanner sc=new Scanner(file);
            while(sc.hasNext())
            {
                String str=sc.next();
                //if length is 20 then read it in array

                if(str.length()==20)
                {
                    for(int j=0;j<20;j++)
                    {
                        grid[index][j]=str.charAt(j);
                    }
                    index++;
                }
                else
                {
                    gen=Integer.parseInt(str);
                    break;
                }
            }
            sc.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return gen;//return gen
    }

    //copy inter into grid at end of each generation
    public void copyInterIntoGrid()
    {
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                grid[i][j]=inter[i][j];
            }
        }
    }

    //process data for generation one at a time
    public void process()
    {
        //create 20*20 thread for each square
        Thread t[]=new Thread[400];
        int index=0;//index

        //instantiate thread
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                t[index] = new Thread(new MyThread(i,j,grid,inter));
                t[index].start();//start
                index++;
            }
        }
        try {
            //keep this main thread on hold untill all the other threads finish their work
            for(int i=0;i<400;i++)
                t[i].join();
        } catch(Exception e)
        {
            System.out.println(e);
        }
        copyInterIntoGrid();//copy inter into grid
    }

    public static void main(String[] args) 
    {
        Game gof=new Game();

        int gen=gof.readFile();

        //process for each generation
        for(int i=0;i<gen;i++)
        {
            gof.process();
        }

        //printing process
        System.out.println("Final output after at last generation");
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                System.out.print(String.valueOf(gof.grid[i][j]));
            }
            System.out.println("");
        }

    }  
}
