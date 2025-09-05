#include <algorithm>
#include <iostream>
#include <vector>

static void gen(const std::vector<long long>& a, int idx, int end, long long sum, std::vector<long long>& out, long long cap)
{
	if (sum > cap)
	{
		return;
	}
	if (idx == end)
	{
		out.push_back(sum);
		return;
	}
	gen(a, idx + 1, end, sum, out, cap);
	gen(a, idx + 1, end, sum + a[idx], out, cap);
}

int main()
{
	int n;
	long long cap;
	std::cin >> n >> cap;

	std::vector<long long> w(n);
	for (int i = 0; i < n; ++i)
	{
		std::cin >> w[i];
	}

	int mid = n / 2;

	std::vector<long long> leftSums;
	std::vector<long long> rightSums;
	leftSums.reserve(1 << mid);
	rightSums.reserve(1 << (n - mid));

	gen(w, 0, mid, 0, leftSums, cap);
	gen(w, mid, n, 0, rightSums, cap);

	std::sort(leftSums.begin(), leftSums.end());
	std::sort(rightSums.begin(), rightSums.end());

	long long ways = 0;

	for (int i = 0; i < static_cast<int>(leftSums.size()); ++i)
	{
		long long remain = cap - leftSums[i];
		if (remain < 0)
		{
			continue;
		}
		int cnt = static_cast<int>(std::upper_bound(rightSums.begin(), rightSums.end(), remain) - rightSums.begin());
		ways += static_cast<long long>(cnt);
	}

	std::cout << ways << std::endl;
	return 0;
}
