package Tuan_11;

import java.util.ArrayList;

public abstract class Graph {
	public int numVex;
	public int[][] adj;
	public int[] path;
	public boolean[] visited;
	protected ArrayList<Integer> listVisit = new ArrayList<>();
	protected ArrayList<Integer> vexVisited = new ArrayList<>();
	public int[][] matrixEdge;

	public Graph(int v) {
		this.numVex = v;
		this.adj = new int[numVex][numVex];
		visited = new boolean[numVex];
		path = new int[numVex];
		// matran trong so
		matrixEdge = new int[numVex][numVex];
	}

///////////////////========================Bai tap tuan 1==================================
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

///////////////////========================Bai tap tuan 2==================================

	// Kiem tra do thi co phai la do thi luong phan
	public abstract boolean isBipartiteGraph();

	// Kiem tra do thi co phai con cua do thi khac hay khong
	public abstract boolean isSubGraph(Graph g1, Graph g2);

	// Duyet DFS duyet theo chieu sau de quy
	public abstract void re_DFS(int start);

	// Kiem tra lien thong
	public abstract boolean isConnection();

	// Dem thanh phan lien thong
	public abstract int countConnection();

	// Tim thanh phan lien thong
	public abstract void findConnection();

///////////////////========================Bai tap tuan 3==================================

	// Duyet DFS = STACK
	public abstract void DFS(int start);

	// Duyet BFS = queue
	public abstract void BFS(int start);

	// Tim duong di dai nhat = dfs
	public abstract void findPathLong(int a, int b);

	// Tim duong di ngan nhat = bfs
	public abstract void findPathShortest(int a, int b);

///////////////////========================Bai tap tuan 4==================================

	// Check chu trinh euler
	public abstract boolean checkCycleEuler();

	// Check duong di Euler
	public abstract boolean checkPathEuler();

	// In ra chu trinh Euler
	public abstract void findCycleEuler(int start);

	// In ra duong di Euler
	public abstract void findPathEuler(int start);

///////////////////========================Bai tap tuan 5==================================

	// kiem tra chu trinh hamilton
	public abstract boolean checkCycleHamilton();

	// kiem tra duong di hamilton
	public abstract boolean checkPathHamilton();

	// In ra 1 chu trinh hamilton
	public abstract void findCycleHamilton();

///////////////////========================Bai tap tuan 6==================================

	// In ra 1 chu trinh hamilton
	public abstract void findAllCycleHamilton();

	public abstract void findAllCycleHamilton(int v);

	public abstract void findAllPathHamilton();

	public abstract void findAllPathHamilton(int v);

///////////////////========================Bai tap tuan 7==================================

	// Duyet cay DFS
	public abstract Graph DFSTree(int v);

	// Duyet cay BFS
	public abstract Graph BFSTree(int v);

///////////////////========================Bai tap tuan 8==================================

	// Them canh co trong so
	public abstract void addEdges(int src, int dest, int weight);

	// Kiem tra co tao chu trinh hay khong
	public abstract boolean hasCycle(int a, int b);

	// Duyet Kruskal
	public abstract Graph Kruskal();

	// =========================Bai tap tuan 9================================

	// Duyet prim
	public abstract Graph Prim(int v);

	// Duyet Floyd
	public abstract int[][] Floyd(int[][] a);

	// Duyet Dijsktra
	public abstract void Dijsktra(int v);

	// =========================Bai tap tuan 10================================

	public abstract void Dijsktra(int src, int des, Graph g);

	// Duyet floyd
	public abstract void floyd();

	
	
	
	// =========================Bai tap tuan 11================================
	
	// Duyet floyd
		public abstract void floyd(int src, int des, Graph g);
		
	//duyet bell manFord
		public abstract void bellman_Ford(int source);
		
	// duyet warshall
		
		public abstract int[][] warshall();

	// Lop canh
	protected class Edges {
		int src, des, weight;

		protected Edges(int a, int b) {
			super();
			this.src = a;
			this.des = b;
		}

		protected Edges(int a, int b, int c) {
			super();
			this.src = a;
			this.des = b;
			this.weight = c;
		}

		public int getSrc() {
			return src;
		}

		public void setSrc(int src) {
			this.src = src;
		}

		public int getDes() {
			return des;
		}

		public void setDes(int des) {
			this.des = des;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edges: " + "(" + src + "," + des + ")";
		}

	}
}