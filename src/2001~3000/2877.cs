using System;
using System.Text;

namespace ConsoleApp1
{
    public class Program
    {
        public static void Main(string[] args)
        {
            int k = int.Parse(Console.ReadLine());
            int total = 0;
            int L = 0;
            while (total < k)
            {
                L++;
                total += (1 << L);
            }

            int prevTotal = total - (1 << L);
            int index = k - prevTotal - 1;
            StringBuilder sb = new StringBuilder();
            for (int i = L - 1; i >= 0; i--)
            {
                sb.Append(((index & (1L << i)) != 0) ? '7' : '4');
            }

            Console.WriteLine(sb.ToString());
        }
    }
}