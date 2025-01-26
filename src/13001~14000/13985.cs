public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();
        int num1 = int.Parse(input[0].ToString());
        int num2 = int.Parse(input[4].ToString());
        int sum = int.Parse(input[8].ToString());

        if (num1 + num2 == sum)
        {
            Console.WriteLine("YES");
        }
        else
        {
            Console.WriteLine("NO");
        }
    }
}