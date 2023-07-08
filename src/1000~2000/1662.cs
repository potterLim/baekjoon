using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine().Trim();
            Stack<int> lengthStack = new Stack<int>();
            Stack<int> multiplierStack = new Stack<int>();

            int count = 0;
            for (int i = 0; i < input.Length; i++)
            {
                char currentChar = input[i];
                if (currentChar == '(')
                {
                    count -= 1;
                    int multiplier = input[i - 1] - '0';
                    lengthStack.Push(count);
                    multiplierStack.Push(multiplier);
                    count = 0;
                }
                else if (currentChar == ')')
                {
                    int currentMultiplier = multiplierStack.Peek();
                    multiplierStack.Pop();
                    int value = currentMultiplier * count;
                    int previousLength = lengthStack.Peek();
                    lengthStack.Pop();
                    count = previousLength + value;
                }
                else
                {
                    count++;
                }
            }

            Console.Write(count);
        }
    }
}
