import java.awt.Color;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class SingleLinkedList {
	static TextAttributes p1 = new TextAttributes(Color.white,Color.BLUE);
	static TextAttributes p2 = new TextAttributes(Color.white,Color.RED);
	static TextAttributes attrs3 = new TextAttributes(Color.LIGHT_GRAY);
	static TextAttributes zero = new TextAttributes(Color.RED);
	static TextAttributes one = new TextAttributes(Color.WHITE);
	static TextAttributes two = new TextAttributes(Color.YELLOW);
	static TextAttributes three = new TextAttributes(Color.CYAN);
	static TextAttributes four = new TextAttributes(Color.MAGENTA);
	static  Console cn = Enigma.getConsole("Beam Up");
	Node head;
	
	public void add(int data ,int index  )
	{
		
		if (head == null) {
			Node newnode = new Node(data);
			head = newnode;
		}
		else{
			Node newnode = new Node(data);
			
			if (index == 1) {
				newnode.setNext(head);
				head = newnode;
			}
			else{
				Node temp = head;
				for(int i = 1; i < index-1 && temp.getNext() != null; i++)
				{
					temp = temp.getNext();
				}
				newnode.setNext((temp.getNext()));
				temp.setNext(newnode);
				
			}
		}
	}
	
	
	public int size(){
		int count = 0;
			Node temp = head;
			while(temp != null){
				count++;
				temp = temp.getNext();
			}
		return count;
	}
	
	public void display(int row ,Player[] P){
		int counter = 1;
		
			Node temp = head;
			while (temp != null) {
				
				if (counter == P[0].getColumn() && P[0].getRow() == row ) cn.setTextAttributes(p1);
				else if (counter == P[1].getColumn() && P[1].getRow() == row) cn.setTextAttributes(p2);
				else if (temp.getData()==0) cn.setTextAttributes(zero);
				else if (temp.getData()==1) cn.setTextAttributes(one);
				else if (temp.getData()==2) cn.setTextAttributes(two);
				else if (temp.getData()==3) cn.setTextAttributes(three);
				else if (temp.getData()==4) cn.setTextAttributes(four);
				if (temp.getNext() != null) {
					System.out.print("[" + temp.getData() + "]");
					cn.setTextAttributes(attrs3);
					System.out.print(" - ");
				}
				else System.out.print("[" + temp.getData() + "]");	
				
				temp = temp.getNext();
				counter++;
			}
			System.out.println("\n");
		
	}
	
	public void makeZero(int player1, int player2){
		int counter = 1;
		
		
		if ((player1 == 1) || (player2 == 1)){ 
			
			head.setData(0);
			return;
		}
		Node temp = head;
		while (temp != null) {
			
			 if ((counter == player1) || (counter == player2)) {
				
				 temp.setData(0);
				 break;
			}
			temp = temp.getNext();
			counter++;
		}
		
	}
	
	public int search(int column){ 
        int counter = 1;
		Node temp = head;
        int loc = 0; 
		while (temp != null) {
			if (counter == column) { 
				loc = temp.getData();  
				return loc;
			}
			else{
				temp = temp.getNext();
			}
			counter++;
		}
		return 0;
	}
	
	public void remove(int columnToRemove){
		 int counter = 1;
		 
		      if (columnToRemove < 1) {
				head = head.getNext().getNext().getNext();
			}
		      else{
		    	  Node temp = head;
				while (temp != null) {   
					if (counter == columnToRemove) {
						
						temp.setNext(temp.getNext().getNext().getNext().getNext());  
						return;
					}
					else
					temp = temp.getNext();
					counter++;
				}
			}
			
		}
	
	
	
}
