public class Program
{
    static void Main(string[] args)
    {

        const int MOD = 1000000000;
        string[] inputs = Console.ReadLine().Split();
        int N = int.Parse(inputs[0]);
        int K = int.Parse(inputs[1]);

        int[,] dp = new int[K + 1, N + 1];

        for (int n = 0; n <= N; n++)
        {
            dp[1, n] = 1;
        }

        for (int k = 2; k <= K; k++)
        {
            for (int n = 0; n <= N; n++)
            {
                dp[k, n] = dp[k - 1, n];
                if (n > 0)
                {
                    dp[k, n] += dp[k, n - 1];
                }
                dp[k, n] %= MOD;
            }
        }

        Console.WriteLine(dp[K, N]);
    }
}