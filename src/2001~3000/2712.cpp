#include <iostream>
#include <iomanip>
#include <string>

int main()
{
	int testCount;
	std::cin >> testCount;

	for (int i = 0; i < testCount; ++i)
	{
		double value;
		std::string unit;
		std::cin >> value >> unit;

		double resultValue;
		std::string resultUnit;

		if (unit == "kg")
		{
			resultValue = value * 2.2046;
			resultUnit = "lb";
		}
		else if (unit == "lb")
		{
			resultValue = value * 0.4536;
			resultUnit = "kg";
		}
		else if (unit == "l")
		{
			resultValue = value * 0.2642;
			resultUnit = "g";
		}
		else if (unit == "g")
		{
			resultValue = value * 3.7854;
			resultUnit = "l";
		}

		std::cout << std::fixed << std::setprecision(4) << resultValue << ' ' << resultUnit << std::endl;
	}

	return 0;
}
