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
		mParent.resize(count);
		mSize.assign(count, 1);
		for (int i = 0; i < count; ++i)
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
	int starCount;
	std::cin >> starCount;

	std::vector<std::pair<double, double>> coordinates(starCount);
	for (int i = 0; i < starCount; ++i)
	{
		double x;
		double y;
		std::cin >> x >> y;
		coordinates[i] = std::make_pair(x, y);
	}

	std::vector<Edge> edges;
	edges.reserve(starCount * (starCount - 1) / 2);
	for (int i = 0; i < starCount; ++i)
	{
		for (int j = i + 1; j < starCount; ++j)
		{
			double dx = coordinates[i].first - coordinates[j].first;
			double dy = coordinates[i].second - coordinates[j].second;
			double dist = std::sqrt(dx * dx + dy * dy);

			Edge e;
			e.fromIndex = i;
			e.toIndex = j;
			e.distance = dist;
			edges.push_back(e);
		}
	}

	std::sort(edges.begin(), edges.end(), EdgeLess);

	DisjointSet disjointSet(starCount);
	double totalCost = 0.0;
	int selectedEdges = 0;

	for (int i = 0; i < static_cast<int>(edges.size()) && selectedEdges < starCount - 1; ++i)
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
