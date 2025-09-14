#include <iostream>
#include <vector>
#include <string>

static int ToIndex(char ch)
{
	if (ch >= 'A' && ch <= 'Z')
	{
		return ch - 'A';
	}
	else
	{
		return 26 + (ch - 'a');
	}
}

int main()
{
	std::string original;
	int queryCount;
	std::cin >> original >> queryCount;

	int length = static_cast<int>(original.size());

	std::vector<int> originalIndex(length, 0);
	for (int i = 0; i < length; ++i)
	{
		originalIndex[i] = ToIndex(original[i]);
	}

	std::vector<int> currentMap(52, 0);
	for (int i = 0; i < 52; ++i)
	{
		currentMap[i] = i;
	}

	for (int qi = 0; qi < queryCount; ++qi)
	{
		int type;
		std::cin >> type;

		if (type == 1)
		{
			char c1;
			char c2;
			std::cin >> c1 >> c2;

			int toReplace = ToIndex(c1);
			int replaceWith = ToIndex(c2);

			if (toReplace != replaceWith)
			{
				for (int i = 0; i < 52; ++i)
				{
					if (currentMap[i] == toReplace)
					{
						currentMap[i] = replaceWith;
					}
				}
			}
		}
		else
		{
			int best = 0;
			int run = 0;
			int prev = -1;

			for (int i = 0; i < length; ++i)
			{
				int now = currentMap[originalIndex[i]];

				if (i == 0)
				{
					prev = now;
					run = 1;
				}
				else
				{
					if (now == prev)
					{
						++run;
					}
					else
					{
						if (run > best)
						{
							best = run;
						}
						prev = now;
						run = 1;
					}
				}
			}

			if (run > best)
			{
				best = run;
			}

			std::cout << best << std::endl;
		}
	}

	return 0;
}
