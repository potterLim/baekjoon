#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
	int cityCount;
	int busCount;
	std::cin >> cityCount >> busCount;

	const int INF = 1'000'000'000;

	std::vector<std::vector<int>> minCost(cityCount + 1, std::vector<int>(cityCount + 1, INF));
	std::vector<std::vector<int>> nextCity(cityCount + 1, std::vector<int>(cityCount + 1, 0));

	for (int i = 1; i <= cityCount; ++i)
	{
		minCost[i][i] = 0;
	}

	for (int i = 0; i < busCount; ++i)
	{
		int from;
		int to;
		int cost;
		std::cin >> from >> to >> cost;
		if (cost < minCost[from][to])
		{
			minCost[from][to] = cost;
			nextCity[from][to] = to;
		}
	}

	for (int k = 1; k <= cityCount; ++k)
	{
		for (int i = 1; i <= cityCount; ++i)
		{
			if (minCost[i][k] == INF)
			{
				continue;
			}
			for (int j = 1; j <= cityCount; ++j)
			{
				if (minCost[k][j] == INF)
				{
					continue;
				}
				int candidate = minCost[i][k] + minCost[k][j];
				if (candidate < minCost[i][j])
				{
					minCost[i][j] = candidate;
					nextCity[i][j] = nextCity[i][k];
				}
			}
		}
	}

	for (int i = 1; i <= cityCount; ++i)
	{
		for (int j = 1; j <= cityCount; ++j)
		{
			if (minCost[i][j] == INF)
			{
				std::cout << 0;
			}
			else
			{
				std::cout << minCost[i][j];
			}
			if (j < cityCount)
			{
				std::cout << ' ';
			}
		}
		std::cout << std::endl;
	}

	for (int i = 1; i <= cityCount; ++i)
	{
		for (int j = 1; j <= cityCount; ++j)
		{
			if (i == j || nextCity[i][j] == 0)
			{
				std::cout << 0 << std::endl;
				continue;
			}

			std::vector<int> path;
			int cur = i;
			path.push_back(cur);
			while (cur != j)
			{
				cur = nextCity[cur][j];
				path.push_back(cur);
			}

			std::cout << path.size();
			for (size_t k = 0; k < path.size(); ++k)
			{
				std::cout << ' ' << path[k];
			}
			std::cout << std::endl;
		}
	}

	return 0;
}