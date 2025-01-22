public class Program
{
    static void Main(string[] args)
    {
        bool[] isFBI = new bool[5];
        bool existFBI = false;
        for (int i = 0; i < 5; ++i)
        {
            string input = Console.ReadLine();
            for (int j = 0; j < input.Length - 2; ++j)
            {
                if (input.Substring(j, 3) == "FBI")
                {
                    isFBI[i] = true;
                    existFBI = true;
                }
            }
        }

        if (existFBI)
        {
            for (int i = 0; i < 5; ++i)
            {
                if (isFBI[i])
                {
                    Console.Write($"{i + 1} ");
                }
            }
        }
        else
        {
            Console.WriteLine("HE GOT AWAY!");
        }
    }
}