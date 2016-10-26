import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;

	private static boolean isRunning;

	public static Graphics g;
	public static Image screen;

	public static Listening listening;

	public static Scene scene;

	public static Window w = new Window();

	public static void main(String[] args) {
		Component component = new Component();
		
		w.add(component);
		//w.undecorate();
		component.init();
		
	}

	public void init() {
		isRunning = true;
		new Thread(this).start();
		listening = new Listening();
		addMouseListener(listening);
		addMouseMotionListener(listening);
		addMouseWheelListener(listening);
		addKeyListener(listening);

		scene = new Scene("Scene", Color.lightGray);
		scene.addObject(new Button(Window.width - 220, Window.height - 60, 100, 50, "New") {
			public void onClick() {
				// System.out.println("Heyo");
				createFrame();
				// System.exit(0);
			}

			public void createFrame() {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						JFrame frame = new JFrame("New Timer");
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						JPanel panel = new JPanel();
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
						panel.setOpaque(true);

						JPanel inputpanel = new JPanel();
						inputpanel.setLayout(new FlowLayout());
						JLabel label = new JLabel("Timer name:");
						JTextField input = new JTextField(20);
						JButton button = new JButton("Enter");
						
						inputpanel.add(label);
						inputpanel.add(input);
						inputpanel.add(button);
						panel.add(inputpanel);
						button.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								String s = input.getText();
								scene.addObject(new Timer(s));
								Timer.count++;
								//System.out.println(s);
								frame.dispose();
							}

						});
						frame.getContentPane().add(BorderLayout.CENTER, panel);
						//frame.setUndecorated(true);
						//frame.setShape(new Rectangle(0, 26, 400, 40));
						frame.pack();
						frame.setLocationRelativeTo(null);// ;(true);
						frame.setVisible(true);
						frame.setResizable(false);
						//frame.setUndecorated(false);

						input.requestFocus();
					}
				});
			}
		});
		scene.addObject(new Button(Window.width - 110, Window.height - 60, 100, 50, "Exit") {
			public void onClick() {
				scene.dump();
				System.exit(0);
			}
		});
//		scene.addObject(new Timer("Timer", 0));
//		Timer.count++;
	}

	public static void resized() {

	}

	public void run() {
		while (isRunning) {
			tick();
			render(g);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void onClick() {
		scene.onClick();
	}

	double start = System.currentTimeMillis();

	public void tick() {
		int time = (int) ((System.currentTimeMillis() - start));
//		if(time > 1000) {
			start = System.currentTimeMillis();
//			scene.tick();
//		}
		try {
//			System.out.println(time);
			scene.tick(time);
		} catch (Exception e) {

		}

		// w.tick();
	}

	public void render(Graphics g) {
		screen = createImage(Window.width, Window.height);
		g = screen.getGraphics();

		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Window.width, Window.height);
		try {
//			System.out.println(time);
			scene.render(g);
		} catch (Exception e) {

		}
		g = this.getGraphics();
		g.drawImage(screen, 0, 0, null);
	}

}
