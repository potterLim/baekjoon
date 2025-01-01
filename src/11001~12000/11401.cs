public class Program
{
    static void Main(string[] args)
    {
        const int MOD = 1000000007;

        string[] inputs = Console.ReadLine().Split();
        int N = int.Parse(inputs[0]);
        int K = int.Parse(inputs[1]);

        long[] fact = new long[N + 1];
        long[] ifact = new long[N + 1];
        fact[0] = 1;

        for (int i = 1; i <= N; i++)
        {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        ifact[N] = ModPow(fact[N], MOD - 2, MOD);
        for (int i = N - 1; i >= 0; i--)
        {
            ifact[i] = (ifact[i + 1] * (i + 1)) % MOD;
        }

        long result = (fact[N] * ifact[K] % MOD) * ifact[N - K] % MOD;
        Console.WriteLine(result);
    }

    static long ModPow(long baseValue, long exp, int mod)
    {
        long result = 1;
        while (exp > 0)
        {
            if ((exp & 1) == 1)
            {
                result = (result * baseValue) % mod;
            }
            baseValue = (baseValue * baseValue) % mod;
            exp >>= 1;
        }
        return result;
    }
}