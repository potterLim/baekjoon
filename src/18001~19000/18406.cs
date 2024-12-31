using System;

public class Program
{
    static void Main(string[] args)
    {
        string pointStr = Console.ReadLine();

        int sumLeft = 0;
        int sumRight = 0;

        for (int i = 0; i < pointStr.Length; ++i)
        {
            if (i < pointStr.Length / 2)
            {
                sumLeft += int.Parse(pointStr[i].ToString());
            }

            else
            {
                sumRight += int.Parse(pointStr[i].ToString());
            }
        }

        if (sumLeft == sumRight)
        {
            Console.WriteLine("LUCKY");
        }

        else
        {
            Console.WriteLine("READY");
        }
    }
}
