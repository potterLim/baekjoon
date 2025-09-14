#include <iostream>
#include <vector>
#include <stack>
#include <utility>

int nodeCount;
std::vector<std::vector<std::pair<int, int>>> adjacencyList;

std::pair<int, int> GetFarthestNode(int startNode)
{
	std::vector<int> distance(nodeCount + 1, -1);
	std::stack<int> stack;
	distance[startNode] = 0;
	stack.push(startNode);

	while (!stack.empty())
	{
		int currentNode = stack.top();
		stack.pop();
		for (int i = 0; i < (int)adjacencyList[currentNode].size(); ++i)
		{
			int nextNode = adjacencyList[currentNode][i].first;
			int edgeWeight = adjacencyList[currentNode][i].second;
			if (distance[nextNode] == -1)
			{
				distance[nextNode] = distance[currentNode] + edgeWeight;
				stack.push(nextNode);
			}
		}
	}

	int farthestNode = startNode;
	int maxDistance = 0;
	for (int i = 1; i <= nodeCount; ++i)
	{
		if (distance[i] > maxDistance)
		{
			maxDistance = distance[i];
			farthestNode = i;
		}
	}
	return {farthestNode, maxDistance};
}

int main()
{
	std::cin >> nodeCount;
	adjacencyList.assign(nodeCount + 1, {});

	for (int i = 0; i < nodeCount - 1; ++i)
	{
		int fromNode, toNode, weight;
		std::cin >> fromNode >> toNode >> weight;
		adjacencyList[fromNode].push_back({toNode, weight});
		adjacencyList[toNode].push_back({fromNode, weight});
	}

	int farNode = GetFarthestNode(1).first;
	int treeDiameter = GetFarthestNode(farNode).second;

	std::cout << treeDiameter << std::endl;
	return 0;
}
