#include <iostream>

int main()
{
	long long x1;
	long long y1;
	long long x2;
	long long y2;
	long long x3;
	long long y3;

	std::cin >> x1 >> y1;
	std::cin >> x2 >> y2;
	std::cin >> x3 >> y3;

	long long dx1;
	dx1 = x2 - x1;
	long long dy1;
	dy1 = y2 - y1;
	long long dx2;
	dx2 = x3 - x1;
	long long dy2;
	dy2 = y3 - y1;

	long long cross;
	cross = dx1 * dy2 - dy1 * dx2;

	if (cross > 0)
	{
		std::cout << 1 << std::endl;
	}
	else
	{
		if (cross < 0)
		{
			std::cout << -1 << std::endl;
		}
		else
		{
			std::cout << 0 << std::endl;
		}
	}

	return 0;
}
