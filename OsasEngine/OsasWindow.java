package OsasEngine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon; 
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;


public class OsasWindow{
	static public int ThisStage = 1;

	static private JFrame frame;// = new JFrame();
	static private File saves_file = new File("stage");

	static private JLabel Background = new JLabel();
	static private JLabel labelMessage = new JLabel();//For GameMessage
	static private JLabel labelMessageSr = new JLabel();//For GameMessage
	
	static public void Wait(int sec){
		try {
	        Thread.sleep(1000 * sec);
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}

	public OsasWindow(JFrame _frame){
		frame = _frame;
		GameMessageInitialization();
	}

	static public void MessageBox(String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Authors message", JOptionPane.INFORMATION_MESSAGE);
    }

	static public void ShowWindow(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setBounds(100, 100, 800, 600);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void SetBackground(String file){
		try {
			Background.setIcon(new ImageIcon(ImageIO.read(new File(file))));
			frame.setContentPane(Background);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public void SaveOrNewF(){ SaveOrNewF(1); }
	static public void SaveOrNewF(int Stage){
		if(saves_file.canExecute()) { 
			saves_file.delete();
		}
		try(FileWriter writer = new FileWriter(saves_file))
        {
			writer.write("Stage=" + Stage);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	static public void LoadOrGetF(){
		try(FileReader reader = new FileReader(saves_file)){//Get stage
			   int c;
			   int i = -1;
	           while((c=reader.read())!=-1) {
	        		i = Character.getNumericValue(c);
	           }
	           ThisStage = i;
		} catch (IOException e1) {
			ThisStage = 1;
			SaveOrNewF();
			e1.printStackTrace();
		}
	}


	static public void ShowGameMessage(String text){
		frame.getContentPane().add(labelMessage);
		frame.getContentPane().add(labelMessageSr);

		labelMessage.setVisible(true);
		labelMessageSr.setVisible(true);
		labelMessage.setText(text);
	}

	static public void HideGameMessage(){
		labelMessage.setVisible(false);
		labelMessageSr.setVisible(false);
	}


	private static void GameMessageInitialization(){
		try {
			Image imMessageSr = ImageIO.read(new File("resources\\Message.png"));
			int hgMessage = imMessageSr.getHeight(null);
			int wdMessage = imMessageSr.getWidth(null);

			labelMessage.setFont(new Font("Times New Roman", Font.BOLD, 30));
			labelMessage.setForeground(Color.WHITE);
			labelMessage.setBounds(55, 20, wdMessage, hgMessage);

			labelMessageSr.setIcon(new ImageIcon(imMessageSr));
			labelMessageSr.setBounds(30, 20, wdMessage, hgMessage);

			frame.getContentPane().setLayout(null);

			HideGameMessage();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}