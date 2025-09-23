#include <algorithm>
#include <iostream>
#include <vector>

struct PositionWeight
{
	int position;
	long long weight;
};

int main()
{
	int length;
	if (!(std::cin >> length))
	{
		return 0;
	}

	std::vector<PositionWeight> weights;
	weights.reserve(length);

	for (int i = 1; i <= length; ++i)
	{
		long long leftChoices = static_cast<long long>(i);
		long long rightChoices = static_cast<long long>(length - i + 1);
		long long contribution = leftChoices * rightChoices;

		PositionWeight pw;
		pw.position = i;
		pw.weight = contribution;
		weights.push_back(pw);
	}

	std::sort(
		weights.begin(),
		weights.end(),
		[](const PositionWeight& a, const PositionWeight& b)
		{
			if (a.weight != b.weight)
			{
				return a.weight > b.weight;
			}
			return a.position < b.position;
		});

	std::vector<int> result;
	result.assign(length + 1, 0);

	int currentValue = length;
	for (int i = 0; i < length; ++i)
	{
		int pos = weights[static_cast<std::size_t>(i)].position;
		result[pos] = currentValue;
		--currentValue;
	}

	for (int i = 1; i <= length; ++i)
	{
		if (i > 1)
		{
			std::cout << ' ';
		}
		std::cout << result[i];
	}
	std::cout << std::endl;

	return 0;
}
