class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();
        string[] suffixes = new string[input.Length];

        for (int i = 0; i < input.Length; i++)
        {
            suffixes[i] = input.Substring(i);
        }

        Array.Sort(suffixes);

        foreach (string suffix in suffixes)
        {
            Console.WriteLine(suffix);
        }
    }
}