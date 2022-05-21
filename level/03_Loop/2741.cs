using System;
using System.Text;

namespace _05_FastOutput1ToN
{
    class Program
    {
        static void Main(string[] args)
        {
            StringBuilder sb = new StringBuilder(128);

            int n = int.Parse(Console.ReadLine());

            for(int i = 1; i<=n; ++i)
            {
                sb.Append(i);
                sb.Append("\n");
            }

            Console.WriteLine(sb);
        }
    }
}
