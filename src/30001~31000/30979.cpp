#include <iostream>
#include <vector>

int main()
{
	int totalMinutes;
	int candyCount;

	std::cin >> totalMinutes;
	std::cin >> candyCount;

	std::vector<int> flavors;
	flavors.resize(static_cast<std::size_t>(candyCount));

	int summedMinutes = 0;
	for (int i = 0; i < candyCount; ++i)
	{
		std::cin >> flavors[static_cast<std::size_t>(i)];
		summedMinutes = summedMinutes + flavors[static_cast<std::size_t>(i)];
	}

	if (summedMinutes >= totalMinutes)
	{
		std::cout << "Padaeng_i Happy\n";
	}
	else
	{
		std::cout << "Padaeng_i Cry\n";
	}

	return 0;
}
