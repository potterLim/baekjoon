#include <algorithm>
#include <cstdint>
#include <iostream>
#include <queue>
#include <vector>

struct Jewel
{
	int Weight;
	int Value;
};

static bool compareJewelByWeightAscThenValueDesc(const Jewel& lhs, const Jewel& rhs)
{
	if (lhs.Weight < rhs.Weight)
	{
		return true;
	}
	else if (lhs.Weight > rhs.Weight)
	{
		return false;
	}
	else
	{
		if (lhs.Value > rhs.Value)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

static bool compareIntAsc(const int lhs, const int rhs)
{
	return lhs < rhs;
}

int main()
{
	int jewelCount;
	int bagCount;

	std::cin >> jewelCount >> bagCount;

	std::vector<Jewel> jewels;
	jewels.reserve(static_cast<std::size_t>(jewelCount));
	for (int i = 0; i < jewelCount; ++i)
	{
		Jewel jewel;
		std::cin >> jewel.Weight >> jewel.Value;
		jewels.push_back(jewel);
	}

	std::vector<int> bagCapacities;
	bagCapacities.resize(static_cast<std::size_t>(bagCount));
	for (int i = 0; i < bagCount; ++i)
	{
		std::cin >> bagCapacities[static_cast<std::size_t>(i)];
	}

	std::sort(jewels.begin(), jewels.end(), compareJewelByWeightAscThenValueDesc);
	std::sort(bagCapacities.begin(), bagCapacities.end(), compareIntAsc);

	std::priority_queue<int> candidateValues;

	std::int64_t totalValue = 0;
	int nextJewelIndex = 0;

	for (int i = 0; i < bagCount; ++i)
	{
		const int bagCapacity = bagCapacities[static_cast<std::size_t>(i)];

		while (nextJewelIndex < jewelCount &&
			   jewels[static_cast<std::size_t>(nextJewelIndex)].Weight <= bagCapacity)
		{
			candidateValues.push(jewels[static_cast<std::size_t>(nextJewelIndex)].Value);
			nextJewelIndex = nextJewelIndex + 1;
		}

		if (!candidateValues.empty())
		{
			totalValue += static_cast<std::int64_t>(candidateValues.top());
			candidateValues.pop();
		}
	}

	std::cout << totalValue << '\n';
	return 0;
}
