#include <iostream>
#include <string>

int main()
{
	int length;
	std::cin >> length;

	std::string binary;
	std::cin >> binary;

	long long oddOperationCount = 0;
	bool lowerAllZero = true;

	for (int i = length - 1; i >= 0; --i)
	{
		int bit = binary[i] - '0';

		if (i == 0 && lowerAllZero)
		{
			break;
		}

		if ((bit == 1) == lowerAllZero)
		{
			++oddOperationCount;
		}

		if (bit == 1)
		{
			lowerAllZero = false;
		}
	}

	std::cout << oddOperationCount << std::endl;
	return 0;
}
