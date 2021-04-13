package project_Group10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class OutputGui{

	
	static JFrame frame = new JFrame();
	
	static JPanel panel1 = new JPanel();
	static JPanel panel2 = new JPanel();
	static JPanel panel3 = new JPanel();
	static JPanel panel4 = new JPanel();
	static JPanel panel5 = new JPanel();
	static JPanel panel6= new JPanel();
	static JPanel panel7 = new JPanel();
	
	static JPanel panele1 = new JPanel();
	static JPanel panele2 = new JPanel();
	static JPanel panele3 = new JPanel();
	static JPanel panele4 = new JPanel();
	
	static JLabel header = new JLabel(); //create a header
	static JLabel elev1 = new JLabel(); //create a header
	static JLabel elev2 = new JLabel(); //create a header
	static JLabel elev3 = new JLabel(); //create a header
	static JLabel elev4 = new JLabel(); //create a header
	static JLabel footer = new JLabel(); //create a header
	
	
	
	static JLabel[] status = new JLabel[4]; //create a header
	static JLabel[] floor = new JLabel[4]; //create a header
	static Border border = BorderFactory.createLineBorder(Color.white,3);
	
	
	

	
	public void frm() {


		for (int i=0; i<4; i++) {
			
			status[i]= new JLabel();
			floor[i]= new JLabel();
			
		}
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 900);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		
		header.setText("Elevator Subsystem"); //set text of header
		header.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
		header.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
		header.setForeground(Color.white); //set font color of text
		header.setFont(new Font("MV Boli",Font.PLAIN,40)); //set font of text
		header.setBackground(Color.black); //set background color
		header.setOpaque(true); //display background color
		header.setBorder(border); //sets border of header (not image+text)
		header.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within header
		header.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within header
		
		elev1.setText("Elevator 1"); //set text of elev1
		elev1.setForeground(Color.white); //set font color of text
		elev1.setFont(new Font("MV Boli",Font.PLAIN,30)); //set font of text
		elev1.setBackground(Color.black); //set background color
		elev1.setOpaque(true); //display background color
		elev1.setBorder(border); //sets border of elev1 (not image+text)
		elev1.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within elev1
		elev1.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within elev1
		//elev1.setBounds(100, 100, 250, 250); //set x,y position within frame as well as dimensions
		
		elev2.setText("Elevator 2"); //set text of elev2
		elev2.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
		elev2.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
		elev2.setForeground(Color.white); //set font color of text
		elev2.setFont(new Font("MV Boli",Font.PLAIN,30)); //set font of text
		elev2.setIconTextGap(-25); //set gap of text to image
		elev2.setBackground(Color.black); //set background color
		elev2.setOpaque(true); //display background color
		elev2.setBorder(border); //sets border of elev2 (not image+text)
		elev2.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within elev2
		elev2.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within elev2
		//elev2.setBounds(100, 100, 250, 250); //set x,y position within frame as well as dimensions

		
		elev3.setText("Elevator 3"); //set text of elev3
		elev3.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
		elev3.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
		elev3.setForeground(Color.white); //set font color of text
		elev3.setFont(new Font("MV Boli",Font.PLAIN,30)); //set font of text
		elev3.setIconTextGap(-25); //set gap of text to image
		elev3.setBackground(Color.black); //set background color
		elev3.setOpaque(true); //display background color
		elev3.setBorder(border); //sets border of elev3 (not image+text)
		elev3.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within elev3
		elev3.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within elev3
		//elev3.setBounds(100, 100, 250, 250); //set x,y position within frame as well as dimensions
		
		
		elev4.setText("Elevator 4"); //set text of elev4
		elev4.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
		elev4.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
		elev4.setForeground(Color.white); //set font color of text
		elev4.setFont(new Font("MV Boli",Font.PLAIN,30)); //set font of text
		elev4.setIconTextGap(-25); //set gap of text to image
		elev4.setBackground(Color.black); //set background color
		elev4.setOpaque(true); //display background color
		elev4.setBorder(border); //sets border of elev4 (not image+text)
		elev4.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within elev4
		elev4.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within elev4
		//elev4.setBounds(100, 100, 250, 250); //set x,y position within frame as well as dimensions
		
		
		footer.setText("Group 10"); //set text of footer
		//footer.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
		//footer.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
		footer.setForeground(Color.red); //set font color of text
		footer.setFont(new Font("MV Boli",Font.PLAIN,20)); //set font of text
		footer.setBackground(Color.black); //set background color
		footer.setOpaque(true); //display background color
		footer.setBorder(border); //sets border of footer (not image+text)
		footer.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within footer
		footer.setHorizontalAlignment(JLabel.RIGHT); //set horizontal position of icon+text within footer
		//footer.setBounds(0, 0, 250, 250); //set x,y position within frame as well as dimensions
		
		for (int i=0; i<4; i++) {
			
			status[i].setText("Status:NA"); //set text of footer
			//footer.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
			//footer.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
			status[i].setForeground(Color.white); //set font color of text
			status[i].setFont(new Font("MV Boli",Font.PLAIN,25)); //set font of text
			status[i].setBackground(Color.black); //set background color
			status[i].setOpaque(true); //display background color
			//footer.setBorder(border); //sets border of footer (not image+text)
			status[i].setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within footer
			status[i].setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within foo
			
			
		}
		

		for (int i=0; i<4; i++) {
			
			floor[i].setText("Current Floor:NA"); //set text of footer
			//footer.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
			//footer.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
			floor[i].setForeground(Color.white); //set font color of text
			floor[i].setFont(new Font("MV Boli",Font.PLAIN,30)); //set font of text
			floor[i].setBackground(Color.black); //set background color
			floor[i].setOpaque(true); //display background color
			//footer.setBorder(border); //sets border of footer (not image+text)
			floor[i].setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within footer
			floor[i].setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within foo
			
			
		}

		
		panel1.setBackground(Color.black);
		panel1.setPreferredSize(new Dimension(100,100));
		

		panel2.setBackground(Color.black);
		panel2.setPreferredSize(new Dimension(300,3000));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
				
		
		panel3.setBackground(Color.black);
		panel3.setPreferredSize(new Dimension(100,100));
		
		panel4.setBackground(Color.black);
		panel4.setPreferredSize(new Dimension(100,100));
		panel5.setBackground(Color.black);
		panel5.setPreferredSize(new Dimension(100,100));
		panel6.setBackground(Color.black);
		panel6.setPreferredSize(new Dimension(100,100));
		panel7.setBackground(Color.black);
		panel7.setPreferredSize(new Dimension(100,100));
		
		
		//------------- sub panels --------------------


		
		panele1.setBackground(Color.black);
		panele2.setBackground(Color.black);
		panele3.setBackground(Color.black);
		panele4.setBackground(Color.black);
		
		panele1.setLayout(new GridLayout(3,1));
		panele2.setLayout(new GridLayout(3,1));
		panele3.setLayout(new GridLayout(3,1));
		panele4.setLayout(new GridLayout(3,1));
		
		panele1.setBorder(border); 
		panele2.setBorder(border); 
		panele3.setBorder(border); 
		panele4.setBorder(border); 
	
		
		panele1.setPreferredSize(new Dimension(300,400));
		panele2.setPreferredSize(new Dimension(300,400));
		panele3.setPreferredSize(new Dimension(300,400));
		panele4.setPreferredSize(new Dimension(300,400));
		
		panel4.add(elev1);
		panele1.add(panel4);
		panele1.add(floor[0]);
		panele1.add(status[0]);
		
		
		panel5.add(elev2);
		panele2.add(panel5);
		panele2.add(floor[1]);
		panele2.add(status[1]);
		
		panel6.add(elev3);
		panele3.add(panel6);
		panele3.add(floor[2]);
		panele3.add(status[2]);
		
		panel7.add(elev4);
		panele4.add(panel7);
		panele4.add(floor[3]);
		panele4.add(status[3]);
		
		panel2.add(panele1);
		panel2.add(panele2);
		panel2.add(panele3);
		panel2.add(panele4);
		panel3.add(footer);
		panel1.add(header);
	
	
		//------------- Adding Main panels to  Frame --------------------
	
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(panel2,BorderLayout.CENTER);
		frame.add(panel3,BorderLayout.SOUTH);
	}	
	
	public synchronized void setFlr1(String s) {floor[0].setText(s);} 
	public synchronized void setFlr2(String s) {floor[1].setText(s);} 
	public synchronized void setFlr3(String s) {floor[2].setText(s);} 
	public synchronized void setFlr4(String s) {floor[3].setText(s);} 
	public synchronized void setStat1(String s) {status[0].setText(s);} 
	public synchronized void setStat2(String s) {status[1].setText(s);} 
	public synchronized void setStat3(String s) {status[2].setText(s);} 
	public synchronized void setStat4(String s) {status[3].setText(s);} 
	
	public static void main(String[] args) {
		OutputGui g = new OutputGui();
		g.frm();
		
	}
	
}