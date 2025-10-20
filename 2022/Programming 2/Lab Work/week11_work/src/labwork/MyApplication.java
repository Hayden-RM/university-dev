package labwork;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyApplication extends JFrame{

	public MyApplication(String title) {
		
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 300);
		setResizable(false);
		
		JButton addButton1 = new JButton("Button 1");
		addButton1.setLocation(100, 50);
		addButton1.setSize(10, 30);
		addButton1.setBounds(100,100,100,100);
		
		JButton addButton2 = new JButton("Button 3");
		addButton2.setLocation(200, 50);
		addButton2.setSize(10, 30);
		addButton2.setBounds(100,100,100,100);
		
		JButton addButton3 = new JButton("Button 2");
		addButton3.setLocation(300, 50);
		addButton3.setSize(10, 30);
		addButton3.setBounds(100,100,100,100);
		
		getContentPane().add(addButton1);
		getContentPane().add(addButton2);
		getContentPane().add(addButton3);
		
		
	}
	
	public static void main(String[] args) {
		MyApplication frame;
	
		
		
		frame = new MyApplication("My Window for buttons");
		frame.setVisible(true);
		
	}
	
	
	
	
	
}
