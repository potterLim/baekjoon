using System;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    static void Main(string[] args)
    {
        int numberOfBooks = int.Parse(Console.ReadLine());
        Dictionary<string, int> booksSold = new Dictionary<string, int>(numberOfBooks);

        for (int i = 0; i < numberOfBooks; ++i)
        {
            string bookName = Console.ReadLine();

            if (booksSold.ContainsKey(bookName))
            {
                booksSold[bookName]++;
            }
            else
            {
                booksSold[bookName] = 1;
            }
        }

        var bestSeller = booksSold.OrderByDescending(book => book.Value)
                                   .ThenBy(book => book.Key)
                                   .First();

        Console.WriteLine(bestSeller.Key);
    }
}

