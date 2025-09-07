#include <iostream>
#include <vector>
#include <climits>

int main()
{
	int matrix_count;
	std::cin >> matrix_count;

	std::vector<int> dimension(matrix_count + 1);
	std::vector<std::pair<int, int>> shape(matrix_count + 1);
	for (int i = 1; i <= matrix_count; ++i)
	{
		int r, c;
		std::cin >> r >> c;
		shape[i] = {r, c};
	}

	dimension[0] = shape[1].first;
	for (int i = 1; i <= matrix_count; ++i)
	{
		dimension[i] = shape[i].second;
	}

	std::vector<std::vector<long long>> min_multiply_cost(matrix_count + 2, std::vector<long long>(matrix_count + 2, 0));

	for (int len = 2; len <= matrix_count; ++len)
	{
		for (int i = 1; i + len - 1 <= matrix_count; ++i)
		{
			int j = i + len - 1;
			long long best = LLONG_MAX;
			for (int k = i; k < j; ++k)
			{
				long long cost_left = min_multiply_cost[i][k];
				long long cost_right = min_multiply_cost[k + 1][j];
				long long cost_merge = 1LL * dimension[i - 1] * dimension[k] * dimension[j];
				long long total = cost_left + cost_right + cost_merge;
				if (total < best)
				{
					best = total;
				}
			}
			min_multiply_cost[i][j] = best;
		}
	}

	std::cout << min_multiply_cost[1][matrix_count] << std::endl;
	return 0;
}
