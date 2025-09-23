#include <iostream>
#include <string>

int main()
{
	int inputLength;
	if (!(std::cin >> inputLength))
	{
		return 0;
	}

	std::string outputPalindrome(inputLength, 'a');
	std::cout << outputPalindrome << std::endl;
	return 0;
}
