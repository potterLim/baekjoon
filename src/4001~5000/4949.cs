using System;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        while (true)
        {
            string input = Console.ReadLine();

            if (input == ".")
            {
                break;
            }

            bool isBalanced = IsBalanced(input);
            if (isBalanced)
            {
                Console.WriteLine("yes");
            }
            else
            {
                Console.WriteLine("no");
            }
        }
    }

    private static bool IsBalanced(string input)
    {
        Stack<char> stack = new Stack<char>();

        foreach (char ch in input)
        {
            if (ch == '[' || ch == '(')
            {
                stack.Push(ch);
            }
            else if (ch == ']')
            {
                if (stack.Count == 0 || stack.Pop() != '[')
                {
                    return false;
                }
            }
            else if (ch == ')')
            {
                if (stack.Count == 0 || stack.Pop() != '(')
                {
                    return false;
                }
            }
        }

        if (stack.Count == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}