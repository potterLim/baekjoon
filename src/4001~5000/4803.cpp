#include <iostream>
#include <vector>
#include <queue>

int main()
{
	int vertexCountTotal;
	int edgeCountTotal;
	int testCaseIndex = 1;

	while (true)
	{
		std::cin >> vertexCountTotal >> edgeCountTotal;
		if (!std::cin)
		{
			return 0;
		}
		if (vertexCountTotal == 0 && edgeCountTotal == 0)
		{
			break;
		}

		std::vector<std::vector<int>> adjacency(vertexCountTotal + 1);
		for (int i = 0; i < edgeCountTotal; ++i)
		{
			int fromVertex;
			int toVertex;
			std::cin >> fromVertex >> toVertex;
			adjacency[fromVertex].push_back(toVertex);
			adjacency[toVertex].push_back(fromVertex);
		}

		std::vector<int> visitedFlag(vertexCountTotal + 1, 0);
		int treeComponentCount = 0;

		for (int i = 1; i <= vertexCountTotal; ++i)
		{
			if (visitedFlag[i])
			{
				continue;
			}

			int componentVertexCount = 0;
			int componentDegreeSum = 0;

			std::queue<int> bfsQueue;
			bfsQueue.push(i);
			visitedFlag[i] = 1;

			while (!bfsQueue.empty())
			{
				int currentVertex = bfsQueue.front();
				bfsQueue.pop();

				++componentVertexCount;
				componentDegreeSum += static_cast<int>(adjacency[currentVertex].size());

				for (int j = 0; j < static_cast<int>(adjacency[currentVertex].size()); ++j)
				{
					int neighborVertex = adjacency[currentVertex][j];
					if (visitedFlag[neighborVertex])
					{
						continue;
					}
					visitedFlag[neighborVertex] = 1;
					bfsQueue.push(neighborVertex);
				}
			}

			int componentEdgeCount = componentDegreeSum / 2;
			if (componentEdgeCount == componentVertexCount - 1)
			{
				++treeComponentCount;
			}
		}

		std::cout << "Case " << testCaseIndex << ": ";
		if (treeComponentCount == 0)
		{
			std::cout << "No trees." << std::endl;
		}
		else if (treeComponentCount == 1)
		{
			std::cout << "There is one tree." << std::endl;
		}
		else
		{
			std::cout << "A forest of " << treeComponentCount << " trees." << std::endl;
		}

		++testCaseIndex;
	}

	return 0;
}
