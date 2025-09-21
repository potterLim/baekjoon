#include <iostream>
#include <string>
#include <vector>

int main()
{
	std::string input;
	if (!(std::cin >> input))
	{
		std::cout << 0 << std::endl;
		return 0;
	}

	std::vector<char> stackBrackets;
	long long totalValue = 0;
	long long currentWeight = 1;
	char previousChar = 0;

	for (std::size_t i = 0; i < input.size(); ++i)
	{
		char ch = input[i];

		if (ch == '(')
		{
			stackBrackets.push_back('(');
			currentWeight *= 2;
		}
		else if (ch == '[')
		{
			stackBrackets.push_back('[');
			currentWeight *= 3;
		}
		else if (ch == ')')
		{
			if (stackBrackets.empty() || stackBrackets.back() != '(')
			{
				std::cout << 0 << std::endl;
				return 0;
			}
			if (previousChar == '(')
			{
				totalValue += currentWeight;
			}
			stackBrackets.pop_back();
			currentWeight /= 2;
		}
		else if (ch == ']')
		{
			if (stackBrackets.empty() || stackBrackets.back() != '[')
			{
				std::cout << 0 << std::endl;
				return 0;
			}
			if (previousChar == '[')
			{
				totalValue += currentWeight;
			}
			stackBrackets.pop_back();
			currentWeight /= 3;
		}
		else
		{
			std::cout << 0 << std::endl;
			return 0;
		}

		previousChar = ch;
	}

	if (!stackBrackets.empty())
	{
		std::cout << 0 << std::endl;
		return 0;
	}

	std::cout << totalValue << std::endl;
	return 0;
}
