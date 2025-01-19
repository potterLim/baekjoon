public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();
        bool isPalindrome = true;

        for (int i = 0; i < (input.Length - 1) / 2; ++i)
        {
            if (input[i] != input[input.Length - 1 - i])
            {
                isPalindrome = false;
                break;
            }
        }

        Console.WriteLine(isPalindrome ? "true" : "false");
    }
}