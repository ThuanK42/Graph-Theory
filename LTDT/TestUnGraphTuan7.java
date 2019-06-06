package Tuan_7;

public class TestUnGraphTuan7 {
	public static void main(String[] args) {
		UnGraph unGraph = new UnGraph(9);
		unGraph.addEdges(1, 2);
		unGraph.addEdges(1, 6);
		unGraph.addEdges(2, 3);
		unGraph.addEdges(2, 5);
		unGraph.addEdges(2, 6);
		unGraph.addEdges(3, 4);
		unGraph.addEdges(3, 5);
		unGraph.addEdges(4, 5);
		unGraph.addEdges(5, 7);
		unGraph.addEdges(6, 7);
		unGraph.addEdges(7, 8);
		unGraph.addEdges(7, 9);
		
		UnGraph unGraph2 = new UnGraph(9);
		unGraph2.addEdges(1, 2);
		unGraph2.addEdges(1, 6);
		unGraph2.addEdges(2, 3);
		unGraph2.addEdges(2, 5);
		unGraph2.addEdges(2, 6);
		unGraph2.addEdges(3, 4);
		unGraph2.addEdges(3, 5);
		unGraph2.addEdges(4, 5);
		unGraph2.addEdges(5, 7);
		unGraph2.addEdges(6, 7);
		unGraph2.addEdges(7, 8);
		unGraph2.addEdges(7, 9);
		
		unGraph.DFSTree(1).printGraph();
		
		unGraph2.BFSTree(0).printGraph();
	}
}
