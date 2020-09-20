package HiVolt2;
import java.util.*;
import java.lang.*;
public class Mho {
	int x;
	int y;
	boolean alive = true;
	public Mho(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public boolean check(int x, int y, int value, int arr[][])
	{
		return arr[y+1][x+1] == value;
	}
	public double dis(int x, int y)
	{
		return Math.sqrt(x*x + y*y);
	}
	public void moves(int x1, int y1, int arr[][])
	{
		int dx = x1-x;
		int dy = y1-y;
		int tempx = x;
		int tempy = y;
		double slope = (double) (double)dy/(double)dx;
		if (Math.abs(dx) == Math.abs(dy))
		{
			if (dx > 0)
			{
				tempx++;
				if (dy > 0)
				{
					tempy++;
				}
				else
				{
					tempy--;
				}
			}
			else
			{
				tempx--;
				if (dy > 0)
				{
					tempy++;
				}
				else
				{
					tempy--;
				}
			}
			x = tempx;
			y = tempy;
			return;
		} 
		else if (Math.abs(dy) == 0)
		{
			if (dx > 0)
			{
				tempx++;
			}
			else
			{
				tempx--;
			}
			x = tempx;
			y = tempy;
			return;
		}
		else if (Math.abs(dx) == 0)
		{
			if (dy > 0)
			{
				tempy++;
			}
			else
			{
				tempy--;
			}
			x = tempx;
			y = tempy;
			return;
		}
		int dxx[] = new int[] {1,1,0,-1,-1,-1,0,1};
		int dyy[] = new int[] {0,1,1,1,0,-1,-1,-1};
		double dis[] = new double[8];
		for (int i =0; i < 8; i++)
		{
			int tempx1 = x + dxx[i];
			int tempy1 = y + dyy[i];
			if (check(tempx1,tempy1,0,arr))
			{
				double dist = dis(tempx1-x, tempy1-y);
				dis[i] = dist;
			}
			else
			{
				dis[i] = -2.0;
			}
		}
		double min = Integer.MAX_VALUE;
		int index = 0;
		for (int i =0; i < 8; i++)
		{
			if (dis[i] < min && dis[i] > 0)
			{
				min = dis[i];
				index = i;
			}
		}
		x+=dxx[index];
		y+=dyy[index];
		return;
	}
}
