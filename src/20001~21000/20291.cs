using System;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    static void Main(string[] args)
    {
        int countFiles = int.Parse(Console.ReadLine());
        string[] nameFiles = new string[countFiles];

        for (int i = 0; i < countFiles; ++i)
        {
            nameFiles[i] = Console.ReadLine();
            nameFiles[i] = nameFiles[i].Split('.')[1];
        }

        Dictionary<string, int> countExtensions = new Dictionary<string, int>(countFiles);

        for (int i = 0; i < countFiles; ++i)
        {
            if (countExtensions.ContainsKey(nameFiles[i]))
            {
                countExtensions[nameFiles[i]]++;
            }

            else
            {
                countExtensions[nameFiles[i]] = 1;
            }
        }

        var sortedExtensions = countExtensions.OrderBy(extension => extension.Key);

        foreach (var extensions in sortedExtensions)
        {
            Console.WriteLine($"{extensions.Key} {extensions.Value}");
        }
    }
}
