#include <iostream>
#include <vector>

int main()
{
	int n;
	std::cin >> n;

	std::vector<int> serim;
	std::vector<int> seongju;

	if (n == 2)
	{
		serim.push_back(1);
		seongju.push_back(2);
	}
	else
	{
		int r = n % 3;

		if (r == 0)
		{
			for (int i = 1; i + 2 <= n; i += 3)
			{
				serim.push_back(i + 2);
				seongju.push_back(i);
				seongju.push_back(i + 1);
			}
		}
		else if (r == 1)
		{
			for (int i = 2; i + 2 <= n; i += 3)
			{
				serim.push_back(i + 2);
				seongju.push_back(i);
				seongju.push_back(i + 1);
			}
		}
		else
		{
			serim.push_back(1);
			serim.push_back(5);
			seongju.push_back(2);
			seongju.push_back(3);
			seongju.push_back(4);
			for (int i = 6; i + 2 <= n; i += 3)
			{
				serim.push_back(i + 2);
				seongju.push_back(i);
				seongju.push_back(i + 1);
			}
		}
	}

	std::cout << static_cast<int>(serim.size()) << std::endl;
	for (int i = 0; i < static_cast<int>(serim.size()); ++i)
	{
		if (i > 0)
		{
			std::cout << ' ';
		}
		std::cout << serim[i];
	}
	std::cout << std::endl;

	std::cout << static_cast<int>(seongju.size()) << std::endl;
	for (int i = 0; i < static_cast<int>(seongju.size()); ++i)
	{
		if (i > 0)
		{
			std::cout << ' ';
		}
		std::cout << seongju[i];
	}
	std::cout << std::endl;

	return 0;
}
