#include <iostream>
#include <queue>
#include <string>
#include <vector>

int main()
{
	int rowCount;
	int colCount;
	std::cin >> rowCount >> colCount;

	std::vector<std::string> grid;
	grid.resize(static_cast<std::size_t>(rowCount));
	for (int i = 0; i < rowCount; ++i)
	{
		std::cin >> grid[static_cast<std::size_t>(i)];
	}

	std::vector<std::vector<int>> dist;
	dist.assign(static_cast<std::size_t>(rowCount),
				std::vector<int>(static_cast<std::size_t>(colCount), 0));

	struct Cell
	{
		int r;
		int c;
	};

	std::queue<Cell> bfsQueue;
	dist[0][0] = 1;
	bfsQueue.push(Cell{0, 0});

	const int dr[4] = {-1, 1, 0, 0};
	const int dc[4] = {0, 0, -1, 1};

	while (!bfsQueue.empty())
	{
		const Cell cur = bfsQueue.front();
		bfsQueue.pop();

		for (int d = 0; d < 4; ++d)
		{
			const int nr = cur.r + dr[d];
			const int nc = cur.c + dc[d];

			if (nr < 0 || nr >= rowCount || nc < 0 || nc >= colCount)
			{
				continue;
			}

			if (grid[static_cast<std::size_t>(nr)][static_cast<std::size_t>(nc)] == '1' && dist[static_cast<std::size_t>(nr)][static_cast<std::size_t>(nc)] == 0)
			{
				dist[static_cast<std::size_t>(nr)][static_cast<std::size_t>(nc)] = dist[static_cast<std::size_t>(cur.r)][static_cast<std::size_t>(cur.c)] + 1;
				bfsQueue.push(Cell{nr, nc});
			}
		}
	}

	std::cout << dist[static_cast<std::size_t>(rowCount - 1)][static_cast<std::size_t>(colCount - 1)] << std::endl;

	return 0;
}
