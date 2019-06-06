package Tuan_1;

public class TestDirGraph {
	public static void main(String[] args) {
		DirGraph dg = new DirGraph(5);
		dg.addEdges(1, 2);
		
		dg.addEdges(4, 1);
		dg.addEdges(4, 3);
		dg.addEdges(4, 5);
		dg.addEdges(5, 6);

		System.out.println("Bac: " + dg.degreeVex(0));
		System.out.println("--------------");
		dg.degreeGraph();
		System.out.println("--------------");
		dg.numEdgesGraph();
		System.out.println("--------------");
		dg.printGraph();
		System.out.println("--------------");
		System.out.println("Sau khi xoa cung");

		dg.delEdges(1, 2);

		System.out.println("Bac: " + dg.degreeVex(0));
		System.out.println("--------------");
		dg.degreeGraph();
		System.out.println("--------------");
		dg.numEdgesGraph();
		System.out.println("--------------");
		dg.printGraph();
		
	}
}
