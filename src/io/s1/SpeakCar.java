package io.s1;

public class SpeakCar extends Thread {
	private int times;

	public SpeakCar(int times) {
		this.times = times;
	}

	public void speak(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("轿车:" + (i + 1) + " ");
		}
		
		System.out.println();
	}

	@Override
	public void run() {
		speak(times);
	}
}
