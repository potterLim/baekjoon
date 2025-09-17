#include <iostream>
#include <vector>
#include <algorithm>

class DisjointSet
{
private:
	std::vector<int> parent;
	std::vector<int> size;

public:
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
		int rootA = Find(a);
		int rootB = Find(b);
		if (rootA == rootB)
		{
			return false;
		}
		if (size[rootA] < size[rootB])
		{
			std::swap(rootA, rootB);
		}
		parent[rootB] = rootA;
		size[rootA] += size[rootB];
		return true;
	}
};

int main()
{
	while (true)
	{
		int houseCount;
		int roadCount;
		std::cin >> houseCount >> roadCount;

		if (houseCount == 0 && roadCount == 0)
		{
			break;
		}

		std::vector<std::tuple<int, int, int>> edges;
		edges.reserve(roadCount);

		long long totalLength = 0;
		for (int i = 0; i < roadCount; ++i)
		{
			int a;
			int b;
			int w;
			std::cin >> a >> b >> w;
			edges.emplace_back(w, a, b);
			totalLength += w;
		}

		std::sort(edges.begin(), edges.end());

		DisjointSet dsu(houseCount);
		long long mstLength = 0;
		int picked = 0;

		for (int i = 0; i < roadCount && picked < houseCount - 1; ++i)
		{
			int w = std::get<0>(edges[i]);
			int u = std::get<1>(edges[i]);
			int v = std::get<2>(edges[i]);
			if (dsu.Union(u, v))
			{
				mstLength += w;
				++picked;
			}
		}

		std::cout << (totalLength - mstLength) << std::endl;
	}

	return 0;
}
