#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

int main()
{
	int n, m;
	std::cin >> n >> m;

	std::vector<std::vector<std::pair<int, int>>> graph(n + 1);

	for (int i = 0; i < m; ++i)
	{
		int from, to, cost;
		std::cin >> from >> to >> cost;
		graph[from].push_back({to, cost});
	}

	int start, target;
	std::cin >> start >> target;

	const int INF = 1'000'000'000;
	std::vector<int> dist(n + 1, INF);
	std::vector<int> parent(n + 1, -1);

	dist[start] = 0;

	using Node = std::pair<int, int>;
	std::priority_queue<Node, std::vector<Node>, std::greater<Node>> pq;
	pq.push({0, start});

	while (!pq.empty())
	{
		int currentDist = pq.top().first;
		int current = pq.top().second;
		pq.pop();

		if (currentDist > dist[current])
		{
			continue;
		}

		for (auto& edge : graph[current])
		{
			int next = edge.first;
			int nextDist = currentDist + edge.second;
			if (nextDist < dist[next])
			{
				dist[next] = nextDist;
				parent[next] = current;
				pq.push({nextDist, next});
			}
		}
	}

	std::cout << dist[target] << std::endl;

	std::vector<int> path;
	for (int i = target; i != -1; i = parent[i])
	{
		path.push_back(i);
	}
	std::reverse(path.begin(), path.end());

	std::cout << path.size() << std::endl;
	for (size_t i = 0; i < path.size(); ++i)
	{
		std::cout << path[i];
		if (i + 1 < path.size())
		{
			std::cout << " ";
		}
	}
	std::cout << std::endl;

	return 0;
}
