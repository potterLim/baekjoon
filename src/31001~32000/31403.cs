public class Program
{
    static void Main(string[] args)
    {
        string A = Console.ReadLine();
        string B = Console.ReadLine();
        string C = Console.ReadLine();

        Console.WriteLine(int.Parse(A) + int.Parse(B) - int.Parse(C));
        Console.WriteLine(int.Parse(A + B) - int.Parse(C));
    }
}