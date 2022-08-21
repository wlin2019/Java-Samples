package com.learnjava.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator2 {
	private static final String EXPRESSION_SEPERATOR = " ";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIV = "/";
	
	private static final String EXPRESSION_PATTERN = "[[0-9]+\s+[\\+-/\\*]\s+[0-9]+]*";

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
				} else {
					System.out.println("Invalid expression: " + expression);
				}
			}
			
		}
		keyboard.close();
		System.out.println("End!");
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
		Pattern pattern = Pattern.compile(EXPRESSION_PATTERN);
		Matcher match = pattern.matcher(expression); 
		return match.matches();
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
