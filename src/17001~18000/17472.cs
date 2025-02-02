public static class Program
{
    private static int[,] mMap;
    private static int mN;
    private static int mM;
    private static readonly int[] DIRECTION_X = { 0, 0, -1, 1 };
    private static readonly int[] DIRECTION_Y = { -1, 1, 0, 0 };
    private static List<(int, int, int)> mBridges;
    private static int[] mParent;

    private static void Main()
    {
        string[] dimensions = Console.ReadLine().Split();
        mN = int.Parse(dimensions[0]);
        mM = int.Parse(dimensions[1]);
        mMap = new int[mN, mM];

        for (int i = 0; i < mN; i++)
        {
            string[] line = Console.ReadLine().Split();
            for (int j = 0; j < mM; j++)
            {
                mMap[i, j] = int.Parse(line[j]);
            }
        }

        int islandCount = labelIslands();
        mBridges = findBridges(islandCount);
        int result = minimumSpanningTree(islandCount);
        Console.WriteLine(result);
    }

    private static int labelIslands()
    {
        int islandId = 1;
        bool[,] bVisited = new bool[mN, mM];

        for (int i = 0; i < mN; i++)
        {
            for (int j = 0; j < mM; j++)
            {
                if (mMap[i, j] == 1 && !bVisited[i, j])
                {
                    markIslandRecursive(i, j, islandId++, bVisited);
                }
            }
        }

        return islandId - 1;
    }

    private static void markIslandRecursive(int x, int y, int islandId, bool[,] bVisited)
    {
        Queue<(int, int)> queue = new Queue<(int, int)>();
        queue.Enqueue((x, y));
        bVisited[x, y] = true;
        mMap[x, y] = islandId;

        while (queue.Count > 0)
        {
            (int cx, int cy) = queue.Dequeue();
            for (int i = 0; i < 4; i++)
            {
                int nx = cx + DIRECTION_X[i];
                int ny = cy + DIRECTION_Y[i];
                if (nx >= 0 && ny >= 0 && nx < mN && ny < mM && mMap[nx, ny] == 1 && !bVisited[nx, ny])
                {
                    queue.Enqueue((nx, ny));
                    bVisited[nx, ny] = true;
                    mMap[nx, ny] = islandId;
                }
            }
        }
    }

    private static List<(int, int, int)> findBridges(int islandCount)
    {
        List<(int, int, int)> bridges = new List<(int, int, int)>();
        for (int i = 0; i < mN; i++)
        {
            for (int j = 0; j < mM; j++)
            {
                if (mMap[i, j] > 0)
                {
                    for (int d = 0; d < 4; d++)
                    {
                        int length = 0, nx = i, ny = j;
                        while (true)
                        {
                            nx += DIRECTION_X[d];
                            ny += DIRECTION_Y[d];
                            if (nx < 0 || ny < 0 || nx >= mN || ny >= mM || mMap[nx, ny] == mMap[i, j])
                                break;
                            if (mMap[nx, ny] > 0)
                            {
                                if (length > 1)
                                {
                                    bridges.Add((mMap[i, j], mMap[nx, ny], length));
                                }
                                break;
                            }
                            length++;
                        }
                    }
                }
            }
        }
        return bridges;
    }

    private static int minimumSpanningTree(int islandCount)
    {
        mBridges.Sort((a, b) => a.Item3.CompareTo(b.Item3));
        mParent = Enumerable.Range(0, islandCount + 1).ToArray();

        int totalLength = 0, edgesUsed = 0;
        foreach ((int a, int b, int length) in mBridges)
        {
            if (find(a) != find(b))
            {
                union(a, b);
                totalLength += length;
                edgesUsed++;
            }
        }

        if (edgesUsed == islandCount - 1)
        {
            return totalLength;
        }
        else
        {
            return -1;
        }
    }

    private static int find(int x)
    {
        while (mParent[x] != x)
        {
            mParent[x] = mParent[mParent[x]];
            x = mParent[x];
        }
        return x;
    }

    private static void union(int a, int b)
    {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB)
        {
            mParent[rootB] = rootA;
        }
    }
}