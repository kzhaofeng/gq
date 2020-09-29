package io.s1;

public class Main {
	public static void speak(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("主人:" + (i + 1) + " ");
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		SpeakElephant elephant = new SpeakElephant(20);
		SpeakCar car = new SpeakCar(20);
		new Thread(elephant).start();
		car.start();
		speak(15);
	}
}
