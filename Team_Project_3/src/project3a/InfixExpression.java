package project3a;

import java.util.Scanner;
import java.util.Stack;

public class InfixExpression {
	//FIXME: need to add binary tree functionality
	private Stack<String> infixStack; //used to store expression in a stack of tokens, tokens are separated by spaces
	private TreeNode root; 
	/**
	 * Creates an "InfixExpression" object
	 * @param exp: current expression in string form
	 */
	public InfixExpression(String line) {
		System.out.println(line); //for test
		String modLine = FixSpacing(line);
		System.out.println(modLine); //for test
		Scanner scan = new Scanner(modLine); //scanner object to look at line
		infixStack = new Stack();
		while(scan.hasNext()) {
			String currToken = scan.next(); //get current token
			infixStack.push(currToken); // push token to stack
		}
		PrintInfix(infixStack);
		System.out.println(); // for test

	}
	/**
	 * Modifies an input string to correct delimiter placement
	 * @param line: unmodified string
	 * @return result: modified string with correct delimiter placement
	 */
	private String FixSpacing(String line) {
		char[] lineArr = line.toCharArray(); //convert line to char array
		StringBuilder newLine = new StringBuilder();
		String result;
		for(int i = 0; i<lineArr.length; i++) {
			if(Character.isDigit(lineArr[i])) {newLine.append(lineArr[i]);} //if current iteration is number append to string builder
			else if(lineArr[i] == '+' || lineArr[i] == '-' || lineArr[i] == '*' || lineArr[i] == '/' || lineArr[i] == '%' || lineArr[i] == '^') {
				//if single character operator, append with correct spacing
				newLine.append(" ");
				newLine.append(lineArr[i]);
				newLine.append(" ");
			}
			else if(lineArr[i] == '|' || lineArr[i] == '&' || lineArr[i] == '>' || lineArr[i] == '<' || lineArr[i] == '=' || lineArr[i] == '!') {
				//if multi-character operator, append both characters with correct spacing and iterate "i" once to unwanted repetitions 
				newLine.append(" ");
				newLine.append(lineArr[i]);
				newLine.append(lineArr[i+1]);
				newLine.append(" ");
				i++;
			}
		}
		newLine.append(" ");
		result = newLine.toString();
		return result;
	}
	/**
	 * Prints expression in user friendly format
	 * @param stk: expression stack
	 */
	public void PrintInfix(Stack<String> stk) {
		while(!stk.isEmpty()) {
			System.out.print(stk.pop());
		}
		System.out.println();
	}

}
