#include <iostream>
#include <vector>

int main()
{
	int length;
	std::cin >> length;

	std::vector<int> sequence;
	sequence.reserve(length);

	if (length == 1)
	{
		std::cout << 1 << std::endl;
		return 0;
	}

	int threshold = length / 2;

	if (length % 2 == 0)
	{
		int half = length / 2;
		for (int i = 1; i <= half; ++i)
		{
			sequence.push_back(half + i);
			sequence.push_back(i);
		}
	}
	else
	{
		int step = threshold;
		int current = 1;
		for (int i = 0; i < length; ++i)
		{
			sequence.push_back(current);
			current += step;
			if (current > length)
			{
				current -= length;
			}
		}
	}

	for (int i = 0; i < length; ++i)
	{
		if (i > 0)
		{
			std::cout << ' ';
		}
		std::cout << sequence[i];
	}
	std::cout << std::endl;
	return 0;
}
