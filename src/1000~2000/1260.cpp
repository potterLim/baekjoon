#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <vector>

int main()
{
	int nodeCount;
	int edgeCount;
	int startNode;
	std::cin >> nodeCount >> edgeCount >> startNode;

	std::vector<std::vector<int>> adjacency(nodeCount + 1);
	for (int i = 0; i < edgeCount; ++i)
	{
		int u;
		int v;
		std::cin >> u >> v;
		adjacency[u].push_back(v);
		adjacency[v].push_back(u);
	}
	for (int i = 1; i <= nodeCount; ++i)
	{
		std::sort(adjacency[i].begin(), adjacency[i].end());
	}

	std::vector<char> visitedD(nodeCount + 1, 0);
	std::vector<int> dfsOrder;
	dfsOrder.reserve(nodeCount);

	std::stack<int> st;
	st.push(startNode);
	while (!st.empty())
	{
		int cur = st.top();
		st.pop();

		if (visitedD[cur] != 0)
		{
			continue;
		}
		else
		{
			visitedD[cur] = 1;
			dfsOrder.push_back(cur);

			const std::vector<int>& nbrs = adjacency[cur];
			for (int i = static_cast<int>(nbrs.size()) - 1; i >= 0; --i)
			{
				int nxt = nbrs[i];
				if (visitedD[nxt] == 0)
				{
					st.push(nxt);
				}
			}
		}
	}

	std::vector<char> visitedB(nodeCount + 1, 0);
	std::vector<int> bfsOrder;
	bfsOrder.reserve(nodeCount);

	std::queue<int> q;
	visitedB[startNode] = 1;
	q.push(startNode);

	while (!q.empty())
	{
		int cur = q.front();
		q.pop();
		bfsOrder.push_back(cur);

		const std::vector<int>& nbrs = adjacency[cur];
		for (int i = 0; i < static_cast<int>(nbrs.size()); ++i)
		{
			int nxt = nbrs[i];
			if (visitedB[nxt] == 0)
			{
				visitedB[nxt] = 1;
				q.push(nxt);
			}
		}
	}

	for (int i = 0; i < static_cast<int>(dfsOrder.size()); ++i)
	{
		if (i != 0)
		{
			std::cout << ' ';
		}
		std::cout << dfsOrder[i];
	}
	std::cout << std::endl;

	for (int i = 0; i < static_cast<int>(bfsOrder.size()); ++i)
	{
		if (i != 0)
		{
			std::cout << ' ';
		}
		std::cout << bfsOrder[i];
	}
	std::cout << std::endl;

	return 0;
}
