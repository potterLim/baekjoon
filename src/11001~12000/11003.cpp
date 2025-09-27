#include <deque>
#include <iostream>
#include <utility>

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int n;
	int windowSize;
	std::cin >> n >> windowSize;

	std::deque<std::pair<int, int>> monoDeque;

	for (int i = 0; i != n; ++i)
	{
		int value;
		std::cin >> value;

		while (!monoDeque.empty() && monoDeque.back().first > value)
		{
			monoDeque.pop_back();
		}
		monoDeque.push_back({value, i});

		while (!monoDeque.empty() && monoDeque.front().second <= i - windowSize)
		{
			monoDeque.pop_front();
		}

		if (i != 0)
		{
			std::cout << ' ';
		}
		std::cout << monoDeque.front().first;
	}

	std::cout << std::endl;
	return 0;
}
