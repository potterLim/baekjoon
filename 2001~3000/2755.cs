namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            Dictionary<string, double> grades = new Dictionary<string, double>()
            {
                {"A+", 4.3}, {"A0", 4.0}, {"A-", 3.7},
                {"B+", 3.3}, {"B0", 3.0}, {"B-", 2.7},
                {"C+", 2.3}, {"C0", 2.0}, {"C-", 1.7},
                {"D+", 1.3}, {"D0", 1.0}, {"D-", 0.7},
            };

            int n = int.Parse(Console.ReadLine());
            double sum = 0;
            double totalCredit = 0;

            for (int i = 0; i < n; i++)
            {
                string[] input = Console.ReadLine().Split(' ');
                double credit = double.Parse(input[1]);
                totalCredit += credit;
                sum += credit * grades.GetValueOrDefault(input[2]);
            }

            double avg = sum / totalCredit;
            Console.WriteLine($"{Math.Round(avg, 2):f2}");
        }
    }
}