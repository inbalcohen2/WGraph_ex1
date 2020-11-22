# WGraph_ex1

## The purpose of the project:
Building a weighted graph (unintentional)
The project contains 2 algorithms - for finding the shortest route in the graph.

-  Bfs algorithm
which I used to check if the graph is connected or not.
-  A Dijkstra's algorithm 
uses to find the shortest path in a weighted graph.
### About the project:
We built a system that builds a weighted graph by the classes:
 WGraph_DS, WGraph_Algo, and another class I craeted: edges.

- The edges class:

was built to hold the distance between 2 nodes which is basically the weight of the node, I set at the destination and also the weights of each node in the graph they were defined as weight. In this class, I used get and set. To get the distance/weight or to initialize the distance/weight.

- The WGraph_DS class:

we have created an internal class - called Node_Info: Through it the nodes in the graph are formed and contained within hasmap structures that contain the graph nodes by a key and by the edges department through which it also reaches the weights of each node and contains them. 

- The outer class WGraph_DS:

we created edges between nodes connected to each other by a connect function that linked them and created a edge with a weight between every 2 nodes it received - which is the distance between the nodes.
In this class we also checked if there is a edge-relationship between 2 nodes, and if so through the class we created edges we returned the weight of the node which is the distance between the 2 nodes
In addition, with the help of the hashmap we defined in the NodeInfo department, we reached the neighbors of a particular node and added them to a container, and created a collection of nodes that have a neighbor-relationship of the same node. (node and its neighbors.)
Finally, the goal is to create a weighted graph made up of nodes and edges that connect them.
And more in this class we have created functions which add, delete nodes and edges with their weights and with the distance between the different nodes and count the amount of edges and nodes that are in the graph.

- The WGraph_Algo :

We implemented a number of functions:
1) - In this class we created a deep copy of the weighted graph so we could duplicate the graph.
2) - save&load in this class we also created save and load functions which save the graph in the form of a file and load the file.
###  BFS algorithm- this algorithm is used by us to travel on the- graph,
and it does so with an efficiency of O (n + v) where -n is the number of nodes and -v is the  number of sides.

And he travels the graph in this way:
He goes through node-node and checks each node only once, if he checked it once he will not check again even if it still exists
This is done using the Hashmap we defined that initializes all nodes in false
And then he goes over the nodes again and he starts at a certain node and goes over all his neighbors and every node he visited makes it true, and that way we know if there is a node that is not connected to the other nodes because it will remain marked in false. 
This algorithm is used in the Isconnected function to check whether the graph is linked - whether it is possible to reach any other vertex in the graph from any vertex.
In addition, in this class, we used Dijkstra's algorithm 3)
 which is very similar to the use of the bfs algorithm only that the use of a Dijkstra's algorithm is with a weighted graph.

### Dijkstra's algorithm: An algorithm for finding the shortest paths between nodes in a graph
he gets 2 nodes- src and -dest should get from the src node to the dest node and go through the nodes with the lowest weight. The algorithm works as follows:
We will first initialize all the weights of the nodes to infinity so that we know which node we have not yet updated, and then we set a priority queue that will contain the nodes we will visit and update their weights.
In addition, we set the hashmap to initialize all nodes in false.
Each node he visited will be marked as a visit (correct), and that way we will know if we visited this node or not, 
and finally, if there is such a node, then he is not connected to the other node and he will remain marked as false.
And that way we will know that its weight does not need to be updated
In the priority queue, we enter the first node and initialize its weight to 0, and all the other nodes in the graph are initialized to infinity.
For the current junction, we will include all its neighbors and update their temporary weights.
The weight of each node is updated according to the parent weight of that node plus the temporary distance between them which is the weight on the edge.
Then the same node we started with becomes the father of this node and leaves the queue, it is marked as one we have already visited and we will not return to it again. 
Each of the introduced neighbors treats him the same way:   
putting his neighbors in line and updating their weights.
Each node can have several neighbors and then also some fathers through whom they reach, so if one of the neighbors is already updated with weight because we reached it through another father-node, then we will check through which neighbor that node will have the lowest weight, then we will keep the lower weight.
We will take the node out of the queue and return it with the updated weight. 
To the same junction is also updated the new father through which we reached a junction with a lower weight.
And so for each node up to the node, we set to reach in the graph.

### Dijkstra's algorithm is used by us for 2 functions in the graph:
3) - shortestpathDist-
For a given source node in a graph, the algorithm finds the shortest path between src and dest and the function returns the weight of the dest which should be the lowest weight.
4) - Shortestpath For a given node in a graph, the algorithm finds the shortest path between src and dest and returns a list of the nodes through which we passed. This list contains the ancestors of the nodes through which we passed.
The list was made by the hashmap that for each current node puts into the container the node that brought us to the current node which is its parent node.



## In conclusion:
In this project we created a weighted graph-
By the different departments, we built a system that builds a graph from nodes that each have a unique key and sides that connect the neighboring nodes, different data structures that contain the nodes, and we added weights to each node in the graph and also the distance to each side connecting 2 vertices.
We checked the shortest paths between each vertex. And we used two algorithms - bfs and dixtra for different operations
