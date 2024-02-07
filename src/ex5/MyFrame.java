package ex5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyFrame extends JFrame{
	
	int com_score = 0;
	int my_score = 0;
	int num = 0;
	
	//store the card numbers
	ArrayList<Integer> list = new ArrayList<>();
	
	//define frame layout 
	//create the panel for line 1 and put 2 back card images in line 1
	JPanel jp1 = new JPanel();
	JLabel jl1 = new JLabel(new ImageIcon("card/backCard.png"));
	JLabel jl2 = new JLabel(new ImageIcon("card/backCard.png"));
	JLabel jl3 = new JLabel(new ImageIcon("card/backCard.png"));
	
	//create panel for computer picture and computer guess value
	JPanel jp21 = new JPanel();
	JLabel jlComputer = new JLabel(iconScaling(new ImageIcon("card/pc.png"),28,28));
	Label computerGuess = null;
	
	//create panel for my picture and my guess value
	JPanel jp22 = new JPanel();
	JLabel jlMe = new JLabel(iconScaling(new ImageIcon("card/me.png"),28,28));
	JTextField myText = new JTextField(10);
	
	//button ok and again
	JButton bok = new JButton("确定");
	JButton bagain = new JButton("再来");
	
	//create the panel for line 2
	JPanel jp2 = new JPanel();
	
	//shrink icon
	public ImageIcon iconScaling(ImageIcon imageIcon, int width, int height) {
		Image img = imageIcon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		imageIcon.setImage(img);
		return imageIcon;
	}
	
	//init frame
	public void init() {
		num = 0;
		if(list.isEmpty()) {
			for(int i = 1; i<=54; i++) {
				list.add(i);
			}
		}
		
		//random order
		Collections.shuffle(list);
		
		//put 3 cards into panel
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		
		//jl1
		jl1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(num != 0) return;
				jl1.setIcon(new ImageIcon("card/"+list.get(0)+".png"));
				num = num+1;
				super.mouseEntered(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				jl1.setIcon(new ImageIcon("card/backCard.png"));
				super.mouseExited(e);
			}
			
		});
		
		//jl2
		jl2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(num != 0) return;
				jl2.setIcon(new ImageIcon("card/"+list.get(1)+".png"));
				num = num+1;
				super.mouseEntered(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				jl2.setIcon(new ImageIcon("card/backCard.png"));
				super.mouseExited(e);
			}
			
		});

		//jl3
		jl3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(num != 0) return;
				jl3.setIcon(new ImageIcon("card/"+list.get(2)+".png"));
				num = num+1;
				super.mouseEntered(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				jl3.setIcon(new ImageIcon("card/backCard.png"));
				super.mouseExited(e);
			}
			
		});
		
		//1~39
		int random = (int)(Math.random()*39)+1;
		computerGuess = new Label(random + "");
		jlComputer.setPreferredSize(new Dimension(28,28));
		jp21.add(jlComputer);
		jp21.add(computerGuess);
		
		jp22.add(jlMe);
		jp22.add(myText);
		
		jp22.add(bok);
		jp22.add(bagain);
		
		jp2.add(jp21);
		jp2.add(jp22);
		
		bagain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Collections.shuffle(list);
				jl1.setIcon(new ImageIcon("card/backCard.png"));
				jl2.setIcon(new ImageIcon("card/backCard.png"));
				jl3.setIcon(new ImageIcon("card/backCard.png"));
				num = 0;
				int random = (int)(Math.random()*39)+1;
				computerGuess.setText(""+ random);
			}
			
		});
		
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(myText.getText().isEmpty()) return;
				
				if(Integer.parseInt(myText.getText()) < 1 || Integer.parseInt(myText.getText()) > 39) return;
				
				jl1.setIcon(new ImageIcon("card/"+list.get(0)+".png"));
				jl2.setIcon(new ImageIcon("card/"+list.get(1)+".png"));
				jl3.setIcon(new ImageIcon("card/"+list.get(2)+".png"));
				
				int result = 0;
				
				for(int i = 0; i<3; i++) {
					if(list.get(i) < 13) result = result + list.get(i);
					else if(list.get(i) > 13 && list.get(i) < 27) result = result + list.get(i)-13;
					else if(list.get(i) > 26 && list.get(i) < 40) result = result + list.get(i)-26;
					else if(list.get(i) >39 && list.get(i) < 53) result = result + list.get(i)-39;
				}
				
				if(Math.abs(result-Integer.parseInt(computerGuess.getText())) < Math.abs(result-Integer.parseInt(myText.getText()))) {
					com_score ++;
					MyFrame.this.setTitle("Computer win!The answer is:"+result+".The latest score is->Computer:You->"+com_score+":"+my_score);
				}else if(Math.abs(result-Integer.parseInt(computerGuess.getText())) > Math.abs(result-Integer.parseInt(myText.getText()))) {
					my_score ++;
					MyFrame.this.setTitle("You win!The answer is:"+result+".The latest score is->Computer:You->"+com_score+":"+my_score);
				}else {
					MyFrame.this.setTitle("Draw even!The answer is:"+result+".The latest score is->Computer:You->"+com_score+":"+my_score);
				}
			}
			
		});
		
		getContentPane().add(jp1, BorderLayout.NORTH);
		getContentPane().add(jp2);
		setTitle("猜牌游戏     "+com_score+":"+my_score);
		pack();
	}
	
}
