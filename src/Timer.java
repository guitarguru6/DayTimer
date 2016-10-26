import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Timer extends GameObject {

	public static int count = 0;

	public Button button;
	public Button delete;
	public Rectangle r;
	public boolean active = false;
	public String name;
	public double time;

	public Timer() {
		this("", 0);
	}

	public Timer(String s) {
		this(s, 0);
	}

	public Timer(String s, double l) {
		name = s;
		time = l;
		setLocation(count);
		button = new Button(r, name, Color.RED) {
			public void onClick() {
				activate();
				if (active) {
					button.setColor(Color.GREEN);
				} else {
					button.setColor(Color.RED);
				}
			}

		};
		button.setAlignment('l');
		Rectangle r2 = r;
		r2.x += r.width + 5;
		r2.width = 40;
		delete = new Button(r, "X", Color.RED) {
			public void onClick() {
				delete();
			};

		};
		delete.font = new Font("Arial", 1, 30);
	}

	public boolean isTimer() {
		return true;
	}

	public void resetLocation(int i) {
		r = new Rectangle(20, i * 50 + 20, 200, 40);
		button.y = r.y;
		button.x = r.x;
		Rectangle r2 = r;
		r2.x += r.width + 5;
		r2.width = 40;

		delete.y = r2.y;

		delete.x = r2.x;
	}

	public void setLocation(int i) {
		r = new Rectangle(20, i * 50 + 20, 200, 40);
	}

	public boolean containsCursor() {
		return button.containsCursor() || delete.containsCursor();
	}

	public void onClick() {
		if (button.containsCursor()) {
			button.onClick();
		}
		if (delete.containsCursor()) {
			delete.onClick();
		}

	}

	public void activate() {
		active = !active;
	}

	public void delete() {
		Component.scene.removeObject(this);
	}

	public String getTime() {
		double d = (time / 1000.0);
		if (d < 60) {
			return "" + ((time / 1000.0));
		} else if (d < 3600) {
			int a = (int) ((time / 1000.0) / 60);
			String s = "" + a + ":";
			int s2 = (int) (((time / 1000.0) - (a * 60)));
			if (s2 < 10) {
				s += "0";
			}
			return s + s2;
		} else {
			int a = (int) ((time / 1000.0) / 3600);
			int b = (int) ((time / 1000.0) / 60) - (a * 60);
			String s = "" + a + ":";
			if (b < 10) {
				s += "0";
			}
			s += b + ":";
			int c = (int) (((time / 1000.0) - (b * 60) - (a * 3600)));
			if (c < 10) {
				s += "0";
			}
			s += c;
			return s;
		}
	}

	public void tick(int l) {
		if (active) {
			time += l;
			button.setText(name + ": " + getTime());
		}
	}

	public void tick() {
		if (active) {
			time += 1;
			button.setText(name + ": " + getTime());
		}
	}

	public void render(Graphics g) {
		if (button != null) {
			button.setText(" " + name + ": " + getTime());
			button.render(g);
			delete.render(g);
		}
	}

	public String toString() {
		return name + ":" + time;

	}

}
