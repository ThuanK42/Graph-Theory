package Tuan_2;

public class TestDirGraphTuan2 {
	public static void main(String[] args) {
		System.out.println("Bai tap tuan 2");
		DirGraph dg10 = new DirGraph(4);
		dg10.addEdges(1, 2);
		dg10.addEdges(2, 3);
		dg10.addEdges(3, 1);
		dg10.addEdges(3, 4);
		dg10.addEdges(4, 1);
		dg10.printGraph();
		System.out.println("Duyệt DFS");
		dg10.re_DFS(3);

		System.out.println();
		System.out.println("Kiem tra lien thong");
		System.out.println(dg10.isConnection());
		System.out.println();
		System.out.println("So thanh phan lien thong");
		System.out.println(dg10.countConnection());
		System.out.println();
		System.out.println("Tim thanh phan lien thong cua do thi");
		dg10.findConnection();

	}
}
