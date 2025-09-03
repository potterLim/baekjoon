#include <iostream>
#include <queue>
#include <vector>

int main()
{
	const int maxPos = 100000;

	int startPos;
	int targetPos;
	std::cin >> startPos >> targetPos;

	if (startPos >= targetPos)
	{
		std::cout << (startPos - targetPos) << std::endl;
		return 0;
	}

	std::vector<int> timeToReach;
	timeToReach.assign(static_cast<std::size_t>(maxPos + 1), -1);

	std::queue<int> bfsQueue;
	timeToReach[static_cast<std::size_t>(startPos)] = 0;
	bfsQueue.push(startPos);

	while (!bfsQueue.empty())
	{
		const int current = bfsQueue.front();
		bfsQueue.pop();

		if (current == targetPos)
		{
			break;
		}

		const int candidates[3] = {current - 1, current + 1, current * 2};

		for (int i = 0; i < 3; ++i)
		{
			const int next = candidates[i];

			if (next < 0 || next > maxPos)
			{
				continue;
			}

			if (timeToReach[static_cast<std::size_t>(next)] == -1)
			{
				timeToReach[static_cast<std::size_t>(next)] =
					timeToReach[static_cast<std::size_t>(current)] + 1;
				bfsQueue.push(next);
			}
		}
	}

	std::cout << timeToReach[static_cast<std::size_t>(targetPos)] << std::endl;
	return 0;
}
