public class Program
{
    private static void Main()
    {
        int testCaseCount = int.Parse(Console.ReadLine());

        for (int i = 0; i < testCaseCount; ++i)
        {
            int schoolCount = int.Parse(Console.ReadLine());
            string maxAlcoholSchool = string.Empty;
            int maxAlcohol = 0;

            for (int j = 0; j < schoolCount; ++j)
            {
                string[] input = Console.ReadLine().Split(' ');
                string schoolName = input[0];
                int alcoholAmount = int.Parse(input[1]);

                if (alcoholAmount > maxAlcohol)
                {
                    maxAlcohol = alcoholAmount;
                    maxAlcoholSchool = schoolName;
                }
            }

            Console.WriteLine(maxAlcoholSchool);
        }
    }
}