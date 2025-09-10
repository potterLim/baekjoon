#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

int main()
{
	std::string first;
	std::string second;

	std::cin >> first;
	std::cin >> second;

	int n = static_cast<int>(first.size());
	int m = static_cast<int>(second.size());

	std::vector<std::vector<int>> lcsLength(n + 1, std::vector<int>(m + 1, 0));

	for (int i = 1; i <= n; ++i)
	{
		for (int j = 1; j <= m; ++j)
		{
			if (first[i - 1] == second[j - 1])
			{
				lcsLength[i][j] = lcsLength[i - 1][j - 1] + 1;
			}
			else
			{
				lcsLength[i][j] = std::max(lcsLength[i - 1][j], lcsLength[i][j - 1]);
			}
		}
	}

	std::cout << lcsLength[n][m] << std::endl;

	if (lcsLength[n][m] > 0)
	{
		std::string lcsResult;
		lcsResult.reserve(static_cast<size_t>(lcsLength[n][m]));

		int i = n;
		int j = m;

		while (i > 0 && j > 0)
		{
			if (first[i - 1] == second[j - 1])
			{
				lcsResult.push_back(first[i - 1]);
				--i;
				--j;
			}
			else if (lcsLength[i - 1][j] >= lcsLength[i][j - 1])
			{
				--i;
			}
			else
			{
				--j;
			}
		}

		std::reverse(lcsResult.begin(), lcsResult.end());
		std::cout << lcsResult << std::endl;
	}

	return 0;
}
