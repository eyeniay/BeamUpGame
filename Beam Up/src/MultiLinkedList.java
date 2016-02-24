import java.awt.Color;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;


public class MultiLinkedList {
	static TextAttributes p1 = new TextAttributes(Color.white,Color.BLUE);
	static TextAttributes p2 = new TextAttributes(Color.white,Color.RED);
	static TextAttributes attrs3 = new TextAttributes(Color.WHITE);
	static TextAttributes zero = new TextAttributes(Color.RED);
	static TextAttributes one = new TextAttributes(Color.WHITE);
	static TextAttributes two = new TextAttributes(Color.YELLOW);
	static TextAttributes three = new TextAttributes(Color.CYAN);
	static TextAttributes four = new TextAttributes(Color.MAGENTA);
	static  Console cn = Enigma.getConsole("Beam Up");
	NodeMll head;
	Player[] P;
	MultiLinkedList(Player[] P){
		
		head = null;
		this.P = P;
		
	}
	
	
	public void addLine(int no){
		NodeMll newnode = new NodeMll(no);
        if (head == null) {
			head = newnode;
		}
        else{
        	NodeMll temp = head;
        	while (temp.getDown() != null) {
				temp = temp.getDown();
			}
        	temp.setDown(newnode);
        }
	}
	
	
	public void add(int row ,int dataToAdd, int place){
	
		NodeMll temp = head;
		while (temp != null) {
			
			if (row == temp.getRow()) {
				Node temp2 = temp.getRight();
				Node newnode = new Node(dataToAdd);
				if (temp2 == null) {
					temp.setRight(newnode);
				}
				else{
					if (place == 1) {
						newnode.setNext(temp.getRight());
						temp.setRight(newnode);
					}
					else{
						for (int i = 1; i < place-1 && temp2.getNext() != null; i++) {
							temp2 = temp2.getNext();
						}
						newnode.setNext(temp2.getNext());
						temp2.setNext(newnode);
					}
				}
			}
			temp = temp.getDown();
		}
		
	}
		
	
	public void display(){
			int counter = 1;
			NodeMll temp = head;
			int row = 11;
			while (temp != null) {
				Node temp2 = temp.getRight();
				cn.getTextWindow().setCursorPosition(19, row);
				while (temp2!=null) {
					if (temp.getRow() == P[0].getRow() && counter == P[0].getColumn()) cn.setTextAttributes(p1);
					else if (temp.getRow() == P[1].getRow() && counter == P[1].getColumn()) cn.setTextAttributes(p2);
					else if (temp2.getData()==0) cn.setTextAttributes(zero);
					else if (temp2.getData()==1) cn.setTextAttributes(one);
					else if (temp2.getData()==2) cn.setTextAttributes(two);
					else if (temp2.getData()==3) cn.setTextAttributes(three);
					else if (temp2.getData()==4) cn.setTextAttributes(four);
					if (temp2.getNext() != null) {
						System.out.print("[" + temp2.getData() + "]");
						cn.setTextAttributes(attrs3);
						System.out.print(" - ");
					}
					else System.out.print("[" + temp2.getData() + "]");	
					counter++;
					temp2 = temp2.getNext();
					}
				counter = 1;
				temp = temp.getDown();
				System.out.println();
				row +=2;
			}
			
		}
		
		public void makeZero(int player1, int player2){  
			int counter = 1;
			
			NodeMll temp = head;
			while (temp != null) {
				if ((player1 == 1 && P[0].getRow() == temp.getRow())||(player2 == 1 && P[1].getRow() == temp.getRow())) {
					
					temp.getRight().setData(0);
				return;
				}
				else{
					Node temp2 = temp.getRight();
					
				
				   while (temp2!= null) {    
					    
					   if (((counter == player1) && temp.getRow() == P[0].getRow() )|| ((counter == player2) && temp.getRow() == P[1].getRow())) {
						
						   temp2.setData(0);
					}
					   temp2 = temp2.getNext();
					   counter++;
				   }
				}
				counter = 1;
				temp = temp.getDown();
			}
			
		}
		public int search(int row, int column){
			int counter = 1;
			NodeMll temp = head;
			int loc = 0;
			while (temp != null) {
				if (temp.getRow() == row) {
					Node temp2 = temp.getRight();
					while (temp2 != null) {
						if (counter == column) {
							loc = temp2.getData();
							return loc;
						}
						else temp2 = temp2.getNext();
						counter++;
					}
				}
				temp = temp.getDown();
			}
			return 0;
			
		}
		
	public int size(int row){
		NodeMll temp = head;
		int counter = 0;
		while (temp!= null) {
			if (temp.getRow() == row) {
				Node temp2 = temp.getRight();
				while (temp2!= null) {
					temp2 = temp2.getNext();
					counter++;
				}
			}
			temp = temp.getDown();
			
		}
		return counter;
	}
	
	public void remove(int row, int columnToRemove){  
		
		int counter = 1;
		NodeMll temp = head;
		while (temp!= null) {
			
			if (temp.getRow() == row) {
				Node temp2 = temp.getRight();
				if (columnToRemove<1) {
					temp.setRight(temp.getRight().getNext().getNext().getNext());     
				}
				else{
					while (temp2 != null) {
						if (counter == columnToRemove) {
							  
							temp2.setNext(temp2.getNext().getNext().getNext().getNext());
							return;
						}
						else temp2 = temp2.getNext();
						counter++;
					}
				}
			}
			counter = 1;
			temp = temp.getDown();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
