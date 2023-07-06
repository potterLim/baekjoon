namespace ConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            string n = Console.ReadLine();
            int i;

            for (i = 1; i < n.Length; i++)
            {
                int front = 1;
                int back = 1;
                for (int j = 0; j < i; j++)
                {
                    front *= (n[j] - '0');
                }

                for (int j = i; j < n.Length; j++)
                {
                    back *= (n[j] - '0');
                }

                if (front == back)
                {
                    Console.WriteLine("YES");
                    break;
                }

            }

            if (i == n.Length)
            {
                Console.WriteLine("NO");
            }
        }
    }
}