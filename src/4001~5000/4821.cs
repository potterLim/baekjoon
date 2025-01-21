public class Program
{
    public static void Main(string[] args)
    {
        while (true)
        {
            string pageCountInput = Console.ReadLine();
            if (pageCountInput == "0")
            {
                break;
            }

            int totalPages = int.Parse(pageCountInput);
            string rangesInput = Console.ReadLine();

            HashSet<int> pagesToPrint = new HashSet<int>();

            string[] ranges = rangesInput.Split(',');
            foreach (string range in ranges)
            {
                string[] bounds = range.Split('-');

                if (bounds.Length == 1)
                {
                    int singlePage = int.Parse(bounds[0]);
                    if (singlePage >= 1 && singlePage <= totalPages)
                    {
                        pagesToPrint.Add(singlePage);
                    }
                }
                else if (bounds.Length == 2)
                {
                    int low = int.Parse(bounds[0]);
                    int high = int.Parse(bounds[1]);

                    if (low > high)
                    {
                        continue;
                    }

                    for (int page = Math.Max(1, low); page <= Math.Min(totalPages, high); page++)
                    {
                        pagesToPrint.Add(page);
                    }
                }
            }

            Console.WriteLine(pagesToPrint.Count);
        }
    }
}