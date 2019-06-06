package Tuan_11;

public class TestDirGraphTuan11 {
	public static void main(String[] args) {

		
		DirGraph dr = new DirGraph(6);
		dr.addEdges(1, 2, 7);
		dr.addEdges(1, 4, 2);
		dr.addEdges(2, 3, 4);
		dr.addEdges(2, 5, 1);
		dr.addEdges(3, 6, 3);
		dr.addEdges(4, 2, 4);
		dr.addEdges(5, 1, 2);
		dr.addEdges(5, 3, 2);
		dr.addEdges(6, 2, 1);

		System.out.println("Floyd tu 2 dinh");
		dr.floyd(0, 4, dr);

		System.out.println();
		
//		DirGraph g6 = new DirGraph(6);
//		g6.addEdges(1, 2, 3);
//		g6.addEdges(1, 3, 2);
//		g6.addEdges(1, 4, 5);
//		g6.addEdges(2, 4, 1);
//		g6.addEdges(2, 5, 4);
//		g6.addEdges(3, 4, 2);
//		g6.addEdges(3, 6, 1);
//		g6.addEdges(4, 5, 3);
//		g6.addEdges(5, 6, 2);
//		System.out.println("Duyet Warshall");
//
//		g6.warshall();
//		System.out.println();

		DirGraph graph = new DirGraph(7);
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
