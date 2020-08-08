package OsasEngine;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Image;

public class OsasObject{
	public int x, y, height, width;

	private JFrame frame;
	private JLabel label= new JLabel();

	public OsasObject(JFrame _frame, int _x, int _y){
		frame = _frame;
		x = _x;
		y = _y;
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(label);
	}

	public void SetObjectImage(String file){
		try {
			Image image = ImageIO.read(new File(file));
			label.setIcon(new ImageIcon(image));
			height = image.getHeight(null);
			width = image.getWidth(null);
			label.setBounds(x, y, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MoveObject(int _x, int _y){
		x = _x; y = _y;
		label.setBounds(_x, _y, width, height);
		label.repaint();
	}

	public void DestroyObject(){
		label.setVisible(false);
	}
}