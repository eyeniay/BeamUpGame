import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class BeamUp {
	public KeyListener klis; 
	
	static  Console cn = Enigma.getConsole("Beam Up");
	
	public int keypr;   // key pressed
	public int rkey; 
	SingleLinkedList[] S = new SingleLinkedList[4];
	DoubleLinkedList[] D = new DoubleLinkedList[4];
	Player[] P = new Player[2];
	MultiLinkedList M = new MultiLinkedList(P);
	static TextAttributes p1 = new TextAttributes(Color.white,Color.BLUE);  //  coloration
	static TextAttributes p2 = new TextAttributes(Color.white,Color.RED);
	static TextAttributes attrs1 = new TextAttributes(Color.ORANGE,Color.ORANGE);     
	static TextAttributes attrs2 = new TextAttributes(Color.DARK_GRAY,Color.DARK_GRAY);
	static TextAttributes attrs4 = new TextAttributes(Color.LIGHT_GRAY);
	static TextAttributes attrs13 = new TextAttributes(Color.LIGHT_GRAY,Color.LIGHT_GRAY);
	static TextAttributes attrs3 = new TextAttributes(Color.WHITE);
	static TextAttributes attrs10 = new TextAttributes(Color.MAGENTA,Color.MAGENTA);
	static TextAttributes attrs5 = new TextAttributes(Color.GREEN);
	static TextAttributes attrs11 = new TextAttributes(Color.GREEN,Color.GREEN);
	static TextAttributes attrs6 = new TextAttributes(Color.YELLOW);
	static TextAttributes attrs12 = new TextAttributes(Color.YELLOW,Color.YELLOW);
	static TextAttributes attrs7 = new TextAttributes(Color.RED,Color.RED);
	static TextAttributes attrs8 = new TextAttributes(Color.gray);
	static TextAttributes attrs9 = new TextAttributes(Color.white);
	static TextAttributes train2 = new TextAttributes(Color.RED);
	static TextAttributes train1 = new TextAttributes(Color.BLUE);
	static TextAttributes cloud = new TextAttributes(Color.cyan);
	static TextAttributes gamescreens = new TextAttributes(Color.BLACK,Color.GREEN);
	static TextAttributes gamescreen = new TextAttributes(Color.GREEN, Color.GREEN);
	static Scanner sc = new Scanner(System.in);
	static int level = 1;
	public static boolean player = true; 
	static int round = 0;
	BeamUp() throws InterruptedException, IOException{

		klis=new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(keypr==0) {
					keypr=1;
					rkey=e.getKeyCode();
				}
			}
			public void keyReleased(KeyEvent e) {}
		};
		cn.getTextWindow().addKeyListener(klis);

		for (int i = 0; i < S.length; i++) {
			S[i] = new SingleLinkedList();
			D[i] = new DoubleLinkedList();
		}
		P[0] = new Player("Player1",0,0,0);
		P[1] = new Player("Player2",0,0,0);
		Screen();

	}

	public void clean(){   
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j <100; j++) {
				System.out.print(" ");
			}
		}
		cn.getTextWindow().setCursorPosition(0, 0);

	}

	public void Numbers(){  // add numbers on list

		Random rnd = new Random();  
		int place =0;
		for (int i = 0; i < 4; i++) { 

			int length = rnd.nextInt(5)+4;  // length between 4 and 8
			if(level == 3) M.addLine(i+1);
			
			for (int j = 0; j < length; j++) { 
				boolean check1 = true, check2 = true, check3 = true; 
				int number = 0;
				if(level ==1 ){
					if(S[i].size()==0) place = 1;
					else place = rnd.nextInt(S[i].size())+1;        // detect location of numbers
				}
				else if (level == 2){
					if(D[i].size() == 0) place = 1;
					else place = rnd.nextInt(D[i].size())+1;
				}
				else {
					if(M.size(i+1)==0) place = 1;
					else place = rnd.nextInt(M.size(i+1))+1;   
				}
				if (level == 1) {
					if (place>=2) {
						check1 = !(S[i].search(place-2)==0 && S[i].search(place-1)==0);
					}
					if (place>1 && place<=S[i].size()) {
						check2 = !(S[i].search(place-1)==0 && S[i].search(place)==0);
					}
					if (place<S[i].size()) {
						check3 = !(S[i].search(place)==0 && S[i].search(place+1)==0);
					}
					if (check1==false || check2==false || check3==false) {
						while (number == 0) {
							number = Probability();
						}
						S[i].add(number, place);
					}
					else {
						S[i].add(Probability(),place);   // add number according to level
					}
				}
				else if (level == 2){
					if (place>=2) {
						check1 = !(D[i].search(place-2)==0 && D[i].search(place-1)==0);
					}
					if (place>1 && place<=D[i].size()) {
						check2 = !(D[i].search(place-1)==0 && D[i].search(place)==0);
					}
					if (place<D[i].size()) {
						check3 = !(D[i].search(place)==0 && D[i].search(place+1)==0);
					}
					if (check1==false || check2==false || check3==false) {
						while (number == 0) {
							number = Probability();
						}
			
						D[i].add(number, place);
					}
					else {
						
						D[i].add(Probability(),place);
					}

				}
				else{
					if (place>=2) {
						check1 = !(M.search(i+1,place-2)==0 && M.search(i+1,place-1)==0);
					}
					if (place>1 && place<=M.size(i+1)) {
						check2 = !(M.search(i+1,place-1)==0 && M.search(i+1,place)==0);
					}
					if (place<M.size(i+1)) {
						check3 = !(M.search(i+1,place)==0 && M.search(i+1,place+1)==0);
					}
					if (check1==false || check2==false || check3==false) {
						while (number == 0) {
							number = Probability();
						}
						
						M.add(i+1,number, place);
					}
					else { 
						
						M.add(i+1, Probability(), place);
					}
				}
				
			}
		}

	}

	public int Probability(){  // probability of numbers
		int number =0;
		Random rnd = new Random();
		number=rnd.nextInt(23)+1;
		if(number <8) {return 0; }
		else if((number <16) && (number >= 8)) {return 1; }
		else if((number <20) && (number >= 16)) {return 2; }
		else if((number <24) && (number >= 22)) {return 3; }
		else  return 4; 
	}

    public void showList() throws InterruptedException{ 
		
		
		int row = 11;    // first location of list on screen
		int column = 19;
		
		cn.setTextAttributes(attrs4);
		cn.getTextWindow().setCursorPosition(16, 22);
		System.out.print("Usable");
		cn.getTextWindow().setCursorPosition(16, 23);
		System.out.print("Direction");
		
		if (level != 3) {
			if(level==1)
			{
			    cn.setTextAttributes(attrs5);
				cn.getTextWindow().setCursorPosition(27, 22);
				System.out.print("   --->");
			}
			
			if(level==2)
			{
				
				cn.setTextAttributes(attrs5);
				cn.getTextWindow().setCursorPosition(27, 22);
				System.out.print("   <--->");
			}
			
			for (int i = 0; i < 4; i++) {   // add list 
				cn.getTextWindow().setCursorPosition(column, row);
				if(level==1) S[i].display(i+1,P);
				else D[i].display(i+1,P);
				row+=2;
			}
		}
		else{
			
			M.display();
			
			cn.setTextAttributes(attrs5);
			cn.getTextWindow().setCursorPosition(27, 22);
			System.out.print("   --->");
			cn.getTextWindow().setCursorPosition(26, 22);
			System.out.print("   |");
			cn.getTextWindow().setCursorPosition(26, 23);
			System.out.print("   |");
			cn.getTextWindow().setCursorPosition(26, 24);
			System.out.print("   v");
			
		}
		
		cn.setTextAttributes(attrs3);
		cn.getTextWindow().setCursorPosition(29, 7);
		System.out.print("---------------------------");
		cn.setTextAttributes(gamescreens);
		if (round<=25) cn.getTextWindow().setCursorPosition(29+round, 7);
		else cn.getTextWindow().setCursorPosition(20, 32);
		System.out.print(round);
		
		cn.setTextAttributes(attrs3); 
		cn.getTextWindow().setCursorPosition(55, 22);    
		if (player) {           // player name and his/her color     
			System.out.print(P[0].getPlayer_name());
			cn.getTextWindow().setCursorPosition(66, 22);
			cn.setTextAttributes(p1); // first user's color
			System.out.print("  ");
		}
		else{
			System.out.print(P[1].getPlayer_name());
			cn.getTextWindow().setCursorPosition(66, 22);
			cn.setTextAttributes(p2);     // second user's color
			System.out.print("  ");
		}
		
		if(P[0].getScore()>0 || P[1].getScore()>0){  // score
		cn.setTextAttributes(attrs3);   
		cn.getTextWindow().setCursorPosition(16, 27);
		System.out.print(P[0].getScore());
		cn.getTextWindow().setCursorPosition(16, 28);
		System.out.print(P[1].getScore());
		}
		
		cn.getTextWindow().setCursorPosition(19, 27);   
		for (int i = 0; i < P[0].getScore(); i=i+2) {  // first user's score color
			cn.setTextAttributes(p1);
			System.out.print(" ");
		}
		cn.getTextWindow().setCursorPosition(19, 28);
		for (int i = 0; i < P[1].getScore(); i=i+2) { // second user's score color
			cn.setTextAttributes(p2);
			System.out.print(" ");
		}
		cn.setTextAttributes(attrs3);
	}

    public void gameScreen() throws InterruptedException{   // game frame
	
	cn.getTextWindow().setCursorPosition(16, 9);   
	cn.setTextAttributes(gamescreens);
	System.out.print("    1     2     3     4     5     6     7     8     ");
	cn.getTextWindow().setCursorPosition(16, 10);  // left frame
	cn.setTextAttributes(gamescreen);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(16, 11);
	cn.setTextAttributes(gamescreens);
	System.out.print("1 ");
	cn.getTextWindow().setCursorPosition(16, 12);
	cn.setTextAttributes(gamescreen);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(16, 13);
	cn.setTextAttributes(gamescreens);
	System.out.print("2 ");
	cn.getTextWindow().setCursorPosition(16, 14);
	cn.setTextAttributes(gamescreen);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(16, 15);
	cn.setTextAttributes(gamescreens);
	System.out.print("3 ");
	cn.getTextWindow().setCursorPosition(16, 16);
	cn.setTextAttributes(gamescreen);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(16, 17);
	cn.setTextAttributes(gamescreens);
	System.out.print("4 ");
	cn.getTextWindow().setCursorPosition(16, 18);
	cn.setTextAttributes(gamescreen);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 10);    // right frame
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 11);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 12);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 13);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 14);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 15);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 16);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 17);
	System.out.print("--");
	cn.getTextWindow().setCursorPosition(66, 18);
	System.out.print("--");
	cn.setTextAttributes(gamescreens);
	cn.getTextWindow().setCursorPosition(16, 19);
	System.out.print("                                                    ");
	cn.setTextAttributes(attrs5);
	cn.getTextWindow().setCursorPosition(20, 3);
	System.out.print( "   DOKUZ EYLUL UNIVERCITY ENTERTAINMENT  ");
	cn.setTextAttributes(attrs5);
	cn.getTextWindow().setCursorPosition(36, 5);
	System.out.print( +level+".LEVEL  ");
	cn.getTextWindow().setCursorPosition(21, 7);
	cn.setTextAttributes(attrs4);
	System.out.print("Round:");
	cn.getTextWindow().setCursorPosition(50, 22);
	System.out.print("Turn:");
	
	
	
}

	public void BeamingUp() throws InterruptedException{   


		cn.getTextWindow().setCursorPosition(45, 24);
		System.out.print("Beam up-");
		cn.getTextWindow().setCursorPosition(54, 24);
		System.out.print("Row:");
		cn.getTextWindow().setCursorPosition(54, 25);
		System.out.print("Column:");

		int row = 24;
		int newcolumn = 99;
		int newrow = 99;

		while (true) {

			if(keypr==1) {    // if keyboard button pressed

				if(rkey==KeyEvent.VK_UP){
					cn.getTextWindow().setCursorPosition(61, row);
					System.out.print("  ");
					if (row == 24)  row=25;
					else row = 24;

					cn.getTextWindow().setCursorPosition(61, row);
					System.out.print("<-");
				}

				if(rkey==KeyEvent.VK_DOWN){
					cn.getTextWindow().setCursorPosition(61, row);
					System.out.print("  ");
					if (row == 25)  row = 24;
					else  row = 25;

					cn.getTextWindow().setCursorPosition(61, row);
					System.out.print("<-");
				}

				if (rkey==KeyEvent.VK_ENTER) {

					Random rnd = new Random();
					cn.getTextWindow().setCursorPosition(61, row);
					System.out.print("  ");
					if (row == 24) {
						while (newrow<1 || newrow>4) {
							cn.getTextWindow().setCursorPosition(61, row);
							newrow = sc.nextInt();
							sc.nextLine();
						}
						do {
							if(level == 1) newcolumn = rnd.nextInt(S[newrow-1].size())+1;
							else if(level == 2) newcolumn = rnd.nextInt(D[newrow-1].size())+1;
							else newcolumn = rnd.nextInt(M.size(newrow))+1;
						}while (intersect(newrow,newcolumn) == false);

						cn.getTextWindow().setCursorPosition(61, 25);
						System.out.print(newcolumn);
					}
					else{
						if (level == 1) {
							while ((S[0].size()<newcolumn && S[1].size()<newcolumn && S[2].size()<newcolumn && S[3].size()<newcolumn) || newcolumn<1 ) {
								cn.getTextWindow().setCursorPosition(61, row);
								newcolumn = sc.nextInt();
								sc.nextLine();
							}
							do {  
								newrow = rnd.nextInt(4)+1;
							} while ((S[newrow-1].size()< newcolumn) || intersect(newrow,newcolumn) == false); // single linked list
							
						}
						else if(level == 2){
							while ((D[0].size()<newcolumn && D[1].size()<newcolumn && D[2].size()<newcolumn && D[3].size()<newcolumn) || newcolumn<1 ) {
								cn.getTextWindow().setCursorPosition(61, row);
								newcolumn = sc.nextInt();
								sc.nextLine();
							}
							do {  
								newrow = rnd.nextInt(4)+1;
							} while ((D[newrow-1].size()< newcolumn) || intersect(newrow,newcolumn) == false); // double linked list    
							
						}
						else{
							while ((M.size(1)<newcolumn && M.size(2)<newcolumn && M.size(3)<newcolumn && M.size(4)<newcolumn) || newcolumn<1 ) {
								cn.getTextWindow().setCursorPosition(61, row);
								newcolumn = sc.nextInt();
								sc.nextLine();
							}
							do {
								newrow = rnd.nextInt(4)+1;
							} while (M.size(newrow)<newcolumn || intersect(newrow,newcolumn) == false );  // multilinked list
						}
						cn.getTextWindow().setCursorPosition(61, 24);
						System.out.print(newrow);

					}

					int turn = 0;
					if (!player) turn = 1;

					int temp = 0;
					P[turn].setRow(newrow);
					P[turn].setColumn(newcolumn);
					if(level==1) temp = P[turn].CalculateScore((S[P[turn].getRow()-1].search(newcolumn)));
					else if(level == 2) temp = P[turn].CalculateScore((D[P[turn].getRow()-1].search(newcolumn)));
					else temp = P[turn].CalculateScore(M.search(P[turn].getRow(),newcolumn));
					P[turn].setScore(P[turn].getScore()+temp);

					if (player) {
						if(level==1) S[newrow-1].makeZero(P[0].getColumn(), 99);
						else if(level== 2) D[newrow-1].makeZero(P[0].getColumn(), 99);
						else M.makeZero(P[0].getColumn(), 99);
					}
					else{
						if(level==1) S[newrow-1].makeZero(99, P[1].getColumn());
						else if(level==2) D[newrow-1].makeZero(99, P[1].getColumn());
						else M.makeZero(99, P[1].getColumn());
					}
					
					if (!player) {
						round++;
					}
					keypr = 0;
					cn.getTextWindow().setCursorPosition(45, 24);
					System.out.print("                ");
					cn.getTextWindow().setCursorPosition(54, 24);
					System.out.print("                ");
					cn.getTextWindow().setCursorPosition(54, 25);
					System.out.print("                ");
					
					Turn();
					return;

				}
				keypr = 0;
			}

			Thread.sleep(20);

		}

	}

	public boolean intersect(int nextrow, int nextcolumn){  // prevent players intersection on same square

		if ((P[0].getRow() == nextrow && P[0].getColumn() == nextcolumn) || (P[1].getRow() == nextrow && P[1].getColumn() == nextcolumn)) 
		{
			return false;
		}
		else  return true;

	}

	public void Game(boolean firsttime) throws InterruptedException, IOException{

		if (firsttime) {
			for (int i = 0; i < 2; i++) {
				
				BeamingUp();
				showList();
			}
			round++;
			firsttime = false;
		}
		int turn = 0;

		while(round<25 || P[0].getScore() == P[1].getScore()) {
			boolean intersect = false;
			showList();
			if(keypr==1) {    // if keyboard button pressed

				if(rkey==KeyEvent.VK_LEFT && level==2) { 
					
					if (player) turn = 0;
					else turn = 1;
					if(turn==1)
					{
						round++;
					}
					if ( ((P[turn].getRow() == P[Math.abs(turn-1)].getRow() && P[Math.abs(turn-1)].getColumn()+1==P[turn].getColumn()))) {
						intersect = true;
					}
					if (P[turn].getColumn()-1>0 && intersect == false ) {

						P[turn].setColumn(P[turn].getColumn()-1);
						int temp = P[turn].CalculateScore((D[P[turn].getRow()-1].search(P[turn].getColumn())));
						P[turn].setScore(P[turn].getScore()+temp);
						if (turn == 0 )  D[P[0].getRow()-1].makeZero(P[0].getColumn(),99);
						else D[P[1].getRow()-1].makeZero(99,P[1].getColumn());
						CheckZeroes();
						Turn();
						
					}
					else{
						BeamingUp();
						CheckZeroes();
						
					}
				}

				if(rkey==KeyEvent.VK_RIGHT){  

					if (player) turn = 0;
					else turn = 1;
					if(turn==1)
					{
						round++;
					}
					if ( ((P[turn].getRow() == P[Math.abs(turn-1)].getRow() && P[Math.abs(turn-1)].getColumn()-1==P[turn].getColumn()))) {
						intersect = true;
					}
					if (level == 1 && P[turn].getColumn()+1<=S[P[turn].getRow()-1].size() && intersect == false ) {

						P[turn].setColumn(P[turn].getColumn()+1);
						int temp = P[turn].CalculateScore((S[P[turn].getRow()-1].search(P[turn].getColumn())));
						P[turn].setScore(P[turn].getScore()+temp);
						if (turn == 0 )  S[P[0].getRow()-1].makeZero(P[0].getColumn(),99);
						else S[P[1].getRow()-1].makeZero(99,P[1].getColumn());
						CheckZeroes();
						Turn();
						

					}
					else if (level == 2 && P[turn].getColumn()+1<=D[P[turn].getRow()-1].size() && intersect == false ) {
						P[turn].setColumn(P[turn].getColumn()+1);
						int temp = P[turn].CalculateScore((D[P[turn].getRow()-1].search(P[turn].getColumn())));
						P[turn].setScore(P[turn].getScore()+temp);
						if (turn == 0 )  D[P[0].getRow()-1].makeZero(P[0].getColumn(),99);
						else D[P[1].getRow()-1].makeZero(99,P[1].getColumn());
						CheckZeroes();
						Turn();
						
					}
					else if (level == 3 && P[turn].getColumn()+1<=M.size(P[turn].getRow()) && intersect == false) {
						P[turn].setColumn(P[turn].getColumn()+1);
						int temp = P[turn].CalculateScore((M.search(P[turn].getRow(),P[turn].getColumn())));
						P[turn].setScore(P[turn].getScore()+temp);
						if (turn == 0 )  M.makeZero(P[0].getColumn(),99);
						else M.makeZero(99,P[1].getColumn());
						CheckZeroes();
						Turn();
						
					}
					else{
						BeamingUp();
						CheckZeroes();
						
					}

				}

				if(rkey==KeyEvent.VK_DOWN && level == 3){   
					
					if (player) turn = 0;
					else turn = 1;
					if(turn==1)
					{
						round++;
					}
					if ( ((P[turn].getColumn() == P[Math.abs(turn-1)].getColumn() && P[Math.abs(turn-1)].getRow()-1==P[turn].getRow()))) {
						intersect = true;
					}
					if (P[turn].getRow()+1<5 && P[turn].getColumn()<=M.size(P[turn].getRow()+1) && intersect == false ) {
						P[turn].setRow(P[turn].getRow()+1);
						int temp = P[turn].CalculateScore((M.search(P[turn].getRow(),P[turn].getColumn())));
						P[turn].setScore(P[turn].getScore()+temp);
						if (turn == 0 )  M.makeZero(P[0].getColumn(),99);
						else M.makeZero(99,P[1].getColumn());
						CheckZeroes();
						Turn();
						
					}
					else{
						BeamingUp();
						CheckZeroes();
						
					}
				}
				if (rkey==KeyEvent.VK_SPACE) {

					if(turn==1)
					{
						round++;
					}
					BeamingUp();
					CheckZeroes();
					

				}
				if (rkey==KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
				
				if (round>=25 && P[0].getScore()!=P[1].getScore() ) {
					if (P[0].getScore()>P[1].getScore()) {
						overGame(0);
					}
					else overGame(1);
				}
				keypr = 0;
			}
			Thread.sleep(20);
		}

	}

	public void CheckZeroes() throws InterruptedException{   

		int counter = 0;
		int length = 0;
		for (int i = 0; i < S.length; i++) {
			counter = 0;
			if (level == 1) length = S[i].size();
			else if (level == 2) length = D[i].size();
			else length = M.size(i+1);

			for (int j = 1; j <= length; j++) {

				if ((level == 1 &&S[i].search(j) == 0)||(level==2 && D[i].search(j) == 0) || (level == 3 && M.search(i+1, j)==0)) {

					if ((((P[0].getRow() == i+1 && P[0].getColumn() == j)) || ((P[1].getRow() == i+1 && P[1].getColumn() == j)))) {
						counter = 0;
					}
					else  counter++;

					if (counter == 3 ) {

						if (level == 1) S[i].remove(j-3);
						else if (level == 2) D[i].Remove(j-3);
						else M.remove(i+1, j-3);
						clean();
						
						if (P[0].getRow() == i+1 && P[0].getColumn()>=j) {
							P[0].setColumn(P[0].getColumn()-3);
						}
						if (P[1].getRow() == i+1 && P[1].getColumn()>=j) {
							P[1].setColumn(P[1].getColumn()-3);
						}
						AddNumber(); 
						gameScreen();
					}
				}
				else counter = 0;

			}


		}


	}

	public void AddNumber(){
		Random rnd = new Random();

		int shortList = 10;
		int temp =5;
		for (int i = 0; i < S.length; i++) {
			if (level == 1 && S[i].size() < shortList) {
				temp = i;
				shortList = S[i].size();
			}
			else if (level == 2 && D[i].size() < shortList) {
				temp = i;
				shortList = D[i].size();
			}
			else if(level == 3 && M.size(i+1)<shortList){                
				temp = i;                                   
				shortList = M.size(i+1);
			}
		}

		for (int i = 0; i < 3; i++) {
			boolean check1 = true, check2 = true, check3 = true; 
			int number = 0;
			int place = 0;
			if(level == 1) place = rnd.nextInt(S[temp].size())+1;
			else if (level == 2)  place = rnd.nextInt(D[temp].size())+1;
			else place = rnd.nextInt(M.size(temp+1))+1;

			if (P[0].getRow() == temp+1 && P[0].getColumn()>=place ) {
				P[0].setColumn(P[0].getColumn()+1);
			}
			if (P[1].getRow() == temp+1 && P[1].getColumn()>=place ) {
				P[1].setColumn(P[1].getColumn()+1);
			}
			if(level == 1){
				if (place>=2) {
					check1 = !(S[temp].search(place-2)==0 && S[temp].search(place-1)==0);
				}
				if (place>1 && place<=S[temp].size()) {
					check2 = !(S[temp].search(place-1)==0 && S[temp].search(place)==0);
				}
				if (place<S[i].size()) {
					check3 = !(S[temp].search(place)==0 && S[temp].search(place+1)==0);
				}
				if (check1==false || check2==false || check3==false) {
					while (number == 0) {
						number = Probability();
					}
					S[temp].add(number, place);
				}
				else {
					S[temp].add(Probability(),place);   
				}
				
			}
			else if(level == 2){

				if (place>=2) {
					check1 = !(D[temp].search(place-2)==0 && D[temp].search(place-1)==0);
				}
				if (place>1 && place<=D[temp].size()) {
					check2 = !(D[temp].search(place-1)==0 && D[temp].search(place)==0);
				}
				if (place<D[temp].size()) {
					check3 = !(D[temp].search(place)==0 && D[temp].search(place+1)==0);
				}
				if (check1==false || check2==false || check3==false) {
					while (number == 0) {
						number = Probability();
					}
					
					D[temp].add(number, place);
				}
				else {
					
					D[temp].add(Probability(),place);
				}
				
			}
			else {
				if (place>=2) {
					check1 = !(M.search(temp+1,place-2)==0 && M.search(temp+1,place-1)==0);
				}
				if (place>1 && place<=M.size(temp+1)) {
					check2 = !(M.search(temp+1,place-1)==0 && M.search(temp+1,place)==0);
				}
				if (place<M.size(temp+1)) {
					check3 = !(M.search(temp+1,place)==0 && M.search(temp+1,place+1)==0);
				}
				if (check1==false || check2==false || check3==false) {
					while (number == 0) {
						number = Probability();
					}
					
					M.add(temp+1,number, place);
				}
				else { 
					
					M.add(temp+1, Probability(), place);
				}
			}
		}
	}

	public void Turn(){  

		if (player) player = false;
		else player = true;

	}

	public void Screen() throws IOException, InterruptedException{  // game's menu


		
		cn.setTextAttributes(attrs6);
		cn.getTextWindow().setCursorPosition(10, 12);
		System.out.println("--------------------"
				+ "--------------------");
		cn.getTextWindow().setCursorPosition(10, 16);
		System.out.println("--------------------"
				+ "--------------------");
	
		cn.setTextAttributes(attrs5);
		cn.getTextWindow().setCursorPosition(14, 14);
		System.out.println("PLAY GAME");
		cn.getTextWindow().setCursorPosition(14, 18);
		System.out.println("OPTIONS");
		cn.getTextWindow().setCursorPosition(14, 22);
		System.out.println("HELP");

		Key();
	}

	public void Key() throws InterruptedException, IOException{  // keyboard controls of menu

		int keyrow=8,keycolumn=10;
		String choice = "PLAY GAME";
		String last = " ";
		int lastrow = 0;
		while(true) {
			if(keypr==1) {    // if keyboard button pressed
				if(rkey==KeyEvent.VK_UP && keyrow>12){ // to up on menu
					cn.setTextAttributes(attrs5);

					if (!last.equalsIgnoreCase(" ")) {
						cn.getTextWindow().setCursorPosition(48, lastrow);
						System.out.print("                      ");
						cn.getTextWindow().setCursorPosition(14, lastrow);
						System.out.print(last);
					}
					cn.setTextAttributes(attrs6);
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow);
					System.out.println("                             "
							+ "                           ");
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow+4);
					System.out.println("                           "
							+ "                          ");
					keyrow-=4;
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow);
					System.out.println("--------------------"
							+ "--------------------");
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow+4);
					System.out.println("--------------------"
							+ "--------------------");
					cn.setTextAttributes(attrs5);
					if (keyrow+2 == 14) choice = "PLAY GAME";
					else choice = "OPTIONS";	
					int j = 10;
					do {   // clear other symbol with loop
						cn.getTextWindow().setCursorPosition(j, keyrow+2);
						for (int i = 0; i < choice.length(); i++) System.out.print(" ");
						cn.getTextWindow().setCursorPosition(j+choice.length(), keyrow+2);
						System.out.print(choice);
						j+=choice.length();
						Thread.sleep(40);

					} while (j<55);
					last = choice;
					lastrow = keyrow+2;
				}

				if(rkey==KeyEvent.VK_DOWN && keyrow<20){ // to down on menu
					cn.setTextAttributes(attrs5);
					if (!last.equalsIgnoreCase(" ")) {
						cn.getTextWindow().setCursorPosition(48, lastrow);
						System.out.print("                   ");
						cn.getTextWindow().setCursorPosition(14, lastrow);
						System.out.print(last);
					}
					cn.setTextAttributes(attrs6);
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow);
					System.out.println("                           "
							+ "                            ");
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow+4);
					System.out.println("                           "
							+ "                      ");
					keyrow+=4;
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow);
					System.out.println("--------------------"
							+ "--------------------");
					cn.getTextWindow().setCursorPosition(keycolumn, keyrow+4);
					System.out.println("--------------------"
							+ "--------------------");
					cn.setTextAttributes(attrs5);
					int j = 10;
					if (keyrow+2 == 14) choice = "PLAY GAME";
					else if (keyrow+2 == 18) choice = "OPTIONS";
					else choice = "HELP";
					do { // clear other symbol with loop
						cn.getTextWindow().setCursorPosition(j, keyrow+2);
						for (int i = 0; i < choice.length(); i++) System.out.print(" ");
						cn.getTextWindow().setCursorPosition(j+choice.length(), keyrow+2);
						System.out.print(choice);
						j+=choice.length();
						Thread.sleep(40);

					} while (j<55);
					last = choice;
					lastrow = keyrow+2;
				}

				if (rkey==KeyEvent.VK_ENTER) { // if press enter button

					if ((keyrow+2 == 14) || (keyrow+2 == 10)) { //play game
						keypr = 0;
						clean();
						Numbers();
						showList();
						gameScreen();
						Game(true);
					}
					else if (keyrow+2 == 18) { // options
						keypr = 0;
						Options();
						clean();
						Screen();
					}
					else{    // help
						clean();
						keypr = 0;
						
						File file = new File("MenuHelp.txt");  // text to help menu
						BufferedReader reader = null;
						reader = new BufferedReader(new FileReader(file));
						String satir = reader.readLine();
						int roww = 2;
						while (satir!=null) {

							cn.setTextAttributes(attrs3);
							cn.getTextWindow().setCursorPosition(5, roww);
							System.out.println(satir);
							satir = reader.readLine();
							roww++;
						}	
						reader.close();
						
					
						cn.setTextAttributes(attrs6);
						System.out.println();
						System.out.println("                 You can press Escape for EXIT");
						System.out.println("               Press Backspace for return to menu");
						while(true){
							if(keypr == 1){  // back main menu from help menu
								if (rkey==KeyEvent.VK_BACK_SPACE) {
									System.out.println("giriyom");
									keypr = 0;
									clean();
									Screen();
								}
								if (rkey==KeyEvent.VK_ESCAPE) {
									System.exit(0);
								}
								keypr = 0;
							}
							Thread.sleep(20);
						}
					}
				}

				keypr=0;    // last action  
			}
			Thread.sleep(20);
		}

	}

	public void Options() throws InterruptedException{    // options menu

		clean();
		cn.getTextWindow().setCursorPosition(25, 15);
		System.out.println(" Level < 1 >");
		int i = level ;
		Scanner sc = new Scanner(System.in);  // user choose difficulties of game
		while(true) {

			if(keypr==1) {    // if keyboard button pressed
				if(rkey==KeyEvent.VK_LEFT){
					if (i == 1) {
						i = 3;
					}
					else i--;
					level = i;
					cn.getTextWindow().setCursorPosition(34, 15);
					System.out.print(i);
				}
				if(rkey==KeyEvent.VK_RIGHT){
					if (i == 3) {
						i = 1;
					}
					else i++;
					level = i;
					cn.getTextWindow().setCursorPosition(34, 15);
					System.out.print(i);
				}
				if (rkey==KeyEvent.VK_ENTER) {  // user write players name

					cn.getTextWindow().setCursorPosition(22, 17);
					System.out.print("Player1 Name = ");
					cn.setTextAttributes(attrs6);
					String player1 = sc.nextLine();
					if (!player1.equals("")) {
						P[0].setPlayer_name(player1);
					}
					cn.setTextAttributes(attrs5);
					cn.getTextWindow().setCursorPosition(22, 19);
					System.out.print("Player2 Name = ");
					cn.setTextAttributes(attrs6);
					String player2 = sc.nextLine();
					if (!player2.equals("")) {
						P[1].setPlayer_name(player2);
					}
					keypr = 0;
					sc.close();
					return;
				}
				
				if (rkey==KeyEvent.VK_ESCAPE) { 
					System.exit(0);
				}

				keypr=0;    // last action  
			}
			Thread.sleep(20);
		}
}

	public void overGame(int winner) throws IOException, InterruptedException{ // end of the game
		clean();
		cn.setTextAttributes(cloud);
		cn.getTextWindow().setCursorPosition(35, 5);
		System.out.println("                                          _");
		cn.getTextWindow().setCursorPosition(35, 6);
		System.out.println("                                         (  )");
		cn.getTextWindow().setCursorPosition(35, 7);
		System.out.println("          _ .                         ( `  ) . )");
		cn.getTextWindow().setCursorPosition(35, 8);
		System.out.println("        (  _ )_                      (_, _(  ,_)_)");
		cn.getTextWindow().setCursorPosition(35, 9);
		System.out.println("      (_  _(_ ,)");
		cn.getTextWindow().setCursorPosition(35, 10);
		System.out.println("               _  _    ");
		cn.getTextWindow().setCursorPosition(35, 11);
		System.out.println("              ( `   )_ ");  
		cn.getTextWindow().setCursorPosition(35, 12);
		System.out.println("             (    )    `) "); 
		cn.getTextWindow().setCursorPosition(35, 13);
		System.out.println("           (_   (_ .  _) _) "); 
		cn.setTextAttributes(attrs6);
		cn.getTextWindow().setCursorPosition(82, 12);
		System.out.println("    |"); 
		cn.getTextWindow().setCursorPosition(82, 13);
		System.out.println("  \\ - /");
		cn.getTextWindow().setCursorPosition(82, 14);
		System.out.println("-= (@) =-");
		cn.getTextWindow().setCursorPosition(82, 15);
		System.out.println("  / ~ \\");
		cn.getTextWindow().setCursorPosition(82, 16);
		System.out.println("    |");

		int column = 20;
		while(column<80){

			cn.getTextWindow().setCursorPosition(20, 21);
			System.out.println("                                                                                             ");
			cn.getTextWindow().setCursorPosition(20, 22);
			System.out.println("                                                                                             ");
			cn.getTextWindow().setCursorPosition(20, 23);
			System.out.println("                                                                                             ");
			cn.getTextWindow().setCursorPosition(20, 24);
			System.out.println("                                                                                             ");
			cn.getTextWindow().setCursorPosition(20, 25);
			System.out.println("                                                                                             ");
			cn.getTextWindow().setCursorPosition(20, 26);
			System.out.println("                                                                                             ");

			if (winner == 0) cn.setTextAttributes(train1);
			else cn.setTextAttributes(train2);	

			cn.getTextWindow().setCursorPosition(column, 21);
			System.out.print("   .---- -  -");
			cn.getTextWindow().setCursorPosition(column, 22);
			System.out.print("  (   ,----- - -");
			cn.getTextWindow().setCursorPosition(column, 23);
			System.out.print("   \\_/      ___");
			cn.getTextWindow().setCursorPosition(column, 24);
			System.out.print(" c--U---^--'o  [_");
			cn.getTextWindow().setCursorPosition(column, 25);
			System.out.print(" |------------'_|");
			cn.getTextWindow().setCursorPosition(column, 26);
			System.out.print("/_(o)(o)--(o)(o)");
			cn.getTextWindow().setCursorPosition(20, 27);
			cn.setTextAttributes(attrs3);
			System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			column++;
			Thread.sleep(50);
		}

		cn.setTextAttributes(attrs9);
		cn.getTextWindow().setCursorPosition(33, 15);
		System.out.print("CONGRATULATIONS");
		cn.getTextWindow().setCursorPosition(33, 17);
		if (winner == 0) System.out.print(P[0].getPlayer_name().toUpperCase()+ " WON");
		else System.out.print(P[1].getPlayer_name().toUpperCase()+ " WON");
		

		int timer =10; //  Timer 
		cn.getTextWindow().setCursorPosition(28, 19);
		System.out.println("Game will close after   seconds");
		while (timer>0) {
			cn.setTextAttributes(attrs6);
			cn.getTextWindow().setCursorPosition(50, 19);
			Thread.sleep(1000);
			timer -= 1;
			System.out.print(timer);
		}
		System.exit(0);

	}


}



