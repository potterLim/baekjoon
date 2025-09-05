#include <algorithm>
#include <iostream>
#include <vector>

int main()
{
	int n;
	std::cin >> n;

	std::vector<int> nums(n);
	for (int i = 0; i < n; ++i)
	{
		std::cin >> nums[i];
	}

	int target;
	std::cin >> target;

	std::sort(nums.begin(), nums.end());

	int left = 0;
	int right = n - 1;
	int pairCount = 0;

	while (left < right)
	{
		int s = nums[left] + nums[right];
		if (s == target)
		{
			pairCount = pairCount + 1;
			left = left + 1;
			right = right - 1;
		}
		else if (s < target)
		{
			left = left + 1;
		}
		else
		{
			right = right - 1;
		}
	}

	std::cout << pairCount << std::endl;
	return 0;
}
