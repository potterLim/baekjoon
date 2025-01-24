public class Program
{
    private static readonly Dictionary<string, double> GradeToPointMap = new Dictionary<string, double>
        {
            {"A+", 4.5}, {"A", 4.0}, {"B+", 3.5}, {"B", 3.0}, {"C+", 2.5},
            {"C", 2.0}, {"D+", 1.5}, {"D", 1.0}, {"F", 0.0}
        };

    public static void Main()
    {
        string input = Console.ReadLine();
        double totalPoints = 0.0;
        int gradeCount = 0;

        for (int i = 0; i < input.Length; i++)
        {
            string grade;
            if (i + 1 < input.Length && input[i + 1] == '+')
            {
                grade = input.Substring(i, 2);
                i++;
            }
            else
            {
                grade = input[i].ToString();
            }

            if (GradeToPointMap.TryGetValue(grade, out double points))
            {
                totalPoints += points;
                gradeCount++;
            }
        }

        double average = totalPoints / gradeCount;
        Console.WriteLine($"{average}");
    }
}