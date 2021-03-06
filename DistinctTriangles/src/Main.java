
/* Sample code to read in test cases:

4 5;0 2,0 1,1 2,1 3,2 3
9 3;1 3,1 8,3 8
9 3;5 6,5 7,6 7
*/
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		Node[] vertexes;

		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line, lineArr[], tmpArr[];
		int eCount, vCount;

		// loop through every set of v/e
		while ((line = buffer.readLine()) != null) {
			HashSet<Triangle> output = new HashSet<Triangle>();
			line = line.trim();
			lineArr = line.split(";");
			tmpArr = lineArr[0].split(" ");
			line = lineArr[1];
			lineArr = line.split(",");

			vCount = Integer.parseInt(tmpArr[0]);
			eCount = Integer.parseInt(tmpArr[1]);

			vertexes = new Node[vCount];

			// loop through all the edges in the input lineArr
			// and build the graph
			for (int i = 0; i < eCount; i++) {
				// grab the next string in lineArr and split on space
				tmpArr = lineArr[i].split(" ");
				int s = Integer.parseInt(tmpArr[0]);
				int t = Integer.parseInt(tmpArr[1]);
				if (vertexes[s] == null) {
					vertexes[s] = new Node(s);
				}
				if (vertexes[t] == null) {
					vertexes[t] = new Node(t);
				}

				// add neighbors to list of each vertex
				vertexes[s].addAdjacent(t);
				vertexes[t].addAdjacent(s);

			}

			int outputCount = 0;
			Node curr;
			// traverse graph, starting at every node,
			// and check if at level 3 from source if youve reached source
			for (int i = 0; i < vCount; i++) {
				Stack<Tuple> stack = new Stack<Tuple>();

				curr = vertexes[i];
				// if node has edges
				if (curr != null) {
					for (Integer n : curr.adjacents) {
						Tuple tmp = new Tuple(n, 1);
						stack.push(tmp);
					}
				}

				// use a stack to DFS from current v
				while (!stack.isEmpty()) {
					Tuple currNode = stack.pop();
					
					// if depth is 3 and back at source v, increase count
					if (currNode.depth == 3) {
						if (currNode.node == i) {
							outputCount++;
						}
					} 
					// if depth less than 3, add it's adjacents onto stack
					else if (currNode.depth < 3) {
						Node innerNode = vertexes[currNode.node];
						for (Integer n : innerNode.adjacents) {
							stack.push(new Tuple(n, currNode.depth + 1));
						}
					}
				}
			}
			System.out.println(outputCount / 6);
		}
	}
}

class Node {
	public HashSet<Integer> adjacents;
	public int value;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node other = (Node) obj;
		if (adjacents == null) {
			if (other.adjacents != null) {
				return false;
			}
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}

	public Node(int value) {
		this.adjacents = new HashSet<Integer>();
		this.value = value;
	}

	public void addAdjacent(int n) {
		this.adjacents.add(n);
	}
}

class Tuple {
	public int node;
	public int depth;

	public Tuple(int node, int dep) {
		this.node = node;
		this.depth = dep;
	}
}

class Triangle {
	public TreeSet<Integer> nodes = new TreeSet<Integer>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triangle other = (Triangle) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}

	public Triangle(int a, int b, int c) {
		this.nodes.add(a);
		this.nodes.add(b);
		this.nodes.add(c);
	}

}