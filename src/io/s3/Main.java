package io.s3;

import java.io.FileReader;
import java.io.PrintStream;

public class Main {
	public static void main(String[] args) {
		try (FileReader input = new FileReader("C:\\Users\\24803\\IdeaProjects\\itcast\\awork\\src\\io\\english.txt")) {
			try (PrintStream output = new PrintStream("C:\\Users\\24803\\IdeaProjects\\itcast\\awork\\src\\io\\englishCount.txt")) {
				int count = 0;
				char[] cbuf = new char[1];
				while (input.read(cbuf) > 0) {
					if (cbuf[0] == ' ') {
						count++;
					}
					if (cbuf[0] == '\r') {
						count++;
						output.print("单词数目：" + count);
						count = 0;
					}
					System.out.print(cbuf[0]);
					output.print(cbuf[0]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}