package Tuan_3;

public class TestUnGraphTuan3 {
	public static void main(String[] args) {
		UnGraph ug = new UnGraph(7);
		ug.addEdges(1, 2);
		ug.addEdges(1, 3);
		ug.addEdges(1, 7);
		ug.addEdges(2, 3);
		ug.addEdges(2, 4);
		ug.addEdges(2, 6);
		ug.addEdges(3, 4);
		ug.addEdges(3, 7);
		ug.addEdges(4, 6);
		ug.addEdges(4, 7);
		ug.addEdges(5, 7);
		
		ug.printGraph();

		UnGraph ug2 = new UnGraph(3);
		ug2.addEdges(1, 2);
		ug2.addEdges(2, 3);
		ug2.addEdges(1, 3);
		

		System.out.println("Do thi la con cua do thi khac: " + ug.isSubGraph2(ug2, ug));
		
		ug.DFS(0);
		
		ug.BFS(0);

		ug.findPathLong(0, 5);
		
		ug.findPathShortest(0, 5);
	}
}
