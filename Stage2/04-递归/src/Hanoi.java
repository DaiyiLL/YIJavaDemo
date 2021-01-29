
public class Hanoi {

	/**
	 * 将 n 个碟子从p1 挪动到 p3
	 * @param n
	 * @param p1
	 * @param p2 中间柱子
	 * @param p3
	 */
	void hanoi(int n, String p1, String p2, String p3) {
		if (n == 1) {
			move(1, p1, p3);
			return;
		}
		hanoi(n - 1, p1, p3, p2);
		move(n, p1, p3);
		hanoi(n - 1, p2, p1, p3);
	}
	
	void move(int no, String from, String to) {
		System.out.println("将" + no + "盘子从" + from + "移动到" + to);
	}
}
