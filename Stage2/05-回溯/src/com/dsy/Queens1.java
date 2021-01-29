package com.dsy;

public class Queens1 {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Queens1().placeQueues(4);
//	}
	
	// 数组索引是行号，数组元素是列号，cols[4] = 5
	int[] cols;
	int ways;
	
	void placeQueues(int n) {
		if (n < 1) return;
		cols = new int[n];
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
			if (isValid(row, col)) {
				cols[row] = col;
				// 摆放下一行
				place(row + 1);
				// 执行完毕后就是回溯
			} 
		}
	}
	
	// 判断row行 co列是否可以摆放皇后
	boolean isValid(int row, int col) {
		for (int i = 0; i < row; i++) {
			// 第col列有皇后
			if (cols[i] == col) {
				System.out.println("[" + row + "][" + col + "] = false");
				return false;
			}
			// 斜线上有皇后
			if (row - i == Math.abs(col - cols[i])) {
				System.out.println("[" + row + "][" + col + "] = false");
				return false;
			}
		}
		System.out.println("[" + row + "][" + col + "] = true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (cols[row] == col) {
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
