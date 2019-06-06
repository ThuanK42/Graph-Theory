package Tuan_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UnGraph extends Graph {
	String result;
	String s;

	public UnGraph(int v) {
		super(v);
	}
// Bai tap tuan 1
	@Override
	// Them canh
	public void addEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src < numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 1;
			adj[dest - 1][src - 1] = 1;
		}
	}

	@Override
	// Xoa canh
	public void delEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src < numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 0;
			adj[dest - 1][src - 1] = 0;
		}
	}

	@Override
	// Bac 1 dinh bat ky = tong gia tri cot tai dinh dua vao
	public int degreeVex(int anyVex) {
		int sum = 0;
		if (anyVex > -1 && anyVex < adj.length) {
			for (int i = 0; i < adj[anyVex].length; i++) {
				sum += adj[i][anyVex];
			}
			return sum;
		} else {
			return -1;
		}
	}

	@Override
// bac cua do thi = bac cua tat ca cac dinh
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
	// So canh cua 1 do thi : 2xcanh=tong bac cua do thi
	public int numEdgesGraph() {
		int sum = 0;
		for (int i = 0; i < adj.length; i++) {
			sum += degreeVex(i);
		}
		System.out.println("So canh cua do thi: " + sum / 2);
		return sum / 2;
	}

	@Override
	// In do thi bang ma tran ke
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
	// Kiem tra do thi co luong phan hay khong
	public boolean isBipartiteGraph() {
		int[] color = new int[numVex];
		// -1 no color,1 color one, 0 color 2
		for (int i = 0; i < color.length; i++) {
			color[i] = -1;
		}

		// step 2 color
		for (int i = 0; i < color.length; i++) {
			for (int j = 0; j < color.length; j++) {
				if (adj[i][j] != 0 && color[j] == -1) {
					if (color[i] == 1)
						color[j] = 0;
					else
						color[j] = 1;
				}
			}
		}

		// step 3 check neu co 2 dinh ke nhau cung mau thi ko luong phan
		for (int i = 0; i < color.length; i++) {
			for (int j = 0; j < color.length; j++) {
				if (adj[i][j] != 0 && color[i] == color[j]) {
					return false;
				}
			}
		}
		return true;
	}

// Duyet do thi theo chieu sau dung de quy
	@Override
	public void re_DFS(int startVex) {
		int V = startVex - 1;// do do do thi co tram chay tu 1 
		visited[V] = true; // danh dau da tham
		System.out.print((V + 1) + "\t");// in ra duong di duyet dfs
		// duyet tat ca cac dinh
		for (int i = 0; i < numVex; i++) {
			// neu dinh ke voi dinh input chua tham && dinh ke va dinh input tao canh - thi danh dau dinh do la true (de quy)
			if (adj[V][i] != 0 && visited[i] == false) {
				re_DFS(i + 1);
			}
		}

	}
