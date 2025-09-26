#include <cassert>
#include <cstdint>
#include <iostream>
#include <stack>
#include <vector>

int main()
{
	int n;
	if (!(std::cin >> n))
	{
		assert(false);
		return 0;
	}

	std::vector<uint64_t> heights;
	heights.reserve(static_cast<size_t>(n) + 1);

	for (int i = 0; i != n; ++i)
	{
		uint64_t h;
		std::cin >> h;
		heights.push_back(h);
	}

	heights.push_back(0);

	std::stack<size_t> indices;
	uint64_t maxArea = 0;

	for (size_t i = 0; i != heights.size(); ++i)
	{
		while (!indices.empty() && heights[indices.top()] > heights[i])
		{
			size_t topIndex = indices.top();
			indices.pop();

			size_t width;
			if (indices.empty())
			{
				width = i;
			}
			else
			{
				width = i - indices.top() - 1;
			}

			uint64_t area = heights[topIndex] * static_cast<uint64_t>(width);
			if (area > maxArea)
			{
				maxArea = area;
			}
		}
		indices.push(i);
	}

	std::cout << maxArea << std::endl;
	return 0;
}
