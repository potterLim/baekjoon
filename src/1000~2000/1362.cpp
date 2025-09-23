#include <iostream>
#include <string>

int main()
{
	int optimalWeight;
	int currentWeight;
	int scenarioIndex = 1;

	while (true)
	{
		std::cin >> optimalWeight >> currentWeight;
		if (optimalWeight == 0 && currentWeight == 0)
		{
			break;
		}

		bool isAlive = true;

		while (true)
		{
			std::string action;
			int value;
			std::cin >> action >> value;

			if (action == "#")
			{
				break;
			}

			if (isAlive)
			{
				if (action == "E")
				{
					currentWeight -= value;
				}
				else
				{
					currentWeight += value;
				}
				if (currentWeight <= 0)
				{
					isAlive = false;
				}
			}
		}

		std::cout << scenarioIndex << ' ';
		if (!isAlive)
		{
			std::cout << "RIP" << std::endl;
		}
		else if (currentWeight > optimalWeight / 2 && currentWeight < 2 * optimalWeight)
		{
			std::cout << ":-)" << std::endl;
		}
		else
		{
			std::cout << ":-(" << std::endl;
		}

		++scenarioIndex;
	}

	return 0;
}
