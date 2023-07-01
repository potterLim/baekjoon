using System;

namespace ConsoleApp1
{
    public class Program1
    {
        static int n;
        static int k;
        static string[] words;
        static bool[] studied = new bool[26];
        static int max = 0;

        public static void Main(string[] args)
        {
            string[] tmp = Console.ReadLine().Split();
            n = int.Parse(tmp[0]);
            k = int.Parse(tmp[1]);
            words = new string[n];

            for (int i = 0; i < n; i++)
            {
                words[i] = Console.ReadLine();
            }

            studied['a' - 'a'] = true;
            studied['n' - 'a'] = true;
            studied['t' - 'a'] = true;
            studied['i' - 'a'] = true;
            studied['c' - 'a'] = true;

            if (k < 5)
            {
                Console.WriteLine(max);
            }
            else
            {
                dfs(0, 0);
                Console.WriteLine(max);
            }
        }

        public static void dfs(int idx, int count)
        {
            if (count + 5 == k)
            {
                countWord();
                return;
            }

            for (int i = idx; i < 26; i++)
            {
                if (studied[i] == true)
                {
                    continue;
                }

                studied[i] = true;
                dfs(i + 1, count + 1);
                studied[i] = false;
            }
        }

        public static void countWord()
        {
            int count = 0;
            for (int i = 0; i < n; i++)
            {
                char[] alphas = words[i].ToCharArray();
                bool flag = true;
                foreach (char c in alphas)
                {
                    if (studied[c - 'a'] == false)
                    {
                        flag = false;
                        break;
                    }
                }
                if (flag == true)
                {
                    count++;
                }
            }
            max = Math.Max(max, count);
        }
    }
}