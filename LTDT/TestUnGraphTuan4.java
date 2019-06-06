package Tuan_4;

public class TestUnGraphTuan4 {
	public static void main(String[] args) {

		UnGraph ug4 = new UnGraph(7);
		ug4.addEdges(1, 2);
		ug4.addEdges(1, 3);
		ug4.addEdges(1, 7);
		ug4.addEdges(2, 3);
		ug4.addEdges(2, 4);
		ug4.addEdges(2, 6);
		ug4.addEdges(3, 4);
		ug4.addEdges(3, 7);
		ug4.addEdges(4, 6);
		ug4.addEdges(4, 7);
		ug4.addEdges(5, 7);

		ug4.printGraph();

		System.out.println("Kiem tra do thi so 4");
		ug4.DFS(0);
		ug4.findWay(0, 5);
		ug4.BFS(0);
		ug4.findWayShortest(0, 5);

		System.out.println("=============================================");

		UnGraph ug = new UnGraph(5);
		ug.addEdges(1, 2);
		ug.addEdges(1, 5);
		ug.addEdges(2, 5);
		ug.addEdges(3, 4);
		ug.addEdges(3, 5);
		ug.addEdges(4, 5);

		UnGraph ug2 = new UnGraph(5);
		ug2.addEdges(1, 2);
		ug2.addEdges(1, 5);
		ug2.addEdges(2, 3);
		ug2.addEdges(2, 5);
		ug2.addEdges(3, 4);
		ug2.addEdges(4, 5);

		System.out.println("Kiem tra do thi so 1");
		ug.printGraph();
		System.out.println("Do thi co lien thong khong ? : " + ug.isConnection());
		System.out.println("Do thi co phai chu trinh Euler khong ? : " + ug.checkCycleEuler());
		System.out.println("Do thi co duong di Euler khong ? : " + ug.checkPathEuler());
		// Do thi 5 dinh thi cho input em truyen vao 0->4 tuong duong voi dinh 1->5 trong hinh ve 
		ug.findCycleEuler(0);

		System.out.println("=============================================");

		System.out.println("Kiem tra do thi so 2");
		ug2.printGraph();
		System.out.println("Do thi co lien thong khong ? : " + ug2.isConnection());
		System.out.println("Do thi co phai chu trinh Euler khong ? : " + ug2.checkCycleEuler());
		System.out.println("Do thi co duong di Euler khong ? : " + ug2.checkPathEuler());
		// Do thi 5 dinh thi input em truyen vao 0->4 tuong duong voi dinh 1->5 trong hinh ve 
		ug2.findPathEuler(1);


	}

}
