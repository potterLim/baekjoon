namespace BOJ
{
    public class Program
    {
        public static void Main()
        {
            string source = Console.ReadLine();
            string dest = Console.ReadLine();

            string result = buildShortestPathDescription(source, dest);
            Console.WriteLine(result);
        }

        private static string buildShortestPathDescription(string source, string dest)
        {
            string[] sSplit = source.Split('/');
            string[] tSplit = dest.Split('/');
            int sCount = sSplit.Length;
            int tCount = tSplit.Length;

            int prefixLen = 0;
            for (int i = 0; i < sCount && i < tCount; ++i)
            {
                if (sSplit[i] == tSplit[i])
                {
                    prefixLen++;
                }
                else
                {
                    break;
                }
            }

            int suffixLen = 0;
            for (int i = 0; i < sCount - prefixLen && i < tCount - prefixLen; ++i)
            {
                bool bSame = sSplit[sCount - 1 - i] == tSplit[tCount - 1 - i];
                if (bSame)
                {
                    suffixLen++;
                }
                else
                {
                    break;
                }
            }

            string[] aParts = new string[prefixLen];
            for (int i = 0; i < prefixLen; ++i)
            {
                aParts[i] = sSplit[i];
            }
            string A = aParts.Length > 0 ? string.Join("/", aParts) + "/" : string.Empty;

            int bCount = sCount - prefixLen - suffixLen;
            string[] bParts = new string[bCount];
            for (int i = 0; i < bCount; ++i)
            {
                bParts[i] = sSplit[prefixLen + i];
            }
            string B = string.Join("/", bParts);

            int cCount = tCount - prefixLen - suffixLen;
            string[] cParts = new string[cCount];
            for (int i = 0; i < cCount; ++i)
            {
                cParts[i] = tSplit[prefixLen + i];
            }
            string C = string.Join("/", cParts);

            string[] dParts = new string[suffixLen];
            for (int i = 0; i < suffixLen; ++i)
            {
                dParts[i] = sSplit[sCount - suffixLen + i];
            }
            string D = dParts.Length > 0 ? "/" + string.Join("/", dParts) : string.Empty;

            string result = A + "{" + B + " => " + C + "}" + D;
            return result;
        }
    }
}
