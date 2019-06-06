package Tuan_1;

import java.util.ArrayList;
import java.util.Stack;

public class DirGraph extends Graph {
	public DirGraph(int v) {
		super(v);
	}

	@Override
	public void addEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src < numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 1;// canh cua do thi co huong la 1 chieu 
		}
	}

	@Override
	public void delEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src < numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 0;
		}
	}

	@Override
	public int degreeVex(int anyVex) {
		int sum = tongNuaBacNgoaiMotDinh(anyVex) + tongNuaBacTrongMotDinh(anyVex);
		return sum;
	}

	public int tongNuaBacNgoaiMotDinh(int anyVex) {
		int sum = 0;
		if (anyVex >= 0 && anyVex < numVex) {
			for (int i = 0; i < adj.length; i++) {
				sum += adj[anyVex][i];
			}
		}
		return sum;
	}

	public int tongNuaBacTrongMotDinh(int anyVex) {
		int sum = 0;
		if (anyVex >= 0 && anyVex < numVex) {
			for (int i = 0; i < adj.length; i++) {

				sum += adj[i][anyVex];
			}
		}
		return sum;
	}

	public int tongNuaBacTrongDoThi() {
		int sum = 0;

		for (int i = 0; i < adj.length; i++) {

			sum += tongNuaBacTrongMotDinh(i);
		}
		return sum;
	}

	public int tongNuaBacNgoaiDoThi() {
		int sum = 0;
		for (int i = 0; i < adj.length; i++) {
			sum += tongNuaBacNgoaiMotDinh(i);
		}
		return sum;
	}

	@Override
	public int degreeGraph() {
		int sum = 0;
		for (int i = 0; i < adj.length; i++) {
			System.out.println("Bac cua dinh " + i + " la: " + degreeVex(i));
			sum += degreeVex(i);
		}
		System.out.println("Bac cua tat ca cac dinh " + sum);
		return sum;

	}

	@Override
