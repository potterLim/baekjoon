using System;

public class Program
{
    private static int[,] mCostMatrix;
    private static int[] mMinCostCache;
    private static int mCountTask;

    public static void Main(string[] args)
    {
        mCountTask = int.Parse(Console.ReadLine());
        mCostMatrix = new int[mCountTask, mCountTask];
        mMinCostCache = new int[1 << mCountTask];
        Array.Fill(mMinCostCache, -1);

        for (int i = 0; i < mCountTask; i++)
        {
            string[] input = Console.ReadLine().Split();
            for (int j = 0; j < mCountTask; j++)
            {
                mCostMatrix[i, j] = int.Parse(input[j]);
            }
        }

        Console.WriteLine(Solve(0, 0));
    }

    private static int Solve(int person, int mask)
    {
        if (person == mCountTask)
            return 0;

        if (mMinCostCache[mask] != -1)
            return mMinCostCache[mask];

        int minCost = int.MaxValue;

        for (int task = 0; task < mCountTask; task++)
        {
            if ((mask & (1 << task)) == 0)
            {
                int newMask = mask | (1 << task);
                minCost = Math.Min(minCost, mCostMatrix[person, task] + Solve(person + 1, newMask));
            }
        }

        mMinCostCache[mask] = minCost;
        return minCost;
    }
}