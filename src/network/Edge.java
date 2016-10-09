package network;


// Most of the code is gotten from 
// http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html for this part


public class Edge {
	private final Vertex source;
	private final Vertex destination;
	private int weight;

	public Edge(Vertex source, Vertex destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}
	@Override
	public String toString() {
		return source + " " + destination;
	}

	
	// I created equals for edge but then I do not need to use this. Therefore, I just commented. 
	/*
	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (source.getName().equals(other.source.getName()) && destination.getName().equals(other.destination.getName()))
			return true;
		return false;
	}
	*/

}
