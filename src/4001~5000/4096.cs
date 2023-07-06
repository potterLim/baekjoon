namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            while (true)
            {
                string n = Console.ReadLine().Trim();
                int count = 0;

                if (n == "0")
                {
                    break;
                }
                else if (n == ReverseString(n))
                {
                    Console.WriteLine(count);
                    continue;
                }
                else
                {
                    int tmp = n.Length;
                    while (n != ReverseString(n))
                    {
                        count++;
                        int t = int.Parse(n) + 1;
                        n = t.ToString().PadLeft(tmp, '0');
                    }
                    Console.WriteLine(count);
                    continue;
                }
            }
        }

        static string ReverseString(string s)
        {
            char[] charArray = s.ToCharArray();
            Array.Reverse(charArray);
            return new string(charArray);
        }
    }
}