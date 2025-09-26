#include <iostream>
#include <string>
#include <vector>

int main()
{
	int length;
	std::cin >> length;

	std::vector<int> sequence(length);
	for (int i = 0; i < length; ++i)
	{
		std::cin >> sequence[i];
	}

	std::vector<int> nextGreater(length, -1);
	std::vector<int> indexStack;
	indexStack.reserve(length);

	for (int i = 0; i < length; ++i)
	{
		while (!indexStack.empty() && sequence[indexStack.back()] < sequence[i])
		{
			nextGreater[indexStack.back()] = sequence[i];
			indexStack.pop_back();
		}
		indexStack.push_back(i);
	}

	std::string out;
	out.reserve(static_cast<std::size_t>(length) * 8);
	for (int i = 0; i < length; ++i)
	{
		if (i > 0)
		{
			out.push_back(' ');
		}
		out += std::to_string(nextGreater[i]);
	}
	out.push_back('\n');
	std::cout << out;

	return 0;
}
