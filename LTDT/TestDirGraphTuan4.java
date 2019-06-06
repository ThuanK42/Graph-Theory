package Tuan_4;

public class TestDirGraphTuan4 {
	public static void main(String[] args) {
		DirGraph dg4 = new DirGraph(4);
		dg4.addEdges(1, 2);
		dg4.addEdges(2, 3);
		dg4.addEdges(2, 4);
		dg4.addEdges(3, 2);
		dg4.addEdges(3, 4);
		dg4.addEdges(4, 1);
		dg4.addEdges(4, 3);

		DirGraph dg5 = new DirGraph(6);
		dg5.addEdges(1, 2);
		dg5.addEdges(1, 5);
		dg5.addEdges(2, 3);
		dg5.addEdges(2, 6);
		dg5.addEdges(3, 1);
		dg5.addEdges(3, 4);
		dg5.addEdges(4, 5);
		dg5.addEdges(5, 6);
		dg5.addEdges(5, 3);
		dg5.addEdges(6, 1);
		dg5.addEdges(6, 4);
		

		System.out.println("Kiem tra do thi 4");
		dg4.printGraph();
		System.out.println("Do thi co chu trinh Euler khong ? " + dg4.checkCycleEuler());
		System.out.println("Do thi co duong di Euler khong ? " + dg4.checkPathEuler());
		// Do thi 4 dinh thi cho input em truyen vao 0->3 tuong duong voi dinh 1->4 trong hinh ve 
		dg4.findCycleEuler(1);

		System.out.println("Kiem tra do thi 5");
		dg5.printGraph();
		System.out.println("Do thi co chu trinh Euler khong ? " + dg5.checkCycleEuler());
		System.out.println("Do thi co duong di Euler khong ? " + dg5.checkPathEuler());
		// Do thi 6 dinh thi cho input em truyen tham so vao phuong thuc la tu 0->5 tuong duong voi dinh 1->6 trong hinh ve 
		dg5.findPathEuler(3);
		
	}
}
