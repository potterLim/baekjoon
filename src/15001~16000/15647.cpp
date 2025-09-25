#include <iostream>
#include <stack>
#include <vector>

struct Edge
{
	int neighbor;
	int length;
};

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int totalNodes;
	std::cin >> totalNodes;

	std::vector<std::vector<Edge>> adjacency(totalNodes + 1);
	adjacency.reserve(totalNodes + 1);

	for (int i = 0; i < totalNodes - 1; ++i)
	{
		int fromNode;
		int toNode;
		int edgeLength;
		std::cin >> fromNode >> toNode >> edgeLength;
		adjacency[fromNode].push_back({toNode, edgeLength});
		adjacency[toNode].push_back({fromNode, edgeLength});
	}

	std::vector<int> parentNode(totalNodes + 1, 0);
	std::vector<long long> distanceFromRoot(totalNodes + 1, 0);
	std::vector<int> dfsOrder;
	dfsOrder.reserve(totalNodes);

	std::stack<int> stackDfs;
	stackDfs.push(1);
	parentNode[1] = -1;

	while (!stackDfs.empty())
	{
		int current = stackDfs.top();
		stackDfs.pop();
		dfsOrder.push_back(current);

		for (int i = 0; i < static_cast<int>(adjacency[current].size()); ++i)
		{
			int next = adjacency[current][i].neighbor;
			int len = adjacency[current][i].length;
			if (next == parentNode[current])
			{
				continue;
			}
			parentNode[next] = current;
			distanceFromRoot[next] = distanceFromRoot[current] + len;
			stackDfs.push(next);
		}
	}

	long long sumDistancesFromRoot = 0;
	for (int i = 1; i <= totalNodes; ++i)
	{
		sumDistancesFromRoot += distanceFromRoot[i];
	}

	std::vector<int> subtreeSize(totalNodes + 1, 1);
	for (int i = static_cast<int>(dfsOrder.size()) - 1; i >= 0; --i)
	{
		int current = dfsOrder[i];
		for (int j = 0; j < static_cast<int>(adjacency[current].size()); ++j)
		{
			int child = adjacency[current][j].neighbor;
			if (child == parentNode[current])
			{
				continue;
			}
			subtreeSize[current] += subtreeSize[child];
		}
	}

	std::vector<long long> totalDistanceSum(totalNodes + 1, 0);
	totalDistanceSum[1] = sumDistancesFromRoot;

	std::stack<int> stackReroot;
	stackReroot.push(1);

	while (!stackReroot.empty())
	{
		int current = stackReroot.top();
		stackReroot.pop();

		for (int i = 0; i < static_cast<int>(adjacency[current].size()); ++i)
		{
			int next = adjacency[current][i].neighbor;
			int len = adjacency[current][i].length;
			if (next == parentNode[current])
			{
				continue;
			}
			totalDistanceSum[next] =
				totalDistanceSum[current] + static_cast<long long>(totalNodes - 2 * subtreeSize[next]) * static_cast<long long>(len);
			stackReroot.push(next);
		}
	}

	for (int i = 1; i <= totalNodes; ++i)
	{
		std::cout << totalDistanceSum[i] << '\n';
	}

	return 0;
}
