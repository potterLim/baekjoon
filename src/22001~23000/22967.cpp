#include <iostream>
#include <vector>

int main()
{
	int n;
	std::cin >> n;

	std::vector<std::vector<int>> adjacent(n + 1, std::vector<int>(n + 1, 0));
	std::vector<int> degree(n + 1, 0);

	for (int i = 0; i < n - 1; ++i)
	{
		int u;
		int v;
		std::cin >> u >> v;
		adjacent[u][v] = 1;
		adjacent[v][u] = 1;
		++degree[u];
		++degree[v];
	}

	if (n <= 4)
	{
		std::vector<std::pair<int, int>> toAdd;
		for (int i = 1; i <= n; ++i)
		{
			for (int j = i + 1; j <= n; ++j)
			{
				if (adjacent[i][j] == 0)
				{
					toAdd.push_back(std::make_pair(i, j));
				}
			}
		}
		std::cout << static_cast<int>(toAdd.size()) << std::endl;
		std::cout << 1 << std::endl;
		for (int i = 0; i < static_cast<int>(toAdd.size()); ++i)
		{
			std::cout << toAdd[i].first << ' ' << toAdd[i].second << std::endl;
		}
		return 0;
	}

	int hub = 1;
	for (int i = 2; i <= n; ++i)
	{
		if (degree[i] > degree[hub])
		{
			hub = i;
		}
	}

	std::vector<std::pair<int, int>> toAdd;
	for (int j = 1; j <= n; ++j)
	{
		if (j == hub)
		{
			continue;
		}
		if (adjacent[hub][j] == 0)
		{
			toAdd.push_back(std::make_pair(hub, j));
		}
	}

	std::cout << static_cast<int>(toAdd.size()) << std::endl;
	std::cout << 2 << std::endl;
	for (int i = 0; i < static_cast<int>(toAdd.size()); ++i)
	{
		std::cout << toAdd[i].first << ' ' << toAdd[i].second << std::endl;
	}

	return 0;
}
