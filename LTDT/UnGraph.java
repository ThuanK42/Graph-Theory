package Tuan_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class UnGraph extends Graph {
	String result;

	public UnGraph(int v) {
		super(v);
	}

	// =========================Bai Tap Tuan1============================

	@Override
	// Them canh
	// Do thi vo huong co canh doi xung
	public void addEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src <= numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 1;
			adj[dest - 1][src - 1] = 1;
		}
	}

	// Them canh co trong so
	public void addEdges(int src, int dest, int weight) {
		if (src >= 0 && dest >= 0 && src <= numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = weight;
			adj[dest - 1][src - 1] = weight;
		}
	}

	public void addEdgesW(int src, int dest, int weight) {
		if (src >= 0 && dest >= 0 && src <= numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = weight;
		}
	}

	@Override
	// Xoa canh
	// set gia tri canh do ve 0
	public void delEdges(int src, int dest) {
		if (src >= 0 && dest >= 0 && src <= numVex && dest <= numVex) {
			adj[src - 1][dest - 1] = 0;
			adj[dest - 1][src - 1] = 0;
		}
	}

	@Override
	// Bac 1 dinh bat ky
	// tong gia tri cua cot tai dinh do
	public int degreeVex(int anyVex) {
		int sum = 0;
		if (anyVex >= 0 && anyVex < adj.length) {// ton tai dinh
			for (int i = 0; i < adj[anyVex].length; i++) {
				sum += adj[i][anyVex];
			}
			return sum;
		} else {
			return -1;
		}
	}

	@Override
// bac cua do thi = tong cac bac trong do thi
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
	// So canh cua 1 do thi : 2xcanh = tong so bac cua do thi
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

	// =========================Bai Tap Tuan2============================

	@Override
	// Kiem tra do thi co luong phan hay khogn
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

