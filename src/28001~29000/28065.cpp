#include <iostream>
#include <vector>

int main()
{
	int length;
	std::cin >> length;

	std::vector<int> swSequence;
	swSequence.reserve(length);

	int leftValue = 1;
	int rightValue = length;

	while (static_cast<int>(swSequence.size()) < length)
	{
		if (leftValue <= rightValue)
		{
			swSequence.push_back(rightValue);
			--rightValue;
		}

		if (static_cast<int>(swSequence.size()) >= length)
		{
			break;
		}

		if (leftValue <= rightValue)
		{
			swSequence.push_back(leftValue);
			++leftValue;
		}
	}

	for (int i = 0; i < length; ++i)
	{
		if (i > 0)
		{
			std::cout << ' ';
		}
		std::cout << swSequence[i];
	}
	std::cout << std::endl;
	return 0;
}
