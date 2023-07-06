namespace ConsoleApp1
{
    class Program
    {
        public static void Main(string[] args)
        {
            int N = int.Parse(Console.ReadLine());
            List<string> studentNumberList = new List<string>();

            for (int i = 0; i < N; i++)
            {
                studentNumberList.Add(Console.ReadLine());
            }

            int numberSize = studentNumberList[0].Length;

            for (int i = 0; i < numberSize; i++)
            {
                List<string> subStringList = new List<string>();
                for (int j = 0; j < N; j++)
                {
                    string tempNumber = studentNumberList[j];

                    tempNumber = tempNumber.Substring(numberSize - i - 1, numberSize - (numberSize - i - 1));

                    if (!subStringList.Contains(tempNumber))
                    {
                        subStringList.Add(tempNumber);
                    }
                    else
                    {
                        break;
                    }
                }

                if (N == subStringList.Count)
                {
                    Console.WriteLine(i + 1);
                    break;
                }
            }
        }
    }
}