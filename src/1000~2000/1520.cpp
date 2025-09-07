#include <iostream>
#include <vector>
#include <algorithm>

struct GridCell
{
	int height;
	int row;
	int col;
};

int main()
{
	int rowCount;
	int colCount;
	std::cin >> rowCount >> colCount;

	std::vector<std::vector<int>> heightMap(rowCount, std::vector<int>(colCount));
	for (int i = 0; i < rowCount; ++i)
	{
		for (int j = 0; j < colCount; ++j)
		{
			std::cin >> heightMap[i][j];
		}
	}

	std::vector<GridCell> gridCells;
	gridCells.reserve(static_cast<size_t>(rowCount) * static_cast<size_t>(colCount));
	for (int i = 0; i < rowCount; ++i)
	{
		for (int j = 0; j < colCount; ++j)
		{
			GridCell cell{heightMap[i][j], i, j};
			gridCells.push_back(cell);
		}
	}

	std::sort(gridCells.begin(), gridCells.end(), [](const GridCell& lhs, const GridCell& rhs)
			  { return lhs.height > rhs.height; });

	std::vector<std::vector<long long>> pathCount(rowCount, std::vector<long long>(colCount, 0));
	pathCount[0][0] = 1;

	static const int ROW_DELTAS[4] = {-1, 1, 0, 0};
	static const int COL_DELTAS[4] = {0, 0, -1, 1};

	for (const GridCell& cell : gridCells)
	{
		long long waysHere = pathCount[cell.row][cell.col];
		if (waysHere == 0)
		{
			continue;
		}

		for (int k = 0; k < 4; ++k)
		{
			int nextRow = cell.row + ROW_DELTAS[k];
			int nextCol = cell.col + COL_DELTAS[k];

			if (nextRow < 0 || nextRow >= rowCount || nextCol < 0 || nextCol >= colCount)
			{
				continue;
			}

			if (heightMap[nextRow][nextCol] < cell.height)
			{
				pathCount[nextRow][nextCol] += waysHere;
			}
		}
	}

	std::cout << pathCount[rowCount - 1][colCount - 1] << std::endl;
	return 0;
}
