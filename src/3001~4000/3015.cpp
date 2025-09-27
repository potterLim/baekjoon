#include <iostream>
#include <stack>
#include <utility>

int main()
{
	int n;
	std::cin >> n;

	std::stack<std::pair<int, int>> heightCountStack;
	long long visiblePairCount = 0;

	for (int i = 0; i != n; ++i)
	{
		int height;
		std::cin >> height;

		int sameHeightCount = 1;

		while (!heightCountStack.empty() && heightCountStack.top().first < height)
		{
			visiblePairCount += heightCountStack.top().second;
			heightCountStack.pop();
		}

		if (!heightCountStack.empty() && heightCountStack.top().first == height)
		{
			int topCount = heightCountStack.top().second;
			visiblePairCount += topCount;
			heightCountStack.pop();
			sameHeightCount += topCount;

			if (!heightCountStack.empty())
			{
				visiblePairCount += 1;
			}
		}
		else
		{
			if (!heightCountStack.empty())
			{
				visiblePairCount += 1;
			}
		}

		heightCountStack.push({height, sameHeightCount});
	}

	std::cout << visiblePairCount << std::endl;
	return 0;
}
