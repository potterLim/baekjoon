#include <iostream>
#include <string>
#include <vector>

int main()
{
	int length;
	std::cin >> length;

	std::vector<int> sequence(length);
	int maxValue = 0;
	for (int i = 0; i < length; ++i)
	{
		std::cin >> sequence[i];
		if (sequence[i] > maxValue)
		{
			maxValue = sequence[i];
		}
	}

	std::vector<int> frequency(maxValue + 1, 0);
	for (int i = 0; i < length; ++i)
	{
		++frequency[sequence[i]];
	}

	std::vector<int> nextGreaterByFreq(length, -1);
	std::vector<int> indexStack;
	indexStack.reserve(length);

	for (int i = 0; i < length; ++i)
	{
		int currentFreq = frequency[sequence[i]];
		while (!indexStack.empty() && frequency[sequence[indexStack.back()]] < currentFreq)
		{
			nextGreaterByFreq[indexStack.back()] = sequence[i];
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
		out += std::to_string(nextGreaterByFreq[i]);
	}
	out.push_back('\n');
	std::cout << out;

	return 0;
}
