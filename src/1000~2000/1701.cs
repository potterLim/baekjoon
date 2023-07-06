using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    public class Program1
    {
        static List<int> GetPartialMatch(string N)
        {
            int M = N.Length;
            List<int> pi = new List<int>(M);
            for (int i = 0; i < M; i++)
            {
                pi.Add(0);
            }

            int begin = 1, matched = 0;
            while (begin + matched < M)
            {
                if (N[begin + matched] == N[matched])
                {
                    matched++;
                    pi[begin + matched - 1] = matched;
                }
                else
                {
                    if (matched == 0)
                    {
                        begin++;
                    }
                    else
                    {
                        begin += matched - pi[matched - 1];
                        matched = pi[matched - 1];
                    }
                }
            }

            return pi;
        }

        public static void Main(string[] args)
        {
            string S = Console.ReadLine();

            int result = 0;
            for (int i = 0; i < S.Length; i++)
            {
                string temp = S.Substring(i, S.Length - i);
                List<int> pi = GetPartialMatch(temp);

                pi.Sort();
                pi.Reverse();
                result = Math.Max(result, pi[0]);
            }

            Console.WriteLine(result);
        }
    }
}