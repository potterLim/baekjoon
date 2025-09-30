#include <algorithm>
#include <iostream>

int main()
{
	long long x1;
	long long y1;
	long long x2;
	long long y2;
	long long x3;
	long long y3;
	long long x4;
	long long y4;

	std::cin >> x1 >> y1 >> x2 >> y2;
	std::cin >> x3 >> y3 >> x4 >> y4;

	long long ax;
	long long ay;
	long long bx;
	long long by;
	long long cx;
	long long cy;
	long long dx;
	long long dy;

	ax = x2 - x1;
	ay = y2 - y1;
	bx = x3 - x1;
	by = y3 - y1;
	cx = x4 - x1;
	cy = y4 - y1;

	long long cross1;
	long long cross2;
	cross1 = ax * by - ay * bx;
	cross2 = ax * cy - ay * cx;

	long long ex;
	long long ey;
	long long fx;
	long long fy;
	long long gx;
	long long gy;

	ex = x4 - x3;
	ey = y4 - y3;
	fx = x1 - x3;
	fy = y1 - y3;
	gx = x2 - x3;
	gy = y2 - y3;

	long long cross3;
	long long cross4;
	cross3 = ex * fy - ey * fx;
	cross4 = ex * gy - ey * gx;

	bool intersects;
	intersects = false;

	if (cross1 == 0 && cross2 == 0 && cross3 == 0 && cross4 == 0)
	{
		long long minx1;
		long long maxx1;
		long long miny1;
		long long maxy1;
		long long minx2;
		long long maxx2;
		long long miny2;
		long long maxy2;

		if (x1 < x2)
		{
			minx1 = x1;
			maxx1 = x2;
		}
		else
		{
			minx1 = x2;
			maxx1 = x1;
		}
		if (y1 < y2)
		{
			miny1 = y1;
			maxy1 = y2;
		}
		else
		{
			miny1 = y2;
			maxy1 = y1;
		}
		if (x3 < x4)
		{
			minx2 = x3;
			maxx2 = x4;
		}
		else
		{
			minx2 = x4;
			maxx2 = x3;
		}
		if (y3 < y4)
		{
			miny2 = y3;
			maxy2 = y4;
		}
		else
		{
			miny2 = y4;
			maxy2 = y3;
		}

		bool overlapX;
		bool overlapY;
		overlapX = !(maxx1 < minx2 || maxx2 < minx1);
		overlapY = !(maxy1 < miny2 || maxy2 < miny1);

		if (overlapX && overlapY)
		{
			intersects = true;
		}
	}
	else
	{
		if (cross1 == 0)
		{
			long long minx;
			long long maxx;
			long long miny;
			long long maxy;

			if (x1 < x2)
			{
				minx = x1;
				maxx = x2;
			}
			else
			{
				minx = x2;
				maxx = x1;
			}
			if (y1 < y2)
			{
				miny = y1;
				maxy = y2;
			}
			else
			{
				miny = y2;
				maxy = y1;
			}

			if (x3 >= minx && x3 <= maxx && y3 >= miny && y3 <= maxy)
			{
				intersects = true;
			}
		}
		if (!intersects && cross2 == 0)
		{
			long long minx;
			long long maxx;
			long long miny;
			long long maxy;

			if (x1 < x2)
			{
				minx = x1;
				maxx = x2;
			}
			else
			{
				minx = x2;
				maxx = x1;
			}
			if (y1 < y2)
			{
				miny = y1;
				maxy = y2;
			}
			else
			{
				miny = y2;
				maxy = y1;
			}

			if (x4 >= minx && x4 <= maxx && y4 >= miny && y4 <= maxy)
			{
				intersects = true;
			}
		}
		if (!intersects && cross3 == 0)
		{
			long long minx;
			long long maxx;
			long long miny;
			long long maxy;

			if (x3 < x4)
			{
				minx = x3;
				maxx = x4;
			}
			else
			{
				minx = x4;
				maxx = x3;
			}
			if (y3 < y4)
			{
				miny = y3;
				maxy = y4;
			}
			else
			{
				miny = y4;
				maxy = y3;
			}

			if (x1 >= minx && x1 <= maxx && y1 >= miny && y1 <= maxy)
			{
				intersects = true;
			}
		}
		if (!intersects && cross4 == 0)
		{
			long long minx;
			long long maxx;
			long long miny;
			long long maxy;

			if (x3 < x4)
			{
				minx = x3;
				maxx = x4;
			}
			else
			{
				minx = x4;
				maxx = x3;
			}
			if (y3 < y4)
			{
				miny = y3;
				maxy = y4;
			}
			else
			{
				miny = y4;
				maxy = y3;
			}

			if (x2 >= minx && x2 <= maxx && y2 >= miny && y2 <= maxy)
			{
				intersects = true;
			}
		}
		if (!intersects)
		{
			bool opposite1;
			bool opposite2;

			opposite1 = (cross1 > 0 && cross2 < 0) || (cross1 < 0 && cross2 > 0);
			opposite2 = (cross3 > 0 && cross4 < 0) || (cross3 < 0 && cross4 > 0);

			if (opposite1 && opposite2)
			{
				intersects = true;
			}
		}
	}

	if (intersects)
	{
		std::cout << 1 << std::endl;
	}
	else
	{
		std::cout << 0 << std::endl;
	}

	return 0;
}