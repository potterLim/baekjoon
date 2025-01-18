using System;

public class Program
{
    public static void Main(string[] args)
    {
        string input = Console.ReadLine();

        if (input == "M")
        {
            Console.WriteLine("MatKor");
        } 
        else if (input == "W")
        {
            Console.WriteLine("WiCys");
        }

        else if (input == "C")
        {
            Console.WriteLine("CyKor");
        }

        else if (input == "A")
        {
            Console.WriteLine("AlKor");
        }

        else if (input == "$")
        {
            Console.WriteLine("$clear");
        }

    }
}