#include <iostream>
#include <vector>
#include <queue>

static bool CheckIfBipartite(const int vertexCount, const std::vector<std::vector<int>>& adjacencyList)
{
	std::vector<int> vertexColor(vertexCount, 0);

	for (int startVertex = 0; startVertex < vertexCount; ++startVertex)
	{
		if (vertexColor[startVertex] != 0)
		{
			continue;
		}

		std::queue<int> bfsQueue;
		vertexColor[startVertex] = 1;
		bfsQueue.push(startVertex);

		while (!bfsQueue.empty())
		{
			int currentVertex = bfsQueue.front();
			bfsQueue.pop();

			for (int neighbor : adjacencyList[currentVertex])
			{
				if (vertexColor[neighbor] == 0)
				{
					vertexColor[neighbor] = -vertexColor[currentVertex];
					bfsQueue.push(neighbor);
				}
				else if (vertexColor[neighbor] == vertexColor[currentVertex])
				{
					return false;
				}
			}
		}
	}

	return true;
}

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int testCaseCount = 0;
	std::cin >> testCaseCount;

	for (int t = 0; t < testCaseCount; ++t)
	{
		int vertexCount = 0;
		int edgeCount = 0;
		std::cin >> vertexCount >> edgeCount;

		std::vector<std::vector<int>> adjacencyList(vertexCount);

		for (int i = 0; i < edgeCount; ++i)
		{
			int vertexU = 0;
			int vertexV = 0;
			std::cin >> vertexU >> vertexV;
			--vertexU;
			--vertexV;
			adjacencyList[vertexU].push_back(vertexV);
			adjacencyList[vertexV].push_back(vertexU);
		}

		bool isBipartite = CheckIfBipartite(vertexCount, adjacencyList);
		if (isBipartite)
		{
			std::cout << "YES" << std::endl;
		}
		else
		{
			std::cout << "NO" << std::endl;
		}
	}

	return 0;
}
