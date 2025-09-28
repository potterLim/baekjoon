#include <deque>
#include <iostream>
#include <utility>
#include <vector>

int main()
{
	int n;
	std::cin >> n;
	int d;
	std::cin >> d;

	std::vector<long long> values;
	values.resize(n);
	for (int i = 0; i < n; ++i)
	{
		long long x;
		std::cin >> x;
		values[i] = x;
	}

	std::vector<long long> dp;
	dp.resize(n);
	std::deque<std::pair<int, long long>> windowMax;
	long long answer;
	answer = values[0];
	dp[0] = values[0];
	windowMax.push_back(std::make_pair(0, dp[0]));

	for (int i = 1; i < n; ++i)
	{
		while (!windowMax.empty() && windowMax.front().first < i - d)
		{
			windowMax.pop_front();
		}

		long long add;
		add = 0;
		if (!windowMax.empty() && windowMax.front().second > 0)
		{
			add = windowMax.front().second;
		}

		dp[i] = values[i] + add;

		if (dp[i] > answer)
		{
			answer = dp[i];
		}

		while (!windowMax.empty() && windowMax.back().second <= dp[i])
		{
			windowMax.pop_back();
		}
		windowMax.push_back(std::make_pair(i, dp[i]));
	}

	std::cout << answer << std::endl;
	return 0;
}
