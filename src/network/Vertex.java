package network;


// Most of the code is gotten from 
// http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html for this part


public class Vertex {
	// I do not need to create id because Id and name are same. However, I do not want to edit. 
	final private String id;
	final private String name;

	
	public Vertex(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	// this is very important to override the equal in comparison. 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// this is very important to override the equal in comparison. 
	@Override
	public boolean equals(Object obj) {
		Vertex other = (Vertex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
