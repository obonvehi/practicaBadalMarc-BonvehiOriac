package classes;

public class Cell{

	private int color,x,y;
	private Cell preCell;
	
	public Cell (int x, int y,int color, Cell cell) {
		
		this.x=x;
		this.y=y;
		this.color = color;
		this.preCell = cell;
	}
	public int getColor() {return color;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public Cell getPreCell() {return preCell;}
	public String toString() {return (" ("+x+","+y+") ");
	}
	
	public static String getColor(int color) {
		
		switch(color) {
		case 1: return "Blau";
		case 2: return "Verd";
		case 3: return "Vermell";
		case 4: return "Groc";
		//...
		default: return "Color fora de rang";
		}
	}
}
