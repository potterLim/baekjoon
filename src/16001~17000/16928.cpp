#include <iostream>
#include <queue>
#include <vector>

int main()
{
	const int boardSize = 100;
	const int diceFaces = 6;

	int ladderCount;
	int snakeCount;

	std::cin >> ladderCount >> snakeCount;

	std::vector<int> warp;
	warp.resize(static_cast<std::size_t>(boardSize + 1));
	for (int i = 1; i <= boardSize; ++i)
	{
		warp[static_cast<std::size_t>(i)] = i;
	}

	for (int i = 0; i < ladderCount; ++i)
	{
		int from;
		int to;
		std::cin >> from >> to;
		warp[static_cast<std::size_t>(from)] = to;
	}
	for (int i = 0; i < snakeCount; ++i)
	{
		int from;
		int to;
		std::cin >> from >> to;
		warp[static_cast<std::size_t>(from)] = to;
	}

	std::vector<int> rollCountTo;
	rollCountTo.assign(static_cast<std::size_t>(boardSize + 1), -1);

	std::queue<int> bfsQueue;
	rollCountTo[1] = 0;
	bfsQueue.push(1);

	while (!bfsQueue.empty())
	{
		const int current = bfsQueue.front();
		bfsQueue.pop();

		if (current == boardSize)
		{
			break;
		}

		for (int face = 1; face <= diceFaces; ++face)
		{
			int next = current + face;
			if (next > boardSize)
			{
				continue;
			}

			next = warp[static_cast<std::size_t>(next)];

			if (rollCountTo[static_cast<std::size_t>(next)] == -1)
			{
				rollCountTo[static_cast<std::size_t>(next)] =
					rollCountTo[static_cast<std::size_t>(current)] + 1;
				bfsQueue.push(next);
			}
		}
	}

	std::cout << rollCountTo[static_cast<std::size_t>(boardSize)] << '\n';
	return 0;
}
