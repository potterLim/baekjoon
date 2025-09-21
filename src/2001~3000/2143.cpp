#include <algorithm>
#include <cstdint>
#include <iostream>
#include <vector>

int main()
{
	long long target = 0;
	int n = 0;
	if (!(std::cin >> target))
	{
		std::cout << 0 << std::endl;
		return 0;
	}
	if (!(std::cin >> n))
	{
		std::cout << 0 << std::endl;
		return 0;
	}

	std::vector<long long> a(n);
	for (int i = 0; i < n; ++i)
	{
		std::cin >> a[i];
	}

	int m = 0;
	if (!(std::cin >> m))
	{
		std::cout << 0 << std::endl;
		return 0;
	}
	std::vector<long long> b(m);
	for (int i = 0; i < m; ++i)
	{
		std::cin >> b[i];
	}

	std::vector<long long> prefixA(n + 1, 0);
	for (int i = 0; i < n; ++i)
	{
		prefixA[i + 1] = prefixA[i] + a[i];
	}

	std::vector<long long> prefixB(m + 1, 0);
	for (int i = 0; i < m; ++i)
	{
		prefixB[i + 1] = prefixB[i] + b[i];
	}

	std::vector<long long> sumsA;
	sumsA.reserve(static_cast<std::size_t>(n) * static_cast<std::size_t>(n + 1) / 2);
	for (int i = 0; i < n; ++i)
	{
		for (int j = i + 1; j <= n; ++j)
		{
			sumsA.push_back(prefixA[j] - prefixA[i]);
		}
	}

	std::vector<long long> sumsB;
	sumsB.reserve(static_cast<std::size_t>(m) * static_cast<std::size_t>(m + 1) / 2);
	for (int i = 0; i < m; ++i)
	{
		for (int j = i + 1; j <= m; ++j)
		{
			sumsB.push_back(prefixB[j] - prefixB[i]);
		}
	}

	std::sort(sumsA.begin(), sumsA.end());
	std::sort(sumsB.begin(), sumsB.end());

	std::size_t indexA = 0;
	long long indexB = static_cast<long long>(sumsB.size()) - 1;
	long long answer = 0;

	while (indexA < sumsA.size() && indexB >= 0)
	{
		long long sum = sumsA[indexA] + sumsB[static_cast<std::size_t>(indexB)];
		if (sum == target)
		{
			long long valueA = sumsA[indexA];
			long long valueB = sumsB[static_cast<std::size_t>(indexB)];
			long long countA = 0;
			long long countB = 0;

			while (indexA < sumsA.size() && sumsA[indexA] == valueA)
			{
				++countA;
				++indexA;
			}
			while (indexB >= 0 && sumsB[static_cast<std::size_t>(indexB)] == valueB)
			{
				++countB;
				--indexB;
			}

			answer += countA * countB;
		}
		else if (sum < target)
		{
			++indexA;
		}
		else
		{
			--indexB;
		}
	}

	std::cout << answer << std::endl;
	return 0;
}
