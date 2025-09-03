#include <iostream>
#include <queue>
#include <vector>

int main()
{
	int testCaseCount;
	std::cin >> testCaseCount;

	for (int t = 0; t < testCaseCount; ++t)
	{
		int width;
		int height;
		int cabbageCount;
		std::cin >> width >> height >> cabbageCount;

		std::vector<std::vector<char>> hasCabbage(static_cast<std::size_t>(height), std::vector<char>(static_cast<std::size_t>(width), 0));

		for (int i = 0; i < cabbageCount; ++i)
		{
			int x;
			int y;
			std::cin >> x >> y;
			hasCabbage[static_cast<std::size_t>(y)][static_cast<std::size_t>(x)] = 1;
		}

		std::vector<std::vector<char>> visited(static_cast<std::size_t>(height), std::vector<char>(static_cast<std::size_t>(width), 0));

		const int dr[4] = {-1, 1, 0, 0};
		const int dc[4] = {0, 0, -1, 1};

		int wormNeeded = 0;

		for (int r = 0; r < height; ++r)
		{
			for (int c = 0; c < width; ++c)
			{
				if (hasCabbage[static_cast<std::size_t>(r)][static_cast<std::size_t>(c)] == 1 && visited[static_cast<std::size_t>(r)][static_cast<std::size_t>(c)] == 0)
				{
					++wormNeeded;

					std::queue<std::pair<int, int>> q;
					q.push(std::make_pair(r, c));
					visited[static_cast<std::size_t>(r)][static_cast<std::size_t>(c)] = 1;

					while (!q.empty())
					{
						const int cr = q.front().first;
						const int cc = q.front().second;
						q.pop();

						for (int d = 0; d < 4; ++d)
						{
							const int nr = cr + dr[d];
							const int nc = cc + dc[d];

							if (nr < 0 || nr >= height || nc < 0 || nc >= width)
							{
								continue;
							}

							if (hasCabbage[static_cast<std::size_t>(nr)][static_cast<std::size_t>(nc)] == 1 && visited[static_cast<std::size_t>(nr)][static_cast<std::size_t>(nc)] == 0)
							{
								visited[static_cast<std::size_t>(nr)][static_cast<std::size_t>(nc)] = 1;
								q.push(std::make_pair(nr, nc));
							}
						}
					}
				}
			}
		}

		std::cout << wormNeeded << std::endl;
	}

	return 0;
}
