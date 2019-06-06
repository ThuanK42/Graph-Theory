package Tuan_2;

public class TestUngraphTuan2 {
	public static void main(String[] args) {
		System.out.println("Bai tap tuan 2");
		System.out.println();
		UnGraph ug2 = new UnGraph(9);

		ug2.addEdges(1, 5);
		ug2.addEdges(1, 7);
		ug2.addEdges(2, 3);
		ug2.addEdges(2, 7);
		ug2.addEdges(2, 9);
		ug2.addEdges(3, 4);
		ug2.addEdges(4, 5);
		ug2.addEdges(4, 9);
		ug2.addEdges(5, 6);
		ug2.addEdges(5, 8);
		ug2.addEdges(6, 7);

		ug2.printGraph();
		System.out.println();

		System.out.println("Kiem tra do thi luong phan hay khong ?");
		System.out.println(ug2.isBipartiteGraph());

		System.out.println();
		UnGraph ug4 = new UnGraph(4);
		ug4.addEdges(1, 2);
		ug4.addEdges(1, 4);
		ug4.addEdges(2, 3);
		ug4.addEdges(3, 4);

		UnGraph ug5 = new UnGraph(4);
		ug5.addEdges(1, 2);
		ug5.addEdges(1, 4);

		System.out.println("Kiem tra 1 do thi co phai la con cua do thi khac hay khong ");
		System.out.println(ug4.isSubGraph(ug5, ug4));

		System.out.println();

		UnGraph ug3 = new UnGraph(7);

		ug3.addEdges(1, 2);
		ug3.addEdges(1, 3);
		ug3.addEdges(1, 7);
		ug3.addEdges(2, 3);
		ug3.addEdges(2, 4);
		ug3.addEdges(2, 6);
		ug3.addEdges(3, 4);
		ug3.addEdges(3, 7);
		ug3.addEdges(4, 6);
		ug3.addEdges(4, 7);
		ug3.addEdges(5, 7);

		ug3.printGraph();

		System.out.println();

		System.out.println("Duyet DFS: ");
		ug3.re_DFS(1);

		System.out.println();
		System.out.println("Do thi co lien thong hay khong ?");
		System.out.println(ug3.isConnection());
		System.out.println();
		System.out.println("So thanh phan lien thong");
		System.out.println(ug3.countConnection());
		System.out.println();
		System.out.println("Thanh phan lien thong");
		ug3.findConnection();

	}
}
