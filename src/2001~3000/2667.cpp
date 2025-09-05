#include <algorithm>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

int main()
{
	int n;
	std::cin >> n;

	std::vector<std::string> grid(n);
	for (int i = 0; i < n; ++i)
	{
		std::cin >> grid[i];
	}

	std::vector<std::vector<char>> visited(n, std::vector<char>(n, 0));
	const int dr[4] = {-1, 1, 0, 0};
	const int dc[4] = {0, 0, -1, 1};

	std::vector<int> sizes;

	for (int r = 0; r < n; ++r)
	{
		for (int c = 0; c < n; ++c)
		{
			if (grid[r][c] == '1' && visited[r][c] == 0)
			{
				int cnt = 0;
				std::queue<std::pair<int, int>> q;
				q.push(std::make_pair(r, c));
				visited[r][c] = 1;

				while (!q.empty())
				{
					int cr = q.front().first;
					int cc = q.front().second;
					q.pop();
					cnt = cnt + 1;

					for (int d = 0; d < 4; ++d)
					{
						int nr = cr + dr[d];
						int nc = cc + dc[d];

						if (nr < 0 || nr >= n || nc < 0 || nc >= n)
						{
							continue;
						}
						if (grid[nr][nc] == '1' && visited[nr][nc] == 0)
						{
							visited[nr][nc] = 1;
							q.push(std::make_pair(nr, nc));
						}
					}
				}

				sizes.push_back(cnt);
			}
		}
	}

	std::sort(sizes.begin(), sizes.end());

	std::cout << static_cast<int>(sizes.size()) << std::endl;
	for (int i = 0; i < static_cast<int>(sizes.size()); ++i)
	{
		std::cout << sizes[i] << std::endl;
	}

	return 0;
}
