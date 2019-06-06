package Tuan_3;

public class TestDirGraphTuan3 {
public static void main(String[] args) {
	DirGraph dg10 = new DirGraph(7);
	dg10.addEdges(1, 2);
	dg10.addEdges(1, 3);
	dg10.addEdges(1, 7);
	dg10.addEdges(2, 3);
	dg10.addEdges(2, 4);
	dg10.addEdges(2, 6);
	dg10.addEdges(3, 4);
	dg10.addEdges(3, 7);
	dg10.addEdges(4, 6);
	dg10.addEdges(4, 7);
	dg10.addEdges(5, 7);

	DirGraph dg2 = new DirGraph(3);
	dg2.addEdges(1, 2);
	dg2.addEdges(2, 3);
	dg2.addEdges(1, 3);

	System.out.println("Do thi la con cua do thi khac: " + dg10.isSubGraph2(dg2, dg10));
	
	dg10.DFS(0);
	
	dg10.BFS(0);

	dg10.findPathLong(0, 5);
	
	dg10.findPathShortest(0, 5);
}
}
