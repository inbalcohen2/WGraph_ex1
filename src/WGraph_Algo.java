package ex1.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class WGraph_Algo implements weighted_graph_algorithms,Serializable {
	private weighted_graph _graph;

	//Create a new graph
	WGraph_Algo(){

		_graph=new WGraph_DS();
	}
	/**
	 *  Init this set of algorithms on the parameter - graph.
	 *  @param g
	 */
	@Override
	public void init(weighted_graph g) {
		this._graph=g;
	}
	/**
	 * Return the underlying graph of which this class works.
	 * @return
	 */
	@Override
	public weighted_graph getGraph() {
		return _graph;
	}
	/**
	 * This function sends to the copy constructor and compute a deep copy of this graph
	 *  @return the copied graph.
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph w_g=new WGraph_DS(this._graph);
		return w_g;
	}
	/**
	 * helper function (for is connected), Run through the graph from a given point, and initialize each Node_data's key in its way to false.
	 * And after the first run, the function runs through the neighbors of each node and sets its neighbors's key to true.
	 * At the end of the action, the key of each graph is set to true or false, while the graph is linked only if all of the nodes keys are set to true. 
	 * @param start
	 */
	private	HashMap<Integer, Boolean> Visited_graph = new HashMap<Integer, Boolean>();
	private void BfsForisConnected (node_info start) {
		// Bfs algorithm using LinkedList of Integer type 
		LinkedList<Integer> queue = new LinkedList<Integer>(); 
		//create a container- Visited_graph Which contains the key and whether we visited it or not
		Visited_graph = new HashMap<Integer, Boolean>();
		for(node_info Node : _graph.getV()) {

			//We initialized the container to false
			// so we know we have already visited these nodes
			Visited_graph.put(Node.getKey(), false);
		}
		//Mark the current node as visited (true) and enqueue it 
		Visited_graph.replace(start.getKey(), true);
		queue.add(start.getKey()); 

		while((!queue.isEmpty())){
			int st=queue.remove();
			for (node_info Node : _graph.getV(st)){

				if(!Visited_graph.get(Node.getKey())) {

					Visited_graph.replace(Node.getKey(), true);

					queue.add(Node.getKey()); 

				} 
			}
		}
	}
	/**
	 * This function checks if there is a valid path from EVREY node to each other node ( Checks if the graph is connected)
	 * , by checking if every node's key is set to true.
	 * @return false if at least one of the node's key is set to false.
	 */
	@Override
	public boolean isConnected() {
		if( _graph==null||_graph.nodeSize()==0||_graph.nodeSize()==1) {
			return true;
		}
		/**
		 * Check if there is a node that initializes to false, if so the graph does not connected
		 */
		BfsForisConnected(_graph.getV().iterator().next());
		for(Boolean B_node: Visited_graph.values()) {
			if(B_node == false ) {
				return false;
			}
		}	
		return true;
	}
	// Initialize all the nodes as INFINITE
	private void inittag() {
		for (node_info i :this._graph.getV()) {
			i.setTag(Double.POSITIVE_INFINITY);
		}
	}

	/**
	 * 
	 * Dijkstra's algorithm: An algorithm for finding the shortest paths between nodes in a graph
he gets 2 nodes- src and -dest should get from the src node to the dest node and go through the nodes with the lowest weight.
 The algorithm works as follows:
We will first initialize all the weights of the nodes to infinity so that we know which node we have not yet updated,
 and then we set a priority queue that will contain the nodes we will visit and update their weights.
In addition, we set the Hashmap to initialize all nodes in false.
Each node he visited will be marked as a visit (correct), and that way we will know if we visited this node or not, 
and finally, if there is such a node, then he is not connected to the other node and he will remain marked as false.
.
In the priority queue, we enter the first node and initialize its weight to 0,
 and all the other nodes in the graph are initialized to infinity. 
 For the current junction,we will include all its neighbors and update their temporary weights.
The weight of each node is updated according to the parent weight of that node plus the temporary distance between them which is the weight on the edge.
Then the same node we started with becomes the father of this node and leaves the queue, 
it is marked as one we have already visited and we will not return to it again. Each of the introduced neighbors treats him the same way: putting his neighbors in line and updating their weights.
so if one of the neighbors is already updated with weight because we reached it through another father-node, then we will check through which neighbor that node will have the lowest weight, then we will keep the lower weight. We will take the node out of the queue and return it with the updated weight. To the same junction is also updated the new father through which we reached a junction with a lower weight.
  And so for each node up to the node, we set to reach in the graph.



	 * @param src
	 * @param dest
	 * This function uses the Dijkstra's algorithm to find the shortest path between src and dest 
	 * and the function returns the weight of the dest which should be the lowest weight
	 *@return the length of the shortest path between src to dest
	 */

	@Override
	public double shortestPathDist(int src, int dest) {
		if(_graph.getNode(src)==null||_graph.getNode(dest)==null) 
			return-1;	
		if(src==dest) { 
			return 0.0;
		}
		PriorityQueue<node_info> queue = new PriorityQueue<node_info>();

		//create a container- Visited_graph Which contains the key and whether we visited it or not
		Visited_graph = new HashMap<Integer, Boolean>();
		for(node_info Node : _graph.getV()) {
			//We initialized the container to false
			// so we know we have already visited these nodes
			Visited_graph.put(Node.getKey(), false);
		}
		//create a container-parentNodes Which contains the nodes Which led to reaching the dest node in the shortest path
		//Map<node_info, node_info> parentNodes = new HashMap<node_info, node_info>();

		//Mark the current node as visited (true) and enqueue it 
		Visited_graph.replace(src, true);
		//this is the node that we will start with him- src 
		node_info start = this._graph.getNode(src);
		//add the node to the queue,and initialize its weight to 0.0
		queue.add(start); 
		start.setTag(0.0);

		while((!queue.isEmpty())){
			node_info u=queue.poll();
			//Run through the edges(with weight) of neighbor of this node (u) 
			for (edges e : u.getEdges().values()){
				//if not 
				if(!Visited_graph.get(e.getDestination().getKey())) {
					//dist is the weight of the node consisting of the weight of 
					//its parent node plus the distance between them 
					double dist = u.getTag()+e.getWeight();

					// if dist is smaller than the weight that exists for this node
					if(dist<e.getDestination().getTag()) {

						//replace the old weight with the new-dist
						e.getDestination().setTag(dist);

						//After the weight has changed to this node it should insert its parent node
						//through which it has reached a smaller weight into the queue of parentNodes
						//parentNodes.put(e.getDestination(), u);

						//After the weight of this nod has changed,
						//it will be deleted from the queue and returned with the new weight
						queue.remove(e.getDestination());
						queue.add(e.getDestination());
					}
				} 
			}
			Visited_graph.replace(u.getKey(), true);
		}
		//Checks if the node we wanted to reach is initialized as infinity' 
		//so there is no way to reach it -then return -1
		double d = _graph.getNode(dest).getTag();
		if(d==Double.POSITIVE_INFINITY) {
			inittag();
			return -1;
		}
		inittag();
		return d;
	}

	//A container that holds all the parent of the node we passed through to get to the short route
	//and then returns this container that is the route
	Map<node_info, node_info> parentNodes = new HashMap<node_info, node_info>();

	/**
	 this function uses the Dijkstra's algorithm to finds the shortest path between src and dest 
	 and returns a list of the nodes through which we passed.
	 This list contains the ancestors of the nodes through which we passed.
     The list was made by the hashmap that for each current node puts into the container the node that 
     brought us to the current node which is its parent node

	 @param src - start node
	 * @param dest - end (target) node
	 * @return 
	 * */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		if(_graph.getNode(src)==null||_graph.getNode(dest)==null) 
			return null;
		List<node_info>  no= new ArrayList<node_info>();
		if(src==dest) {
			no.add(_graph.getNode(src));
			return no;
		}
		//create a PriorityQueue
		PriorityQueue<node_info> queue = new PriorityQueue<node_info>();
		//create a container- Visited_graph Which contains the key and whether we visited it or not
		Visited_graph = new HashMap<Integer, Boolean>();
		for(node_info Node : _graph.getV()) {
			//We initialized the container to false
			// so we know we have already visited these nodes
			Visited_graph.put(Node.getKey(), false);
		}
		//create a container-parentNodes Which contains the nodes Which led to reaching the dest node in the shortest path
		Map<node_info, node_info> parentNodes = new HashMap<node_info, node_info>();

		//Mark the current node as visited (true) and enqueue it 
		Visited_graph.replace(src, true);
		//this is the node that we will start with him- src 
		node_info start = this._graph.getNode(src);
		//add the node to the queue,and initialize its weight to 0.0
		queue.add(start); 
		start.setTag(0.0);

		while((!queue.isEmpty())){
			node_info u=queue.poll();
			//Run through the edges(with weight) of neighbor of this node (u) 
			for (edges e : u.getEdges().values()){
				//if not 
				if(!Visited_graph.get(e.getDestination().getKey())) {
					//dist is the weight of the node consisting of the weight of 
					//its parent node plus the distance between them 
					double dist = u.getTag()+e.getWeight();

					// if dist is smaller than the weight that exists for this node
					if(dist<e.getDestination().getTag()) {

						//replace the old weight with the new-dist
						e.getDestination().setTag(dist);

						//After the weight has changed to this node it should insert its parent node
						//through which it has reached a smaller weight into the queue of parentNodes
						parentNodes.put(e.getDestination(), u);

						//After the weight of this nod has changed,
						//it will be deleted from the queue and returned with the new weight
						queue.remove(e.getDestination());
						queue.add(e.getDestination());
					}
				} 
			}
			Visited_graph.replace(u.getKey(), true);
		}
		List<node_info> shortestPath = new ArrayList<>();
		node_info node = this._graph.getNode(dest);
		while(node != null) {
			shortestPath.add(node);
			node = parentNodes.get(node);

		}
		//Checks if the node we wanted to reach is initialized as infinity 
		//so there is no way to reach it -then return null
		double d = _graph.getNode(dest).getTag();
		if(d==Double.POSITIVE_INFINITY) {
			inittag();
			return null;
		}
		Collections.reverse(shortestPath);
		inittag();
		return shortestPath;
	}

	/**
	 * Saves this weighted (undirected) graph to the given
	 * file name
	 * @param file - the file name (may include a relative path).
	 * @return true - iff the file was successfully saved
	 */
	@Override
	public boolean save(String file) {
		//holds the wished name to save by;
		//String file = file_S;
		try
		{    
			FileOutputStream file_S = new FileOutputStream(file); //writing data to file;  
			ObjectOutputStream out = new ObjectOutputStream(file_S);  //serilaze the object to the file
			out.writeObject(this._graph); 
			out.close();  
			file_S.close(); 
			return true;
		}   
		catch(IOException ex) {
			System.out.println("IOException is caughtf");
			return false;
		}
	}
	/**
	 * This method load a graph to this graph algorithm.
	 * if the file was successfully loaded - the underlying graph
	 * of this class will be changed (to the loaded one), in case the
	 * graph was not loaded the original graph should remain "as is".
	 * @param file - file name
	 * @return true - iff the graph was successfully loaded.
	 */
	@Override
	public boolean load(String file) {
		try
		{    
			FileInputStream file_S = new FileInputStream(file); 
			ObjectInputStream in = new ObjectInputStream(file_S);
			this._graph= (weighted_graph)in.readObject(); 
			in.close(); 
			file_S.close();
			System.out.println("Object has been deserialized");
			return true;
		}    

		catch(IOException ex) 
		{     System.out.println("IOException is caught");
		return false;
		} 

		catch(ClassNotFoundException ex) 
		{    System.out.println("ClassNotFoundException is caught");
		return false;
		} 

	}
}
