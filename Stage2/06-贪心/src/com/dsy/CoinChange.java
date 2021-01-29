package com.dsy;

import java.util.Arrays;
import java.util.Comparator;

public class CoinChange {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		int[] faces = {25, 10, 5, 1};
//		int money = 40;
//		int coins = 0;
//		for (int i = 0; i < faces.length && money > 0; i++) {
//			int count = money / faces[i];
//			money -= count * faces[i];
//			coins += count;
//			System.out.println(count + "枚面值为:" + faces[i]);
//		}
//		System.out.println(coins);
		
		int[] coins = {186,419,83,408};
		int amount = 6249;
		System.out.println(coinChange(coins, amount));
	}
	
	public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        System.out.println(Arrays.toString(coins));
		int coinCount = 0;
		for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {
			int count = amount / coins[i];
//			if (count <= 0) continue;
			amount -= count * coins[i];
			coinCount += count;
			System.out.println(count);
		}
		System.out.println(amount);
		return amount > 0 ? -1 : coinCount;
    }

}
