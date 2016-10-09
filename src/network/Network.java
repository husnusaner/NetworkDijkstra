package network;

import java.util.HashSet;
import java.util.Set;

public class Network {
	//set default latency
	private static int latency = 1;
	//to hold vertex and egdes because it is graph
	private Set<Vertex> vertexes = new HashSet<Vertex>();
	private Set<Edge> edges = new HashSet<Edge>();
	
	//constructor
	public Network(){		
	}

	//create new instances for every call with default latency
	public static Network createWithLatency(int latency2) {
		// TODO: Implement factory method for creating a new network with a default latency
		latency = latency2;
		Network net = new Network();
		return net;
	}
	
	//I considered two way connected from picture in the pdf. Therefore, implemented in simalr manner
	public void connect(String idA, String idB) {
		// TODO: Implement adding a network connection between to nodes -- default latency is used
		// You may (or may not) need to throw exceptions... change signature if needed
		//one way directional
		connect(idA,idB,latency);
		// add the other way direction
		connect(idB,idA,latency);
		
	}

	public void connect(String idA, String idB, int latency) {
		// TODO: Implement adding a network connection between two nodes with a specified latency
		// You may (or may not) need to throw exceptions... change signature if needed
		
		//similar to previous adding, one way directional adding
		connectHelper(idA,idB,latency);
		//the other directional adding
		connectHelper(idB,idA,latency);
		
		
	}
	
	//This is helper function to add edges and vertices to graph
	public void connectHelper(String idA, String idB, int latency) {
		// TODO: Implement adding a network connection between two nodes with a specified latency
		// You may (or may not) need to throw exceptions... change signature if needed
		
		// create vertices
		Vertex vr1  = new Vertex(idA, idA);
		//add vertices if it is not already in
		vertexes.add(vr1);
		Vertex vr2  = new Vertex(idB, idB);
		vertexes.add(vr2);
		
		//create edges and add it to graph
		//Note: this adding also updates the graph if the edge is already in the graph
		Edge edg = new Edge (vr1, vr2, latency);
		if (!edges.contains(edg))
			edges.add(edg);
		else{
			//if the edges already there we remove and added again by updating with new latency
			edges.remove(edg);
			edg.setWeight(latency);
			edges.add(edg);
		}
		
	}
	
	
	public NetworkPath sendPacket(String idA, String idB) {
		// TODO: Simulates sending a packet from node idA to node idB. Returns the route that the packet followed
		// You may (or may not) need to throw exceptions... change signature if needed
		
		//create source vertex from string
		Vertex source  = new Vertex(idA, idA);
		//create destination from string
		Vertex destination  = new Vertex(idB, idB);
		// create network path, basically,  it is dijkstra  algorithm set up accoring to source for all vertextes
		NetworkPath path = new NetworkPath(source, vertexes, edges);
		// get distance if there is a path
		path.getPath(destination);
		return path;
	}

	public Set<Vertex> getVertexes() {
		return vertexes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}
}
