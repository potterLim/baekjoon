#include <algorithm>
#include <array>
#include <cmath>
#include <iostream>

int main()
{
	long long ability1;
	long long ability2;
	long long ability3;
	long long ability4;
	long long ability5;
	long long ability6;
	long long ability7;
	long long ability8;

	std::cin >> ability1 >> ability2 >> ability3 >> ability4 >> ability5 >> ability6 >> ability7 >> ability8;

	std::array<long double, 8> unitX;
	std::array<long double, 8> unitY;

	long double pi;
	pi = std::acos(-1.0L);

	for (int i = 0; i < 8; ++i)
	{
		long double theta;
		theta = (pi / 4.0L) * static_cast<long double>(i);
		unitX[i] = std::cos(theta);
		unitY[i] = std::sin(theta);
	}

	std::array<long long, 8> abilities;
	abilities[0] = ability1;
	abilities[1] = ability2;
	abilities[2] = ability3;
	abilities[3] = ability4;
	abilities[4] = ability5;
	abilities[5] = ability6;
	abilities[6] = ability7;
	abilities[7] = ability8;

	std::array<int, 8> indexPerm;
	for (int i = 0; i < 8; ++i)
	{
		indexPerm[i] = i;
	}

	long long convexCount;
	convexCount = 0;

	long double eps;
	eps = 1e-12L;

	do
	{
		std::array<long double, 8> pointX;
		std::array<long double, 8> pointY;

		for (int i = 0; i < 8; ++i)
		{
			long double radius;
			radius = static_cast<long double>(abilities[indexPerm[i]]);
			pointX[i] = radius * unitX[i];
			pointY[i] = radius * unitY[i];
		}

		bool seenPositive;
		bool seenNegative;
		seenPositive = false;
		seenNegative = false;

		bool isConvex;
		isConvex = true;

		for (int i = 0; i < 8; ++i)
		{
			int j;
			int k;
			j = (i + 1) % 8;
			k = (i + 2) % 8;

			long double edge1x;
			long double edge1y;
			long double edge2x;
			long double edge2y;

			edge1x = pointX[j] - pointX[i];
			edge1y = pointY[j] - pointY[i];
			edge2x = pointX[k] - pointX[j];
			edge2y = pointY[k] - pointY[j];

			long double crossValue;
			crossValue = edge1x * edge2y - edge1y * edge2x;

			if (crossValue > eps)
			{
				seenPositive = true;
			}
			else
			{
				if (crossValue < -eps)
				{
					seenNegative = true;
				}
			}

			if (seenPositive && seenNegative)
			{
				isConvex = false;
				break;
			}
		}

		if (isConvex)
		{
			++convexCount;
		}

	} while (std::next_permutation(indexPerm.begin(), indexPerm.end()));

	std::cout << convexCount << std::endl;
	return 0;
}
