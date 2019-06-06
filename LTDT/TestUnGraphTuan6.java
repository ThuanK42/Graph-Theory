package Tuan_6;

public class TestUnGraphTuan6 {
	public static void main(String[] args) {
		UnGraph ug = new UnGraph(5);
		ug.addEdges(1, 2);
		ug.addEdges(1, 5);
		ug.addEdges(2, 3);
		ug.addEdges(2, 4);
		ug.addEdges(2, 5);
		ug.addEdges(3, 4);
		ug.addEdges(4, 5);

		UnGraph ug2 = new UnGraph(6);
		ug2.addEdges(1, 2);
		ug2.addEdges(1, 3);
		ug2.addEdges(2, 3);
		ug2.addEdges(3, 4);
		ug2.addEdges(4, 5);
		ug2.addEdges(5, 6);
		ug2.addEdges(6, 4);

		UnGraph ug3 = new UnGraph(4);
		ug3.addEdges(1, 2);
		ug3.addEdges(2, 3);
		ug3.addEdges(2, 4);
		ug3.addEdges(3, 4);

		
		System.out.println("Chu trinh hamilton do thi 1: ");
		ug.findAllCycleHamilton();

		System.out.println("Chu trinh hamilton xuat phat tu 1 dinh do thi 1: ");
		ug.findAllCycleHamilton(2);
		
		System.out.println("Duong di hamilton");
		ug2.findAllPathHamilton();
		
		System.out.println("Duong di hamilton tu 1 dinh ");
		ug2.findAllPathHamilton(0);
		
	}

}
