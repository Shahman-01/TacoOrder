package tacos.leetcode;

import java.util.Arrays;
import java.util.Stack;

class Solution {
	public static void main(String[] args) {
		int[] arr = {73,74,75,71,69,72,76,73};

		System.out.println(Arrays.toString(new Solution().dailyTemperatures(arr)));
	}
	public int[] dailyTemperatures(int[] temperatures) {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < temperatures.length - 1; i++) {
			stack.push(temperatures[i]);
			if (temperatures[i] < temperatures[i + 1]) {
				temperatures[i] = stack.size();
				stack.pop();
			}
		}

		if (temperatures.length > 0)
			temperatures[temperatures.length - 1] = 0;

		return temperatures;
	}
}