//Phuong thuc nay tuong tu ben tren nhung co dieu ko in ra duong di
	public void re_DFS2(int startVex) {
		int V = startVex - 1;
		visited[V] = true;
		for (int i = 0; i < numVex; i++) {
			if (adj[V][i] != 0 && visited[i] == false) {
				re_DFS(i + 1);
			}
		}
	}

	@Override
	// Kiem tra tinh lien thong cua do thi
	public boolean isConnection() {
		re_DFS2(1); // truyen 1 de duyet toan bo dinh co trong do thi thoa dk
		// check neu co dinh nao chua tham - thi do thi ko lien thong
		// dinh do ko tao canh - dinh co lap
		for (int i = 0; i < numVex; i++) {
			if (visited[i] == false)
				return false;
		}
		return true;
	}

	@Override
	// Dem so thanh phan lien thong
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
	// Tim cac thanh phan lien thong cua do thi
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

	@Override
	public boolean isSubGraph(Graph g1, Graph g2) {
		ArrayList<Edges> listSub = new ArrayList<>();
		ArrayList<Edges> listSup = new ArrayList<>();
		int count = 0;
		// Kiem tra dinh : dinh con <= dinh cha
		if (g1.numVex <= g2.numVex) {
			for (int i = 0; i < g1.numVex; i++) {
				for (int j = 0; j < g1.numVex; j++) {
					if (g1.adj[i][j] != 0) {
						listSub.add(new Edges(i, j));
					}
				}
			}
		}
		// Them canh la listSup
		for (int i = 0; i < g2.numVex; i++) {
			for (int j = 0; j < g2.numVex; j++) {
				if (g2.adj[i][j] != 0) {
					listSup.add(new Edges(i, j));
				}
			}
		}
// Do thi con co chua trong do thi cha hay ko
		for (int i = 0; i < listSub.size(); i++) {
			for (int j = 0; j < listSup.size(); j++) {
				if (listSub.get(i).toString().equalsIgnoreCase(listSup.get(j).toString())) {
					count++;
				}
			}
		}
		if (count == listSub.size()) {
			return true;
		}
		return false;
	}

	@Override
	// Stack LIFO - vao sau ra truoc 
	public void DFS(int start) {
		System.out.println("Duyet DFS tu dinh: " + (start + 1));
		// stack de phong ngua truong hop quay lui ;
		Stack<Integer> stack = new Stack<>();
		// list de luu danh sach duong di da qua
		ArrayList<Integer> listVisit = new ArrayList<>();
		// arr danh dau dinh da qua
		boolean[] visit = new boolean[numVex];
		// dau tien add dinh da cho vao
		visit[start] = true;
		listVisit.add(start);
		// Push bổ sung một phần tử vào đỉnh (top) của ngăn xếp,
		// nghĩa là sau các phần tử đã có trong ngăn xếp.
		// Pop tra ve gia trinh dinh ngan xep va xoa dinh do lun
		stack.push(start);
		// dừng khi stack rỗng
		while (!stack.empty()) {
			int count = 0;//so dinh da duyet
			// peek kiểm tra xem đỉnh của danh sách có rỗng không
			// nếu rỗng thì trả về null còn không trả về giá trị của đỉnh danh sách.
			start = stack.peek();
			// duyet va kiem tra
			for (int j = 0; j < visit.length; j++) {
				if (this.adj[start][j] > 0 && visit[j] == false) {// neu tao input tao canh voi dinh ke && dinh ke chua tham
					listVisit.add(j);// them dinh ke vao list da tham
					visit[j] = true;// danh dau dinh do da tham
					stack.push(j);// them vao stack
					// neu them roi thi dung lai theo quy tac cuon
					break;
				} else { //nguoc lai thi tang so dinh da tham len
					count++;
				}
			}
			if (count == visit.length) {// toan bo dinh da tham thi xoa het stack de dung while
				stack.pop();
			}
		}
		print(listVisit);
	}

	private void print(ArrayList<Integer> listVisit) {
		String string = "";
		String temp = " ==> ";
		for (int k = 0; k < listVisit.size(); k++) {
			string += listVisit.get(k) + 1;
			if (k < listVisit.size() - 1) {
				string += temp;
			}
		}
		System.out.println(string);

	}

	@Override// tuong tu stack chi co dieu cach chay cua no la vao truoc ra truoc
	public void BFS(int start) {
		System.out.println("Duyet BFS tu dinh: " + (start + 1));
		Queue<Integer> queue = new LinkedList<>();//dung de duyet
		boolean[] visited = new boolean[numVex];// mang danh dau dinh tham
		ArrayList<Integer> list = new ArrayList<>();// mang in duong di
		visited[start] = true;//danh dau input da tham
		list.add(start);// them dinh do la list duong di
		queue.add(start);// add vao queue
		while (!queue.isEmpty()) {
			start = queue.remove();//lay thang moi add ra de xet va xoa no lun
			for (int i = 0; i < visited.length; i++) {// chay toan bo dinh
				if (this.adj[start][i] > 0 && visited[i] == false) {// neu no tao canh voi dinh ke && dinh ke chua tham
					list.add(i);
					visited[i] = true;// danh dau da tham
					queue.add(i);// add vao queue
				}
			}
		}
		print(list);
	}

	@Override
	// Duyet theo chieu sau tim duong dai nhat
	public void findPathLong(int src, int dest) {
		System.out.println("Duong di dai nhat tu dinh: " + (src + 1) + " den: " + (dest + 1) + " la: ");

		// stack de phong ngua truong hop quay lui ;
		Stack<Integer> stack = new Stack<>();
		// list de luu danh sach duong di da qua
		ArrayList<Integer> listVisit = new ArrayList<>();
		// arr danh dau dinh da qua
		int[] visit = new int[numVex];
		// dau tien add dinh da cho vao
		visit[src] = 1;
		listVisit.add(src);
		// Push bổ sung một phần tử vào đỉnh (top) của ngăn xếp,
		// nghĩa là sau các phần tử đã có trong ngăn xếp.
		// Pop giải phóng và trả về phần tử đang đứng ở đỉnh của ngăn xếp
		stack.push(src);
		// dừng khi stack rỗng
		while (!stack.empty()) {
			// bien count de kiem tra khi nao can lay phan tu ra khoi stack
			int count = 0;
			src = stack.peek();// lay ra ma khong xoa

			if (src == dest) {// neu thang lay ra do trung diem dich dung vong lap
				break;
			}
			// duyet va kiem tra
			for (int j = 0; j < visit.length; j++) {
				if (this.adj[src][j] > 0 && visit[j] == 0) {
					// neu j chưa thăm thi them vao list
					listVisit.add(j);
					visit[j] = 1;
					stack.push(j);
					// neu them roi thi dung lai theo quy tac cuon
					break;
				} else {
					count++;
				}
			}
			if (count == visit.length) {
				stack.pop();
			}
		}
		print(listVisit);

	}

	@Override// code tuong tu nhung chi khac nhau cac duyet 
	public void findPathShortest(int src, int dest) {
		System.out.println("Duong di ngan nhat tu dinh: " + (src + 1) + " den: " + (dest + 1) + " la: ");
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[numVex];
		ArrayList<Integer> list = new ArrayList<>();
		visited[src] = true;
		list.add(src);
		queue.add(src);
		while (!queue.isEmpty()) {
			src = queue.remove();
			if (src == dest)
				break;
			for (int i = 0; i < visited.length; i++) {
				if (this.adj[src][i] > 0 && visited[i] == false) {
					list.add(i);
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		print(list);
	}
}
