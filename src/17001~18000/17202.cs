public class Program
{
    static void Main(string[] args)
    {
        string phoneNumberA = Console.ReadLine();
        string phoneNumberB = Console.ReadLine();

        List<int> combinedNumber = new List<int>();

        for (int i = 0; i < phoneNumberA.Length; i++)
        {
            combinedNumber.Add(phoneNumberA[i] - '0');
            combinedNumber.Add(phoneNumberB[i] - '0');
        }

        while (combinedNumber.Count > 2)
        {
            List<int> nextNumbers = new List<int>();

            for (int i = 0; i < combinedNumber.Count - 1; i++)
            {
                int sum = combinedNumber[i] + combinedNumber[i + 1];
                nextNumbers.Add(sum % 10);
            }

            combinedNumber = nextNumbers;
        }

        Console.WriteLine($"{combinedNumber[0]}{combinedNumber[1]}");
    }
}