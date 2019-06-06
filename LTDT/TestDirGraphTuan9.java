package Tuan_9;

public class TestDirGraphTuan9 {
	public static void main(String[] args) {

		Graph ug = new DirGraph(9);
		ug.addEdges(1, 2, 8);
		ug.addEdges(2, 3, 9);
		ug.addEdges(3, 4, 4);
		ug.addEdges(4, 5, 6);
		ug.addEdges(5, 7, 5);
		ug.addEdges(7, 6, 10);
		ug.addEdges(6, 1, 3);
		ug.addEdges(7, 8, 11);
		ug.addEdges(7, 9, 1);
		ug.addEdges(5, 2, 12);
		ug.addEdges(6, 2, 7);
		ug.addEdges(5, 3, 18);

		System.out.println("Prim");
		ug.Prim(1).printGraph();
		System.out.println();

		Graph ug2 = new DirGraph(7);
		ug2.addEdges(1, 2, 8);
		ug2.addEdges(2, 3, 4);
		ug2.addEdges(2, 4, 1);
		ug2.addEdges(3, 6, 3);
		ug2.addEdges(4, 5, 5);
		ug2.addEdges(5, 6, 6);
		ug2.addEdges(5, 7, 3);
		ug2.addEdges(6, 7, 2);
		
		System.out.println("Thuat toan Dijsktra");
		ug2.Dijsktra(0);
		System.out.println();
		
		Graph ug3 = new DirGraph(6);
		ug3.addEdges(1, 2, 7);
		ug3.addEdges(1, 4, 2);
		ug3.addEdges(2, 3, 4);
		ug3.addEdges(2, 5, 1);
		ug3.addEdges(3, 6, 3);
		ug3.addEdges(4, 2, 4);
		ug3.addEdges(5, 1, 2);
		ug3.addEdges(5, 3, 2);
		ug3.addEdges(6, 2, 1);
		
		System.out.println();
		ug3.Floyd(ug3.adj);

	}
}
