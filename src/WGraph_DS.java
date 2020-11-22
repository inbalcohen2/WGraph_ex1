package ex1.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class WGraph_DS implements weighted_graph,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int MC;
	private int edgeSize;
	private HashMap <Integer,node_info> nodes_TheGragh_W;

	public class NodeInfo implements node_info ,Serializable, Comparable<node_info>{

		private static final long serialVersionUID = 1L;
		private int key;
		private String info;
		private double tag;
		private HashMap<Integer, edges> nodes_TheGragh_W;
		/**
		 * constructor of NodeInfo
		 */
		public NodeInfo(int key)  {
			this.key=key;
			info="";
			tag=Double.POSITIVE_INFINITY;
			nodes_TheGragh_W=new  HashMap<Integer, edges>();
		}
		/**
		 * A copy constructor of this NodeInfo
		 * @param other
		 */
		public NodeInfo(node_info other) {
			this.key=other.getKey();
			this.info=other.getInfo();
			this.tag = other.getTag();

			//Creates a t container of nodes into which the copied nodes will insert
			HashMap<Integer,edges> GW = new HashMap<Integer,edges>();
			for(edges i: ((NodeInfo) other).getEdges().values()) {
				GW.put(i.getDestination().getKey(), i);
			}
			this.nodes_TheGragh_W=GW;
		}
		/**
		 * Return the key (id) associated with this node.
		 * @return
		 */
		@Override
		public int getKey() {
			return this.key;
		}
		/**
		 * return the remark (meta data) associated with this node.
		 * @return
		 */
		@Override
		public String getInfo() {
			return info;
		}
		/**
		 * Allows changing the remark (meta data) associated with this node.
		 * @param s
		 */
		@Override
		public void setInfo(String s) {
			info=s;
		}
		/**
		 * Temporal data (aka distance, color, or state)
		 * which can be used be algorithms
		 * @return
		 */
		@Override
		public double getTag() {
			return tag;
		}
		/**
		 * Allow setting the "tag" value for temporal marking an node - common
		 * practice for marking by algorithms.
		 * @param t - the new value of the tag
		 */
		@Override
		public void setTag(double t) {
			tag = t;

		}  
		// This container holds the neighbors of this node( with the unique key) and the 
		//edges that holds the weights and destination

		public  HashMap<Integer, edges> getEdges(){
			return this.nodes_TheGragh_W;
		}

		// a CompareTo function for the PriorityQueue
		@Override
		public int compareTo(node_info arg0) {
			int ans = 0;
			if (this.tag - arg0.getTag() > 0) ans = 1;
			else if (this.tag -  arg0.getTag() < 0) ans = -1;
			return ans;
		}
		private WGraph_DS getEnclosingInstance() {
			return WGraph_DS.this;
		}
	}

	/**
	 * An empty constructor
	 */
	public  WGraph_DS() {
		nodes_TheGragh_W = new HashMap<Integer,node_info>();
		MC=0;
		edgeSize=0;
	}
	/**
	 * A copy constructor of this Graph_DS 
	 * @param otherG
	 */
	public WGraph_DS(weighted_graph otherG) {
		this.MC=otherG.getMC();
		this.edgeSize=otherG.edgeSize();
		//Creates a t container of nodes into which the copied nodes will insert

		nodes_TheGragh_W = new HashMap<Integer,node_info>();
		HashMap<Integer,node_info> t = new HashMap<Integer,node_info>();
		for(node_info i: otherG.getV()) {
			t.put(i.getKey(), new NodeInfo(i));
		}
		this.nodes_TheGragh_W=t;
	}
	/**
	 * This function return the node_info by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id
	 */
	@Override
	public node_info getNode(int key) {
		return nodes_TheGragh_W.get(key);
	}
	/**
	 * This function returns true if there is a edge between 2 nodes that she get 
	 * @param node1 
	 * @param node2
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {

		if(nodes_TheGragh_W.containsKey(node1) &&nodes_TheGragh_W.containsKey(node2)) {

			return ((NodeInfo) getNode(node1)).getEdges().containsKey(node2);

		}
		return false;
	}
	/**this function return the weight between 2 nodes that she get (node1, node1)
	 * if there is a edge between them, if not return -1
	 * @param node1
	 * @param node2
	 * @return
	 */    
	@Override
	public double getEdge(int node1, int node2) {
		if(hasEdge(node1,node2)) {
			return ((NodeInfo) this.getNode(node1)).getEdges().get(node2).getWeight();
		}
		return -1;
	}
	/**
	 * This function add a new node to the graph with the given node_data.
	 * @param a
	 */
	@Override
	public void addNode(int key) {
		if(!nodes_TheGragh_W.containsKey(key)) {
			MC++;
			node_info new_node = new NodeInfo (key) ;
			nodes_TheGragh_W.put(key,new_node);
		}
	}
	/**
	 * This function add an edge into the graph- between two nodes that she get ,with an edge with weight >=0.
	 * if the edge node1-node2 already exists, the method simply updates the weight of the edge
	 * @param node1 
	 * @param node2
	 * @param w
	 */

	@Override
	public void connect(int node1, int node2, double w) {
		if (getNode(node1)==null ||getNode(node2)==null) return;

		if(w<0) {
			throw new IllegalArgumentException("Error: weight should be bigger or equal than 0");
		}
		if (hasEdge(node1,node2)) {
			((NodeInfo) getNode(node1)).getEdges().get(node2).setWeight(w);
			((NodeInfo) getNode(node2)).getEdges().get(node1).setWeight(w);
			MC++;
		}
		if(nodes_TheGragh_W.containsKey(node1)&&nodes_TheGragh_W.containsKey(node2)
				&&(node1!=node2) &&!(hasEdge(node1,node2))
				&&nodes_TheGragh_W.containsKey(node1) &&nodes_TheGragh_W.containsKey(node2)) {
			MC++;
			edgeSize++;
			((NodeInfo) getNode(node1)).getEdges().put(node2, new edges(w,getNode(node2)));
			((NodeInfo) getNode(node2)).getEdges().put(node1, new edges(w,getNode(node1)));
		}
	}

	/**
	 * This method return a pointer for the collection representing all the nodes in the graph.
	 * @return Collection<node_info>  
	 */
	@Override
	public Collection<node_info> getV() {
		return  nodes_TheGragh_W.values();

	}
	/**This method returns a Collection containing all the nodes connected to node_id
	 * @return Collection<<node_Info>
	 */
	@Override
	public Collection<node_info> getV(int node_id) {
		Collection<node_info> ans = new ArrayList<node_info>();
		if (getNode(node_id)==null) return null;
		for(edges i:((NodeInfo) getNode(node_id)).getEdges().values()) {
			ans.add(i.getDestination());
		}

		return ans;
	}
	/**
	 * This function delete the node (with the given ID) from the graph
	 * and removes all the edges which starts or ends at this node.
	 * @param key
	 * @return the data of the removed node 
	 */
	@Override
	public node_info removeNode(int key) {

		//check if the node contained in the graph
		if(this.nodes_TheGragh_W.containsKey(key)) {
			Iterator<node_info> iter = getV(key).iterator();
			while(iter.hasNext()) {
				((NodeInfo) iter.next()).getEdges().remove(key);
				edgeSize--;
				MC++;
			}
			MC++;
		}

		return nodes_TheGragh_W.remove(key);
	}
	/**
	 * This function delete the edge(if the edge exists) between two nodes that she get
	 * @param node1
	 * @param node2
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		if(hasEdge(node1,node2)){
			MC++;
			edgeSize--;
			((NodeInfo) getNode(node1)).getEdges().remove(node2);
			((NodeInfo) getNode(node2)).getEdges().remove(node1);
		}
	}		
	/** This function return the number of vertices (nodes) in the graph.
	 */

	@Override
	public int nodeSize() {
		return nodes_TheGragh_W.size();
	}
	/** This function return the number of edges in the graph.
	 */
	@Override
	public int edgeSize() {
		return edgeSize;
	}
	/** 
	 * This function return the amount of changes made to the graph (Made Count)
	 */
	@Override
	public int getMC() {
		return MC;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) 
			return false;

		WGraph_DS that = (WGraph_DS) obj;

		if (MC != that.MC) return false;
		if (edgeSize != that.edgeSize) return false;

		return this.nodes_TheGragh_W.size()==((weighted_graph)obj).nodeSize()
				&&this.edgeSize==((weighted_graph)obj).edgeSize();


	}
}
