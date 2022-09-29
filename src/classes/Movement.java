package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Movement {

	private Board board;
	private HashMap<Integer, ArrayList<Cell>> tracks = new HashMap<Integer, ArrayList<Cell>>();
	
	public Movement(Board board) {
		this.board=board;
	}
	
	/*
	x i y és la inicial
	La k és el color (te rank de 1 a 4 i per les posicions finals és el seu valor en negatiu -1 a -4. 
	
	*/
	
	public void trobarMoviment(int x, int y, int k, Cell cell) { 

		if(board.esLliure(x,y)) {
			board.omple(x, y, k);
			Cell c = new Cell(x,y,k,cell);
			trobarMoviment(x-1,y,k,c);
			trobarMoviment(x+1,y,k,c);
			trobarMoviment(x,y-1,k,c);
			trobarMoviment(x,y+1,k,c);
			board.buidar(x,y);
		}
		else if(board.isFinal(x,y,cell)) {
			afegirFinal(new Cell(x,y,k,cell));
		}
	}
	
	public void afegirFinal(Cell cell) {
		if(tracks.containsKey(cell.getColor())) tracks.get(cell.getColor()).add(cell);
		else{
			ArrayList<Cell> colorTrack = new ArrayList<Cell>();
			colorTrack.add(cell);
			tracks.put(cell.getColor(),colorTrack);
		}
	}
	public ArrayList<Cell> getColorTracks(int color) {
		return tracks.get(color);
	}
}
	