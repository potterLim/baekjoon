#include <iostream>
#include <string>

int main()
{
	int answerLength;
	std::cin >> answerLength;

	std::string alphabet;
	std::cin >> alphabet;

	std::string universal;
	universal.reserve(static_cast<std::size_t>(answerLength) * alphabet.size());

	for (int i = 0; i < answerLength; ++i)
	{
		universal += alphabet;
	}

	std::cout << universal << std::endl;
	return 0;
}
