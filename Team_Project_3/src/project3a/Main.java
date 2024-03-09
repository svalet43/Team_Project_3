package project3a;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String args[]) {
		InfixExpression infix;
		String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("inputFile.txt"))) {
            while ((line = reader.readLine()) != null) {infix = new InfixExpression(line);}
        } 
        catch (FileNotFoundException e) {System.out.println("Error: File not found.");}
        catch (IOException e) {System.out.println("Error reading file.");}
	}
}
