#include <iostream>
#include <vector>

int main()
{
	int coinCount;
	int targetSum;
	std::cin >> coinCount >> targetSum;

	std::vector<int> coinValues(coinCount);
	for (int i = 0; i < coinCount; ++i)
	{
		std::cin >> coinValues[i];
	}

	std::vector<long long> ways(targetSum + 1, 0);
	ways[0] = 1;

	for (int i = 0; i < coinCount; ++i)
	{
		int coinValue = coinValues[i];
		for (int j = coinValue; j <= targetSum; ++j)
		{
			ways[j] += ways[j - coinValue];
		}
	}

	std::cout << ways[targetSum] << std::endl;
	return 0;
}
