#include <iostream>

int main()
{
	int testCaseCount;
	std::cin >> testCaseCount;

	for (int i = 0; i < testCaseCount; ++i)
	{
		int countryCount;
		int flightTypeCount;
		std::cin >> countryCount >> flightTypeCount;

		for (int j = 0; j < flightTypeCount; ++j)
		{
			int a;
			int b;
			std::cin >> a >> b;
		}

		std::cout << (countryCount - 1) << std::endl;
	}

	return 0;
}
