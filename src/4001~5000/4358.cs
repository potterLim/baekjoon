using System.Text;

public class Program
{
    public static void Main(string[] args)
    {
        Dictionary<string, int> treeCounts = new Dictionary<string, int>();
        int totalCount = 0;
        string inputLine;

        while ((inputLine = Console.ReadLine()) != null)
        {
            if (!treeCounts.ContainsKey(inputLine))
            {
                treeCounts[inputLine] = 0;
            }

            treeCounts[inputLine]++;
            totalCount++;
        }

        var sortedTrees = treeCounts.OrderBy(tree => tree.Key);
        StringBuilder result = new StringBuilder();

        foreach (var tree in sortedTrees)
        {
            string treeName = tree.Key;
            double percentage = (tree.Value / (double)totalCount) * 100;
            result.AppendLine($"{treeName} {percentage:F4}");
        }

        Console.Write(result.ToString());
    }
}