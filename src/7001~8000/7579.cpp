#include <iostream>
#include <vector>

int main()
{
	int appCount;
	int requiredMemory;
	std::cin >> appCount >> requiredMemory;

	std::vector<int> appMemories(appCount);
	for (int i = 0; i < appCount; ++i)
	{
		std::cin >> appMemories[i];
	}

	std::vector<int> appCosts(appCount);
	int maxTotalCost = 0;
	for (int i = 0; i < appCount; ++i)
	{
		std::cin >> appCosts[i];
		maxTotalCost += appCosts[i];
	}

	std::vector<long long> bestMemory(maxTotalCost + 1, -1);
	bestMemory[0] = 0;

	for (int i = 0; i < appCount; ++i)
	{
		int cost = appCosts[i];
		int memory = appMemories[i];
		for (int c = maxTotalCost; c >= cost; --c)
		{
			if (bestMemory[c - cost] >= 0)
			{
				long long candidate = bestMemory[c - cost] + memory;
				if (candidate > bestMemory[c])
				{
					bestMemory[c] = candidate;
				}
			}
		}
	}

	int minimalCost = 0;
	while (minimalCost <= maxTotalCost && bestMemory[minimalCost] < requiredMemory)
	{
		++minimalCost;
	}

	std::cout << minimalCost << std::endl;
	return 0;
}
