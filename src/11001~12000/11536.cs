public class Program
{
    static void Main(string[] args)
    {
        int countPeople = int.Parse(Console.ReadLine());
        List<string> NamesPeople = new List<string>(countPeople);

        for (int i = 0; i < countPeople; ++i)
        {
            NamesPeople.Add(Console.ReadLine());
        }

        bool isAscending = NamesPeople.SequenceEqual(NamesPeople.OrderBy(name => name));
        bool isDescending = NamesPeople.SequenceEqual(NamesPeople.OrderByDescending(name => name));

        if (isAscending)
        {
            Console.WriteLine("INCREASING");
        }
        else if (isDescending)
        {
            Console.WriteLine("DECREASING");
        }
        else
        {
            Console.WriteLine("NEITHER");
        }
    }
}