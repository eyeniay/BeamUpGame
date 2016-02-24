
public class NodeMll {

	private int row;
	private NodeMll down;
	private Node right;
	
	
	public NodeMll(int row){
		
		this.row = row;
		down = null;
		right = null;
		
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public NodeMll getDown() {
		return down;
	}


	public void setDown(NodeMll down) {
		this.down = down;
	}


	public Node getRight() {
		return right;
	}


	public void setRight(Node right) {
		this.right = right;
	}
	
	
	
	
}
