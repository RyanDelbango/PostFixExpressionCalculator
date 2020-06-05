//Ryan Delbango


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.Scanner;
import java.util.regex.Pattern;


public class PostfixExpression
{

public static final Pattern CHARACTER = Pattern.compile("\\S.*?");
public static final Pattern UNSIGNED_DOUBLE = Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");

public static void main(String[] args) throws Exception
{
	System.out.print("\n");
	System.out.println("Hello! This is a postfix expression calculator.");
	System.out.print("\n");
	BufferedReader inputStream = new BufferedReader(new FileReader("in.dat"));
	String line = inputStream.readLine();
	Scanner input;
	String next;

	while (line != null)
	{

	Stack<Double> numbers = new Stack<Double>();
	double operand1;
	double operand2;
	double exp = 1.0;
	input = new Scanner(line);
	
	while (input.hasNext())
	{

		if (input.hasNext(UNSIGNED_DOUBLE))
		{
			next = input.findInLine(UNSIGNED_DOUBLE);
			numbers.push(new Double(next));
		}
		
		else
		{
			next = input.findInLine(CHARACTER);
			char first = next.charAt(0);
				if (first == '+')
				{
					operand2 = numbers.pop();
					operand1 = numbers.pop();
					numbers.push(operand1 + operand2);
				}
				else if (first == '-')
				{
					operand2 = numbers.pop();
					operand1 = numbers.pop();
					numbers.push(operand1 - operand2);
				}
				else if (first == '*')
				{
					operand2 = numbers.pop();
					operand1 = numbers.pop();
					numbers.push(operand1 * operand2);
				}
				else if (first == '/')
				{
					operand2 = numbers.pop();
					operand1 = numbers.pop();
					numbers.push(operand1 / operand2);
				}
				else if (first == '_')
				{	
					operand1 = numbers.pop();
					numbers.push(operand1 - (2 * operand1));
				}
				else if (first == '#')
				{
					operand1 = numbers.pop();
					numbers.push(Math.sqrt(operand1));
				}
				else if (first == '^')
				{
					operand2 = numbers.pop();
					operand1 = numbers.pop();
					while (operand2 != 0)
					{
					exp = (exp * operand1);
					operand2--;
					}
					numbers.push(exp);
					exp = 1.0;
				}
			}
		}
	System.out.println("The value of " + '"' + line + '"' + " is " + numbers.pop());
	System.out.print("\n");
	line = inputStream.readLine();
	}

	System.out.println("Bye-bye!");
	System.out.print("\n");
	inputStream.close();
	}
}