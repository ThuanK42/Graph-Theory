package Tuan_1;

public class TestUnGraph {
	public static void main(String[] args) {
		UnGraph ug = new UnGraph(6);
		ug.addEdges(1, 2);
		ug.addEdges(2, 3);
		ug.addEdges(2, 5);
		ug.addEdges(3, 5);
		ug.addEdges(3, 4);
		ug.addEdges(5, 6);

		System.out.println("Bac: " + ug.degreeVex(1));
		System.out.println("------------");
		ug.degreeGraph();
		System.out.println("------------");
		ug.numEdgesGraph();
		System.out.println("------------");
		ug.printGraph();

		System.out.println("------------");

		System.out.println("Sau khi xóa cạnh---------");

		ug.delEdges(1, 2);
		System.out.println("Bac: " + ug.degreeVex(1));
		System.out.println("------------");
		System.out.println(ug.degreeVex(3));
		System.out.println("------------");
		ug.degreeGraph();
		System.out.println("------------");
		ug.numEdgesGraph();
		System.out.println("------------");
		ug.printGraph();

		System.out.println("------------");

	}

}
