namespace ConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            List<string> strs = new List<string>(n);

            for (int i = 0; i < n; i++)
            {
                strs.Add(Console.ReadLine());
            }

            int[] count = new int[strs[0].Length];

            for (int i = 1; i < n; i++)
            {
                for (int j = 0; j < strs[0].Length; j++)
                {
                    if (strs[i][j] == strs[0][j])
                    {
                        count[j]++;
                    }
                }
            }

            for(int i = 0; i < strs[0].Length; i++)
            {
                if (count[i] == n - 1)
                {
                    Console.Write(strs[0][i]);
                }
                else
                {
                    Console.Write('?');
                }
            }
        }
    }
}