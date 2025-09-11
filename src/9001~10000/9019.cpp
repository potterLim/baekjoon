#include <iostream>
#include <vector>
#include <queue>
#include <string>
#include <algorithm>

static int OpD(int n)
{
	return (n * 2) % 10000;
}
static int OpS(int n)
{
	return (n == 0) ? 9999 : (n - 1);
}
static int OpL(int n)
{
	return (n % 1000) * 10 + (n / 1000);
}
static int OpR(int n)
{
	return (n % 10) * 1000 + (n / 10);
}

int main()
{
	int testCaseCount;
	std::cin >> testCaseCount;

	for (int i = 0; i < testCaseCount; ++i)
	{
		int startValue;
		int targetValue;
		std::cin >> startValue >> targetValue;

		const int SIZE = 10000;

		std::vector<int> previous(SIZE, -1);
		std::vector<char> how(SIZE, 0);
		std::vector<char> visited(SIZE, 0);

		std::queue<int> q;
		q.push(startValue);
		visited[startValue] = 1;

		while (!q.empty())
		{
			int curr = q.front();
			q.pop();
			if (curr == targetValue)
			{
				break;
			}

			int nextVals[4] = {OpD(curr), OpS(curr), OpL(curr), OpR(curr)};
			const char ops[4] = {'D', 'S', 'L', 'R'};

			for (int j = 0; j < 4; ++j)
			{
				int nxt = nextVals[j];
				if (visited[nxt])
				{
					continue;
				}
				visited[nxt] = 1;
				previous[nxt] = curr;
				how[nxt] = ops[j];
				q.push(nxt);
			}
		}

		std::string result;
		for (int i = targetValue; i != startValue; i = previous[i])
		{
			result.push_back(how[i]);
		}
		std::reverse(result.begin(), result.end());

		std::cout << result << std::endl;
	}

	return 0;
}
