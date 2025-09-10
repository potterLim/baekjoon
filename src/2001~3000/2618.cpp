#include <iostream>
#include <vector>
#include <algorithm>

struct Point
{
	int x;
	int y;
};

static int GetDistance(const Point& a, const Point& b)
{
	int dx = a.x - b.x;
	if (dx < 0)
	{
		dx = -dx;
	}
	int dy = a.y - b.y;
	if (dy < 0)
	{
		dy = -dy;
	}
	return dx + dy;
}

int main()
{
	int gridSize;
	int incidentCount;
	std::cin >> gridSize;
	std::cin >> incidentCount;

	std::vector<Point> incident(incidentCount + 1);
	for (int i = 1; i <= incidentCount; ++i)
	{
		std::cin >> incident[i].x >> incident[i].y;
	}

	const int INF = 1'000'000'000;

	std::vector<std::vector<int>> minDistance(incidentCount + 1, std::vector<int>(incidentCount + 1, INF));
	std::vector<std::vector<std::pair<int, int>>> parent(incidentCount + 1, std::vector<std::pair<int, int>>(incidentCount + 1, {-1, -1}));
	std::vector<std::vector<int>> decision(incidentCount + 1, std::vector<int>(incidentCount + 1, 0));

	minDistance[0][0] = 0;

	for (int i = 0; i <= incidentCount; ++i)
	{
		for (int j = 0; j <= incidentCount; ++j)
		{
			if (minDistance[i][j] == INF)
			{
				continue;
			}
			int nextIdx = std::max(i, j) + 1;
			if (nextIdx > incidentCount)
			{
				continue;
			}

			Point car1Pos = (i == 0) ? Point{1, 1} : incident[i];
			Point car2Pos = (j == 0) ? Point{gridSize, gridSize} : incident[j];
			Point nextPos = incident[nextIdx];

			int cand1 = minDistance[i][j] + GetDistance(car1Pos, nextPos);
			if (cand1 < minDistance[nextIdx][j])
			{
				minDistance[nextIdx][j] = cand1;
				parent[nextIdx][j] = {i, j};
				decision[nextIdx][j] = 1;
			}

			int cand2 = minDistance[i][j] + GetDistance(car2Pos, nextPos);
			if (cand2 < minDistance[i][nextIdx])
			{
				minDistance[i][nextIdx] = cand2;
				parent[i][nextIdx] = {i, j};
				decision[i][nextIdx] = 2;
			}
		}
	}

	int bestI = 0;
	int bestJ = 0;
	int best = INF;
	for (int i = 0; i <= incidentCount; ++i)
	{
		if (minDistance[i][incidentCount] < best)
		{
			best = minDistance[i][incidentCount];
			bestI = i;
			bestJ = incidentCount;
		}
		if (minDistance[incidentCount][i] < best)
		{
			best = minDistance[incidentCount][i];
			bestI = incidentCount;
			bestJ = i;
		}
	}

	std::cout << best << std::endl;

	std::vector<int> assignment;
	assignment.reserve(incidentCount);

	int curI = bestI;
	int curJ = bestJ;
	while (curI != 0 || curJ != 0)
	{
		assignment.push_back(decision[curI][curJ]);
		std::pair<int, int> p = parent[curI][curJ];
		curI = p.first;
		curJ = p.second;
	}

	for (int k = static_cast<int>(assignment.size()) - 1; k >= 0; --k)
	{
		std::cout << assignment[k] << std::endl;
	}

	return 0;
}
