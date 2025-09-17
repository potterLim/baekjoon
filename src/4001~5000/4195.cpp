#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>

struct DisjointSet
{
	std::vector<int> parent;
	std::vector<int> setSize;

	explicit DisjointSet(int capacity)
	{
		parent.resize(capacity);
		setSize.resize(capacity, 1);
		for (int i = 0; i < capacity; ++i)
		{
			parent[i] = i;
		}
	}

	int Find(int x)
	{
		if (parent[x] != x)
		{
			parent[x] = Find(parent[x]);
		}
		return parent[x];
	}

	int Union(int a, int b)
	{
		int ra = Find(a);
		int rb = Find(b);
		if (ra == rb)
		{
			return setSize[ra];
		}

		if (setSize[ra] < setSize[rb])
		{
			std::swap(ra, rb);
		}

		parent[rb] = ra;
		setSize[ra] += setSize[rb];
		return setSize[ra];
	}
};

int main()
{
	int testCaseCount;
	std::cin >> testCaseCount;

	for (int i = 0; i < testCaseCount; ++i)
	{
		int relationCount;
		std::cin >> relationCount;

		DisjointSet dsu(relationCount * 2 + 5);

		std::unordered_map<std::string, int> idOf;
		idOf.reserve(relationCount * 2 + 5);

		int nextId = 0;

		for (int j = 0; j < relationCount; ++j)
		{
			std::string nameA;
			std::string nameB;
			std::cin >> nameA >> nameB;

			if (idOf.find(nameA) == idOf.end())
			{
				idOf[nameA] = nextId++;
			}
			if (idOf.find(nameB) == idOf.end())
			{
				idOf[nameB] = nextId++;
			}

			int a = idOf[nameA];
			int b = idOf[nameB];

			int sizeAfterUnion = dsu.Union(a, b);
			std::cout << sizeAfterUnion << std::endl;
		}
	}

	return 0;
}
