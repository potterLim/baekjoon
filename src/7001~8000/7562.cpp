#include <iostream>
#include <vector>
#include <queue>

struct Position
{
	int row;
	int col;
};

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int testCaseCount = 0;
	std::cin >> testCaseCount;

	for (int t = 0; t < testCaseCount; ++t)
	{
		int boardSize = 0;
		std::cin >> boardSize;

		Position start;
		Position target;
		std::cin >> start.row >> start.col;
		std::cin >> target.row >> target.col;

		if (start.row == target.row && start.col == target.col)
		{
			std::cout << 0 << std::endl;
			continue;
		}

		std::vector<std::vector<int>> distance(boardSize, std::vector<int>(boardSize, -1));
		std::queue<Position> bfsQueue;

		int dRow[8] = {-2, -2, -1, -1, 1, 1, 2, 2};
		int dCol[8] = {-1, 1, -2, 2, -2, 2, -1, 1};

		distance[start.row][start.col] = 0;
		bfsQueue.push(start);

		bool found = false;

		while (!bfsQueue.empty() && !found)
		{
			Position current = bfsQueue.front();
			bfsQueue.pop();

			for (int i = 0; i < 8; ++i)
			{
				int nextRow = current.row + dRow[i];
				int nextCol = current.col + dCol[i];

				if (nextRow < 0 || nextRow >= boardSize || nextCol < 0 || nextCol >= boardSize)
				{
					continue;
				}
				if (distance[nextRow][nextCol] != -1)
				{
					continue;
				}

				distance[nextRow][nextCol] = distance[current.row][current.col] + 1;

				if (nextRow == target.row && nextCol == target.col)
				{
					std::cout << distance[nextRow][nextCol] << std::endl;
					found = true;
					break;
				}

				bfsQueue.push({nextRow, nextCol});
			}
		}

		if (!found)
		{
			std::cout << -1 << std::endl;
		}
	}

	return 0;
}
