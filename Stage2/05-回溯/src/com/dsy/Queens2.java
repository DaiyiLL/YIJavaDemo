package com.dsy;

public class Queens2 {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Queens1().placeQueues(4);
//	}
	
	int[] queens;
	// 标记某一列是否有皇后
	boolean[] cols;
	/*
	 * 标记着某一斜线上是否有皇后(左上角 -》 右下角)
	 */
	boolean[] leftTop;
	/*
	 * 标记着某一斜线上是否有皇后(右上角 -》 左下角)
	 */
	boolean[] rightTop;
	int ways;
	
	void placeQueues(int n) {
		if (n < 1) return;
		cols = new boolean[n];
		queens = new int[n];
		leftTop = new boolean[(n << 1) - 1];
		rightTop = new boolean[leftTop.length];
		place(0);
		
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/*
	 * 从row行开始摆放皇后
	 */
	void place(int row) {
		if (row == cols.length) {
			// 成功找到一个N皇后的摆法
			ways++;
			show();
			return;
		}
		for (int col = 0; col < cols.length; col++) {
			if (cols[col]) continue;
			int ltIndex = row - col + cols.length - 1;
			if (leftTop[ltIndex]) continue;
			int rtIndex = row + col;
			if (rightTop[rtIndex]) continue;
			
			queens[row] = col;
			cols[col] = true;
			leftTop[ltIndex] = true;
			rightTop[rtIndex] = true;
			// 摆放下一行
			place(row + 1);
			// 执行完毕后就是回溯
			cols[col] = false;
			leftTop[ltIndex] = false;
			rightTop[rtIndex] = false;
		}
	}
	
	// 判断row行 co列是否可以摆放皇后
	boolean isValid(int row, int col) {
		
		System.out.println("[" + row + "][" + col + "] = true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (queens[row] == col) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		
		System.out.println("==========================");
	}

}
