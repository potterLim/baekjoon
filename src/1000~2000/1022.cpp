#include <iostream>
#include <vector>
#include <iomanip>
#include <cmath>

int ComputeSpiralValue(int row, int col)
{
	int ring = std::max(std::abs(row), std::abs(col));
	int squareMax = (2 * ring + 1) * (2 * ring + 1);

	if (row == ring && col != -ring)
	{
		return squareMax - (ring - col);
	}
	if (col == -ring)
	{
		return squareMax - 2 * ring - (ring - row);
	}
	if (row == -ring)
	{
		return squareMax - 4 * ring - (ring + col);
	}
	return squareMax - 6 * ring - (ring + row);
}

int DigitLength(int value)
{
	int length = 0;
	int temp = value;
	while (temp > 0)
	{
		++length;
		temp /= 10;
	}
	if (length == 0)
	{
		length = 1;
	}
	return length;
}

int main()
{
	int r1;
	int c1;
	int r2;
	int c2;
	std::cin >> r1 >> c1 >> r2 >> c2;

	int rows = r2 - r1 + 1;
	int cols = c2 - c1 + 1;

	std::vector<std::vector<int>> values(rows, std::vector<int>(cols, 0));
	int maxValue = 0;

	for (int i = 0; i < rows; ++i)
	{
		for (int j = 0; j < cols; ++j)
		{
			int r = r1 + i;
			int c = c1 + j;
			int v = ComputeSpiralValue(r, c);
			values[i][j] = v;
			if (v > maxValue)
			{
				maxValue = v;
			}
		}
	}

	int width = DigitLength(maxValue);

	for (int i = 0; i < rows; ++i)
	{
		for (int j = 0; j < cols; ++j)
		{
			std::cout << std::setw(width) << values[i][j];
			if (j + 1 < cols)
			{
				std::cout << ' ';
			}
		}
		std::cout << std::endl;
	}

	return 0;
}
