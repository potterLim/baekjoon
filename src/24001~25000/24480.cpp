#include <iostream>
#include <vector>
#include <stack>
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
	std::vector<size_t> nextIndex(vertexCount + 1, 0);
	std::stack<int> dfsStack;

	int order = 1;
	dfsStack.push(startVertex);

	while (!dfsStack.empty())
	{
		int currentVertex = dfsStack.top();

		if (visitOrder[currentVertex] == 0)
		{
			visitOrder[currentVertex] = order++;
		}

		bool pushed = false;
		while (nextIndex[currentVertex] < adjacencyList[currentVertex].size())
		{
			int neighbor = adjacencyList[currentVertex][nextIndex[currentVertex]];
			++nextIndex[currentVertex];

			if (visitOrder[neighbor] == 0)
			{
				dfsStack.push(neighbor);
				pushed = true;
				break;
			}
		}

		if (!pushed)
		{
			dfsStack.pop();
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
