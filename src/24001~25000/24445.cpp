#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <sstream>

int main()
{
	int vertexCount;
	int edgeCount;
	int startVertex;
	std::cin >> vertexCount >> edgeCount >> startVertex;

	std::vector<std::vector<int>> adjacencyList(vertexCount + 1);
	for (int i = 0; i < edgeCount; ++i)
	{
		int u;
		int v;
		std::cin >> u >> v;
		adjacencyList[u].push_back(v);
		adjacencyList[v].push_back(u);
	}
	for (int i = 1; i <= vertexCount; ++i)
	{
		std::sort(adjacencyList[i].begin(), adjacencyList[i].end(), std::greater<int>());
	}

	std::vector<int> visitOrder(vertexCount + 1, 0);
	std::queue<int> bfsQueue;

	int order = 1;
	bfsQueue.push(startVertex);
	visitOrder[startVertex] = -1;

	while (!bfsQueue.empty())
	{
		int currentVertex = bfsQueue.front();
		bfsQueue.pop();

		visitOrder[currentVertex] = order++;
		for (int i = 0; i < static_cast<int>(adjacencyList[currentVertex].size()); ++i)
		{
			int neighbor = adjacencyList[currentVertex][i];
			if (visitOrder[neighbor] == 0)
			{
				visitOrder[neighbor] = -1;
				bfsQueue.push(neighbor);
			}
		}
	}

	std::ostringstream out;
	for (int i = 1; i <= vertexCount; ++i)
	{
		out << visitOrder[i] << '\n';
	}
	std::cout << out.str();

	return 0;
}
