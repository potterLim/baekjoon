#include <iostream>
#include <vector>
#include <stack>

int main()
{
	int nodeCount;
	std::cin >> nodeCount;

	std::vector<std::vector<int>> adjacency(nodeCount + 1);
	for (int i = 0; i < nodeCount - 1; ++i)
	{
		int u;
		int v;
		std::cin >> u >> v;
		adjacency[u].push_back(v);
		adjacency[v].push_back(u);
	}

	int root = 1;
	std::vector<int> parent(nodeCount + 1, 0);
	std::vector<int> order;
	order.reserve(nodeCount);

	std::stack<int> stack;
	stack.push(root);
	parent[root] = -1;

	while (!stack.empty())
	{
		int current = stack.top();
		stack.pop();
		order.push_back(current);

		const std::vector<int>& neighbors = adjacency[current];
		for (int i = 0; i < static_cast<int>(neighbors.size()); ++i)
		{
			int next = neighbors[i];
			if (next == parent[current])
			{
				continue;
			}
			parent[next] = current;
			stack.push(next);
		}
	}

	std::vector<int> dpNotAdaptor(nodeCount + 1, 0);
	std::vector<int> dpAdaptor(nodeCount + 1, 0);

	for (int i = static_cast<int>(order.size()) - 1; i >= 0; --i)
	{
		int u = order[i];
		int takeU = 1;
		int skipU = 0;

		const std::vector<int>& neighbors = adjacency[u];
		for (int j = 0; j < static_cast<int>(neighbors.size()); ++j)
		{
			int v = neighbors[j];
			if (v == parent[u])
			{
				continue;
			}
			skipU += dpAdaptor[v];
			takeU += (dpNotAdaptor[v] < dpAdaptor[v]) ? dpNotAdaptor[v] : dpAdaptor[v];
		}

		dpAdaptor[u] = takeU;
		dpNotAdaptor[u] = skipU;
	}

	int answer = (dpNotAdaptor[root] < dpAdaptor[root]) ? dpNotAdaptor[root] : dpAdaptor[root];
	std::cout << answer << std::endl;
	return 0;
}
