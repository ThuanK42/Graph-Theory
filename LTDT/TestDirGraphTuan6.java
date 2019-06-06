package Tuan_6;

public class TestDirGraphTuan6 {
	public static void main(String[] args) {
		DirGraph dg = new DirGraph(4);
		
		dg.addEdges(1, 2);
		dg.addEdges(2, 3);
		dg.addEdges(3, 4);
		dg.addEdges(4, 2);

		
		DirGraph dg2 = new DirGraph(4);
		dg2.addEdges(1, 2);
		dg2.addEdges(2, 3);
		dg2.addEdges(3, 4);
		dg2.addEdges(4, 1);

		System.out.println("Chu trinh hamilton do thi 1: ");
		dg2.findAllCycleHamilton();

		System.out.println("Chu trinh hamilton xuat phat tu 1 dinh do thi 1: ");
		dg2.findAllCycleHamilton(4);
		
		System.out.println("Duong di hamilton");
		dg.findAllPathHamilton();
		
		System.out.println("Duong di hamilton tu 1 dinh ");
		dg.findAllPathHamilton(1);

	}
}
