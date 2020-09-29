package io.s2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class Main {
	public static void main(String[] args) {
		try (Reader input = new FileReader("C:\\Users\\24803\\IdeaProjects\\itcast\\awork\\src\\io\\a.txt")) {
			try (Writer output = new FileWriter("C:\\Users\\24803\\IdeaProjects\\itcast\\awork\\src\\io\\b.txt", true)) {
				char[] chars = new char[1024];
				int hasread = 0;
				while ((hasread = input.read(chars)) > 0) {
					output.write(chars, 0, hasread);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
