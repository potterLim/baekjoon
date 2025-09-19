#include <iostream>
#include <vector>
#include <algorithm>

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
	int fromVertex;
	int toVertex;
	int weight;
};

bool EdgeLess(const Edge& a, const Edge& b)
{
	return a.weight < b.weight;
}

int main()
{
	int vertexCount;
	int edgeCount;
	std::cin >> vertexCount >> edgeCount;

	std::vector<Edge> edges;
	edges.resize(edgeCount);
	for (int i = 0; i < edgeCount; ++i)
	{
		int from;
		int to;
		int weight;
		std::cin >> from >> to >> weight;
		edges[i].fromVertex = from;
		edges[i].toVertex = to;
		edges[i].weight = weight;
	}

	std::sort(edges.begin(), edges.end(), EdgeLess);

	DisjointSet disjointSet(vertexCount);
	long long totalWeight = 0;
	int selectedEdges = 0;

	for (int i = 0; i < edgeCount && selectedEdges < vertexCount - 1; ++i)
	{
		if (disjointSet.Union(edges[i].fromVertex, edges[i].toVertex))
		{
			totalWeight += static_cast<long long>(edges[i].weight);
			++selectedEdges;
		}
	}

	std::cout << totalWeight << std::endl;
	return 0;
}
