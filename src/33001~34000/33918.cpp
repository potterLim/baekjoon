#include <deque>
#include <iostream>
#include <vector>

int main()
{
	int n;
	int m;
	int c;
	int d;
	std::cin >> n >> m >> c >> d;

	std::vector<int> best;
	best.resize(n);
	for (int i = 0; i < n; ++i)
	{
		int x;
		std::cin >> x;
		best[i] = x;
	}

	int stepLimit;
	stepLimit = d / c;

	long long minDistanceSum;
	minDistanceSum = -1;

	for (int r = 0; r < c; ++r)
	{
		std::vector<int> temps;
		for (int v = 1 + r; v <= m; v += c)
		{
			temps.push_back(v);
		}

		int len;
		len = static_cast<int>(temps.size());
		if (len == 0)
		{
			continue;
		}

		std::vector<long long> dpPrev;
		dpPrev.resize(len);
		for (int j = 0; j < len; ++j)
		{
			long long diff;
			if (best[0] >= temps[j])
			{
				diff = static_cast<long long>(best[0] - temps[j]);
			}
			else
			{
				diff = static_cast<long long>(temps[j] - best[0]);
			}
			dpPrev[j] = diff;
		}

		for (int t = 1; t < n; ++t)
		{
			std::deque<std::pair<int, long long>> window;
			std::vector<long long> dpNow;
			dpNow.resize(len);

			int rptr;
			rptr = -1;

			for (int j = 0; j < len; ++j)
			{
				int rightBound;
				rightBound = j + stepLimit;
				if (rightBound >= len)
				{
					rightBound = len - 1;
				}

				while (rptr < rightBound)
				{
					++rptr;
					while (!window.empty() && window.back().second >= dpPrev[rptr])
					{
						window.pop_back();
					}
					window.push_back(std::make_pair(rptr, dpPrev[rptr]));
				}

				int leftBound;
				leftBound = j - stepLimit;
				if (leftBound < 0)
				{
					leftBound = 0;
				}
				while (!window.empty() && window.front().first < leftBound)
				{
					window.pop_front();
				}

				long long minPrev;
				minPrev = window.front().second;

				long long diff;
				if (best[t] >= temps[j])
				{
					diff = static_cast<long long>(best[t] - temps[j]);
				}
				else
				{
					diff = static_cast<long long>(temps[j] - best[t]);
				}

				dpNow[j] = minPrev + diff;
			}

			dpPrev.swap(dpNow);
		}

		for (int j = 0; j < len; ++j)
		{
			if (minDistanceSum == -1 || dpPrev[j] < minDistanceSum)
			{
				minDistanceSum = dpPrev[j];
			}
		}
	}

	long long totalTaste;
	totalTaste = static_cast<long long>(n) * static_cast<long long>(m) - minDistanceSum;

	std::cout << totalTaste << std::endl;
	return 0;
}
