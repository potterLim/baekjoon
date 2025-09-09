#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
	int n;
	std::cin >> n;

	std::vector<int> sequence(n);
	for (int i = 0; i < n; ++i)
	{
		std::cin >> sequence[i];
	}

	std::vector<int> dp(n, 1);
	std::vector<int> prevIndex(n, -1);

	int maxLength = 1;
	int lastIndex = 0;

	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < i; ++j)
		{
			if (sequence[j] < sequence[i] && dp[j] + 1 > dp[i])
			{
				dp[i] = dp[j] + 1;
				prevIndex[i] = j;
			}
		}

		if (dp[i] > maxLength)
		{
			maxLength = dp[i];
			lastIndex = i;
		}
	}

	std::cout << maxLength << std::endl;

	std::vector<int> lis;
	for (int i = lastIndex; i != -1; i = prevIndex[i])
	{
		lis.push_back(sequence[i]);
	}
	std::reverse(lis.begin(), lis.end());

	for (size_t i = 0; i < lis.size(); ++i)
	{
		std::cout << lis[i];
		if (i + 1 < lis.size())
		{
			std::cout << " ";
		}
	}
	std::cout << std::endl;

	return 0;
}
