
public class Player {

	private String player_name;
	private int Score;
	private int row;
	private int column;
	
	
	public Player(String player_name, int score, int row, int column) {
		this.player_name = player_name;
		Score = score;
		this.row = row;
		this.column = column;
	}
	
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}

	public int CalculateScore(int intToCalculate){
		
		switch(intToCalculate){
		case 0: return 0;
		case 1: return 1;
		case 2: return 2;
		case 3: return 4;
		case 4: return 8;
		}
		return intToCalculate; 
	}
	
	
	
	
	
	
	
	
}
