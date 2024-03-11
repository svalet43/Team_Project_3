package project3a;

import java.util.Scanner;
import java.util.Stack;

public class InfixExpression {
	//FIXME: need to add binary tree functionality
	private Stack<String> operandStk,operatorStk; //store expression operands
	private TreeNode root; 
	private int operatorLen, operandLen; //length of stacks
	/**
	 * Creates an "InfixExpression" object, the object is a binary tree of the input expression
	 * @param exp: current expression in string form
	 */
	public InfixExpression(String line) {
		System.out.println(line); //for test
		String modLine = FixSpacing(line);
		System.out.println(modLine); //for test
		Scanner scan = new Scanner(modLine); //scanner object to look at line
		operandStk = new Stack();
		operandLen = 0;
		operatorStk = new Stack();
		operatorLen = 0;
		//populate stacks
		while(scan.hasNext()) {
			String currToken = scan.next(); //get current token
			if(Character.isDigit(currToken.charAt(0))) {//if current token is digit, push to operand stack
				operandStk.push(currToken);
				operandLen++;
			}
			else { //if currToken is not digit, push to operator stack
				operatorStk.push(currToken);
				operatorLen++;
			}
		}
		PrintStack(operatorStk); //for tests
		PrintStack(operandStk);
		String postfix = ToPostfix();
		System.out.println(postfix);
		System.out.println();
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
	 * Prints expression from stack, !!!!temporary!!!
	 * @param stk: expression stack
	 */
	private void PrintStack(Stack<String> stk) {
		while(!stk.isEmpty()) {
			System.out.print(stk.pop());
		}
		System.out.println();
	}
	/**
	 * Method determines the precedence of an input operator
	 * @param op: String of operator
	 * @return: returns int value of precedence, higher the value the faster it is evaluated, returns 0 if invalid operator
	 */
	private int Precedence(String op) {
		if(op.equals("^")) {return 7;}
		else if(op.equals("*") || op.equals("/") || op.equals("%")) {return 6;}
		else if(op.equals("+") || op.equals("-")) {return 5;}
		else if(op.equals(">") || op.equals(">=") || op.equals("<") || op.equals("<=")) {return 4;}
		else if(op.equals("==") || op.equals("!=")) {return 3;}
		else if(op.equals("&&")) {return 2;}
		else if(op.equals("||")) {return 2;}
		return 0;
	}
	/** FIXME!!!!!!!!!!!!!!!!
	 *  converts infix expression to postfix 
	 * @param infix: infix expression
	 * @return result: postfix expression
	 */
	private String ToPostfix() {
		StringBuilder postfix = new StringBuilder();
		String op, left = "",right = "";
		while(!operatorStk.isEmpty()) { //while operator stack is not empty
			op = operatorStk.pop(); //get operator
			if(op.equals(")")){//closing parenthesis case
				while(!operatorStk.peek().equals("(")) { //while next operator is not "("
					op = operatorStk.pop(); //get operator
					if(!operandStk.isEmpty() && !operandStk.peek().isEmpty()) {right = operandStk.pop();} //while operand stack is not empty, assign right
					if(!operandStk.isEmpty() && !operandStk.peek().isEmpty()) {left = operandStk.pop();}//while operand stack is not empty, assign left
					postfix.append(left); //append strings
					postfix.append(" ");
					postfix.append(right);
					postfix.append(" ");
					postfix.append(op);
					postfix.append(" ");
				}
				if(operatorStk.peek().equals("(")){operatorStk.pop();} //pop opening parenthesis from operator stack
			}
			else if(Precedence(op) > Precedence(operatorStk.peek())) {
				operandStk.push(op);
			}
			else {
				// Pop operators from operand's stack until finding a low precedence operator
				while (!operatorStk.isEmpty() && Precedence(operatorStk.peek()) >= Precedence(op) &&
						!operatorStk.peek().equals("(")) {
					postfix.append(operandStk.pop()).append(" ");
				}
				operandStk.push(op);
			}
			while (!operandStk.isEmpty()) {
				postfix.append(operandStk.pop()).append(" ");
			}
		}
		return postfix.toString().trim();
	}
	/** FIXME!!!!!!!!!!!
	 * Converts postfix expression to  binary expression tree
	 * @param postfix: postfix expression
	 */
	private void ToTree(String postfix) {

	}
	/**FIXME!!!!!!!!!!1
	 * Evaluates expression tree
	 * @param root: the root of the tree
	 * @return integer result of expression tree
	 */
	private int Evaluate(TreeNode root) {

	}

}
