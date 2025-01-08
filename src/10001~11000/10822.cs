public class Program
{
    static void Main(string[] args)
    {
        string[] strNums = Console.ReadLine().Split(',');
        int sum = 0;

        for (int i = 0; i < strNums.Length; ++i)
        {
            sum += int.Parse(strNums[i]);
        }

        Console.WriteLine(sum);
    }
}
