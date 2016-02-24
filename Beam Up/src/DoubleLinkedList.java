import java.awt.Color;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;


public class DoubleLinkedList {
	static TextAttributes p1 = new TextAttributes(Color.white,Color.BLUE);
	static TextAttributes p2 = new TextAttributes(Color.white,Color.RED);
	static TextAttributes attrs3 = new TextAttributes(Color.WHITE);
	static TextAttributes zero = new TextAttributes(Color.RED);
	static TextAttributes one = new TextAttributes(Color.WHITE);
	static TextAttributes two = new TextAttributes(Color.YELLOW);
	static TextAttributes three = new TextAttributes(Color.CYAN);
	static TextAttributes four = new TextAttributes(Color.MAGENTA);
	static  Console cn = Enigma.getConsole("Beam Up");
	NodeDll head;
	NodeDll tail;
	
	public DoubleLinkedList(){
		head = null;
		tail = null;		
	}
	
	public void add(int dataToAdd , int place){
		
        if (head == null && tail == null) {
			
			NodeDll newnode = new NodeDll(dataToAdd);
			head = newnode;
			tail = newnode;
			
		}
        else{
        	boolean added = false;
        	NodeDll newnode = new NodeDll(dataToAdd);
        	
        	if (place == 1) {
        		newnode.setNext(head);
        		head.setPrev(newnode);
        		head = newnode;
			}
        	else{
        		NodeDll temp = head;
        	for (int i = 1; i < place-1 ; i++) {
				
				if (temp.getNext() == null) {
					newnode.setPrev(temp);
					temp.setNext(newnode);
					added = true;
					break;
				}
				temp = temp.getNext();
			}
        	if ( added == false) {
        		newnode.setPrev(temp);
            	newnode.setNext(temp.getNext());
            	temp.setNext(newnode);
			}
        	
        	}
        }
        
		
	}
	
	public void makeZero(int player1, int player2){
        int counter = 1;
		
		
		if ((player1 == 1) || (player2 == 1)){ 
			head.setData(0);
			return;
		}
		NodeDll temp = head;
		while (temp != null) {
			if ((counter == player1) || (counter == player2)) { 
				
				temp.setData(0);
				break;
			}
			temp = temp.getNext();
			counter++;
		}
	}
	
	public void Remove(int columnToRemove){
		int counter = 1;
		
		if (columnToRemove < 1) {
			head = head.getNext().getNext().getNext();;
			head.setPrev(null);
		}
		else{
			NodeDll temp = head;
			while (temp != null) {

				if (counter == columnToRemove) {
					
					temp.setNext(temp.getNext().getNext().getNext().getNext());
					if (temp.getNext() != null) {
						temp.getNext().setPrev(temp);
					}
					return;
				}
				else temp = temp.getNext();
				counter++;
			}
			
		}
	}
	
	
	public int size(){
		
		int count = 0;
			NodeDll temp = head;
			while (temp != null) {
				count++;
				temp = temp.getNext();
			}
		return count ;
	}
	
    public void display(int row, Player[] P){
		
		int count = 1;
			NodeDll temp = head;
			while (temp != null) {
				if (count == P[0].getColumn() && P[0].getRow() == row) cn.setTextAttributes(p1);
				else if (count == P[1].getColumn() && P[1].getRow() == row) cn.setTextAttributes(p2);
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
				count++;
			}
			System.out.println("\n");
		
	}
	
   
    public int search(int column){
		int counter = 1;
		NodeDll temp = head;
		int loc = 0;
			while (temp!= null) {
				if (counter  == column) {
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
	
}
