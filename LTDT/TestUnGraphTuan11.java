package Tuan_11;

public class TestUnGraphTuan11 {
	public static void main(String[] args) {

		UnGraph g6 = new UnGraph(6);
		g6.addEdges(1, 2, 3);
		g6.addEdges(1, 3, 2);
		g6.addEdges(1, 4, 5);
		g6.addEdges(2, 4, 1);
		g6.addEdges(2, 5, 4);
		g6.addEdges(3, 4, 2);
		g6.addEdges(3, 6, 1);
		g6.addEdges(4, 5, 3);
		g6.addEdges(5, 6, 2);
		System.out.println("Duyet Warshall");

		g6.warshall();
		System.out.println();

		UnGraph graph = new UnGraph(7);
		graph.addEdges(1, 2, 4);
		graph.addEdges(1, 3, 7);
		graph.addEdges(2, 3, 2);
		graph.addEdges(3, 4, 2);
		graph.addEdges(3, 5, 3);
		graph.addEdges(3, 6, 2);
		graph.addEdges(4, 3, 1);
		graph.addEdges(4, 5, 1);
		graph.addEdges(5, 6, 1);
		graph.addEdges(6, 2, 5);
		graph.addEdges(6, 7, 3);
		graph.addEdges(7, 2, 2);
		graph.addEdges(7, 6, 4);
		graph.addEdges(7, 5, 8);
		graph.addEdges(1, 7, 1);
		
		System.out.println("Duyet BellMan_Ford");
		graph.bellman_Ford(0);

	}
}
