

public class NodeDll {
	
	int data;
	private NodeDll prev;
	private NodeDll next;
	public NodeDll(int dataToAdd){
		data = dataToAdd;
		next = null;
		prev = null;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public NodeDll getPrev() {
		return prev;
	}
	public void setPrev(NodeDll prev) {
		this.prev = prev;
	}
	public NodeDll getNext() {
		return next;
	}
	public void setNext(NodeDll next) {
		this.next = next;
	}

}