// So cung cua do thi co huong = tong bac ngoai = tong bac trong
	public int numEdgesGraph() {
		int sum = 0;

		for (int i = 0; i < adj.length; i++) {

			sum += tongNuaBacNgoaiMotDinh(i);
		}
		System.out.println("So cung cua do thi la: " + sum);
		return sum;

	}

	@Override
	public void printGraph() {
		System.out.println("Ma tran cua do thi\n");
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].length; j++) {
				System.out.print(adj[i][j] + "    ");
			}
			System.out.println();
		}
	}

	@Override
	public boolean isBipartiteGraph() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSubGraph(Graph g1, Graph g2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void re_DFS(int startVex) {
		int V = startVex - 1;
		visited[V] = true;
		System.out.print((V + 1) + "\t");
		for (int i = 0; i < numVex; i++) {
			if (adj[V][i] != 0 && visited[i] == false) {
				re_DFS(i + 1);
			}
		}

	}

// Chuyen doi ma tran co huong ve ma tran vo huong
// ban xem tai day de hieu ro hon: https://youtu.be/s2fQhCNstWY
	public void chuyenDoiMaTran(DirGraph g) {
		int[][] test = g.adj;
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test.length; j++) {
				if (test[i][j] != 0) {
					this.adj[i][j] = test[i][j];
					this.adj[j][i] = test[j][i];

				}
			}
		}
	}

	// Kiem tra do thi lien thong yeu
	// ban xem tai day de hieu ro hon : https://youtu.be/s2fQhCNstWY
	public boolean ktDoThiLienThongYeu() {
		chuyenDoiMaTran(this);
		int startVex=1;
		int V = startVex - 1;
		visited[V] = true;
		for (int i = 0; i < numVex; i++) {
			if (adj[V][i] != 0 && visited[i] == false) {
				re_DFS(i + 1);
			}
		}
		// check
		for (int i = 0; i < numVex; i++) {
			if (visited[i] == false)
				return false;
		}
		return true;

	}

	// Kiem tra do thi lien thong manh
	public boolean ktDoThiLienThongManh() {
		boolean right = true;
		int startVex=1;
		int V = startVex - 1;
		visited[V] = true;
		for (int i = 0; i < numVex; i++) {
			if (adj[V][i] != 0 && visited[i] == false) {
				re_DFS(i + 1);
			}
		}
		for (int k = 0; k < visited.length; k++) {
			if (tongNuaBacNgoaiMotDinh(k) == 0 || tongNuaBacTrongMotDinh(k) == 0)
				right = false;
			if (visited[k] != true)
				return false;

		}
		if (right) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean isConnection() {
		if (ktDoThiLienThongManh() || ktDoThiLienThongYeu()) {
			return true;
		}

		return false;
	}

	@Override// ban xem tai day de hieu ro hon : https://youtu.be/iGSbkXioOb8
	public int countConnection() {
		int demLienThong = 1;
		int i = 0;
		int numberPoint = adj.length;
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> listVisit = new ArrayList<>();
		// dau tien la gan setTemp bang tat ca cac dinh co trong do thi
		ArrayList<Integer> setTmp = new ArrayList<>();
		for (int j = 0; j < adj.length; j++) {
			setTmp.add(j);
		}
		int[] visit = new int[numberPoint];
		visit[i] = 1;
		listVisit.add(i);
		stack.push(i);
		setTmp.remove(i);
		while (!stack.empty()) {
			int count = 0;
			i = stack.peek();
			for (int j = 0; j < visit.length; j++) {
				if (this.adj[i][j] > 0 && visit[j] != 1) {
					listVisit.add(j);
					visit[j] = 1;
					stack.push(j);
					// neu phan tu do da duyet roi thi xoa di
					for (int j2 = 0; j2 < setTmp.size(); j2++) {
						if (setTmp.get(j2) == j) {
							setTmp.remove(j2);
						}
					}
					break;
				} else {
					count++;
				}
			}
			if (count == visit.length) {
				// neu phan tu duyet xong roi thi break
				if (!setTmp.isEmpty()) {
					// chua thi lay phan tu dau tien nhat ra duyet tiep
					i = setTmp.get(0);
					if (i < adj.length) {
						demLienThong++;
						stack.removeAll(stack);
						// thuc hien push de stack khong rong
						stack.push(i);
						// remove lo trinh cu de ghi vao lo trinh moi
						listVisit.removeAll(listVisit);
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}
		return demLienThong;
	}

	@Override
	// ban xem tai day de hieu ro hon : https://youtu.be/iGSbkXioOb8
	public void findConnection() {
		int demLienThong = 1;
		int i = 0;
		int numberPoint = adj.length;
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> listVisit = new ArrayList<>();
		// dau tien la gan setTemp bang tat ca cac dinh co trong do thi
		ArrayList<Integer> setTmp = new ArrayList<>();
		for (int j = 0; j < adj.length; j++) {
			setTmp.add(j);
		}
		int[] visit = new int[numberPoint];
		visit[i] = 1;
		listVisit.add(i);
		stack.push(i);
		setTmp.remove(i);
		while (!stack.empty()) {
			int count = 0;
			i = stack.peek();
			for (int j = 0; j < visit.length; j++) {
				if (this.adj[i][j] > 0 && visit[j] != 1) {
					listVisit.add(j);
					visit[j] = 1;
					stack.push(j);
					// neu phan tu do da duyet roi thi xoa di
					for (int j2 = 0; j2 < setTmp.size(); j2++) {
						if (setTmp.get(j2) == j) {
							setTmp.remove(j2);
						}
					}
					break;
				} else {
					count++;
				}
			}
			if (count == visit.length) {
				// neu khong con duong di
				System.out.println("So thanh phan lien thong : " + demLienThong);
				// xuat ra lo trinh
				print(listVisit);
				// neu phan tu duyet xong roi thi break
				if (!setTmp.isEmpty()) {
					// chua thi lay phan tu dau tien nhat ra duyet tiep
					i = setTmp.get(0);
					if (i < adj.length) {
						demLienThong++;
						stack.removeAll(stack);
						// thuc hien push de stack khong rong
						stack.push(i);
						// remove lo trinh cu de ghi vao lo trinh moi
						listVisit.removeAll(listVisit);
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}

	}

	private void print(ArrayList<Integer> listVisit) {
		String string = "";
		String temp = " ==> ";
		for (int k = 0; k < listVisit.size(); k++) {
			string += listVisit.get(k)+1;
			if (k < listVisit.size() - 1) {
				string += temp;
			}
		}
		System.out.println(string);
		
	}
}
