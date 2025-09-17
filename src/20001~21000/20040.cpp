#include <iostream>
#include <vector>

struct DisjointSet
{
	std::vector<int> parent;
	std::vector<int> size;

	explicit DisjointSet(int n)
	{
		parent.resize(n);
		size.assign(n, 1);
		for (int i = 0; i < n; ++i)
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

	bool Union(int a, int b)
	{
		int ra = Find(a);
		int rb = Find(b);
		if (ra == rb)
		{
			return false;
		}
		if (size[ra] < size[rb])
		{
			std::swap(ra, rb);
		}
		parent[rb] = ra;
		size[ra] += size[rb];
		return true;
	}
};

int main()
{
	int vertexCount;
	int turnCount;
	std::cin >> vertexCount >> turnCount;

	DisjointSet dsu(vertexCount);

	for (int i = 1; i <= turnCount; ++i)
	{
		int u;
		int v;
		std::cin >> u >> v;

		if (!dsu.Union(u, v))
		{
			std::cout << i << std::endl;
			return 0;
		}
	}

	std::cout << 0 << std::endl;
	return 0;
}
