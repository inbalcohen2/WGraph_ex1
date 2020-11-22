package ex1.src;

public class edges {
	/**
	 *  This class was created to hold and access
	 *  the weights of the nodes and the weights of the edges which is the distance between 2 nodes
	 */
	
	// distnation is the weight of a edge - the distance between 2 vertices
	private node_info destination;
	
	//the weight of the node
	private double Weight;
	
	
	public edges(double w, node_info d) {
			this.destination=d;
			Weight = w;
		    }
	
	public edges(edges e) {
		this.Weight=e.getWeight();
		destination =e.getDestination();
	}
	
	public node_info getDestination() {
		return this.destination;
	}
	
	public void setDestination(node_info d) {
		this.destination=d;
	}
	
	public double getWeight() {
		return this.Weight;
	}
	
	public void setWeight(double d) {
		this.Weight=d;
	}
}
