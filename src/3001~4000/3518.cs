using System.Text;

public class Program
{
    public static void Main(string[] args)
    {
        List<string> lines = new List<string>();
        string inputLine;

        while ((inputLine = Console.ReadLine()) != null)
        {
            lines.Add(inputLine.Trim());
        }

        List<string[]> wordsByLine = lines.Select(line => line.Split(new[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)).ToList();

        int maxColumns = wordsByLine.Max(words => words.Length);
        int[] maxColumnWidths = new int[maxColumns];

        foreach (var words in wordsByLine)
        {
            for (int i = 0; i < words.Length; i++)
            {
                maxColumnWidths[i] = Math.Max(maxColumnWidths[i], words[i].Length);
            }
        }

        StringBuilder result = new StringBuilder();

        foreach (var words in wordsByLine)
        {
            for (int i = 0; i < words.Length; i++)
            {
                result.Append(words[i]);

                if (i < words.Length - 1)
                {
                    int padding = maxColumnWidths[i] - words[i].Length + 1;
                    result.Append(' ', padding);
                }
            }

            result.AppendLine();
        }

        Console.Write(result.ToString());
    }
}