#include <algorithm>
#include <cstdlib>
#include <iostream>
#include <vector>

int main()
{
	int n;
	std::cin >> n;

	std::vector<long long> a(n);
	for (int i = 0; i < n; ++i)
	{
		std::cin >> a[i];
	}

	std::sort(a.begin(), a.end());

	int left = 0;
	int right = n - 1;

	long long bestSumAbs = llabs(a[left] + a[right]);
	long long bestL = a[left];
	long long bestR = a[right];

	while (left < right)
	{
		long long s = a[left] + a[right];
		long long absS = llabs(s);

		if (absS < bestSumAbs)
		{
			bestSumAbs = absS;
			bestL = a[left];
			bestR = a[right];
		}

		if (s < 0)
		{
			left = left + 1;
		}
		else if (s > 0)
		{
			right = right - 1;
		}
		else
		{
			break;
		}
	}

	std::cout << bestL << ' ' << bestR << std::endl;
	return 0;
}
