import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Scene extends GameObject {
	private Color bg;
	private String name;
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public ArrayList<GameObject> remove = new ArrayList<GameObject>();

	public Scene() {
		this("");
	}

	public Scene(String n) {
		this(n, Color.WHITE);
	}

	public Scene(Color c) {
		this("", c);
	}

	public Scene(String n, Color c) {
		name = n;
		setBg(c);
		startup();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getBg() {
		return bg;
	}

	public void setBg(Color bg) {
		this.bg = bg;
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void removeObject(GameObject o) {
		remove.add(o);

	}

	public void drawObjects(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}

	public void tick(int l) {
		for (GameObject o : objects) {
			o.tick(l);
		}
		int a = remove.size();
		for (int i = 0; i < objects.size(); i++) {
			for (int j = 0; j < remove.size(); j++) {
				if (objects.get(i) == remove.get(j)) {
					objects.remove(i);
					i--;
					remove.remove(j);
					j--;
					Timer.count--;
				}
			}
		}
		if (a > 0) {
			int b = 0;
			for (GameObject o : objects) {
				if (o.isTimer()) {
					o.resetLocation(b);
					b++;
				}
			}
		}
	}

	public void tick() {
		for (GameObject o : objects) {
			o.tick();
		}
	}

	public void render(Graphics g) {
		g.setColor(bg);
		g.fillRect(0, 0, Window.width, Window.height);

		drawObjects(g);
	}

	public void onClick() {
		for (GameObject o : objects) {
			if (o.containsCursor()) {
				o.onClick();
			}
		}

	}

	public void startup() {
		try {
			Scanner fr = new Scanner(new File("res//log.dat"));
			while (fr.hasNextLine()) {
				String s = fr.nextLine().trim();
				String[] s2 = s.split(":");
				if (s2.length >= 2) {
					Timer t = new Timer(s2[0], (double) (Double.valueOf(s2[1].trim())));
					this.addObject(t);
					Timer.count++;
				}
			}
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dump() {
		try {
			PrintWriter out = new PrintWriter("res//log.dat", "UTF-8");
			for (GameObject o : objects) {
				if (!o.toString().equals("")) {
					out.println(o.toString());
					System.out.println("written");
				}
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}