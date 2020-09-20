package HiVolt2;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
//import javax.sound.*;
public class Board extends JFrame{ //implements KeyListener{
	Random r= new Random();
	JFrame f = new JFrame();
	JFrame f2 = new JFrame();
	JFrame f3 = new JFrame();
	JFrame f4 = new JFrame();
	JFrame thx = new JFrame();;
	Mho Mhos[];
	Player p1;
	Fence fence[];
	JLabel l;
	JTextField txt;
	JLabel input;
	JPanel panel;
	int arr[][] = new int[12][12];
	public JButton arr2[][];
	boolean gameover = false;
	public Board(){}
	public void initallize()
	{
		arr2 = new JButton[12][12];
		f.setSize(800, 800);
		f.setLayout(new GridLayout(12,12));
		f.setResizable(false);
		f.setTitle("Moves: 0");
		for (int i =0;i<12; i++)
		{
			for (int ii =0; ii <12; ii++)
			{
				arr2[i][ii] = new JButton();
				arr2[i][ii].setBackground(new Color(255,255,255));
				arr2[i][ii].setBorderPainted(false);
				arr2[i][ii].setOpaque(true);
				arr2[i][ii].addKeyListener(new KeyListener () {
					@Override
					public void keyPressed(KeyEvent e) {
						String str = "";
						str += e.getKeyChar();
						str = str.toUpperCase();
						process(str.charAt(0));
					}
					@Override
					public void keyTyped(KeyEvent e) {}
					@Override
					public void keyReleased(KeyEvent e) {}
				});
				f.add(arr2[i][ii]);
			}
		}
		Mhos = new Mho[12];
		fence = new Fence[20];
		for (int i =0; i < 20; i++)
		{
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			if (arr[y+1][x+1] != 0)
			{
				i--;
				continue;
			}
			else
			{
				arr[y+1][x+1] = 1;
				fence[i] = new Fence(x,y);
				ImageIcon imageIcon = new ImageIcon("D:/Fence.jpg");
				Image image = imageIcon.getImage();
				Image image2 = image.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
				arr2[y+1][x+1].setIcon(new ImageIcon(image2));
			}
		}
		for (int i = 0; i < 12; i++)
		{
			arr[0][i] = 1;
			arr[i][0] = 1;
			arr[11][i] = 1;
			arr[i][11] = 1;
			ImageIcon imageIcon = new ImageIcon("D:/Fence.jpg");
			Image image = imageIcon.getImage();
			Image image2 = image.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
			arr2[0][i].setIcon(new ImageIcon(image2));
			arr2[i][0].setIcon(new ImageIcon(image2));
			arr2[11][i].setIcon(new ImageIcon(image2));
			arr2[i][11].setIcon(new ImageIcon(image2));
			
		}
		for (int i =0; i < 12; i++)
		{
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			if (arr[y+1][x+1] != 0)
			{
				i--;
				continue;
			}
			else
			{
				arr[y+1][x+1] = 2;
				Mhos[i] = new Mho(x,y);
				ImageIcon imageIcon2 = new ImageIcon("D:/Mho2.jpg");
				Image image23 = imageIcon2.getImage();
				Image image22 = image23.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
				arr2[y+1][x+1].setIcon(new ImageIcon(image22));
			}
		}
		for (int i =0; i < 1; i++)
		{
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			if (arr[y+1][x+1] != 0)
			{
				i--;
				continue;
			}
			else
			{
				p1= new Player(x,y);
				ImageIcon imageIcon = new ImageIcon("D:/Player.jpg");
				Image image = imageIcon.getImage();
				Image image2 = image.getScaledInstance(45, 59,  java.awt.Image.SCALE_SMOOTH);
				arr2[y+1][x+1].setIcon(new ImageIcon(image2));
			}
		}
		f.setVisible(true);
	}
	public boolean check(int x, int y, int value)
	{
		return arr[y+1][x+1] == value;
	}
	public boolean deadP(int x, int y)
	{
		if (arr[y][x] == 0)
			return false;
		return true;
	}
	public boolean deadM(int x, int y)
	{
		if (arr[y][x] == 0)
			return false;
		return true;
	}
	public void process(char keyPressed)
	{
		arr2[p1.y+1][p1.x+1].setIcon(new ImageIcon());
		p1.move(keyPressed);
		if (deadP(p1.x+1,p1.y+1))
		{
			gameover();
			return;
		}
		if (keyPressed == 'J')
			System.out.println(arr[p1.y+1][p1.x+1]);
		int count = 0;
		for (int i =0; i <12; i++)
		{
			if (Mhos[i].alive)
			{
				count++;
			}
		}
		f.setTitle("Moves: " + p1.moves + "; Mhos left: " + count + "/12");
		ImageIcon imageIcon = new ImageIcon("D:/Player.jpg");
		Image image = imageIcon.getImage();
		Image image2 = image.getScaledInstance(45, 59,  java.awt.Image.SCALE_SMOOTH);
		arr2[p1.y+1][p1.x+1].setIcon(new ImageIcon(image2));
		f.setVisible(true);
		boolean alldead = true;
		for (int i =0; i < 12; i++)
		{
			if (Mhos[i].alive && keyPressed != 'J')
			{
				arr[Mhos[i].y+1][Mhos[i].x+1] = 0;
				arr2[Mhos[i].y+1][Mhos[i].x+1].setIcon(new ImageIcon());
				Mhos[i].moves(p1.x, p1.y, arr);
				if (deadM(Mhos[i].x+1,Mhos[i].y+1))
				{
					Mhos[i].alive = false;
					continue;
				}
				ImageIcon imageIcon2 = new ImageIcon("D:/Mho2.jpg");
				Image image23 = imageIcon2.getImage();
				Image image22 = image23.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
				arr2[Mhos[i].y+1][Mhos[i].x+1].setIcon(new ImageIcon(image22));
				arr[Mhos[i].y+1][Mhos[i].x+1] = 2;
			}
		}
		for (int i =0; i < 12; i++)
		{
			if (Mhos[i].alive) alldead = false;
		}
		if (alldead || p1.moves >= 12)
		{
			gamewin();
			return;
		}
		f.setVisible(true);
		if (deadP(p1.x+1,p1.y+1))
		{
			gameover();
			return;
		}
	}
	public void gameover()
	{
		gameover= true;
		f2.setSize(800, 800);
		f2.setLayout(new GridLayout(1,1));
		f2.setResizable(false);
		JButton j = new JButton();
		ImageIcon imageIcon2 = new ImageIcon("D:/gameover.jpg");
		Image image23 = imageIcon2.getImage();
		Image image22 = image23.getScaledInstance(800, 800,  java.awt.Image.SCALE_SMOOTH);
	    ImageIcon img = new ImageIcon(image22);
	    j.setIcon(img);
	    f2.add(j);
	    f.setVisible(false);
	    f3.setVisible(false);
	    f2.setVisible(true);
	    ask();
	}
	public void gamewin()
	{
		gameover= true;
		f2.setSize(800, 800);
		f2.setLayout(new GridLayout(1,1));
		f2.setResizable(false);
		JButton j = new JButton();
		ImageIcon imageIcon2 = new ImageIcon("D:/Win.jpg");
		Image image23 = imageIcon2.getImage();
		Image image22 = image23.getScaledInstance(800, 800,  java.awt.Image.SCALE_SMOOTH);
	    ImageIcon img = new ImageIcon(image22);
	    j.setIcon(img);
	    f2.add(j);
	    f.setVisible(false);
	    f3.setVisible(false);
	    f2.setVisible(true);
	    ask();
	}
	public void ask()
	{
	    input = new JLabel("Do you want to play again? (y for yes and n for no)");
	    txt = new JTextField(5);
	    txt.addActionListener(new Listener());
	    panel = new JPanel();
	    panel.setPreferredSize (new Dimension(500, 500));
        panel.setBackground (Color.white);
        panel.add (input);
        panel.add (txt);
        f4.getContentPane().add(panel);
        f4.pack();
        f4.setVisible(true);
	}
	class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			String text = txt.getText();
			if (text.equals("y"))
			{
				f.setVisible(false);
				f2.setVisible(false);
				f3.setVisible(false);
				initallize();
			}
			else if(text.equals("n"))
			{
				JButton jj = new JButton();
				ImageIcon imageIcon5 = new ImageIcon("D:/thx.jpg");
				Image image5 = imageIcon5.getImage();
				Image imagef = image5.getScaledInstance(800, 800,  java.awt.Image.SCALE_SMOOTH);
			    ImageIcon img = new ImageIcon(imagef);
			    jj.setIcon(new ImageIcon());
				thx.setSize(800, 800);
				thx.setLayout(new GridLayout(1,1));
				thx.setResizable(false);
			    thx.add(jj);
			    f.setVisible(false);
			    f2.setVisible(false);
			    f3.setVisible(false);
			    f4.setVisible(false);
			    thx.setVisible(true);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    System.exit(0);
			}
			else
			{
				input = new JLabel("Invalid input. Try again. (y for yes and n for no)");
			}
		}
	}
}
