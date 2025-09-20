#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

int main()
{
	int villageCount;
	std::cin >> villageCount;

	std::vector<int> population(villageCount + 1);
	for (int i = 1; i <= villageCount; ++i)
	{
		std::cin >> population[i];
	}

	std::vector<std::vector<int>> adjacency(villageCount + 1);
	for (int i = 0; i < villageCount - 1; ++i)
	{
		int a;
		int b;
		std::cin >> a >> b;
		adjacency[a].push_back(b);
		adjacency[b].push_back(a);
	}

	int root = 1;

	std::vector<int> parent(villageCount + 1, 0);
	std::vector<int> order;
	order.reserve(villageCount);

	std::stack<int> stack;
	stack.push(root);
	parent[root] = -1;

	while (!stack.empty())
	{
		int u = stack.top();
		stack.pop();
		order.push_back(u);

		const std::vector<int>& adj = adjacency[u];
		for (int i = 0; i < static_cast<int>(adj.size()); ++i)
		{
			int v = adj[i];
			if (v == parent[u])
			{
				continue;
			}
			parent[v] = u;
			stack.push(v);
		}
	}

	std::vector<long long> dpNotSelected(villageCount + 1, 0);
	std::vector<long long> dpSelected(villageCount + 1, 0);

	for (int i = static_cast<int>(order.size()) - 1; i >= 0; --i)
	{
		int u = order[i];

		long long takeU = population[u];
		long long skipU = 0;

		const std::vector<int>& adj = adjacency[u];
		for (int j = 0; j < static_cast<int>(adj.size()); ++j)
		{
			int v = adj[j];
			if (v == parent[u])
			{
				continue;
			}
			takeU += dpNotSelected[v];
			skipU += std::max(dpNotSelected[v], dpSelected[v]);
		}

		dpSelected[u] = takeU;
		dpNotSelected[u] = skipU;
	}

	long long answer = std::max(dpNotSelected[root], dpSelected[root]);
	std::cout << answer << std::endl;

	return 0;
}
