public class Program
{
    private static void Main()
    {
        string[] fatherColors = Console.ReadLine().Split(' ');
        string[] motherColors = Console.ReadLine().Split(' ');

        HashSet<string> bodyColors = new HashSet<string>();
        HashSet<string> tailColors = new HashSet<string>();

        foreach (string color in fatherColors)
        {
            bodyColors.Add(color);
            tailColors.Add(color);
        }

        foreach (string color in motherColors)
        {
            bodyColors.Add(color);
            tailColors.Add(color);
        }

        List<string> sortedBodyColors = bodyColors.ToList();
        List<string> sortedTailColors = tailColors.ToList();

        sortedBodyColors.Sort();
        sortedTailColors.Sort();

        List<string> results = new List<string>();

        foreach (string body in sortedBodyColors)
        {
            foreach (string tail in sortedTailColors)
            {
                results.Add(body + " " + tail);
            }
        }

        foreach (string result in results)
        {
            Console.WriteLine(result);
        }
    }
}