#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
	int testCaseCount;
	std::cin >> testCaseCount;

	for (int i = 0; i < testCaseCount; ++i)
	{
		int cardCount;
		std::cin >> cardCount;

		std::vector<int> cards(cardCount + 1);
		std::vector<int> prefixSum(cardCount + 1, 0);
		for (int j = 1; j <= cardCount; ++j)
		{
			std::cin >> cards[j];
			prefixSum[j] = prefixSum[j - 1] + cards[j];
		}

		std::vector<std::vector<int>> bestScore(cardCount + 2, std::vector<int>(cardCount + 2, 0));

		for (int len = 1; len <= cardCount; ++len)
		{
			for (int j = 1; j + len - 1 <= cardCount; ++j)
			{
				int k = j + len - 1;

				if (j == k)
				{
					bestScore[j][k] = cards[j];
				}
				else
				{
					int total = prefixSum[k] - prefixSum[j - 1];
					int optionLeft = total - bestScore[j + 1][k];
					int optionRight = total - bestScore[j][k - 1];
					bestScore[j][k] = std::max(optionLeft, optionRight);
				}
			}
		}

		std::cout << bestScore[1][cardCount] << std::endl;
	}

	return 0;
}
