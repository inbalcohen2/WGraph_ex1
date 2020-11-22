package ex1.tests;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

class WGraph_DS_Test {


	@Test
	void test_add_Node() {
		weighted_graph g= new WGraph_DS();
		// add nodes to the graph
		g.addNode(0); g.addNode(1);
		g.addNode(2); g.addNode(3);
		g.addNode(4); g.addNode(5);
		// check if the nodes that creates exist in the graph
		assertNotNull(g.getNode(0));
		assertNotNull(g.getNode(1));
		assertNotNull(g.getNode(2));
		assertNotNull(g.getNode(3));
		assertNotNull(g.getNode(4));
		assertEquals(6, g.nodeSize());
		//add node that is in the graph		
		g.addNode(5);
		assertEquals(6, g.nodeSize());
		g.removeNode(5);
		assertEquals(7, g.getMC());
		//node that is not in the graph		
		assertNull(g.getNode(6));


	}

	@Test
	void test_hasEdge() {
		weighted_graph g= new WGraph_DS();
		g.addNode(0); g.addNode(1);
		g.addNode(2); g.addNode(3);
		g.addNode(4); g.addNode(5);

		g.connect(0, 1, 1); 
		g.connect(0, 4, 2);
		g.connect(1, 2, 1);
		g.connect(1, 4, 5);
		g.connect(1, 5, 6);
		g.connect(2, 3, 4);
		g.connect(3, 5, 3);

		assertEquals(6 ,g.nodeSize());
		assertEquals(7, g.edgeSize());
		assertEquals(13, g.getMC());
		//remove edges that exists in the graph	
		g.removeEdge(1, 5);
		g.removeEdge(0, 4);
		//remove edge that does not exists in the graph		
		g.removeEdge(6,7);
		assertEquals(15, g.getMC());
		//remove edges that exists in the graph			
		assertTrue(g.hasEdge(0, 1));
		//remove edge that does not exists in the graph	
		assertFalse(g.hasEdge(3, 6));	
		assertFalse(g.hasEdge(1, 5));
		assertFalse(g.hasEdge(0, 4));
		assertEquals(15, g.getMC());
	}


	@Test
	void test_connect() {
		weighted_graph g= new WGraph_DS();
		g.addNode(0); g.addNode(1);
		g.addNode(2); g.addNode(3);
		g.addNode(4); g.addNode(5);

		g.connect(0, 1, 1); 
		g.connect(0, 4, 2);
		g.connect(1, 2, 1);
		g.connect(1, 4, 5);
		g.connect(1, 5, 6);
		g.connect(2, 3, 4);
		g.connect(3, 5, 3);
		assertEquals(7 ,g.edgeSize());
		// two node that does not exist in the graph
		g.connect(6, 7, 5);
		//the node size/edge/MC does not changes
		assertEquals(7 ,g.edgeSize());
		assertEquals(6 ,g.nodeSize());
		assertEquals(13, g.getMC());
		//get the edges between the nodes that exist in the graph
		assertEquals(2, (int)g.getEdge(0, 4));	
		assertEquals(4, (int)g.getEdge(2, 3));
		assertEquals(5, (int)g.getEdge(1, 4));
		assertEquals(3, (int)g.getEdge(3, 5));
		g.removeEdge(1, 4);
		assertFalse(g.hasEdge(4, 1));
		g.connect(4,1,2);
		assertEquals(2, (int)g.getEdge(1, 4));
	}



	@Test

	void testNode_size() {
		weighted_graph g= new WGraph_DS();
		g.addNode(0); g.addNode(1);
		g.addNode(2); g.addNode(3);
		g.addNode(4); g.addNode(5);
		assertEquals(6 ,g.nodeSize());
		//remove node that exist in the graph
		g.removeNode(2);
		g.removeNode(5);
		//remove node that does not exist in the graph
		g.removeNode(8);
		assertEquals(4 ,g.nodeSize());
	}

	@Test
	void test_remove() {

		weighted_graph g= new WGraph_DS();
		//8 nodes
		g.addNode(0); g.addNode(1);
		g.addNode(2); g.addNode(3);
		g.addNode(4); g.addNode(5);
		g.addNode(6); g.addNode(7);
		// 9 edges
		g.connect(0, 1, 1); 
		g.connect(0, 4, 2);
		g.connect(1, 2, 1);
		g.connect(1, 4, 5);
		g.connect(1, 5, 6);
		g.connect(2, 3, 4);
		g.connect(3, 5, 3);
		g.connect(4, 6, 3);
		g.connect(6 ,7, 2);
		assertEquals(8 ,g.nodeSize());
		assertEquals(9, g.edgeSize());

		//remove nodes\edges that does not exist in the graph
		g.removeNode(8);
		g.removeNode(9);
		g.removeEdge(7, 2);
		//all this not changes
		assertEquals(8 ,g.nodeSize());
		assertEquals(9, g.edgeSize());
		assertEquals(17, g.getMC());
		//remove node and edge that exist in the graph
		g.removeEdge(1, 5);
		g.removeNode(7);
		assertFalse(g.hasEdge(6, 7));
		assertEquals(7, g.edgeSize());
		assertEquals(20, g.getMC());
		//remove edge between the same node
		g.removeEdge(0, 0);
		//not changes
		assertEquals(7 ,g.nodeSize());
		assertEquals(7, g.edgeSize());
		assertEquals(20, g.getMC());


	}
	@Test
	public void tast_getV() {
		weighted_graph g= new WGraph_DS();
				g.addNode(0); g.addNode(1);
				g.addNode(2); g.addNode(3);
				g.addNode(4); g.addNode(5);
			
				g.connect(0, 1, 1); 
				g.connect(0, 4, 2);
				g.connect(0, 5, 6);
				g.connect(1, 2, 1);
				g.connect(1, 4, 5);
				g.connect(1, 5, 6);
				g.connect(1, 3, 4);
				g.connect(3, 5, 3);
				assertEquals(6 ,g.nodeSize());
				assertEquals(8, g.edgeSize());
		//nodes that exist in the graph
		int Neighbors_0= g.getV(0).size();	
		assertEquals(3,Neighbors_0);
		int Neighbors_1= g.getV(1).size();	
		assertEquals(5,Neighbors_1);
	}
}





