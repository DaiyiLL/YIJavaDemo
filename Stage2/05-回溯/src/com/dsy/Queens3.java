package com.dsy;

public class Queens3 {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Queens1().placeQueues(4);
//	}
	
	int[] queens;
	int ways;
	// 标记某一列是否有皇后
	byte cols;
	/*
	 * 标记着某一斜线上是否有皇后(左上角 -》 右下角)
	 */
	short leftTop;
	/*
	 * 标记着某一斜线上是否有皇后(右上角 -》 左下角)
	 */
	short rightTop;
	
	void place8Queues() {
		queens = new int[8];
		place(0);
		
		System.out.println(8 + "皇后一共有" + ways + "种摆法");
	}
	
	/*
	 * 从row行开始摆放皇后
	 */
	void place(int row) {
		if (row == 8) {
			// 成功找到一个N皇后的摆法
			ways++;
			show();
			return;
		}
		for (int col = 0; col < 8; col++) {
			int colIndex = (1 << col);
			if ((cols & colIndex) != 0) continue;
			int ltIndex = (1 << (row - col + 7));
			if ((leftTop & ltIndex) != 0) continue;
			int rtIndex = (1 << (row + col));
			if ((rightTop & rtIndex) != 0) continue;
			
			queens[row] = col;
			cols |= colIndex;
			leftTop |= ltIndex;
			rightTop |= rtIndex;
			// 摆放下一行
			place(row + 1);
			// 执行完毕后就是回溯
			cols &= (~colIndex);
			leftTop &= (~ltIndex);
			rightTop &= (~rtIndex);
		}
	}
	
	// 判断row行 co列是否可以摆放皇后
	boolean isValid(int row, int col) {
		
		System.out.println("[" + row + "][" + col + "] = true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
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
