package com.sap;

public class ArraySort {
	public static void toBig(double[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			for (int k = i + 1; k < arr.length; k++) {
				if (arr[k] < arr[i])// 交换条件（排序条件）
				{
					double number = arr[i];
					arr[i] = arr[k];
					arr[k] = number;
				} // 交换
			}
		}
		
	}
}
