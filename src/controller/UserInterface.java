package controller;
import java.util.ArrayList;

import Keyboard.Keyboard;
import classes.Board;
import classes.Cell;
import classes.Movement;

public class UserInterface {
	
	private static  Board board;
	private static Movement moviments;
	private static int colorsNumber = 4;
	private static int boardSize = 5;
	private static Cell[] cells;
	
	
	public static void main(String[] args) {
		UserInterface game = new UserInterface();
		
		cells = new Cell[colorsNumber];
		board = new Board(boardSize);
		
		game.fillBoard();
		
		moviments = new Movement(board);
		
		for(int i = 0;i<cells.length;i++) {
			Cell cell = cells[i];
			board.buidar(cell.getX(), cell.getY());
			/*
			 * S'ha d'eliminar la primera posició pel del recorregut per entrar dins el if (esLliure) i despres ja es torna a omplenar. 
			 * 
			 */
			moviments.trobarMoviment(cell.getX(), cell.getY(), cell.getColor(), null);
			board.omple(cell.getX(), cell.getY(),cell.getColor());
		}
		
		board.printBoard();		
		
		for(int color=1;color<=colorsNumber;color++) {
			ArrayList<Cell> colorTracks = moviments.getColorTracks(color);
			System.out.println("\n\nSolucions del color " +Cell.getColor(colorTracks.get(0).getColor()));
			
			if (!colorTracks.isEmpty()) {
				for(Cell cell: colorTracks) {
					System.out.println("");
					while(cell!=null) {
						System.out.print(cell.toString());
						cell=cell.getPreCell();
					}
				} 
				
			} else	System.out.println("No hi ha solució.");
		}//end for
		game.voracious();
	}	
	
	private void fillBoard() {
		
		System.out.println("PUZZLE de CANONADES");
		System.out.println("*******************\n");
		
		for(int i=1;i<=colorsNumber;i++) {

			System.out.println ("Entra la ubicació de la primera parella de punts");
			System.out.println ("************************************************");
			System.out.println ("Entra la ubicació del primer punt de color " +Cell.getColor(i)+"\n");
			insertCells(i,false);
			System.out.println ("Entra la ubicació del segon punt de color " +Cell.getColor(i)+"\n");
			insertCells(i,true);
		}
	}
	private void insertCells(int color, boolean end){

		int x=0,y=0;
		try {
		System.out.println("Entra la ubicació X [0," + (boardSize - 1) + "]");
		x = Integer.parseInt(Keyboard.readString());
		System.out.println("Entra la ubicació y [0," + (boardSize - 1) + "]");
		y = Integer.parseInt(Keyboard.readString());
		
		}catch(Exception e) {
			System.out.println("ERROR: Valor incorrecte.");
			insertCells(color, end);
		}
		if (!board.esLliure(x,y)) {
			System.out.println("ERROR: No és una casella valida.");
			insertCells(color, end);
		}
		else {
			if(!end) {
			board.omple(x, y, color);
			cells[color-1] = new Cell(x,y,color,null); 
			
			}
			else {
				Cell cell = cells[color-1];
				if (cell.getX()+cell.getY()!=x+y+1&&cell.getX()+cell.getY()!=x+y-1) {
					board.omple(x, y, -color);
				}
				else {System.out.println("ERROR: No és una casella valida.");
				insertCells(color, end);
				}
			}
		}
	}
	public void  voracious() {
		
		Board finalBoard = new Board(boardSize);
		ArrayList<Cell> blueTracks = moviments.getColorTracks(1);
		ArrayList<Cell> greenTracks = moviments.getColorTracks(2);
		ArrayList<Cell> redTracks = moviments.getColorTracks(3);
		ArrayList<Cell> yellowTracks = moviments.getColorTracks(4);
		
		
		for(Cell blueCell: blueTracks) {
			finalBoard.supColor(blueCell.getColor());
			if (fillBoard(blueCell,finalBoard)) {
				
				for(Cell greenCell: greenTracks) {
					finalBoard.supColor(greenCell.getColor());
					if (fillBoard(greenCell,finalBoard)) {
						
						for(Cell redCell: redTracks) {
							finalBoard.supColor(redCell.getColor());
							if (fillBoard(redCell,finalBoard)) {
								
								for(Cell yellowCell: yellowTracks) {
									finalBoard.supColor(yellowCell.getColor());
									if (fillBoard(yellowCell,finalBoard)) {
										
										if (finalBoard.full()) {
											finalBoard.printBoard();
											return;
										}//if
									}//if
								}//for yellow
							}//if
						}//for red
					}//if 
				}//for green
			}//if
		}//for blue
		System.out.println("No hi ha solució.");
	}
	public boolean fillBoard(Cell cell, Board finalBoard) {
		
		while(cell!=null) {
			
			if (!finalBoard.esLliure(cell.getX(),cell.getY())) return false;
			
			finalBoard.omple(cell.getX(),cell.getY(),cell.getColor());
			cell=cell.getPreCell();
			}
		return true;
	}
}