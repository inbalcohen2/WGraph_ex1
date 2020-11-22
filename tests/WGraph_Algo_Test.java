package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

class WGraph_Algo_Test {

	weighted_graph g= new WGraph_DS();
	weighted_graph_algorithms ga= new WGraph_Algo();

	@BeforeEach
	public void test() {
		g.addNode(1); g.addNode(2); 
		g.addNode(3); g.addNode(5);
		g.addNode(4); g.addNode(7);
		g.addNode(6); g.addNode(9);
		g.addNode(8); g.addNode(10);


		g.connect(1, 2, 2.3);
		g.connect(1,4,4.1);
		g.connect(1,6,6.5);
		g.connect(2,3,3.4);
		g.connect(2,4,1.1);
		g.connect(2,5,8.4);
		g.connect(3,4,9.5);
		g.connect(3,6,4.3);
		g.connect(3,7,5.8);
		g.connect(4,9,5);
		g.connect(4,10,2.7);
		g.connect(5,6,7.8);
		g.connect(5,7,3.6);
		g.connect(5,8,1.1);
		g.connect(5,10,1.7);
		g.connect(8,9,8.2);

		ga.init(g);
	}

	@Test
	void test_copy() {
		weighted_graph g_copy= ga.copy();

		assertTrue(g.equals(g_copy));
		assertEquals(true, g_copy.nodeSize()==ga.getGraph().nodeSize());
		assertEquals(true, g_copy.edgeSize()==ga.getGraph().edgeSize());
		g.removeNode(10);
		assertNull(g.getNode(10));
		assertNotEquals(true,g_copy.nodeSize()==ga.getGraph().nodeSize());
		assertNotEquals(true, g_copy.getMC()==ga.getGraph().getMC());
	}



	@Test
	void test_isconnected() {
		assertTrue(ga.isConnected());
		g.removeEdge(3, 6);
		g.removeEdge(1, 6);
		g.removeEdge(5, 6);
		assertFalse(ga.isConnected());
		//There are no nodes in the graph
		ga.init(null);
		assertTrue(ga.isConnected());
		//connect between 2 nodes that does not exist in the graph 
		g.connect(12,11, 1.4);
		assertTrue(ga.isConnected());
		//new graph
		g= new WGraph_DS();
		ga.init(g);
		assertTrue(ga.isConnected());
		//one node in the graph
		g.addNode(8);
		assertTrue(ga.isConnected());
		// 2 nodes in the graph without connect between them
		g.addNode(10);
		assertFalse(ga.isConnected());
		
	}
	@Test
	void test_shortestpathDist() {
		//assertEquals(11.4, ga.shortestPathDist(7, 1));
		assertEquals(6.1, ga.shortestPathDist(2, 9));
		assertEquals(8, ga.shortestPathDist(4, 7));
		g.removeEdge(3, 7);
		g.removeEdge(5, 7);
		assertEquals(-1, ga.shortestPathDist(1, 7));
		assertEquals(6.6, ga.shortestPathDist(2, 8));
		assertEquals(0, ga.shortestPathDist(10, 10));
		assertEquals(1, ga.shortestPath(1,1).size());
		assertEquals(-1, ga.shortestPathDist(13, 13));
		
		
	}
	@Test
	void test_shortestpath() {
		assertEquals(6, ga.shortestPath(1,7).size());
		assertEquals(null, ga.shortestPath(1,11));
		assertEquals(1, ga.shortestPath(2,2).size());
		assertEquals(5, ga.shortestPath(2,8).size());
		assertEquals(null, ga.shortestPath(12,12));
		g.removeNode(7);
		assertEquals(null, ga.shortestPath(5,7));
		
		
		
	}

	@Test
	void test_save_load() {
		
		
		weighted_graph g1= new WGraph_DS();
		weighted_graph_algorithms ga1= new WGraph_Algo();
		g1.addNode(1); g1.addNode(2); 
		g1.addNode(3); g1.addNode(5);
		g1.addNode(4); g1.addNode(7);
		g1.addNode(6); g1.addNode(9);
		g1.addNode(8); g1.addNode(10);


		g1.connect(1, 2, 2.3);
		g1.connect(1,4,4.1);
		g1.connect(1,6,6.5);
		g1.connect(2,3,3.4);
		g1.connect(2,4,1.1);
		g1.connect(2,5,8.4);
		g1.connect(3,4,9.5);
		ga1.init(g1);

 	assertTrue(ga.save("text_graph"));
 	assertTrue(ga.load("text_graph"));
		
		
	}
}
