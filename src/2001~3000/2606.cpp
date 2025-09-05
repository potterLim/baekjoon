#include <iostream>
#include <queue>
#include <vector>

int main()
{
	int nodeCount;
	std::cin >> nodeCount;

	int pairCount;
	std::cin >> pairCount;

	std::vector<std::vector<int>> adjacency;
	adjacency.resize(static_cast<std::size_t>(nodeCount + 1));

	for (int i = 0; i < pairCount; ++i)
	{
		int u;
		int v;
		std::cin >> u >> v;
		adjacency[static_cast<std::size_t>(u)].push_back(v);
		adjacency[static_cast<std::size_t>(v)].push_back(u);
	}

	std::vector<char> visited;
	visited.assign(static_cast<std::size_t>(nodeCount + 1), 0);

	std::queue<int> bfsQueue;
	visited[1] = 1;
	bfsQueue.push(1);

	int infectedCount = 0;

	while (!bfsQueue.empty())
	{
		const int current = bfsQueue.front();
		bfsQueue.pop();

		for (std::size_t i = 0; i < adjacency[static_cast<std::size_t>(current)].size(); ++i)
		{
			const int next = adjacency[static_cast<std::size_t>(current)][i];
			if (visited[static_cast<std::size_t>(next)] == 0)
			{
				visited[static_cast<std::size_t>(next)] = 1;
				bfsQueue.push(next);
				infectedCount = infectedCount + 1;
			}
		}
	}

	std::cout << infectedCount << std::endl;
	return 0;
}
