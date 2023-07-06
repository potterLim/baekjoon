namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            int[] nums = Array.ConvertAll(Console.ReadLine().Split(' '), int.Parse);

            int[] dp = new int[n];
            dp[0] = nums[0];
            int max = dp[0];

            for (int i = 1; i < n; i++)
            {
                dp[i] = Math.Max(dp[i - 1] + nums[i], nums[i]);
                max = Math.Max(max, dp[i]);
            }

            Console.WriteLine(max);
        }
    }
}