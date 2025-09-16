#include <iostream>
#include <vector>

struct DisjointSet
{
	std::vector<int> parent;
	std::vector<int> rank;

	explicit DisjointSet(int n)
	{
		parent.resize(n + 1);
		rank.assign(n + 1, 0);
		for (int i = 1; i <= n; ++i)
		{
			parent[i] = i;
		}
	}

	int Find(int x)
	{
		if (parent[x] != x)
		{
			parent[x] = Find(parent[x]);
		}
		return parent[x];
	}

	void Union(int a, int b)
	{
		int ra = Find(a);
		int rb = Find(b);
		if (ra == rb)
		{
			return;
		}
		if (rank[ra] < rank[rb])
		{
			parent[ra] = rb;
		}
		else if (rank[ra] > rank[rb])
		{
			parent[rb] = ra;
		}
		else
		{
			parent[rb] = ra;
			rank[ra] += 1;
		}
	}
};

int main()
{
	int cityCount;
	std::cin >> cityCount;

	int planLength;
	std::cin >> planLength;

	DisjointSet dsu(cityCount);

	for (int i = 1; i <= cityCount; ++i)
	{
		for (int j = 1; j <= cityCount; ++j)
		{
			int connected;
			std::cin >> connected;
			if (connected == 1)
			{
				dsu.Union(i, j);
			}
		}
	}

	std::vector<int> plan(planLength);
	for (int i = 0; i < planLength; ++i)
	{
		std::cin >> plan[i];
	}

	bool possible = true;
	int root = dsu.Find(plan[0]);
	for (int i = 1; i < planLength; ++i)
	{
		if (dsu.Find(plan[i]) != root)
		{
			possible = false;
			break;
		}
	}

	if (possible)
	{
		std::cout << "YES" << std::endl;
	}
	else
	{
		std::cout << "NO" << std::endl;
	}

	return 0;
}
