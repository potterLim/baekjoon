#include <iostream>
#include <vector>
#include <array>
#include <queue>
#include <string>

struct State
{
	int row;
	int col;
	int usedBreak;
};

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int rowCount = 0;
	int colCount = 0;
	std::cin >> rowCount >> colCount;

	std::vector<std::string> mapGrid(rowCount);
	for (int i = 0; i < rowCount; ++i)
	{
		std::cin >> mapGrid[i];
	}

	std::vector<std::vector<std::array<int, 2>>> distance(rowCount, std::vector<std::array<int, 2>>(colCount, {-1, -1}));
	std::queue<State> bfsQueue;

	distance[0][0][0] = 1;
	bfsQueue.push({0, 0, 0});

	int dRow[4] = {-1, 1, 0, 0};
	int dCol[4] = {0, 0, -1, 1};

	while (!bfsQueue.empty())
	{
		State current = bfsQueue.front();
		bfsQueue.pop();

		if (current.row == rowCount - 1 && current.col == colCount - 1)
		{
			std::cout << distance[current.row][current.col][current.usedBreak] << std::endl;
			return 0;
		}

		for (int i = 0; i < 4; ++i)
		{
			int nextRow = current.row + dRow[i];
			int nextCol = current.col + dCol[i];

			if (nextRow < 0 || nextRow >= rowCount || nextCol < 0 || nextCol >= colCount)
			{
				continue;
			}

			if (mapGrid[nextRow][nextCol] == '0')
			{
				if (distance[nextRow][nextCol][current.usedBreak] == -1)
				{
					distance[nextRow][nextCol][current.usedBreak] = distance[current.row][current.col][current.usedBreak] + 1;
					bfsQueue.push({nextRow, nextCol, current.usedBreak});
				}
			}
			else
			{
				if (current.usedBreak == 0 && distance[nextRow][nextCol][1] == -1)
				{
					distance[nextRow][nextCol][1] = distance[current.row][current.col][0] + 1;
					bfsQueue.push({nextRow, nextCol, 1});
				}
			}
		}
	}

	std::cout << -1 << std::endl;
	return 0;
}
