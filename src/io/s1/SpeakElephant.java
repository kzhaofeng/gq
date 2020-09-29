package io.s1;

public class SpeakElephant implements Runnable {
	private int times;

	public SpeakElephant(int times) {
		this.times = times;
	}

	public void speak(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("大象:" + (i + 1) + " ");
		}
		
		System.out.println();
	}

	@Override
	public void run() {
		speak(times);
	}
}
