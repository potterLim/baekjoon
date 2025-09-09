#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
	int n;
	std::cin >> n;

	std::vector<long long> a(n);
	for (int i = 0; i < n; ++i)
	{
		std::cin >> a[i];
	}

	std::vector<long long> tails(n);
	std::vector<int> tailIndex(n, -1);
	std::vector<int> prevIndex(n, -1);

	int lisLen = 0;

	for (int i = 0; i < n; ++i)
	{
		int pos = static_cast<int>(std::lower_bound(tails.begin(), tails.begin() + lisLen, a[i]) - tails.begin());

		if (pos > 0)
		{
			prevIndex[i] = tailIndex[pos - 1];
		}

		tails[pos] = a[i];
		tailIndex[pos] = i;

		if (pos == lisLen)
		{
			++lisLen;
		}
	}

	std::cout << lisLen << std::endl;

	std::vector<long long> answer;
	for (int i = tailIndex[lisLen - 1]; i != -1; i = prevIndex[i])
	{
		answer.push_back(a[i]);
	}
	std::reverse(answer.begin(), answer.end());

	for (size_t i = 0; i < answer.size(); ++i)
	{
		std::cout << answer[i];
		if (i + 1 < answer.size())
		{
			std::cout << ' ';
		}
	}
	std::cout << std::endl;

	return 0;
}
