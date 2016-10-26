import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Button extends GameObject implements Drawable {
	public int x, y, width, height;
	
	//public Rectangle r;
	public Font font = new Font("Arial", 2, 20);
	private String text = "";
	private Color color = Color.YELLOW;
	public char alignment = 'c';

	public Button() {
		this(0, 0, 0, 0, "");
	}
	
	public Button(int x, int y, int w, int h) {
		this(x, y, w, h, "");
	}
	
	public Button(int x, int y, int w, int h, String t) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		setText(t);
		
		//r = new Rectangle(x, y, w, h);
	}
	
	public Button(Rectangle r, String t, Color c) {
		this(r.x, r.y, r.width, r.height, t);
		color = c;
	}
	
	public Button(int x, int y, int w, int h, String t, Color c) {
		this(x, y, w, h, t);
		color = c;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}
	
	public char getAlignment() {
		return alignment;
	}

	public void setAlignment(char c) {
		this.alignment = c;
	}

	public boolean containsCursor() {
		Rectangle r2 = new Rectangle(x, y, width, height);
		return r2.contains(Listening.mx, Listening.my);
	}
	
//	public void resetSize() {
//		x = r.x;
//		y = r.y;
//		width = r.width;
//		height = r.height;
//	}
	
	public abstract void onClick();
	
	public boolean isButton() {
		return true;
	}
	
//	public void centerString(Graphics g, Font font) {
//	    FontRenderContext frc = new FontRenderContext(null, true, true);
//
//	    Rectangle2D r2D = font.getStringBounds(text, frc);
//	    int rWidth = (int) Math.round(r2D.getWidth());
//	    int rHeight = (int) Math.round(r2D.getHeight());
//	    int rX = (int) Math.round(r2D.getX());
//	    int rY = (int) Math.round(r2D.getY());
//
//	    int a = (width / 2) - (rWidth / 2) - rX;
//	    int b = (height / 2) - (rHeight / 2) - rY;
//
//	    g.setFont(font);
//	    g.drawString(text, x + a, y + b);
//	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		if(containsCursor()) g.setColor(Color.WHITE);
		
		g.fillRoundRect(x, y, width, height, 5, 5);
		
		g.setColor(color);
		g.fillRoundRect(x+1, y+1, width-2, height-2, 5, 5);
		
		g.setColor(Color.BLACK);
		//Font font = new Font("Arial", 2, 20);
		Rectangle r2 = new Rectangle(x, y, width, height);
		if(alignment == 'c') {
			centerString(g, text, font, r2);
		} else if(alignment == 'l') {
			leftString(g, text, font,  r2);
		}

	}
	
}
