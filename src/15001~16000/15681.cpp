#include <iostream>
#include <vector>
#include <stack>
#include <string>

int main()
{
	int vertexCount;
	int rootVertex;
	int queryCount;
	std::cin >> vertexCount >> rootVertex >> queryCount;

	std::vector<std::vector<int>> adjacency(vertexCount + 1);
	adjacency.reserve(vertexCount + 1);

	for (int i = 0; i < vertexCount - 1; ++i)
	{
		int u;
		int v;
		std::cin >> u >> v;
		adjacency[u].push_back(v);
		adjacency[v].push_back(u);
	}

	std::vector<int> parent(vertexCount + 1, 0);
	std::vector<int> subtreeSize(vertexCount + 1, 0);
	std::vector<int> postorder;
	postorder.reserve(vertexCount);

	std::stack<int> stack;
	stack.push(rootVertex);
	parent[rootVertex] = -1;

	while (!stack.empty())
	{
		int current = stack.top();
		stack.pop();
		postorder.push_back(current);

		const std::vector<int>& adj = adjacency[current];
		for (int i = 0; i < static_cast<int>(adj.size()); ++i)
		{
			int next = adj[i];
			if (next == parent[current])
			{
				continue;
			}
			parent[next] = current;
			stack.push(next);
		}
	}

	for (int i = static_cast<int>(postorder.size()) - 1; i >= 0; --i)
	{
		int node = postorder[i];
		int size = 1;
		const std::vector<int>& adj = adjacency[node];
		for (int j = 0; j < static_cast<int>(adj.size()); ++j)
		{
			int child = adj[j];
			if (child == parent[node])
			{
				continue;
			}
			size += subtreeSize[child];
		}
		subtreeSize[node] = size;
	}

	std::string output;
	output.reserve(queryCount * 3);
	for (int i = 0; i < queryCount; ++i)
	{
		int queryVertex;
		std::cin >> queryVertex;
		output += std::to_string(subtreeSize[queryVertex]);
		output += '\n';
	}
	std::cout << output;
	return 0;
}
