package Tuan_5;

public class TestUnGraphTuan5 {

	public static void main(String[] args) {
		System.out.println("===============================TUAN 5==================================");

		UnGraph ug10 = new UnGraph(5);
		ug10.addEdges(1, 2);
		ug10.addEdges(1, 3);
		ug10.addEdges(2, 3);
		ug10.addEdges(2, 4);
		ug10.addEdges(2, 5);
		ug10.addEdges(3, 4);
		ug10.addEdges(4, 5);

		UnGraph ug12 = new UnGraph(4);
		ug12.addEdges(1, 2);
		ug12.addEdges(2, 3);
		ug12.addEdges(3, 4);
		ug12.addEdges(4, 1);

		UnGraph ug11 = new UnGraph(6);
		ug11.addEdges(1, 2);
		ug11.addEdges(1, 5);
		ug11.addEdges(1, 6);
		ug11.addEdges(2, 3);
		ug11.addEdges(2, 4);
		ug11.addEdges(3, 4);
		ug11.addEdges(5, 6);

		UnGraph ug13 = new UnGraph(4);
		ug13.addEdges(1, 2);
		ug13.addEdges(2, 3);
		ug13.addEdges(2, 4);
		ug13.addEdges(4, 3);
		
		UnGraph ug14 = new UnGraph(9);
		ug14.addEdges(1, 2);
		ug14.addEdges(2, 5);
		ug14.addEdges(2, 3);
		ug14.addEdges(3, 4);
		ug14.addEdges(3, 6);
		ug14.addEdges(4, 6);
		ug14.addEdges(4, 7);
		ug14.addEdges(5, 6);
		ug14.addEdges(6, 8);
		
		ug11.addEdges(7, 8);
		ug11.addEdges(8, 9);

		System.out.println("Kiem tra chu trinh Hamilton do thi 1: " + ug10.checkCycleHamilton());
		System.out.println("Kiem tra chu trinh Hamilton do thi 2: " + ug11.checkCycleHamilton());
		System.out.println("Kiem tra chu trinh Hamilton do thi 3: " + ug12.checkCycleHamilton());
		System.out.println("Kiem tra chu trinh Hamilton do thi 4: " + ug13.checkCycleHamilton());

		System.out.println("Kiem tra duong di Hamilton do thi 1: " + ug10.checkPathHamilton());
		System.out.println("Kiem tra duong di Hamilton do thi 2: " + ug11.checkPathHamilton());
		System.out.println("Kiem tra duong di Hamilton do thi 3: " + ug12.checkPathHamilton());
		System.out.println("Kiem tra duong di Hamilton do thi 4: " + ug13.checkPathHamilton());
		//System.out.println("Kiem tra duong di Hamilton do thi 4: " + ug14.checkPathHamilton());

		ug10.findCycleHamilton();

	}
}
