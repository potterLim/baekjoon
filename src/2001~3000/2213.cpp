#include <algorithm>
#include <iostream>
#include <vector>

class WeightedIndependentSetSolver
{
public:
	explicit WeightedIndependentSetSolver(int vertexCount)
		: mAdjacencyList(vertexCount + 1)
		, mWeight(vertexCount + 1, 0)
		, mDpInclude(vertexCount + 1, 0)
		, mDpExclude(vertexCount + 1, 0)
		, mParent(vertexCount + 1, 0)
	{
	}

	void SetWeight(int vertex, long long w)
	{
		mWeight[vertex] = w;
	}

	void AddEdge(int u, int v)
	{
		mAdjacencyList[u].push_back(v);
		mAdjacencyList[v].push_back(u);
	}

	void SolveFromRoot(int root)
	{
		BuildDfsOrder(root);
		RunTreeDp();
		ReconstructSolution(root);
		std::sort(mChosenVertices.begin(), mChosenVertices.end());
	}

	long long GetMaximumWeight() const
	{
		long long includeRoot = mDpInclude[1];
		long long excludeRoot = mDpExclude[1];
		if (excludeRoot > includeRoot)
		{
			return excludeRoot;
		}
		else
		{
			return includeRoot;
		}
	}

	const std::vector<int>& GetChosenVertices() const
	{
		return mChosenVertices;
	}

private:
	std::vector<std::vector<int>> mAdjacencyList;
	std::vector<long long> mWeight;
	std::vector<long long> mDpInclude;
	std::vector<long long> mDpExclude;
	std::vector<int> mDfsOrder;
	std::vector<int> mParent;
	std::vector<int> mChosenVertices;

	void BuildDfsOrder(int root)
	{
		std::vector<int> stack;
		std::vector<char> visited(mAdjacencyList.size(), 0);

		stack.push_back(root);
		mParent[root] = 0;
		visited[root] = 1;

		while (!stack.empty())
		{
			int u = stack.back();
			stack.pop_back();
			mDfsOrder.push_back(u);

			for (std::size_t i = 0; i < mAdjacencyList[u].size(); ++i)
			{
				int v = mAdjacencyList[u][i];
				if (visited[v] == 0)
				{
					visited[v] = 1;
					mParent[v] = u;
					stack.push_back(v);
				}
			}
		}
	}

	void RunTreeDp()
	{
		for (int i = static_cast<int>(mDfsOrder.size()) - 1; i >= 0; --i)
		{
			int u = mDfsOrder[static_cast<std::size_t>(i)];

			mDpInclude[u] = mWeight[u];
			mDpExclude[u] = 0;

			for (std::size_t j = 0; j < mAdjacencyList[u].size(); ++j)
			{
				int v = mAdjacencyList[u][j];
				if (v == mParent[u])
				{
					continue;
				}

				mDpInclude[u] += mDpExclude[v];

				if (mDpInclude[v] > mDpExclude[v])
				{
					mDpExclude[u] += mDpInclude[v];
				}
				else
				{
					mDpExclude[u] += mDpExclude[v];
				}
			}
		}
	}

	void ReconstructSolution(int root)
	{
		std::vector<int> stackVertex;
		std::vector<char> stackTake;

		if (mDpInclude[root] >= mDpExclude[root])
		{
			stackVertex.push_back(root);
			stackTake.push_back(1);
		}
		else
		{
			stackVertex.push_back(root);
			stackTake.push_back(0);
		}

		while (!stackVertex.empty())
		{
			int u = stackVertex.back();
			char take = stackTake.back();
			stackVertex.pop_back();
			stackTake.pop_back();

			if (take == 1)
			{
				mChosenVertices.push_back(u);
				for (std::size_t i = 0; i < mAdjacencyList[u].size(); ++i)
				{
					int v = mAdjacencyList[u][i];
					if (v == mParent[u])
					{
						continue;
					}
					stackVertex.push_back(v);
					stackTake.push_back(0);
				}
			}
			else
			{
				for (std::size_t i = 0; i < mAdjacencyList[u].size(); ++i)
				{
					int v = mAdjacencyList[u][i];
					if (v == mParent[u])
					{
						continue;
					}
					stackVertex.push_back(v);
					if (mDpInclude[v] >= mDpExclude[v])
					{
						stackTake.push_back(1);
					}
					else
					{
						stackTake.push_back(0);
					}
				}
			}
		}
	}
};

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int vertexCount = 0;
	if (!(std::cin >> vertexCount))
	{
		return 0;
	}

	WeightedIndependentSetSolver solver(vertexCount);

	for (int i = 1; i <= vertexCount; ++i)
	{
		long long w = 0;
		std::cin >> w;
		solver.SetWeight(i, w);
	}

	int edgesRead = 0;
	while (edgesRead < vertexCount - 1)
	{
		int u = 0;
		int v = 0;
		if (!(std::cin >> u >> v))
		{
			break;
		}
		solver.AddEdge(u, v);
		++edgesRead;
	}

	int root = 1;
	solver.SolveFromRoot(root);

	long long bestWeight = solver.GetMaximumWeight();
	const std::vector<int>& chosen = solver.GetChosenVertices();

	std::cout << bestWeight << std::endl;

	for (std::size_t i = 0; i < chosen.size(); ++i)
	{
		if (i > 0)
		{
			std::cout << " ";
		}
		std::cout << chosen[i];
	}
	std::cout << std::endl;

	return 0;
}
