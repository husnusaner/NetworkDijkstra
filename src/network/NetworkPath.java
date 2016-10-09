package network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


// Most of the code is gotten from 
// http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html for this part
public class NetworkPath {
	// vertexes
	private ArrayList<Vertex> vertexes = new ArrayList<Vertex>();
	// edges
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	// path return
	private List<String> path;
	// helper sets for dijacstra
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;
	// source set up
	private Vertex source;
	// hold distance from source to destination. 
	private int time = 0;



	public NetworkPath(Vertex source, Set<Vertex> vertexes, Set<Edge> edges){
		// TODO Auto-generated constructor stub
		this.vertexes.addAll(vertexes);
		this.edges.addAll(edges); 
		this.source = source;
		settledNodes = new HashSet<Vertex>();
		unSettledNodes = new HashSet<Vertex>();
		distance = new HashMap<Vertex, Integer>();
		predecessors = new HashMap<Vertex, Vertex>();

		distance.put(this.source, 0);
		unSettledNodes.add(this.source);
		while (unSettledNodes.size() > 0) {
			Vertex node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}
	// Find minimal distance target node from source node in adjacent nodes 
	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)	+ getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}
	
	// Get distance in two vertexes
	private int getDistance(Vertex node, Vertex target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)) {
				//System.out.println(edge.getWeight());
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	// This returns the adjacent vertexes which is not already processed
	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && !isSettled(edge.getDestination())){
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	//
	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertex vertex) {
		return settledNodes.contains(vertex);
	}

	// return infinty (max), actual minimum distance 
	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<String> getPath(Vertex target) {
		time = 0; 
		path = new LinkedList<String>();
		Vertex step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			System.out.println("null");
			return null;
		}
		path.add(step.getName());
		while (predecessors.get(step) != null) {
			// adding time if the edges already there for getTime method
			time = time + getDistance(step,predecessors.get(step));
			step = predecessors.get(step);
			path.add(step.getName());
		}
		// Put it into the correct order
		Collections.reverse(path);
		return (LinkedList<String>) path;
	}

	/**
	 * Time token to follow this path.
	 * @return The time (ms)
	 */
	public int getTime() {
		// TODO: Change this so that it corresponds to the time taken to follow the path
		// Please see the getPath(Vertex target) how to find the time
		return time;
	}
	
	@Override
	public String toString() {
		if (path != null)
			return path.toString();
		else
			return "[]";

	}


}