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
			else if(lineArr[i] == '+' || lineArr[i] == '-' || lineArr[i] == '*' || lineArr[i] == '/' || lineArr[i] == '%' || lineArr[i] == '^' 
					|| lineArr[i] == '(' || lineArr[i] == ')') {
				//if single character operator, append with correct spacing
				newLine.append(" ");
				newLine.append(lineArr[i]);
				newLine.append(" ");
			}
			else if(lineArr[i] == '|' || lineArr[i] == '&' || lineArr[i] == '=' || lineArr[i] == '!') {
				//if multi-character operator, append both characters with correct spacing and iterate "i" once to unwanted repetitions 
				newLine.append(" ");
				newLine.append(lineArr[i]);
				newLine.append(lineArr[i+1]);
				newLine.append(" ");
				i++;
			}
			else if(lineArr[i] == '>' || lineArr[i] == '<') {
				//if equality operator, check if it is 2 char operator
				if(Character.isDigit(lineArr[i+1])){
					//if not 2 char operator, append
					newLine.append(" ");
					newLine.append(lineArr[i]);
					newLine.append(" ");
				}
				else {
					//otherwise, append 2 char operator
					newLine.append(" ");
					newLine.append(lineArr[i]);
					newLine.append(lineArr[i+1]);
					newLine.append(" ");
					i++;
				}
			}
		}
		newLine.append(" ");
		result = newLine.toString();
		return result;
	}
	/**
	 * Prints expression from stack, !!!!temporary!!!!
	 * @param stk: expression stack
	 */
	public void PrintInfix(Stack<String> stk) {
		while(!stk.isEmpty()) {
			System.out.print(stk.pop());
		}
		System.out.println();
	}
	/**
	 * Method determines the precedence of an input operator character
	 * @param op: String of operator
	 * @return: returns int value of precedence, higher the value the faster it is evaluated, returns 0 if invalid operator
	 */
	public int OperatorPrecedence(String op) {
		switch(op) {
		//power
		case"^":return 7;
		//arithmetic
		case"*":return 6;
		case"/":return 6;
		case"%":return 6;
		case"+":return 5;
		case"-":return 5;
		//comparison
		case">":return 4;
		case">=":return 4;
		case"<":return 4;
		case"<=":return 4;
		//equality comparison
		case"==":return 3;
		case"!=":return 3;
		//logical and
		case"&&":return 2;
		//logical or
		case"||":return 1;
		
		default:return 0;
		}
	}

}
