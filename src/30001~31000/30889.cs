public class Program
{
    static void Main(string[] args)
    {
        bool[,] seats = new bool[10, 20];
        int countPerson = int.Parse(Console.ReadLine());
        int row;
        int column;

        for (int i = 0; i < countPerson; ++i)
        {
            string str = Console.ReadLine();

            Console.WriteLine(str[0].ToString());
            row = str[0] - 'A';
            column = int.Parse(str.Substring(1)) - 1;

            seats[row, column] = true;
        }

        for (int i = 0; i < 10; ++i)
        {
            for (int j = 0; j < 20; ++j)
            {
                if (seats[i, j] == true)
                {
                    Console.Write("o");
                }
                else
                {
                    Console.Write(".");
                }
            }
            Console.WriteLine();
        }
    }
}