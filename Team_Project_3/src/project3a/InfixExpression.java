package project3a;

import java.util.Scanner;
import java.util.Stack;

public class InfixExpression {
	//FIXME: need to add binary tree functionality
	private Stack<String> operandStk; //store expression operands
	private Stack<TreeNode> operatorStk; //store expression operators
	private TreeNode root; 
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
		operatorStk = new Stack();
		while(scan.hasNext()) {
			String currToken = scan.next(); //get current token
			char ch = currToken.charAt(0);
			if(Character.isDigit(ch)) {operandStk.push(currToken);} //if current token is digit, push to operand stack
			else if(ch == '(') {operatorStk.push(new TreeNode(ch));} //if opening paren, push to operator stack
			else if(ch == ')') {} //FIXME: needs to iterate until opening parenthesis is found, adding elements to tree
			else {} //FIXME: needs to iterate until opening parenthesis is found, adding elements to tree
		}

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
	public void PrintInfix(Stack<String> stk) {
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
	public int OperatorPrecedence(String op) {
		if(op.equals("^")) {return 7;}
		else if(op.equals("*") || op.equals("/") || op.equals("%")) {return 6;}
		else if(op.equals("+") || op.equals("-")) {return 5;}
		else if(op.equals(">") || op.equals(">=") || op.equals("<") || op.equals("<=")) {return 4;}
		else if(op.equals("==") || op.equals("!=")) {return 3;}
		else if(op.equals("&&")) {return 2;}
		else if(op.equals("||")) {return 2;}
		return 0;
	}
	/** FIXME!!!!!!!!!!!
	 * Converts stack to binary expression tree
	 * @param stk
	 * @return
	 */
	public TreeNode toExpTree() {
		
	}
	/**FIXME!!!!!!!!!!1
	 * Evaluates expression tree
	 * @param root: the root of the tree
	 * @return integer result of expression tree
	 */
	public int Evaluate(TreeNode root) {
		
	}

}
