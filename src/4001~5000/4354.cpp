#include <iostream>
#include <string>
#include <vector>

int main()
{
	std::string inputString;
	while (true)
	{
		if (!std::getline(std::cin, inputString))
		{
			return 0;
		}
		if (inputString == ".")
		{
			break;
		}

		int stringLength = static_cast<int>(inputString.size());
		std::vector<int> prefixFunction(stringLength, 0);

		for (int i = 1; i < stringLength; ++i)
		{
			int matchedLength = prefixFunction[i - 1];
			while (matchedLength > 0 && inputString[i] != inputString[matchedLength])
			{
				matchedLength = prefixFunction[matchedLength - 1];
			}
			if (inputString[i] == inputString[matchedLength])
			{
				matchedLength += 1;
			}
			prefixFunction[i] = matchedLength;
		}

		int lastPrefixLength = prefixFunction[stringLength - 1];
		int periodLength = stringLength - lastPrefixLength;
		int repetitionCount;

		if (stringLength % periodLength == 0)
		{
			repetitionCount = stringLength / periodLength;
		}
		else
		{
			repetitionCount = 1;
		}

		std::cout << repetitionCount << std::endl;
	}

	return 0;
}
