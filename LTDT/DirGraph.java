package Tuan_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DirGraph extends Graph {
	public DirGraph(int v) {
		super(v);
	}

//=========================Bai tap tuan 1================================
	@Override
	public void addEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src <= numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 1;
		}
	}
	// Them canh co trong so

	public void addEdges(int src, int dest, int weight) {
		if (src >= 0 && dest >= 0 && src <= numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = weight;

		}
	}

	@Override
	public void delEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src <= numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 0;
		}
	}

	@Override
	public int degreeVex(int anyVex) {
		int sum = tongNuaBacNgoaiMotDinh(anyVex) + tongNuaBacTrongMotDinh(anyVex);
		return sum;
	}

	public int tongNuaBacNgoaiMotDinh(int anyVex) { // tong hang
		int sum = 0;
		if (anyVex >= 0 && anyVex < numVex) {
			for (int i = 0; i < numVex; i++) {
				sum += adj[anyVex][i];
			}
		}
		return sum;
	}

	public int tongNuaBacTrongMotDinh(int anyVex) { // tong cot
		int sum = 0;
		if (anyVex >= 0 && anyVex < numVex) {
			for (int i = 0; i < numVex; i++) {
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

	// ========================================Bai tap tuan
	// 2================================

	@Override
	public boolean isBipartiteGraph() {
		chuyenDoiMaTran(this);
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
	public void re_DFS(int startVex) {
		int V = startVex - 1;
		visited[V] = true;
		// System.out.print((V + 1) + "\t");
		for (int i = 0; i < numVex; i++) {
			if (adj[V][i] != 0 && visited[i] == false) {
				re_DFS(i + 1);
			}
		}

	}

// Chuyen doi ma tran co huong ve ma tran vo huong
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
	public boolean ktDoThiLienThongYeu() {
		chuyenDoiMaTran(this);
		re_DFS(1);
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
		for (int k = 0; k < visited.length; k++) {
			if (tongNuaBacNgoaiMotDinh(k) == 0 || tongNuaBacTrongMotDinh(k) == 0) {
				right = false;
			}
		}
		if (isConnection() && right) {
			return true;
		} else {
			return false;
		}
	}

	// Kiem tra lien thong
	@Override
	public boolean isConnection() {
		int i = 0;
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> listVisit = new ArrayList<>();
		visited[i] = true;
		listVisit.add(i);
		stack.push(i);
		while (!stack.empty()) {
			int count = 0;
			i = stack.peek();
			for (int j = 0; j < visited.length; j++) {
				if (this.adj[i][j] > 0 && visited[j] == false) {
					listVisit.add(j);
					visited[j] = true;
					stack.push(j);
					break;
				} else {
					count++;
				}
			}
			if (count == visited.length) {
				stack.pop();
			}
		}
		for (int j = 0; j < visited.length; j++) {
			if (visited[j] != true)
				return false;
		}
		return true;
	}

//Dem thanh phan lien thong
	@Override
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

//Tim thanh phan lien thong
	@Override
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
			string += listVisit.get(k) + 1;
			if (k < listVisit.size() - 1) {
				string += temp;
			}
		}
		System.out.println(string);
	}

	// ==================Bai tap tuan3====================

//Duyet DFS
	@Override
	public void DFS(int start) {
		System.out.println("Duyet DFS tu dinh: " + (start + 1));
		// stack de phong ngua truong hop quay lui ;
		Stack<Integer> stack = new Stack<>();
		// list de luu danh sach duong di da qua
		ArrayList<Integer> listVisit = new ArrayList<>();
		// arr danh dau dinh da qua
		int[] visit = new int[numVex];
		// dau tien add dinh da cho vao
		visit[start] = 1;
		listVisit.add(start);
		// Push bÃ¡Â»â€¢ sung mÃ¡Â»â„¢t phÃ¡ÂºÂ§n tÃ¡Â»Â­ vÃƒÂ o Ã„â€˜Ã¡Â»â€°nh (top)
		// cÃ¡Â»Â§a ngÃ„Æ’n xÃ¡ÂºÂ¿p,
		// nghÃ„Â©a lÃƒÂ  sau cÃƒÂ¡c phÃ¡ÂºÂ§n tÃ¡Â»Â­ Ã„â€˜ÃƒÂ£ cÃƒÂ³ trong ngÃ„Æ’n
		// xÃ¡ÂºÂ¿p.
		// Pop giÃ¡ÂºÂ£i phÃƒÂ³ng vÃƒÂ  trÃ¡ÂºÂ£ vÃ¡Â»? phÃ¡ÂºÂ§n tÃ¡Â»Â­ Ã„â€˜ang
		// Ã„â€˜Ã¡Â»Â©ng Ã¡Â»Å¸ Ã„â€˜Ã¡Â»â€°nh cÃ¡Â»Â§a
		// ngÃ„Æ’n xÃ¡ÂºÂ¿p
		stack.push(start);
		// dÃ¡Â»Â«ng khi stack rÃ¡Â»â€”ng
		while (!stack.empty()) {
			// bien count de kiem tra khi nao can lay phan tu ra khoi stack
			int count = 0;
			// peek kiÃ¡Â»Æ’m tra xem Ã„â€˜Ã¡Â»â€°nh cÃ¡Â»Â§a danh sÃƒÂ¡ch cÃƒÂ³ rÃ¡Â»â€”ng
			// khÃƒÂ´ng
			// nÃ¡ÂºÂ¿u rÃ¡Â»â€”ng thÃƒÂ¬ trÃ¡ÂºÂ£ vÃ¡Â»? null cÃƒÂ²n khÃƒÂ´ng trÃ¡ÂºÂ£
			// vÃ¡Â»? giÃƒÂ¡ trÃ¡Â»â€¹ cÃ¡Â»Â§a
			// Ã„â€˜Ã¡Â»â€°nh danh sÃƒÂ¡ch.
			start = stack.peek();
			// duyet va kiem tra
			for (int j = 0; j < visit.length; j++) {
				if (this.adj[start][j] != 0 && visit[j] == 0) {
					// neu j chÃ†Â°a thÃ„Æ’m thi them vao list
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

// Duyet BFS
	@Override
	public void BFS(int start) {
		System.out.println("Duyet BFS tu dinh: " + (start + 1));
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[numVex];
		ArrayList<Integer> list = new ArrayList<>();
		visited[start] = true;
		list.add(start);
		queue.add(start);

		while (!queue.isEmpty()) {
			start = queue.remove();
			for (int i = 0; i < visited.length; i++) {
				if (this.adj[start][i] != 0 && visited[i] == false) {
					list.add(i);
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		print(list);
	}

	@Override
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
		// Push bÃ¡Â»â€¢ sung mÃ¡Â»â„¢t phÃ¡ÂºÂ§n tÃ¡Â»Â­ vÃƒÂ o Ã„â€˜Ã¡Â»â€°nh (top)
		// cÃ¡Â»Â§a ngÃ„Æ’n xÃ¡ÂºÂ¿p,
		// nghÃ„Â©a lÃƒÂ  sau cÃƒÂ¡c phÃ¡ÂºÂ§n tÃ¡Â»Â­ Ã„â€˜ÃƒÂ£ cÃƒÂ³ trong ngÃ„Æ’n
		// xÃ¡ÂºÂ¿p.
		// Pop giÃ¡ÂºÂ£i phÃƒÂ³ng vÃƒÂ  trÃ¡ÂºÂ£ vÃ¡Â»? phÃ¡ÂºÂ§n tÃ¡Â»Â­ Ã„â€˜ang
		// Ã„â€˜Ã¡Â»Â©ng Ã¡Â»Å¸ Ã„â€˜Ã¡Â»â€°nh cÃ¡Â»Â§a
		// ngÃ„Æ’n xÃ¡ÂºÂ¿p
		stack.push(src);
		// dÃ¡Â»Â«ng khi stack rÃ¡Â»â€”ng
		while (!stack.empty()) {
			// bien count de kiem tra khi nao can lay phan tu ra khoi stack
			int count = 0;
			// peek kiÃ¡Â»Æ’m tra xem Ã„â€˜Ã¡Â»â€°nh cÃ¡Â»Â§a danh sÃƒÂ¡ch cÃƒÂ³ rÃ¡Â»â€”ng
			// khÃƒÂ´ng
			// nÃ¡ÂºÂ¿u rÃ¡Â»â€”ng thÃƒÂ¬ trÃ¡ÂºÂ£ vÃ¡Â»? null cÃƒÂ²n khÃƒÂ´ng trÃ¡ÂºÂ£
			// vÃ¡Â»? giÃƒÂ¡ trÃ¡Â»â€¹ cÃ¡Â»Â§a
			// Ã„â€˜Ã¡Â»â€°nh danh sÃƒÂ¡ch.
			src = stack.peek();
			if (src == dest) {
				break;
			}
			// duyet va kiem tra
			for (int j = 0; j < visit.length; j++) {
				if (this.adj[src][j] != 0 && visit[j] == 0) {
					// neu j chÃ†Â°a thÃ„Æ’m thi them vao list
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

// Tim duong di ngan nhat
	@Override
	public void findPathShortest(int src, int dest) {
		System.out.println("Duong di ngan nhat tu dinh: " + (src + 1) + " den: " + (dest + 1) + " la: ");
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>();
		visited[src] = true;
		list.add(src);
		queue.add(src);
		while (!queue.isEmpty()) {
			src = queue.poll(); // Ã¡Â»Å¸ Ã„â€˜ÃƒÂ¢y, lÃ¡ÂºÂ¥y tÃ¡Â»Â« Ã„â€˜Ã¡ÂºÂ±ng sau
			for (int i = 0; i < numVex; i++) {
				if (this.adj[src][i] != 0 && visited[i] == false) {

					list.add(i);
					visited[i] = true;
					queue.add(i);
					if (i == dest) {
						queue.clear(); // xÃƒÂ³a hÃ¡ÂºÂ¿t bÃ¡ÂºÂ¯t dÃƒÂ²ng while dÃ¡Â»Â«ng
						break;
					}
				}
			}
		}
		print(list);
	}

	// ====================Bai tap tuan4=================

// Kiem tra chu trinh Euler
	@Override
	public boolean checkCycleEuler() {
		int count = 0;
		for (int i = 0; i < numVex; i++) {
			if (tongNuaBacNgoaiMotDinh(i) == tongNuaBacTrongMotDinh(i))
				count++;
		}
		if (ktDoThiLienThongYeu() == true && numVex > 1 && count == numVex)
			return true;
		return false;
	}

// Kiem tra duong di Euler
	@Override
	public boolean checkPathEuler() {
		int count = 0;
		int canBang = 0;
		for (int i = 0; i < numVex; i++) {
			// 2 dinh thoa dieu kien
			if (tongNuaBacNgoaiMotDinh(i) == (tongNuaBacTrongMotDinh(i) + 1)
					|| tongNuaBacTrongMotDinh(i) == (tongNuaBacNgoaiMotDinh(i) + 1)) {
				count++;
			}
			// cac dinh con lai can bang
			if (tongNuaBacTrongMotDinh(i) == tongNuaBacNgoaiMotDinh(i)) {
				canBang++;
			}
		}
		if (ktDoThiLienThongYeu() == true & count == 2 && canBang == numVex - 2) {
			return true;
		}
		return false;
	}

// Tim chu trinh Euler
	@Override
	public void findCycleEuler(int start) {
		if (checkCycleEuler() == false) {
			System.out.println("Khong co chu trinh euler");
		} else {
			System.out.println("Chu trinh Euler la: ");
			Stack<Integer> stack = new Stack<Integer>();
			ArrayList<Integer> list = new ArrayList<Integer>(); // luu dinh da duyet
			stack.push(start);
			list.add(start);
			while (!stack.isEmpty()) {
				int x = stack.pop(); // lay ra

				for (int i = 0; i < numVex; i++) {
					if (adj[x][i] != 0) { // Co the lap lai dinh nhung khong lap lai canh
						stack.push(i);
						list.add(i);
						adj[x][i] = 0;
						x = i;
						i = 0;

					}
					if (i == numVex && !stack.isEmpty()) { // chay het cac canh
						stack.pop();
					}
				}

			}
			print(list);
		}
	}

// Tim duong di Euler
	@Override
	public void findPathEuler(int start) {
		ArrayList<Integer> listHasPathEuler = new ArrayList<>();
		if (checkPathEuler() == false) {
			System.out.println("Khong co duong di Euler");
		} else {
			if (degreeVex(start) % 2 == 0) {
				for (int i = 0; i < numVex; i++) {
					if (degreeVex(i) % 2 != 0) {
						listHasPathEuler.add(i);
					}
				}
				System.out.println("Khong co duong di tu dinh " + start);
				System.out.println("Danh sach dinh co duong di: " + listHasPathEuler.toString());
			} else {
				Stack<Integer> stack = new Stack<Integer>();
				ArrayList<Integer> list = new ArrayList<Integer>(); // luu dinh da duyet
				stack.push(start);
				list.add(start);
				while (!stack.isEmpty()) {
					int x = stack.pop(); // lay ra
					for (int j = 0; j < numVex; j++) {
						if (adj[x][j] != 0) { // Co the lap lai dinh nhung khong lap lai canh
							stack.push(j);
							list.add(j);
							adj[x][j] = 0;
							x = j;
							j = 0;
							break;
						}
						if (j == numVex && !stack.isEmpty()) { // chay het cac canh
							stack.pop();
						}
					}
				}
				print(list);
			}

		}

	}

	// ========================================Bai tap tuan
	// 5================================

	// kiem tra chu trinh hamilton
	@Override
	/*
	 * MÃ¡Â»?i Ã„â€˜Ã¡Â»â€œ thÃ¡Â»â€¹ cÃƒÂ³ hÃ†Â°Ã¡Â»â€ºng, cÃƒÂ³ n Ã„â€˜Ã¡Â»â€°nh,
	 * liÃƒÂªn thÃƒÂ´ng mÃ¡ÂºÂ¡nh. NÃ¡ÂºÂ¿u mÃ¡Â»â€”i Ã„â€˜Ã¡Â»â€°nh v thuÃ¡Â»â„¢c
	 * Ã„â€˜Ã¡Â»â€œ thÃ¡Â»â€¹ thÃ¡Â»?a: deg-(v)Ã¢â€°Â¥n/2 vÃƒÂ  deg+(v)Ã¢â€°Â¥n/2
	 */
	public boolean checkCycleHamilton() {
		int count = 0;
		if (ktDoThiLienThongManh()) {
			return true;
		}
		for (int i = 0; i < numVex; i++) {

			if (tongNuaBacNgoaiMotDinh(i) >= (numVex / 2) && tongNuaBacTrongMotDinh(i) >= (numVex / 2)) {
				count++;
			}
		}
		if (count == numVex) {
			return true;
		}

		return false;
	}

//	List<Integer> listVisitHamiltonian = new ArrayList<>();
//
//	public boolean checkPathHamilton() {
//		int count = 0;
//		String label[] = new String[numVex];
//		int n = numVex;
//		for (int i = 0; i < n; i++)
//			label[i] = "NOT_IN_STACK";
//		for (int i = 0; i < n; i++) {
//			label[i] = "IN_STACK";
//			if (dfs(i, label, 1)) {
//				listVisitHamiltonian.add(i);
//				for (int k = listVisitHamiltonian.size() - 1; k >= 0; k--)
//					listVisitHamiltonian.removeAll(listVisitHamiltonian);
//				for (int j = 0; j < n; j++)
//					label[j] = "NOT_IN_STACK";
//				count++;
//			}
//			label[i] = "NOT_IN_STACK";
//		}
//		return (count > 0) ? true : false;
//	}

//	public boolean dfs(int v, String label[], int instack_count) {
//		int n = numVex;
//		if (instack_count == n)
//			return true;
//		for (int i = 0; i < n; i++)
//			if (this.adj[v][i] > 0 && label[i].equals("NOT_IN_STACK")) {
//				label[i] = "IN_STACK";
//				if (dfs(i, label, instack_count + 1)) {
//					listVisitHamiltonian.add(i);
//					return true;
//				}
//				label[i] = "NOT_IN_STACK";
//			}
//		return false;
//	}

	// In ra 1 chu trinh hamilton

	int[] hc = new int[numVex];

	public void findCycleHamilton() {

		for (int i = 0; i < adj.length; i++)
			visited[i] = false;
		hc[0] = 0;
		visited[0] = true;
		Expand(1);

	}

	private void Expand(int i) {
		for (int j = 0; j < numVex; j++)
			if (!visited[j] && this.adj[hc[i - 1]][j] > 0) {
				hc[i] = j;
				if (i < numVex - 1) {
					visited[j] = true;
					Expand(i + 1);
					visited[j] = false;
				} else if (this.adj[hc[i]][0] > 0) {
					printHamiltonCycle(hc);
				}
			}

	}

	private void printHamiltonCycle(int[] hc2) {
		System.out.println("Chu trinh Hamilton: ");
		String string = "";
		String temp = " ==> ";
		for (int k = 0; k < hc2.length; k++) {
			string += hc2[k] + 1;
			if (k < hc2.length - 1) {
				string += temp;
			}
		}
		System.out.println(string + temp + (hc2[0] + 1));

	}

	@Override

	public boolean checkPathHamilton() {
		// TODO Auto-generated method stub
		return false;
	}

///////////////////========================Bai tap tuan 6==================================

	@Override
	// chu trinh hamilton
	public void findAllCycleHamilton() {
		for (int i = 0; i < numVex; i++) {
			visited[i] = false;
		}
		path[0] = 0;
		visited[0] = true;
		Expand2(1);
	}

	private void Expand2(int i) {
		for (int j = 0; j < numVex; j++) {
			if (adj[path[i - 1]][j] != 0 && visited[j] == false) {
				path[i] = j;
				if (i < numVex - 1) {
					visited[j] = true;
					Expand2(i + 1);
					visited[j] = false;
				} else if (i == numVex - 1) {
					if (adj[path[i]][path[0]] != 0) {
						printCycle(path);
					}
				}
			}
		}

	}

	private void printCycle(int[] path) {
		String result = "";
		for (int i = 0; i < path.length; i++) {
			result += path[i] + 1 + "==>";
		}
		System.out.println(result + (path[0] + 1));

	}

	@Override
	// chu trinh hamilton tu 1 dinh
	public void findAllCycleHamilton(int v) {
		for (int i = 0; i < numVex; i++) {
			visited[i] = false;
		}
		int start = v - 1;
		path[0] = start;
		visited[start] = true;
		Expand2(1);
	}

	@Override
	// duong di hamilton
	public void findAllPathHamilton() {
		int minVex = minDegree();
		for (int i = 0; i < numVex; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < numVex; i++) {
			if (degreeVex(i) == minVex) {
				path[0] = i;
				visited[i] = true;
				expandPath(1);
			}
		}
	}

	public void expandPath(int i) {
		for (int j = 0; j < numVex; j++) {
			if (adj[path[i - 1]][j] != 0 && visited[j] == false) {
				path[i] = j;
				if (i < numVex - 1) {
					visited[j] = true;
					expandPath(i + 1);
					visited[j] = false;
				} else if (i == numVex - 1) {
					printPath(path);
				}
			}
		}
	}

	public void printPath(int[] path) {
		String result = "";
		for (int i = 0; i < path.length; i++) {
			result += path[i] + 1 + "==>";
		}
		String result1 = result.substring(0, result.length() - 3);
		System.out.println(result1);
	}

	private int minDegree() {
		int min = degreeVex(0);
		for (int i = 0; i < numVex; i++) {
			if (min > degreeVex(i)) {
				min = degreeVex(i);
			}
		}
		return min;
	}

	@Override
	// Duong di hamilton tu 1 dinh
	public void findAllPathHamilton(int k) {
		ArrayList<Integer> listMinVex = new ArrayList<>();
		int minVex = minDegree();
		for (int i = 0; i < numVex; i++) {
			if (degreeVex(i) == minVex) {
				listMinVex.add(i);
			}
		}
		if (degreeVex(k) == minVex) {
			for (int i = 0; i < numVex; i++) {
				visited[i] = false;
			}
			path[0] = k;
			visited[k] = true;
			expandPath(1);
		} else {
			k = listMinVex.get(0);
			path[0] = k;
			visited[k] = true;
			expandPath(1);
		}
	}

	// =====================================Bai tap tuan7
	// =====================================

	@Override
	public Graph DFSTree(int v) {
		Graph tree = new DirGraph(numVex);
		tree.adj = treeDFS(v);
		System.out.println("Duyet cay theo DFS:");
		return tree;
	}

	int[][] src = new int[numVex][numVex];

	private int[][] treeDFS(int v) {
		int s = v - 1;
		visited[s] = true;
		for (int i = 0; i < numVex; i++) {
			if (adj[s][i] != 0 && visited[i] == false) {
				visited[i] = true;
				src[s][i] = 1;
				treeDFS(i + 1);
			}
		}
		return src;
	}

	@Override
	public Graph BFSTree(int anyVex) {
		System.out.println("Dang duyet tu dinh " + (anyVex + 1));
		Graph tree = new DirGraph(numVex);
		tree.adj = treeBFS(anyVex);
		System.out.println("Duyet cay theo BFS:");
		return tree;
	}

	int b[][] = new int[numVex][numVex];

	public int[][] treeBFS(int anyVex) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(anyVex);
		visited[anyVex] = true;
		while (!queue.isEmpty()) {
			int v = queue.remove();
			for (int i = 0; i < adj.length; i++) {
				if (adj[v][i] != 0 && visited[i] == false) {
					b[v][i] = 1;
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		return b;
	}

/////////=====================Bai tap tuan 8=========================
	@Override
	public boolean hasCycle(int a, int b) {
		// TODO Auto-generated method stub
		visited = new boolean[numVex];

		for (int i = 0; i < numVex; i++) {
			visited[i] = false;
		}
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(a);
		while (!q.isEmpty()) {
			int v = q.remove();
			for (int i = 0; i < numVex; i++) {
				if (adj[v][i] != 0 && visited[i] == false) {
					visited[i] = true;
					q.add(i);
					if (i == b) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * Sap xep cac canh co trong so tang dan
	 */
	public List<Edges> sortWeight() {
		List<Edges> list = new ArrayList<Graph.Edges>();
		for (int i = 0; i < numVex; i++) {
			for (int j = 0; j < numVex; j++) {
				if (adj[i][j] != 0) {
					list.add(new Edges(i, j, adj[i][j]));
				}
			}
		}
		Collections.sort(list, new Comparator<Edges>() {

			@Override
			public int compare(Edges e1, Edges e2) {
				// TODO Auto-generated method stub
				return e1.weight - e2.weight;
			}
		});
		return list;

	}

	@Override
	public Graph Kruskal() {
		Graph tree = new DirGraph(numVex);
		int trongSo = 0;
		int numberofEdges = 0;
		while (numberofEdges < numVex - 1) {
			List<Edges> listEdgesSorted = sortWeight();
			for (Edges edges : listEdgesSorted) {
				if (tree.hasCycle(edges.src, edges.des) == false) {
					tree.addEdges(edges.src + 1, edges.des + 1, edges.weight);
					numberofEdges++;
					trongSo += edges.weight;
				}
			}
		}
		System.out.println("Duyet Kruskal: ");
		tree.printGraph();
		System.out.println("Trong so: " + trongSo);
		return tree;
	}

	// ========================Bai tap tuan 9==========================

	@Override
	public Graph Prim(int index) {
		Graph tree = new UnGraph(this.numVex);
		int trongSo = 0;
		int numberofEdges = 0;
		List<Edges> listEdges = new ArrayList<Graph.Edges>(); // chua canh chay tu dinh
		List<Integer> l = new ArrayList<Integer>();// chua dinh
		int v = index - 1;
		l.add(v);
		while (numberofEdges < numVex - 1) {
			for (int k = 0; k < l.size(); k++) {
				for (int i = 0; i < numVex; i++) {
					if (adj[l.get(k)][i] != 0 && !l.contains(i)) {
						listEdges.add(new Edges(l.get(k), i, adj[l.get(k)][i]));
					}
				}
			}
			Collections.sort(listEdges, new Comparator<Edges>() {

				@Override
				public int compare(Edges e1, Edges e2) {
					// TODO Auto-generated method stub
					return e1.weight - e2.weight;
				}
			});
			for (int i = 0; i < listEdges.size(); i++) {
				Edges edges = listEdges.get(0);
				if (tree.hasCycle(edges.src, edges.des) == false) {
					tree.addEdges(edges.src + 1, edges.des + 1, adj[edges.src][edges.des]);

					l.add(edges.des);
					trongSo += edges.weight;
					numberofEdges++;
					listEdges.clear();

				}
			}
		}

		System.out.println("Trong so: " + trongSo);
		return tree;
	}

	public void Dijsktra(int anyVex) {
		int[] P = new int[numVex];// dinh
		int[] L = new int[numVex];// do dai cua 1 vi tri dc cap nhat
		boolean[] R = new boolean[numVex];// danh dau dinh da di
		for (int i = 0; i < R.length; i++) {
			L[i] = Integer.MAX_VALUE;
			R[i] = false;
		}
		L[anyVex] = 0;
		P[anyVex] = -1;
		for (int i = 0; i < numVex; i++) {

			// tim dinh tiep theo de duyet
			int u = minDisTance(L, R);

			R[u] = true;
			for (int j = 0; j < numVex; j++) {
				if (R[j] == false && adj[u][j] != 0 && L[u] != Integer.MAX_VALUE && L[u] + adj[u][j] < L[j]) {
					P[j] = u;
					L[j] = L[u] + adj[u][j];
				}
			}
		}
		printPart(L, P, 0);
//			System.out.println("Do thi Dijsktra");
//			 printOneArray(P);
	}

	// in mang 1 chieu
//		public void printOneArray(int[] arr) {
//			String string = "";
//			String temp = " ==> ";
//			for (int k = 0; k < arr.length; k++) {
//				string += arr[k]+1;
//				if (k < arr.length - 1) {
//					string += temp;
//				}
//			}
//			System.out.println(string + temp );
//		}
	public void printPart(int[] L, int[] P, int startVex) {
		for (int i = 0; i < P.length; i++) {
			System.out.print("\n duong di ngan nhat tu ");
			int goVex = i;
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(goVex);
			while (startVex != goVex) {
				stack.push(P[goVex]);
				goVex = P[goVex];
			}
			System.out.print(stack.pop());
			while (!stack.isEmpty()) {
				System.out.print("--->" + stack.pop());
			}
			System.out.print("\n Do dai = " + L[i]);
		}
	}

	public int minDisTance(int L[], boolean R[]) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		for (int i = 0; i < numVex; i++) {
			if (R[i] == false && L[i] <= min) {
				min = L[i];
				min_index = i;
			}
		}
		return min_index;
	}

	@Override
	public int[][] Floyd(int[][] a) {
		int dist[][] = new int[numVex][numVex];
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				if (a[i][j] == 0) {
					dist[i][j] = 1000;
				} else {
					dist[i][j] = a[i][j];
				}
			}
		}

		for (int k = 0; k < dist.length; k++) {

			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist.length; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		printMatrixFloyd(dist);
		return dist;
	}

	public void printMatrixFloyd(int dist[][]) {
		System.out.println("ma tran Floyd");
		for (int i = 0; i < numVex; ++i) {
			for (int j = 0; j < numVex; ++j) {
				if (dist[i][j] >= 1000) {
					System.out.print("0   ");
				} else {
					System.out.print(dist[i][j] + "   ");
				}
			}
			System.out.println();
		}
	}

//==Bai tap tuan 10
	@Override
	public void floyd() {

		int[][] W = adj;
		for (int i = 0; i < numVex; i++) {
			for (int j = 0; j < numVex; j++) {
				if (adj[i][j] == 0) {
					W[i][j] = 9999;
				} else {
					W[i][j] = adj[i][j];
				}
			}
		}
		for (int k = 0; k < numVex; k++) {
			for (int i = 0; i < numVex; i++) {
				for (int j = 0; j < W.length; j++) {
					if (W[i][j] != 0 && W[i][j] < Integer.MAX_VALUE) {
						if (W[i][j] > W[i][k] + W[k][j]) {
							W[i][j] = W[i][k] + W[k][j];
						} else {
							W[i][j] = W[i][j];
						}
					}

				}
			}
			System.out.println("K = " + (k + 1));
			adj = W;
			printGraph();
		}

	}

	@Override
	public void Dijsktra(int src, int des, Graph g) {
		int v = src - 1;
		int u = des - 1;
		int[] P = new int[numVex];
		int[] L = new int[numVex];
		boolean[] R = new boolean[numVex];
		int numofVex = 0;
		for (int i = 0; i < numVex; i++) {
			L[i] = Integer.MAX_VALUE;
			P[i] = -1;
			R[i] = false;
		}
		L[v] = 0;
		P[v] = -1;
		while (numofVex < numVex - 1) {
			int index = 0;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < R.length; i++) {
				if (L[i] < min && !R[i]) {
					min = L[i];
					index = i;
				}
			}
			for (int i = 0; i < R.length; i++) {
				if (g.adj[index][i] != 0 && g.adj[index][i] < Integer.MAX_VALUE) {
					if (L[i] > L[index] + g.adj[index][i]) {
						L[i] = L[index] + g.adj[index][i];
						P[i] = index;
					}
				}
			}
			R[index] = true;
			numofVex++;
			if ((index + 1) == u) {
				printPath(P, L, v, u);
				break;
			}
		}

	}

	public void printPath(int[] path, int[] dis, int s, int d) {
		int dest = d;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(dest);
		while (d != s) {
			stack.push(path[d]);
			d = path[d];
		}
		System.out.println("\n Shortest Path from " + (stack.pop() + 1));
		while (!stack.isEmpty()) {
			System.out.println("---->" + (stack.pop() + 1));
		}
		System.out.println("\t Distance = " + dis[dest]);
	}

	// =========================Bai tap tuan 11================================
	@Override
	public void floyd(int src, int des, Graph g) {
		int dist[][] = new int[numVex][numVex];
		int[][] P = adj;
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				if (adj[i][j] == 0) {
					dist[i][j] = 9999;
					P[i][j] = 9999;
				} else {
					dist[i][j] = adj[i][j];
					P[i][j] = j;
				}
			}
		}

		for (int k = 0; k < dist.length; k++) {
			printMatrixFloyd(P);
			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist.length; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						P[i][j] = P[i][k];
					}
				}
			}
		}
		int copyA = src;
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(src);
		while (src != des) {
			src = P[src][des];
			array.add(src);
		}

		String st = "";
		for (Integer integer : array) {
			st += integer + "==>";
		}

		System.out.println("duong di tu " + st + " \n co do dai la = " + dist[copyA][des]);

	}

	@Override
	public void bellman_Ford(int source) {
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj.length; j++) {
				if (adj[i][j] == 0) {
					adj[i][j] = max;
				}
			}
		}

		// int[][] W = this.arr;
		int[] L = new int[this.numVex]; // L[v] Khoảng cách ngắn nhất từ s đến các đỉnh v
		int[] P = new int[this.numVex]; // chứa đỉnh liền trước của đỉnh đang xét, dùng để lưu đường đi

		// bước 1: khởi tạo ban đầu đồ thị, duyệt tất cả các đỉnh
		// Mỗi đỉnh i, gán nhãn bởi cặp (pre[i], l[i])
		for (int i = 0; i < this.numVex; i++) {
			P[i] = -1; // đỉnh liền trước == null
			if (i == source) {
				L[i] = 0; // nếu đỉnh đưa vào bằng đỉnh i thì cho nó bằng 0
			} else {
				L[i] = max; // ngược lại gán là vô cùng
			}
		}
		// bước 2: kết nạp cạnh, chạy thuật toán
		boolean stop = false;
		int k = 0; // chưa duyệt đỉnh nào
		while (!stop) {
			stop = true;
			k++;
			for (int i = 0; i < this.numVex; i++) {
				for (int j = 0; j < this.numVex; j++) {
					if (adj[i][j] < max && L[i] != max) { // nếu có cạnh
						if (L[j] > L[i] + adj[i][j]) { // nếu khoảng cách j > khoảng cách i + trọng số (i,j)
							L[j] = L[i] + adj[i][j];
							P[j] = i; // gán đỉnh liền trước
							stop = false;
						}
					}
				}
			}
			if (k > this.numVex) {
				if (stop == false) {
					System.out.println("Do thi co chu trinh am");
					stop = true;
					break;
				}
			}
		}
		// in ra đường đi
		for (int i = 0; i < this.numVex; i++) {
			System.out.println("Duong di ngan nhat tu " + (source + 1) + " den " + (i + 1) + " la:\n");
			int destination = i;
			Stack<Integer> stack = new Stack<>();
			stack.push(destination);
			while (source != destination) {
				stack.push(P[destination]);
				destination = P[destination];
			}
			while (!stack.isEmpty()) {
				int v = stack.pop();
				System.out.print(" ==> " + (v + 1));
			}
			System.out.println("\n Do dai la: " + L[i]);
		}

	}

	@Override
	public int[][] warshall() {
		int[][] R = new int[numVex][numVex];
		R = adj;
		for (int k = 0; k < R.length; k++) {
			for (int i = 0; i < R.length; i++) {
				if (R[i][k] > 0) {
					for (int j = 0; j < R.length; j++) {
						if (R[k][j] > 0) {
							R[i][j] = 1;
						}

					}

				}

			}
			adj = R;
			System.out.println("R = " + (k + 1));
			System.out.println();
			printGraph();

		}
		return R;
	}
}
