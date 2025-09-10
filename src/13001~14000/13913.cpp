#include <iostream>
#include <vector>
#include <queue>

int main()
{
	int startPos;
	int targetPos;
	std::cin >> startPos >> targetPos;

	const int MAX_POS = 100000;

	std::vector<int> timeToReach(MAX_POS + 1, -1);
	std::vector<int> previous(MAX_POS + 1, -1);

	std::queue<int> q;
	q.push(startPos);
	timeToReach[startPos] = 0;

	while (!q.empty())
	{
		int current = q.front();
		q.pop();

		if (current == targetPos)
		{
			break;
		}

		int nextCandidates[3] = {current - 1, current + 1, current * 2};

		for (int i = 0; i < 3; ++i)
		{
			int next = nextCandidates[i];
			if (next < 0 || next > MAX_POS)
			{
				continue;
			}
			if (timeToReach[next] != -1)
			{
				continue;
			}

			timeToReach[next] = timeToReach[current] + 1;
			previous[next] = current;
			q.push(next);
		}
	}

	std::cout << timeToReach[targetPos] << std::endl;

	std::vector<int> path;
	for (int i = targetPos; i != -1; i = previous[i])
	{
		path.push_back(i);
		if (i == startPos)
		{
			break;
		}
	}

	for (int i = static_cast<int>(path.size()) - 1; i >= 0; --i)
	{
		std::cout << path[i];
		if (i != 0)
		{
			std::cout << ' ';
		}
	}
	std::cout << std::endl;

	return 0;
}
