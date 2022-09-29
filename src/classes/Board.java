package classes;

public class Board {					

	private static final int LLIURE = 0; // esta lliure

	int[][] board;

	public Board(int sizeBoard) {
		
		board = new int[sizeBoard][sizeBoard];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j]=LLIURE;
			}
		}
	}
	public void printBoard() {
		System.out.println("");
		for (int i = 0; i < board.length; i++) {
			System.out.println("");
			for (int j = 0; j < board.length; j++) {
					System.out.print(board[i][j]==LLIURE ?"|B":"|"+board[i][j]);
			}
		}	
	}
	/*Metodes utilitzats per tecnica voraç
	 *************************************
	 * 
	 */
	public boolean full() {
		for(int i = 0; i<board.length;i++) {
			for(int j = 0; j<board.length;j++) {
				if (board[i][j]==LLIURE) return false;
			}
		}
		return true;
	}
	public void supColor(int color) {
		for(int i = 0; i<board.length;i++) {
			for(int j = 0; j<board.length;j++) {
				if (board[i][j]==color) board[i][j]=LLIURE;
			}
		}
	}
	
	/*Metodes utilitzats per moviment
	 ********************************
	 * 
	 */
	public void buidar(int x,int y){
		board[x][y]=LLIURE;
		}
	public boolean esLliure(int x,int y) {
		return (x>=0&&x<5&&y>=0&&y<5&&board [x][y]==LLIURE);
	}
	public boolean isFinal(int x,int y,Cell cell) {
		return (x>=0&&x<5&&y>=0&&y<5&&cell.getColor()==-board[x][y]);
	}
	public void omple (int x, int y, int k) {
		board[x][y]=k;
	}
}
