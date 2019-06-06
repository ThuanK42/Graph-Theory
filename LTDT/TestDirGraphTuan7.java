package Tuan_7;

public class TestDirGraphTuan7 {
public static void main(String[] args) {
	DirGraph dr = new DirGraph(9);
	dr.addEdges(1, 2);
	dr.addEdges(1, 6);
	dr.addEdges(2, 3);
	dr.addEdges(2, 5);
	dr.addEdges(2, 6);
	dr.addEdges(3, 4);
	dr.addEdges(3, 5);
	dr.addEdges(4, 5);
	dr.addEdges(5, 7);
	dr.addEdges(6, 7);
	dr.addEdges(7, 8);
	dr.addEdges(7, 9);
	
	DirGraph dr2 = new DirGraph(9);
	dr2.addEdges(1, 2);
	dr2.addEdges(1, 6);
	dr2.addEdges(2, 3);
	dr2.addEdges(2, 5);
	dr2.addEdges(2, 6);
	dr2.addEdges(3, 4);
	dr2.addEdges(3, 5);
	dr2.addEdges(4, 5);
	dr2.addEdges(5, 7);
	dr2.addEdges(6, 7);
	dr2.addEdges(7, 8);
	dr2.addEdges(7, 9);
	
	dr.DFSTree(1).printGraph();
	
	dr2.BFSTree(0).printGraph();
}
}
