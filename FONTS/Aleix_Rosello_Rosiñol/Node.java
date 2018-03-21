
public class Node {

	private Node left, right;
	private String value;
	
	Node() {
		
	}
	
	Node(String value) {
		this.value = value;
	}
	
	public Node getLeftNode() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el fill esquerra.</Ul>
		  * 
		*/
		return left;
	}
	
	public Node getRightNode() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el fill dret.</Ul>
		  * 
		*/
		return right;
	}
	
	public boolean isLeaf() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna si el node es una fulla.</Ul>
		  * 
		*/
		return left == null && right == null;
	}
	
	public String getValue() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el valor del node.</Ul>
		  * 
		*/
		return value;
	}
	
	public void setLeftNode(Node left) {
		/** <p><b>Pre:</b></p>  <Ul>Node vàlid.</Ul>
		  * <p><b>Post:</b></p> <Ul>Indica el nou fill esquerra.</Ul>
		  * 
		*/
		this.left = left;
	}
	
	public void setRightNode(Node right) {
		/** <p><b>Pre:</b></p>  <Ul>Node vàlid.</Ul>
		  * <p><b>Post:</b></p> <Ul>Indica el nou fill dret.</Ul>
		  * 
		*/
		this.right = right;
	}
	
}
