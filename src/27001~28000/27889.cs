namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string str = Console.ReadLine();
            if (str.Equals("NLCS"))
            {
                Console.WriteLine("North London Collegiate School");
            } 
            else if (str.Equals("BHA"))
            {
                Console.WriteLine("Branksome Hall Asia");
            }
            else if (str.Equals("KIS"))
            {
                Console.WriteLine("Korea International School");
            }
            else if (str.Equals("SJA"))
            {
                Console.WriteLine("St. Johnsbury Academy");
            }
        }
    }
}