import OsasEngine.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.io.File;



public class Example{
	//Create frame
	static private JFrame frame = new JFrame();
	//Create Window
	static OsasWindow osas = new OsasWindow(frame);
	//Create main hero
	static OsasObject LittleMauro;

	static public void main(String[] args){
		//Show Window
		osas.ShowWindow();

		//Set Background(file name)
		//osas.SetBackground("");//Path to image
		
		//Show Menu
		ShowMenu();

		//Show Message Box (text)
		osas.MessageBox("You lose, when open this game. UHaaaahaa");
	}

	static void LoadStage(){
		//State of main hero
		LittleMauro = new OsasObject(frame, 50, 350);//(frame, x, y)

		//Chose image for object
		LittleMauro.SetObjectImage("");//Path to image

		//Create Key Listener
		frame.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent event){
				switch (event.getKeyChar()){
					case 'a':{
						LittleMauro.MoveObject(LittleMauro.x-10, LittleMauro.y); 
						osas.HideGameMessage();
						break;
					}
					case 'd':{
						LittleMauro.MoveObject(LittleMauro.x+10, LittleMauro.y); 
						osas.HideGameMessage();
						break;
					}
					case 'w':{
						LittleMauro.MoveObject(LittleMauro.x, LittleMauro.y-50);

						new Thread(new Runnable() { // New procces for no waiting in game, just waiting passive and go down
           					public void run() {
		                    	osas.Wait(1); // wait 1 second
		                        LittleMauro.MoveObject(LittleMauro.x, LittleMauro.y+50);
				            }
				        }).start();

						osas.HideGameMessage();
						break;
					}
					case 'c':{osas.ShowGameMessage("Little Mauro: Treba mislit"); break;}
					default:{break;}
				}
			}
		});

		//Chose stage
		switch (osas.ThisStage){
			case 1:{Stage_test(); break;}
			//...
			default:{osas.MessageBox("Stage doesn't found");}//osas.SaveOrNewF();
		}
	}

	static void Stage_test(){

		osas.MessageBox("Stage test begin");
		
		//Show Game Message
		osas.ShowGameMessage("Little Mauro: i am reaady");

		//Hide Game Message
		osas.HideGameMessage();

		//Create sound1(file)
		OsasSound sound1 = new OsasSound("");//Path to music

		//Stop sound1, maybe its playing now
		sound1.stop();


		//Play sound1
		sound1.play();

		//Get x, y , width, height of object
		int xtest = LittleMauro.x;
		int ytest = LittleMauro.y;
		int wtest = LittleMauro.width;
		int htest = LittleMauro.height;

		//Create object
		OsasObject stone = new OsasObject(frame, 100, 100);//(frame, x, y)

		//Delete Object
		stone.DestroyObject();


		//When stage final
		osas.ThisStage = osas.ThisStage + 1;

		//Save(Number of stage), if no number => new game, example: SaveOrNewF();
		osas.SaveOrNewF(osas.ThisStage);
		LoadStage();
 
	}

static public void ShowMenu(){
			
			JButton btnLoad = new JButton("Load game");
			JButton btnNew = new JButton("New game");
			JButton btnAbout = new JButton("About");
			JButton btnExit = new JButton("Exit");

			btnLoad.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					btnLoad.setVisible(false);
					btnNew.setVisible(false);
					btnAbout.setVisible(false);
					btnExit.setVisible(false);

					osas.LoadOrGetF();
					LoadStage();
				}
			});
		
			btnNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnLoad.setVisible(false);
					btnNew.setVisible(false);
					btnAbout.setVisible(false);
					btnExit.setVisible(false);

					osas.SaveOrNewF();
					osas.ThisStage = 1;
					LoadStage();
				}
			});

			btnAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					osas.MessageBox("Revas, Loris, Big Mauro, Nicola");
				}
			});
		
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(1);
				}
			});
			
			btnLoad.setBounds(300, 337, 200, 44);
			btnNew.setBounds(300, 387, 200, 44);
			btnAbout.setBounds(300, 437, 200, 44);
			btnExit.setBounds(300, 487, 200, 44);
			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(btnLoad);
			frame.getContentPane().add(btnAbout);
			frame.getContentPane().add(btnNew);
			frame.getContentPane().add(btnExit);
		}
	
}
