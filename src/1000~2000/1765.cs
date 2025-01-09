using System;

public class Program
{
    private static int[] parent;
    private static int[] rank;

    public static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());
        int m = int.Parse(Console.ReadLine());

        InitializeUnionFind(n);

        for (int i = 0; i < m; i++)
        {
            string[] relation = Console.ReadLine().Split();
            char type = relation[0][0];
            int p = int.Parse(relation[1]);
            int q = int.Parse(relation[2]);

            if (type == 'F')
            {
                Union(p, q);
            }
            else if (type == 'E')
            {
                int pEnemy = GetEnemy(p);
                int qEnemy = GetEnemy(q);

                if (pEnemy != -1)
                {
                    Union(pEnemy, q);
                }

                if (qEnemy != -1)
                {
                    Union(qEnemy, p);
                }

                SetEnemy(p, q);
            }
        }

        int teamCount = CountTeams(n);
        Console.WriteLine(teamCount);
    }

    private static void InitializeUnionFind(int n)
    {
        parent = new int[2 * n + 1];
        rank = new int[2 * n + 1];

        for (int i = 1; i <= 2 * n; i++)
        {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    private static int Find(int x)
    {
        if (parent[x] != x)
        {
            parent[x] = Find(parent[x]);
        }

        return parent[x];
    }

    private static void Union(int x, int y)
    {
        int rootX = Find(x);
        int rootY = Find(y);

        if (rootX != rootY)
        {
            if (rank[rootX] < rank[rootY])
            {
                parent[rootX] = rootY;
            }
            else if (rank[rootX] > rank[rootY])
            {
                parent[rootY] = rootX;
            }
            else
            {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    private static int GetEnemy(int x)
    {
        if (x + parent.Length / 2 < parent.Length)
        {
            return Find(x + parent.Length / 2);
        }

        return -1;
    }

    private static void SetEnemy(int x, int y)
    {
        int enemyX = x + parent.Length / 2;
        int enemyY = y + parent.Length / 2;

        parent[enemyX] = Find(y);
        parent[enemyY] = Find(x);
    }

    private static int CountTeams(int n)
    {
        bool[] visited = new bool[2 * n + 1];
        int count = 0;

        for (int i = 1; i <= n; i++)
        {
            int root = Find(i);
            if (!visited[root])
            {
                visited[root] = true;
                count++;
            }
        }

        return count;
    }
}
