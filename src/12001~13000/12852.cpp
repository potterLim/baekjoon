#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
	int n;
	std::cin >> n;

	std::vector<int> dp(n + 1, 0);
	std::vector<int> prev(n + 1, 0);

	dp[1] = 0;

	for (int i = 2; i <= n; ++i)
	{
		dp[i] = dp[i - 1] + 1;
		prev[i] = i - 1;

		if (i % 2 == 0 && dp[i] > dp[i / 2] + 1)
		{
			dp[i] = dp[i / 2] + 1;
			prev[i] = i / 2;
		}

		if (i % 3 == 0 && dp[i] > dp[i / 3] + 1)
		{
			dp[i] = dp[i / 3] + 1;
			prev[i] = i / 3;
		}
	}

	std::cout << dp[n] << std::endl;

	std::vector<int> path;
	for (int i = n; i != 0; i = prev[i])
	{
		path.push_back(i);
		if (i == 1)
		{
			break;
		}
	}

	for (size_t i = 0; i < path.size(); ++i)
	{
		std::cout << path[i];
		if (i + 1 < path.size())
		{
			std::cout << " ";
		}
	}
	std::cout << std::endl;

	return 0;
}
