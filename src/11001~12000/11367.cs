public class Program
{
    public static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());

        for (int i = 0; i < n; i++)
        {
            string input = Console.ReadLine();
            string[] tokens = input.Split(' ');
            string name = tokens[0];
            int grade = int.Parse(tokens[1]);

            string letterGrade = getLetterGrade(grade);
            Console.WriteLine($"{name} {letterGrade}");
        }
    }

    private static string getLetterGrade(int grade)
    {
        if (grade >= 97)
        {
            return "A+";
        }
        else if (grade >= 90)
        {
            return "A";
        }
        else if (grade >= 87)
        {
            return "B+";
        }
        else if (grade >= 80)
        {
            return "B";
        }
        else if (grade >= 77)
        {
            return "C+";
        }
        else if (grade >= 70)
        {
            return "C";
        }
        else if (grade >= 67)
        {
            return "D+";
        }
        else if (grade >= 60)
        {
            return "D";
        }
        else
        {
            return "F";
        }
    }
}