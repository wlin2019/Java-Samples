/**
 * 
 */
package com.learnjava.calculator;

import java.util.Scanner;

/**
 * A simple calculator class prosses a command line input of a simple arithmetic expression 
 * and evaluates it.
 * 
 * This version has limited function. The expression only allows the limited operators of 
 * +, -, *, and /. It does not support precedence and evaluates the expression from left 
 * to right. Only integer is supported for the numbers in the expression.
 * 
 * @author Warren Lin
 *
 */
public class calculator1 {
	private static final String EXPRESSION_SEPERATOR = " ";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIV = "/";

	/**
	 * The command line processor receives an input arithmetic expression, and evaluates
	 * it. 
	 * 
	 * Type in the arithmetic expression and hit return to evaluate the expression. The
	 * result will be printed.
	 * 
	 * @param args not used in this version.
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		boolean continueToCalculate = true;
		while (continueToCalculate) {
			System.out.println("Please enter a mathematical expression followed by enter key. ");
			String expression = keyboard.nextLine();
			if (expression.isEmpty()) {
				continueToCalculate = false;
			} else {
				if (validateExpression(expression)) {
					double result = evaluateExpression(expression);
					System.out.println(String.format("%s = %f", expression, result));
				}
			}
			
		}
		keyboard.close();
		System.out.println("End");
	}

	/**
	 * Validates the expression.
	 * 
	 * TODO: Implement it later.
	 * 
	 * @param expression An arithmetic expression.
	 * @return true if valid, false otherwise.
	 */
	private static boolean validateExpression(String expression) {
		return true;
	}
	
	/**
	 * Evaluates an arithmetic expression string.
	 * 
	 * @param expression An arithmetic expression string using space as seporator.
	 * 
	 * @return the value of the expression.
	 */
	private static double evaluateExpression(String expression) {
		String[] elements = expression.split(EXPRESSION_SEPERATOR);
		double total = 0.0;
		String op = null;
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].isEmpty()) continue;
			if (op == null) {
				total = Integer.parseInt(elements[i]);
				op = "";
				continue;
			}
			if (op.isEmpty()) {
				op = elements[i];
				continue;
			} else {
				if (op.equals(PLUS)) {
					total += Integer.parseInt(elements[i]);
				} else if (op.equals(MINUS)) {
					total -= Integer.parseInt(elements[i]);
				} else if (op.equals(MULTIPLY)) {
					total *= Integer.parseInt(elements[i]);
				} else if (op.equals(DIV)) {
					total /= Integer.parseInt(elements[i]);
				} else {
					System.out.println(String.format(
							"Invald expression at elemet, %d. %s. Expect a supported operator.", i, op));
					return total;
				}
				op = "";
			}
		}
		return total;
	}
}
