package Tuan_5;

public class TestDirGraphTuan5 {
	public static void main(String[] args) {

		System.out.println("===============================TUAN5==============================");

		DirGraph dg = new DirGraph(4);
		dg.addEdges(1, 2);
		dg.addEdges(2, 3);
		dg.addEdges(3, 4);
		dg.addEdges(4, 1);

//		DirGraph dg2 = new DirGraph(5);
//		dg2.addEdges(1, 2);
//		dg2.addEdges(1, 4);
//		dg2.addEdges(2, 5);
//		dg2.addEdges(3, 5);
//		dg2.addEdges(3, 1);
//		dg2.addEdges(4, 2);
//		dg2.addEdges(4, 3);
//		dg2.addEdges(5, 4);
//		dg2.addEdges(5, 1);

		System.out.println("Kiem tra chu trinh Hamilton: " + dg.checkCycleHamilton());
		// System.out.println("Kiem tra duong di Hamilton: " + dg2.checkPathHamilton());
		dg.findCycleHamilton();

	}
}
