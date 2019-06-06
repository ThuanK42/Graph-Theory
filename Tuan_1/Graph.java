package Tuan_1;

import java.util.ArrayList;

public abstract class Graph {
	public int numVex;
	public int[][] adj;
	public boolean[] visited;
	protected ArrayList<Integer> listVisit = new ArrayList<>();
	protected ArrayList<Integer> vexVisited = new ArrayList<>();

	public Graph(int v) {
		this.numVex = v;
		this.adj = new int[numVex][numVex];
		visited = new boolean[numVex];
	}

	// Tuan 1
	// Them canh
	public abstract void addEdges(int src, int dest);

	// Xoa canh
	public abstract void delEdges(int src, int dest);

	// Bac cua 1 dinh
	public abstract int degreeVex(int anyVex);

	// Bac cua do thi
	public abstract int degreeGraph();

	// So canh
	public abstract int numEdgesGraph();

	// in ra do thi
	public abstract void printGraph();

	// Lop canh
	protected class Edges {
		int src, des;

		protected Edges(int a, int b) {
			super();
			this.src = a;
			this.des = b;
		}

		@Override
		public String toString() {
			return "Edges: " + "(" + src + "," + des + ")";
		}

	}
}