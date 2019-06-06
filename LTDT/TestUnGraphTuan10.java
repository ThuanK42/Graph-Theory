package Tuan_10;

public class TestUnGraphTuan10 {
	public static void main(String[] args) {

		UnGraph ug3 = new UnGraph(6);
		ug3.addEdgesW(1, 2, 7);
		ug3.addEdgesW(1, 4, 2);
		ug3.addEdgesW(2, 3, 4);
		ug3.addEdgesW(2, 5, 1);
		ug3.addEdgesW(3, 6, 3);
		ug3.addEdgesW(4, 2, 4);
		ug3.addEdgesW(5, 1, 2);
		ug3.addEdgesW(5, 3, 2);
		ug3.addEdgesW(6, 2, 1);

		System.out.println("Floyd");
		ug3.floyd();

//		UnGraph ug = new UnGraph(7);
//		ug.addEdges(1, 2, 8);
//		ug.addEdges(2, 3, 4);
//		ug.addEdges(2, 4, 1);
//		ug.addEdges(3, 6, 3);
//		ug.addEdges(4, 5, 5);
//		ug.addEdges(5, 6, 6);
//		ug.addEdges(5, 7, 3);
//		ug.addEdges(6, 7, 2);
//		System.out.println();
//		System.out.println("Duyet Dijsktra");
//		ug.Dijsktra(1, 7, ug);

	}
}
