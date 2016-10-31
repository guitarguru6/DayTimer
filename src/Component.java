import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
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
			public void onClick(int a) {
				createFrame();
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
								if (s.length() > 15)
									s = s.substring(0, 15);
								scene.addObject(new Timer(s));
								Timer.count++;
								frame.dispose();
							}

						});
						frame.getContentPane().add(BorderLayout.CENTER, panel);
						frame.pack();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						frame.setResizable(false);

						input.requestFocus();
					}
				});
			}
		});
		scene.addObject(new Button(Window.width - 110, Window.height - 60, 100, 50, "Exit") {
			public void onClick(int a) {
				scene.dump();
				System.exit(0);
			}
		});
		TextBox t = new TextBox(500, 80, 275, 75);
		t.addLine("Left click on a timer to start/stop it");
		t.addLine("Right click on a timer to reset it");
		t.addLine("Click the \"X\" next to a timer to delete it");
		scene.addObject(t);
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

	public static void onClick(int a) {
		scene.onClick(a);
	}

	double start = System.currentTimeMillis();

	public void tick() {
		int time = (int) ((System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		try {
			scene.tick(time);
		} catch (Exception e) {

		}
	}

	public void render(Graphics g) {
		screen = createImage(Window.width, Window.height);
		g = screen.getGraphics();

		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Window.width, Window.height);
		try {
			scene.render(g);
		} catch (Exception e) {
		}
		g = this.getGraphics();
		g.drawImage(screen, 0, 0, null);
	}

}
