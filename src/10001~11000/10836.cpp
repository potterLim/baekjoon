#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
	int gridSize;
	int dayCount;
	std::cin >> gridSize >> dayCount;

	int edgeLen = gridSize + gridSize - 1;

	std::vector<int> diff(edgeLen + 1, 0);

	for (int i = 0; i < dayCount; ++i)
	{
		int zeroCount;
		int oneCount;
		int twoCount;
		std::cin >> zeroCount >> oneCount >> twoCount;

		int p1 = zeroCount;
		int p2 = zeroCount + oneCount;

		if (p1 < edgeLen)
		{
			diff[p1] += 1;
		}
		if (p2 < edgeLen)
		{
			diff[p2] += 1;
		}
	}

	std::vector<int> edgeGrow(edgeLen, 0);
	int running = 0;
	for (int i = 0; i < edgeLen; ++i)
	{
		running += diff[i];
		edgeGrow[i] = running;
	}

	std::vector<std::vector<int>> hive(gridSize, std::vector<int>(gridSize, 1));

	int idx = 0;
	for (int i = gridSize - 1; i >= 0; --i)
	{
		hive[i][0] += edgeGrow[idx];
		++idx;
	}

	for (int j = 1; j < gridSize; ++j)
	{
		hive[0][j] += edgeGrow[idx];
		++idx;
	}

	for (int i = 1; i < gridSize; ++i)
	{
		for (int j = 1; j < gridSize; ++j)
		{
			int fromUp = hive[i - 1][j];
			int fromLeft = hive[i][j - 1];
			hive[i][j] = std::max(fromUp, fromLeft);
		}
	}

	for (int i = 0; i < gridSize; ++i)
	{
		for (int j = 0; j < gridSize; ++j)
		{
			std::cout << hive[i][j];
			if (j + 1 < gridSize)
			{
				std::cout << ' ';
			}
		}
		std::cout << std::endl;
	}

	return 0;
}
