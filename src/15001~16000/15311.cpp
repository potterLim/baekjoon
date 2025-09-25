#include <cmath>
#include <iostream>
#include <vector>

int main()
{
	long long maxRequest;
	std::cin >> maxRequest;

	int t = static_cast<int>(std::ceil(std::sqrt(static_cast<double>(maxRequest))));

	std::vector<long long> prefix;
	prefix.reserve(2 * t + 1);

	for (int i = 0; i <= t; ++i)
	{
		prefix.push_back(i);
	}
	for (int i = 2; i <= t; ++i)
	{
		prefix.push_back(static_cast<long long>(i) * static_cast<long long>(t));
	}

	std::vector<long long> bags;
	bags.reserve(prefix.size() - 1);
	for (int i = 1; i < static_cast<int>(prefix.size()); ++i)
	{
		bags.push_back(prefix[i] - prefix[i - 1]);
	}

	std::cout << static_cast<int>(bags.size()) << std::endl;
	for (int i = 0; i < static_cast<int>(bags.size()); ++i)
	{
		if (i > 0)
		{
			std::cout << ' ';
		}
		std::cout << bags[i];
	}
	std::cout << std::endl;

	return 0;
}
