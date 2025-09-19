#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <iomanip>

class DisjointSet
{
private:
	std::vector<int> mParent;
	std::vector<int> mSize;

public:
	explicit DisjointSet(int count)
	{
		mParent.resize(count + 1);
		mSize.assign(count + 1, 1);
		for (int i = 1; i <= count; ++i)
		{
			mParent[i] = i;
		}
	}

	int Find(int x)
	{
		if (mParent[x] != x)
		{
			mParent[x] = Find(mParent[x]);
		}
		return mParent[x];
	}

	bool Union(int a, int b)
	{
		int rootA = Find(a);
		int rootB = Find(b);
		if (rootA == rootB)
		{
			return false;
		}
		if (mSize[rootA] < mSize[rootB])
		{
			std::swap(rootA, rootB);
		}
		mParent[rootB] = rootA;
		mSize[rootA] += mSize[rootB];
		return true;
	}
};

struct Edge
{
	int fromIndex;
	int toIndex;
	double distance;
};

bool EdgeLess(const Edge& a, const Edge& b)
{
	return a.distance < b.distance;
}

int main()
{
	int godCount;
	int preConnectedCount;
	std::cin >> godCount >> preConnectedCount;

	std::vector<std::pair<long long, long long>> coord(godCount + 1);
	for (int i = 1; i <= godCount; ++i)
	{
		long long x;
		long long y;
		std::cin >> x >> y;
		coord[i].first = x;
		coord[i].second = y;
	}

	DisjointSet disjointSet(godCount);

	for (int i = 0; i < preConnectedCount; ++i)
	{
		int a;
		int b;
		std::cin >> a >> b;
		disjointSet.Union(a, b);
	}

	std::vector<Edge> edges;
	edges.reserve(godCount * (godCount - 1) / 2);
	for (int i = 1; i <= godCount; ++i)
	{
		for (int j = i + 1; j <= godCount; ++j)
		{
			long long dx = coord[i].first - coord[j].first;
			long long dy = coord[i].second - coord[j].second;
			double dist = std::sqrt(static_cast<long double>(dx) * dx + static_cast<long double>(dy) * dy);

			Edge e;
			e.fromIndex = i;
			e.toIndex = j;
			e.distance = dist;
			edges.push_back(e);
		}
	}

	std::sort(edges.begin(), edges.end(), EdgeLess);

	double totalCost = 0.0;
	int selectedEdges = 0;
	for (int i = 0; i < static_cast<int>(edges.size()) && selectedEdges < godCount - 1; ++i)
	{
		if (disjointSet.Union(edges[i].fromIndex, edges[i].toIndex))
		{
			totalCost += edges[i].distance;
			++selectedEdges;
		}
	}

	std::cout << std::fixed << std::setprecision(2) << totalCost << std::endl;
	return 0;
}
