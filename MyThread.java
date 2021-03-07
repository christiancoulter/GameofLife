package com.gameoflife;

class MyThread implements Runnable
{
	 private char grid[][],inter[][];
	    int x, y;

	    //initialize all the variables in the constructor like square index , grid,inter
	    public MyThread(int x,int y,char [][]grid,char [][]inter)
	    {
	        this.x=x;
	        this.y=y;
	        this.grid=grid;
	        this.inter=inter;
	    }

	    //find the status for x,y square for next generation
	    public boolean makeAlive()
	    {
	        int neigh=0;

	        for(int i=x-1;i<=x+1;i++)
	        {
	            for(int j=y-1;j<=y+1;j++)
	            {
	                if((i!=x || j!=y) && i>=0 && j>=0 && i<20 && j<20 && grid[i][j]=='X')
	                {
	                    neigh++;
	                }
	            }
	        }

	        //if this sqare is dead and it has exact three neighbur then make it alive

	        if(grid[x][y]=='O' && neigh==3)
	            return true;

	        if(grid[x][y]=='X' && (neigh==2||neigh==3))//if it is alive and it has either 2 or 4 neighbours then keep it alive for next generation
	            return true;
	        
	        return false;//otherwise make it dead
	    }

	    //every thread on calling start() willl call run() method and will update value in intermediate grid(inter) by extracting input from grid[][]

	    @Override

	    public void run()
	    {
	        boolean alive=makeAlive();
	        if(alive)//if it is supposed to be alive for next generation then mak it alive in next generation
	            inter[x][y]='X';
	        else
	            inter[x][y]='O';
	    }

}
