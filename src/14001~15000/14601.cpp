#include <iostream>
#include <vector>

namespace
{
	std::vector<std::vector<int>> board;
	int nextTileId = 1;

	void TileRecursively(int originX, int originY, int size, int holeX, int holeY)
	{
		if (size == 1)
		{
			return;
		}

		int half = size / 2;

		int midLeftX = originX + half - 1;
		int midTopY = originY + half - 1;
		int midRightX = originX + half;
		int midBottomY = originY + half;

		bool holeInTL = (holeX < originX + half) && (holeY < originY + half);
		bool holeInTR = (holeX >= originX + half) && (holeY < originY + half);
		bool holeInBL = (holeX < originX + half) && (holeY >= originY + half);
		bool holeInBR = (holeX >= originX + half) && (holeY >= originY + half);

		int tileId = nextTileId++;

		if (!holeInTL)
			board[midTopY][midLeftX] = tileId;
		if (!holeInTR)
			board[midTopY][midRightX] = tileId;
		if (!holeInBL)
			board[midBottomY][midLeftX] = tileId;
		if (!holeInBR)
			board[midBottomY][midRightX] = tileId;

		int tlHoleX = holeX;
		int tlHoleY = holeY;
		if (!holeInTL)
		{
			tlHoleX = midLeftX;
			tlHoleY = midTopY;
		}
		TileRecursively(originX, originY, half, tlHoleX, tlHoleY);

		int trHoleX = holeX;
		int trHoleY = holeY;
		if (!holeInTR)
		{
			trHoleX = midRightX;
			trHoleY = midTopY;
		}
		TileRecursively(originX + half, originY, half, trHoleX, trHoleY);

		int blHoleX = holeX;
		int blHoleY = holeY;
		if (!holeInBL)
		{
			blHoleX = midLeftX;
			blHoleY = midBottomY;
		}
		TileRecursively(originX, originY + half, half, blHoleX, blHoleY);

		int brHoleX = holeX;
		int brHoleY = holeY;
		if (!holeInBR)
		{
			brHoleX = midRightX;
			brHoleY = midBottomY;
		}
		TileRecursively(originX + half, originY + half, half, brHoleX, brHoleY);
	}
}

int main()
{
	int k;
	std::cin >> k;

	int boardSize = 1 << k;

	int holeXInput;
	int holeYInput;
	std::cin >> holeXInput >> holeYInput;

	board.assign(boardSize, std::vector<int>(boardSize, 0));

	int holeCol = holeXInput - 1;
	int holeRow = boardSize - holeYInput;

	board[holeRow][holeCol] = -1;

	TileRecursively(0, 0, boardSize, holeCol, holeRow);

	for (int i = 0; i < boardSize; ++i)
	{
		for (int j = 0; j < boardSize; ++j)
		{
			if (j > 0)
			{
				std::cout << ' ';
			}
			std::cout << board[i][j];
		}
		std::cout << std::endl;
	}

	return 0;
}