// Duyet do thi theo chieu sau dung de quy
	@Override
	public void re_DFS(int startVex) {
		int V = startVex - 1;
		visited[V] = true;// set vi tri input da tham
		System.out.print((V + 1) + "\t");
		for (int i = 0; i < numVex; i++) {// chay het tat ca cac dinh
			if (adj[V][i] != 0 && visited[i] == false) {// neu dinh do voi dinh ke co canh, dinh ke chua tham thi set
														// dinh ke da tham
				re_DFS(i + 1);
			}
		}
	}

	public void re_DFS2(int startVex) {
		int V = startVex - 1;
		visited[V] = true;
		for (int i = 0; i < numVex; i++) {
			if (adj[V][i] != 0 && visited[i] == false) {
				re_DFS2(i + 1);
			}
		}
	}

	@Override
	// Kiem tra tinh lien thong cua do thi
	public boolean isConnection() {
		re_DFS2(1);// tra ve danh sach ca dinh da tham trong do thi(cac dinh do tao canh voi nhau)
		// check
		for (int i = 0; i < numVex; i++) {// kiem tra tat ca cac dinh trong do thi
			if (visited[i] == false)// neu dinh nao chua tham (dinh co lap)
				return false;// do thi ko lien thong
		}
		return true;
	}

	@Override
	// Dem so thanh phan lien thong
	// code nhanh hon, de hieu hon, moi ban xem tai : https://youtu.be/iGSbkXioOb8
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
	// code nhanh hon, de hieu hon, moi ban xem tai : https://youtu.be/iGSbkXioOb8
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

	// =========================Bai Tap Tuan3============================

	@Override
	// Stack LIFO
	public void DFS(int start) {
		System.out.println("Duyet DFS tu dinh: " + (start + 1));
		// stack de phong ngua truong hop quay lui ;
		Stack<Integer> stack = new Stack<>();
		// list de luu danh sach duong di da qua
		ArrayList<Integer> listVisit = new ArrayList<>();
		// tao mang danh dau diem da tham
		boolean[] visit = new boolean[numVex];
		// set trang thai da tham cho input
		visit[start] = true;
		// them dinh vao mang duong di
		listVisit.add(start);
		// them dinh vao stack
		stack.push(start);
		// dung khi stack rong
		while (!stack.empty()) {
			// bien count dem so dinh da tham
			int count = 0;
//peek tra ve gia tri cua phan tu nam tren cung nhung ko xoa no
			start = stack.peek();
			// duyet va kiem tra
			for (int j = 0; j < visit.length; j++) {// chay toan bo dinh
				if (this.adj[start][j] > 0 && visit[j] == false) {// neu dinh start tao canh voi dinh ke, dinh ke chua
																	// tham
					// them dinh tiep theo vao list in duong
					listVisit.add(j);
					// set gia tri dinh do = true (da tham)
					visit[j] = true;
					// them vao stack
					stack.push(j);
					// neu them roi thi dung lai theo quy tac cuon
					break;
				} else {
					count++;
				}
			}
			if (count == visit.length) { // neu da tham du dinh
				stack.pop();// xoa het stack dung while
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

	@Override
	// Duyet BFS - FIFO
	// tuong tu nhu tren nhung chi khac cach duyet
	public void BFS(int start) {
		System.out.println("Duyet BFS tu dinh: " + (start + 1));
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[numVex];
		ArrayList<Integer> list = new ArrayList<>();
		visited[start] = true;
		list.add(start);
		queue.add(start);
		while (!queue.isEmpty()) {
			start = queue.remove();// lay dinh vua cho vao ra roi xoa lun
			for (int i = 0; i < visited.length; i++) {
				if (this.adj[start][i] > 0 && visited[i] == false) {
					list.add(i);
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		print(list);
	}

	@Override
	// Duyet theo chieu sau tim duong dai nhat
	// tuong tu dfs chi khac khi duyet la neu dinh lay ra = dinh dich thi minh dung
	// dong while lun
	public void findPathLong(int src, int dest) {
		System.out.println("Duong di dai nhat tu dinh: " + (src + 1) + " den: " + (dest + 1) + " la: ");
		Stack<Integer> stack = new Stack<>();
		ArrayList<Integer> listVisit = new ArrayList<>();
		int[] visit = new int[numVex];
		visit[src] = 1;
		listVisit.add(src);
		stack.push(src);
		while (!stack.empty()) {
			int count = 0;
			src = stack.peek();
			if (src == dest) {// neu dinh lay ra trung dinh dich dung while lun
				break;
			}
			for (int j = 0; j < visit.length; j++) {
				if (this.adj[src][j] > 0 && visit[j] == 0) {
					listVisit.add(j);
					visit[j] = 1;
					stack.push(j);
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

	@Override
//Tim duong di ngan nhat = BFS
	public void findPathShortest(int src, int dest) {
		System.out.println("Duong di ngan nhat tu dinh: " + (src + 1) + " den: " + (dest + 1) + " la: ");
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>();
		visited[src] = true;
		list.add(src);
		queue.add(src);
		while (!queue.isEmpty()) {
			src = queue.poll(); // lay dinh ra va xoa dinh do lun (dinh dau tien trong hang doi)
			for (int i = 0; i < numVex; i++) {
				if (this.adj[src][i] > 0 && visited[i] == false) {
					list.add(i);
					visited[i] = true;
					queue.add(i);
					if (i == dest) { // kiem tra dinh co trung dinh dich ko
						queue.clear(); // xoa het queue
						break;// dung vong while
					}

				}

			}
		}
		print(list);
	}

	// =========================Bai Tap Tuan4============================
//Chu trinh euler toan bo dinh deu bac chan
	@Override
	public boolean checkCycleEuler() {
		boolean result = false;
		int count = 0;

		for (int i = 0; i < numVex; i++) {
			if (degreeVex(i) % 2 == 0) {
				count++; // dem so dinh bac chan
			}
		}
// chu trinh euler co so dinh >1, lien thong manh, cac dinh deu la bac chan
		if (isConnection() == true && numVex > 1 && count == numVex) {
			result = true;
		}
		return result;
	}

	// Kiem tra duong di euler
	@Override
	public boolean checkPathEuler() {
		boolean result = false;
		int count = 0;
		for (int i = 0; i < numVex; i++) {
			if (degreeVex(i) % 2 != 0)
				count++;
		}
		// path euler : lien thon manh, dinh > 1, 2 dinh le
		if (isConnection() == true && numVex > 1 && count == 2)
			result = true;
		return result;
	}

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
						adj[x][i] = adj[i][x] = 0; // duyet canh nao roi thi xoa canh do
						x = i;// gan dinh tiep theo cho x (neu ko x se ko thay doi bien)
						i = 0;// cho no chay lại de kiem tra
					}
					if (i == numVex && !stack.isEmpty()) { // chay het cac canh
						stack.pop();
					}
				}
			}
			print(list);
		}
	}

	@Override
	public void findPathEuler(int start) {
		ArrayList<Integer> listHasPathEuler = new ArrayList<>();
		if (checkPathEuler() == false) {
			System.out.println("Khong co duong di Euler");
		} else {
			if (degreeVex(start) % 2 == 0) {//
				for (int i = 0; i < numVex; i++) {
					if (degreeVex(i) % 2 != 0) {
						listHasPathEuler.add(i);
					}
				}
				System.out.println("Khong co duong di tu " + start);
				System.out.println("Danh sach diem co duong di: " + listHasPathEuler.toString());
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
							adj[x][j] = adj[j][x] = 0;// xoa canh
							x = j;
							j = 0;
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
	// =========================Bai Tap Tuan5============================

	// kiem tra chu trinh hamilton
	@Override

	// cach 1: chu trinh hamilton phai lien thong + kt tung do thi
	public boolean checkCycleHamilton() {
		boolean result = false;
		if (this.isConnection()) {// 1 do thi co chu trinh hamilton phai lien thong
			int count1 = 0; // so dinh co bac >= n/2
			int count2 = 0; // so dinh co bac = 2 (do thi vong)
			int count3 = 0; // so dinh co bac n = k -1 (do thi du ??)
			for (int i = 0; i < numVex; i++) {
				if (degreeVex(i) >= numVex / 2)
					count1++;
				if (degreeVex(i) == 2)
					count2++;
				if (degreeVex(i) == numVex - 1)
					count3++;
			}
			if (numVex >= 3) {
				if (count1 == numVex || count2 == numVex || count3 == numVex)
					result = true;
			}
		}
		return result;
	}

	// kiem tra duong di hamilton
	@Override
	public boolean checkPathHamilton() {
		boolean result = false;
		if (this.isConnection()) {
			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			for (int i = 0; i < numVex; i++) {
				if (degreeVex(i) >= (numVex - 1) / 2)
					count1++;
				if (degreeVex(i) == 1)
					count2++;
				if (degreeVex(i) < (numVex - 1) / 2 && degreeVex(i) > 1)
					count3++;
			}
			if (numVex >= 3) {
				if ((count1 == numVex) || (count2 == 2 && count1 == numVex - 2) || (count2 == 2 && count3 <= count2))
					result = true;
			}
		}
		return result;
	}

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
		System.out.println("Chu trinh hamilton hien co la: ");
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
///////////////////========================Bai tap tuan 6==================================

	@Override
	// in chu trinh hamilton
	public void findAllCycleHamilton() {
		if (checkCycleHamilton() == true) {
			for (int i = 0; i < numVex; i++) {
				visited[i] = false;
			}
			path[0] = 0;
			visited[0] = true;
			Expand2(1);
		} else {
			System.out.println("Khong co chu trinh hamilton");
		}
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
	// in chu trinh hamilton tu1 dinh
	public void findAllCycleHamilton(int v) {
		if (checkCycleHamilton() == true) {
			for (int i = 0; i < numVex; i++) {
				visited[i] = false;
			}
			int start = v - 1;
			path[0] = start;
			visited[start] = true;
			Expand2(1);
		} else {
			System.out.println("Khong co chu trinh hamilton");
		}
	}

	@Override
	// in duong di hamilton
	public void findAllPathHamilton() {
		if (checkPathEuler() == true) {
			int minVex = minDegree();// bac nho nhat
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
		} else {
			System.out.println("Khong co duong di hamilton");
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
	// in duong di hamilton tu 1 dinh
	public void findAllPathHamilton(int k) {
		ArrayList<Integer> listMinVex = new ArrayList<>();
		int minVex = minDegree();
		for (int i = 0; i < numVex; i++) {
			if (degreeVex(i) == minVex) {// duong di hamilton thong thuong di tu dinh co bac min
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

	// =========================Bai Tap Tuan7============================

	@Override
	public Graph DFSTree(int v) {
		Graph tree = new UnGraph(numVex);
		tree.adj = treeDFS(v);
		System.out.println("Duyet cay theo DFS:");
		return tree;
	}

	int[][] a = new int[numVex][numVex]; // mang tam de duyet
	private int[][] treeDFS(int v) {
		int s = v - 1;// do do thi co tram cho chay tu 1 nhung minh duyet tu 0
		visited[s] = true;
		for (int i = 0; i < numVex; i++) {
			if (adj[s][i] != 0 && visited[i] == false) {
				visited[i] = true;
				a[s][i] = a[i][s] = 1;// them canh tai vi tri tuong ben ma tran ke adj cho a
				treeDFS(i + 1);//de quy
			}
		}
		return a;
	}

	@Override
	public Graph BFSTree(int anyVex) {
		// System.out.println("Dang duyet tu dinh "+(anyVex+1));
		Graph tree = new UnGraph(numVex);
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
			int v = queue.remove();// lay va xoa dinh do lun
			for (int i = 0; i < adj.length; i++) {
				if (adj[v][i] != 0 && visited[i] == false) {
					b[v][i] = b[i][v] = 1;// tao canh
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		return b;
	}

/////////=====================Bai tap tuan 8=========================
	@Override
	// kiem tra chu trinh giua 2 dinh bat ky
	// de hieu hon la tim dc duong di giua 2 dinh do
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
	private List<Edges> sortWeight() {
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
		Graph tree = new UnGraph(numVex);
		int trongSo = 0;
		int numberofEdges = 0;
		while (numberofEdges < numVex - 1) {// so lan chay = so dinh -1
			List<Edges> listEdgesSorted = sortWeight();// tra ve danh sach cac canh co trong so tang dan
			for (Edges edges : listEdgesSorted) {
				if (tree.hasCycle(edges.src, edges.des) == false) {// neu ko tao chu trinh
					tree.addEdges(edges.src + 1, edges.des + 1, edges.weight);// them canh vao
					numberofEdges++;
					trongSo += edges.weight;// cong don trong so
				}
			}
		}

		System.out.println("Duyet Kruskal");
		tree.printGraph();
		System.out.println("Trong so cua do thi la: " + trongSo);
		return tree;
	}

	// ========================Bai tap tuan 9==========================

	@Override
	public Graph Prim(int index) {
		Graph tree = new UnGraph(this.numVex);
		int trongSo = 0;
		int numberofEdges = 0;
		List<Edges> listEdges = new ArrayList<Graph.Edges>(); // danh sach canh 
		List<Integer> listOfVex = new ArrayList<Integer>();// danh sach cac dinh
		int v = index - 1;// do do thi co cho la chay tu 1 nen phai tru
		listOfVex.add(v);
		while (numberofEdges < numVex - 1) {
			for (int k = 0; k < listOfVex.size(); k++) {
				for (int i = 0; i < numVex; i++) {
					if (adj[listOfVex.get(k)][i] != 0 && !listOfVex.contains(i)) { // neu dinh do tao canh va dinh ke chua tham
						listEdges.add(new Edges(listOfVex.get(k), i, adj[listOfVex.get(k)][i]));
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
				Edges edges = listEdges.get(0); // lay ra canh dau tien
				if (tree.hasCycle(edges.src, edges.des) == false) {// neu ko tao chu trinh
					tree.addEdges(edges.src + 1, edges.des + 1, adj[edges.src][edges.des]);// them canh
					listOfVex.add(edges.des);// dinh dich cua canh truoc la diem dau cua canh sau
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
		int[] P = new int[numVex];// danh sach cac dinh da duyet truoc dinh dang xet
		int[] L = new int[numVex];// do dai min tu dinh dang xet den cac dinh khac
		boolean[] R = new boolean[numVex];// danh dau dinh da di
		for (int i = 0; i < R.length; i++) {
			L[i] = Integer.MAX_VALUE;// cho cac dinh ban dau co trong so = vo cung
			R[i] = false;// danh dau cac dinh chua tham
		}
		L[anyVex] = 0;// trong so dinh input ban dau la 0
		P[anyVex] = -1;// chua co dinh truoc dinh input dc xet nen cho no la null hay -1
		for (int i = 0; i < numVex; i++) {
			// tim dinh tiep theo de duyet
			int u = minDisTance(L, R);// tim dinh co trong so min
			R[u] = true;// danh dau dinh do da tham
			for (int j = 0; j < numVex; j++) {// chay toan bo cac dinh
				// dinh chua tham, tao canh(dinh co gia tri min den cac dinh ke), trong so != vo cung
				//trong so dinh j > trong so u + trong so uj
				if (R[j] == false && adj[u][j] != 0 && L[u] != Integer.MAX_VALUE && L[u] + adj[u][j] < L[j]) {
					P[j] = u;// gan dinh co trong so min (u) cho pj
					L[j] = L[u] + adj[u][j];// cap nhat lai trong so nho hon cho j
				}
			}
		}
		printPart(L, P, 0);// hien tai no chi chay duoc tu dinh 0 chua nghi dc cach sua
	}

	public void printPart(int[] L, int[] P, int startVex) {
		for (int i = 0; i < P.length; i++) { // mang P luc nay da chua toan bo phan tu da duyet
			System.out.print("\n duong di ngan nhat tu ");
			int goVex = i;// vi tri phan tu
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(goVex);
			while (startVex != goVex) {// dinh dau != dinh dich thi chay
				stack.push(P[goVex]);// them gia tri vao stack
				goVex = P[goVex];// gan gia tri phan tu man p thu govex cho govẽx
			}
			System.out.print(stack.pop());// lay dinh tren cung ra roi xoa
			while (!stack.isEmpty()) {// trong khi ko rong
				System.out.print("--->" + stack.pop());// dinh ke 
			}
			System.out.print("\n Do dai = " + L[i]);
		}
	}
// tra ve dinh co gia tri nho nhat
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
		int dist[][] = new int[numVex][numVex]; // mang tam
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				if (a[i][j] == 0) {// neu do thi dang xet co cho nao do = 0 (ko tao canh)
					dist[i][j] = 1000; // the vi tri tuong ung do trong mang dist thanh 1000(dang le la vo cung nhung chua tim dc cach sua)
				} else {
					dist[i][j] = a[i][j]; // thay gia tri tuong ung o mang dau vao cho mang tam
				}
			}
		}
		for (int k = 0; k < dist.length; k++) {// k bien trung gian
			for (int i = 0; i < dist.length; i++) {// chay hang
				for (int j = 0; j < dist.length; j++) {//chay cot
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
					System.out.print("âˆž   ");
				} else {
					System.out.print(dist[i][j] + "   ");
				}
			}
			System.out.println();
		}
	}

//==============Bai tap tuan 10==================
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
		int[] P = new int[numVex];// dinh lien truoc dinh da tham
		int[] L = new int[numVex];// trong min giua 2 input -> cac dinh ke
		boolean[] R = new boolean[numVex]; // mang kiem tra da tham
		int numofVex = 0;
		for (int i = 0; i < numVex; i++) {
			L[i] = Integer.MAX_VALUE;
			P[i] = -1;
			R[i] = false;
		}
		L[v] = 0;
		P[v] = -1;
		while (numofVex < numVex - 1) {// n dinh thi chay n-1 lan
			int index = 0;// dinh co gia tri min
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < R.length; i++) {
				if (L[i] < min && !R[i]) {// neu dinh chua tham va co trong so < min
					min = L[i];// cap nhat gia tri nho hon cho min
					index = i;// gan dinh do cho index
				}
			}
			for (int i = 0; i < R.length; i++) {
				if (g.adj[index][i] != 0 && g.adj[index][i] < Integer.MAX_VALUE) {// neu tao canh va gia tri tai dinh do < min
					if (L[i] > L[index] + g.adj[index][i]) { // neu trong so i > trong so index(dinh gia tri nho)+ trong so index i
						L[i] = L[index] + g.adj[index][i];// cap nhat lai trong so i
						P[i] = index;// gan dinh gia tri min vao mang
					}
				}
			}
			R[index] = true;//set trang thai da tham cho no
			numofVex++;// tang so lan duyet len
			if ((index + 1) == u) {// neu trung dinh dich thi dung
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

//	// Code co tram thuat toan dijkstra chay tu 1 dinh
//
//	public void dijkstra(int v) {
//		int[] P = new int[numVex];
//		int[] L = new int[numVex];
//		boolean[] R = new boolean[numVex];
//		int numofVex = 0;
//		for (int i = 0; i < numVex; i++) {
//			L[i] = Integer.MAX_VALUE;
//			P[i] = -1;
//			R[i] = false;
//		}
//		int start = v - 1;
//		L[start] = 0;
//		P[start] = -1;
//		while (numofVex < numVex - 1) {
//			int index = 0;
//			int min = Integer.MAX_VALUE;
//			for (int i = 0; i < R.length; i++) {
//				if (L[i] < min && !R[i]) {
//					min = L[i];
//					index = i;
//				}
//			}
//			for (int i = 0; i < P.length; i++) {
//				if (adj[index][i] != 0 && adj[index][i] < Integer.MAX_VALUE) {
//					if (L[i] > L[index] + adj[index][i]) {
//						L[i] = L[index] + adj[index][i];
//						P[i] = index;
//					}
//				}
//			}
//			R[index] = true;
//			numofVex++;
//		}
//		printPath(P, L, (start));
//	}
//
//	public void printPath(int[] path, int[] dis, int s) {
//		for (int i = 0; i < path.length; i++) {
//			System.out.println("\n Shortest Path from " + (s + 1) + " to " + (i + 1) + ": ");
//			int dest = i;
//			Stack<Integer> stack = new Stack<Integer>();
//			stack.push(dest);
//			while (dest != s) {
//				stack.push(path[dest]);
//				dest = path[dest];
//			}
//		}
//	}

	/*
	 * 
	 * in duong di co Tram
	 * 
	 * public int min(int[] L, boolean[] R) { int v = 0; int m = Integer.MAX_VALUE;
	 * for (int i = 0; i < L.length; i++) { if (L[i]<m&&!R[i]) { m = L[i]; v=i; } }
	 * return v; }
	 * 
	 */
	// có đường đi(thăm rồi) vẫn cập nhật lại

}
