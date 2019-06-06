package Tuan_8;

public class TestDirGraphTuan8 {
	public static void main(String[] args) {
		DirGraph ug = new DirGraph(9);
		ug.addEdges(1, 2, 8);
		ug.addEdges(1, 6, 3);
		ug.addEdges(2, 3, 9);
		ug.addEdges(2, 5, 12);
		ug.addEdges(6, 2, 7);
		ug.addEdges(3, 4, 4);
		ug.addEdges(3, 5, 18);
		ug.addEdges(4, 5, 6);
		ug.addEdges(5, 7, 5);
		ug.addEdges(6, 7, 10);
		ug.addEdges(7, 8, 11);
		ug.addEdges(7, 9, 1);
		
		

		ug.Kruskal();

		ug.Prim(0);
	}
}
