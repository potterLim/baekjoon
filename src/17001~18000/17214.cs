public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();
        List<string> terms = new List<string>();
        string result = string.Empty;
        
        int idxStart = 0;
        for (int i = 0; i < input.Length; ++i)
        {
            if (i != 0 && (input[i] == '+' || input[i] == '-'))
            {
                terms.Add(input.Substring(idxStart, i - idxStart));
                idxStart = i;
            }
        }
        terms.Add(input.Substring(idxStart, input.Length - idxStart));

        for (int i = 0; i < terms.Count; ++i)
        {
            if (i != 0 && terms[i][0] == '+')
            {
                result += "+";
            }
            result += integrateSingleTerm(terms[i]);
        }

        if (string.IsNullOrEmpty(result))
        {
            result += "W";
        }
        else
        {
            result += "+W";
        }
        Console.WriteLine(result);
    }

    private static string integrateSingleTerm(string singleTerm)
    {
        string result = string.Empty;

        if (singleTerm == "0")
        {
            return result;
        }

        double coefficient = 1.0;
        int degree = 0;

        for (int i = 0; i < singleTerm.Length; ++i)
        {
            if (singleTerm[i] == 'x')
            {
                degree++;
            }
            if (i != 0 && degree == 1)
            {
                if (singleTerm[i - 1] == '-')
                {
                    coefficient = -1.0;
                }
                else
                {
                    coefficient = double.Parse(singleTerm.Substring(0, i));
                }
            }
        }

        if (degree == 0)
        {
            coefficient = double.Parse(singleTerm);
        }

        if (coefficient < 0)
        {
            result += "-" + (Math.Abs(coefficient) / (degree + 1));
        }
        else
        {
            result += (coefficient / (degree + 1));
        }

        for (int i = 0; i <= degree; ++i)
        {
            result += "x";
        }

        if (result.StartsWith("1x"))
        {
            result = result.Remove(0, 1);
        }
        else if (result.StartsWith("-1x"))
        {
            result = result.Remove(1, 1);
        }

        return result;
    }
}