#include <iostream>
#include <vector>

int main()
{
	int weightCount;
	std::cin >> weightCount;

	std::vector<int> weights(weightCount);
	int totalWeight = 0;
	for (int i = 0; i < weightCount; ++i)
	{
		std::cin >> weights[i];
		totalWeight += weights[i];
	}

	int marbleCount;
	std::cin >> marbleCount;

	std::vector<int> marbles(marbleCount);
	for (int i = 0; i < marbleCount; ++i)
	{
		std::cin >> marbles[i];
	}

	std::vector<char> reachable(totalWeight + 1, 0);
	reachable[0] = 1;

	for (int i = 0; i < weightCount; ++i)
	{
		int w = weights[i];
		std::vector<char> nextReachable = reachable;

		for (int j = 0; j <= totalWeight; ++j)
		{
			if (!reachable[j])
			{
				continue;
			}

			int diffSameSide = j + w;
			if (diffSameSide <= totalWeight)
			{
				nextReachable[diffSameSide] = 1;
			}

			int diffOppositeSide = (j >= w) ? (j - w) : (w - j);
			nextReachable[diffOppositeSide] = 1;
		}

		reachable.swap(nextReachable);
	}

	for (int i = 0; i < marbleCount; ++i)
	{
		int m = marbles[i];
		if (m <= totalWeight && reachable[m])
		{
			std::cout << 'Y';
		}
		else
		{
			std::cout << 'N';
		}
		if (i + 1 < marbleCount)
		{
			std::cout << ' ';
		}
	}
	std::cout << std::endl;

	return 0;
}
