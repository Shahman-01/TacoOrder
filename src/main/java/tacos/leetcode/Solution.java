package tacos.leetcode;

import java.util.*;

class Solution {
	public static void main(String[] args) {
		int target = 10;
		int[] position = {0, 4, 2};
		int[] speed = {2, 1, 3};
		System.out.println(new Solution().carFleetSec(target, position, speed));
	}

	private int carFleetSec(int target, int[] position, int[] speed) {
		if (position.length == 1)
			return 1;

		Car[] cars = new Car[position.length];

		for (int i = 0; i < speed.length; i++) {
			cars[i] = new Car(position[i], speed[i]);
		}

		Arrays.sort(cars, (x, y) -> Integer.compare(y.position, x.position));

		int res = 0;
		double lastTime = 0;

		for (Car car : cars) {
			double arrivalTime = (target - car.position) / (double) car.speed;

			if (arrivalTime > lastTime) {
				res++;
				lastTime = arrivalTime;
			}
		}

		return res;
	}

	private static class Car {
		public int position;
		public int speed;

		public Car(int position, int speed) {
			this.position = position;
			this.speed = speed;
		}
	}
}