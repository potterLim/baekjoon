#include <cmath>
#include <iomanip>
#include <iostream>

int main()
{
	int n;
	std::cin >> n;

	long long firstX;
	long long firstY;
	std::cin >> firstX >> firstY;

	long long prevX;
	long long prevY;
	prevX = firstX;
	prevY = firstY;

	long long sumCross;
	sumCross = 0;

	for (int i = 1; i < n; ++i)
	{
		long long x;
		long long y;
		std::cin >> x >> y;

		long long term;
		term = prevX * y - prevY * x;
		sumCross += term;

		prevX = x;
		prevY = y;
	}

	long long closingTerm;
	closingTerm = prevX * firstY - prevY * firstX;
	sumCross += closingTerm;

	long double area;
	area = std::fabs(static_cast<long double>(sumCross)) / 2.0L;

	std::cout << std::fixed << std::setprecision(1) << static_cast<double>(area) << std::endl;
	return 0;
}
