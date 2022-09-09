package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {
	private boolean reflexiveFound;
	private boolean symmFound;
	private boolean transFound;
	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {

		for (int x = 0; x < set.size(); x++) {
			String target = set.get(x);
			String targetRelation = String.format("%s,%s", target, target); // create new string to find
			for (int y = 0; y < relation.size(); y++) {
				reflexiveFound = false; // set false before each iteration
				String pos = relation.get(y);
				if (pos.equals(targetRelation)) { // if found break out and set reflexive to true
					reflexiveFound = true;
					break;
				}
				if (y == relation.size() - 1 && !reflexiveFound) { // if still false return
					return reflexiveFound;
				}
			}

		}
		return reflexiveFound;
	}

	public String reverseString(String string) {
		char ch;
		String nstr = "";
		// code inspired by https://www.geeksforgeeks.org/reverse-a-string-in-java/
		for (int i = 0; i < string.length(); i++) {
			ch = string.charAt(i); // extracts each character
			nstr = ch + nstr; // adds each character in front of the existing string

		}
		return nstr;
	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {

		for (int x = 0; x < relation.size(); x++) {

			String target = relation.get(x);

			for (int y = 0; y < relation.size(); y++) {
				symmFound = false;
				String pos = relation.get(y);
				if (pos.equals(reverseString(target))) { // if the position equals reverse of target
					symmFound = true;
					break;
				} else if (y == (relation.size() - 1) && symmFound == false) {
					System.out.printf("The relation needed is %s", reverseString(target));
					return symmFound;
				}
			}

		}

		return symmFound;

	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {

		for (int x = 0; x < relation.size(); x++) {
			String elementOne = relation.get(x);
			String[] sepStrings1 = elementOne.split(",", 2);
			for (int y = 0; y < relation.size(); y++) {
				String elementTwo = relation.get(y);
				String[] sepStrings2 = elementTwo.split(",", 2);
				if (sepStrings1[1].equals(sepStrings2[0])) { // if the 2nd element of string 1 equals first element
																// string 2
					String toFind = String.format("%s,%s", sepStrings1[0], sepStrings2[1]); // create the new string to
																							// be found
					transFound = false;
					for (int z = 0; z < relation.size(); z++) {

						String pos = relation.get(z);
						if (pos.equals(toFind)) { // if trans relation found then true and break
							transFound = true;
							break;
						}
						if (z == (relation.size() - 1) && transFound == false) { // otherwise return not found
							return transFound;
						}

					}

				}

			}
		}
		return transFound;

	}

	/**
	 * This method returns a boolean based on whether the input sets are
	 * anti-symmetric TODO: Complete this method (Note a set is not passed in as a
	 * parameter but a list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		if (isReflexive(set, relation) && isSymmetric(relation) && isTransitive(relation)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {
		List<String> equivClass = new ArrayList<>();
		if (isEquivalence(set, relation)) {
			for (int x = 1; x < relation.size(); x++) {
				String[] addEquiv = relation.get(x).split(",");
				if (addEquiv[0].contains(node)) {
					equivClass.add(addEquiv[1]);
				}
			}
			return equivClass;
		} else {
			System.out.println("Can't compute equivalence class as this is not an equivalence relation");
			return null;
		}
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {// name to breadthFirstSearch

		List<Node<String>> visited = new ArrayList<>();

		Set<Entry<Node<String>, LinkedList<Edge<Node<String>>>>> keys = adjacencyMap.entrySet();

		visited = bfsHelper(start, visited);

		for (Map.Entry<Node<String>, LinkedList<Edge<Node<String>>>> index : keys) {
			Node<String> node = index.getKey();
			System.out.println(node);
			if (!visited.contains(node)) {
				visited = bfsHelper(node, visited);
			}
		}

		return visited;

	}

	public List<Node<String>> bfsHelper(Node<String> start, List<Node<String>> visited) {
		Node<String> next = start;
		NodesStackAndQueue<Node<String>> queue = new NodesStackAndQueue<>(); // create queue
		queue.append(next); // append to queue
		visited.add(next); // add as visited already
		while (!queue.isEmpty()) {
			next = queue.pop();

			LinkedList<Edge<Node<String>>> edge = (adjacencyMap.get(next));
			if (edge == null) { // if no edges then continue to next queue node
				continue;
			} else {
				for (int x = 0; x < edge.size(); x++) {
					if (!visited.contains(edge.get(x).getTarget())) { // if not visited then add visited and append
						visited.add(edge.get(x).getTarget());
						queue.append(edge.get(x).getTarget());
					}
				}

			}
		}
		return visited;
	}

	public List<Node<String>> dfsHelper(Node<String> start, List<Node<String>> visited) {
		Node<String> next = start;
		NodesStackAndQueue<Node<String>> stack = new NodesStackAndQueue<>(); // create stack DS
		stack.push(next); // start loaded into stack

		while (!stack.isEmpty()) {
			next = stack.pop();
			if (!visited.contains(next)) {
				visited.add(next); // if not visited, add as visited
			}
			LinkedList<Edge<Node<String>>> edge = (adjacencyMap.get(next)); // take the linked list(of edges) of the
																			// Node
			if (edge == null) { // if node has no edges then continue to next node in stack
				continue;
			}
			for (int x = 0; x < edge.size(); x++) { // if it does have edges
				if (!visited.contains(edge.get(x).getTarget())) { // if the next node is not visited

					stack.push(edge.get(x).getTarget()); // push onto stack

				}

			}
		}
		return visited;
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {

		List<Node<String>> visited = new ArrayList<>();

		Set<Entry<Node<String>, LinkedList<Edge<Node<String>>>>> keys = adjacencyMap.entrySet();

		visited = dfsHelper(start, visited);
		if (!rooted) {
			for (Map.Entry<Node<String>, LinkedList<Edge<Node<String>>>> index : keys) {
				Node<String> node = index.getKey();

				if (!visited.contains(node)) {
					visited = dfsHelper(node, visited);
				}
			}
		}

		return visited;
	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
