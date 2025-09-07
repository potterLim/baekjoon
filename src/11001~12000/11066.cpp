#include <iostream>
#include <vector>
#include <climits>

int main()
{
	int test_case_count;
	std::cin >> test_case_count;

	for (int i = 0; i < test_case_count; ++i)
	{
		int chapter_count;
		std::cin >> chapter_count;

		std::vector<long long> file_sizes(chapter_count + 1);
		for (int j = 1; j <= chapter_count; ++j)
		{
			std::cin >> file_sizes[j];
		}

		std::vector<long long> prefix_sum(chapter_count + 1, 0);
		for (int j = 1; j <= chapter_count; ++j)
		{
			prefix_sum[j] = prefix_sum[j - 1] + file_sizes[j];
		}

		std::vector<std::vector<long long>> min_cost(chapter_count + 2, std::vector<long long>(chapter_count + 2, 0));
		std::vector<std::vector<int>> optimal_split(chapter_count + 2, std::vector<int>(chapter_count + 2, 0));

		for (int j = 1; j <= chapter_count; ++j)
		{
			optimal_split[j][j] = j;
		}

		for (int length = 2; length <= chapter_count; ++length)
		{
			for (int j = 1; j + length - 1 <= chapter_count; ++j)
			{
				int k = j + length - 1;

				int start_split = optimal_split[j][k - 1];
				int end_split = optimal_split[j + 1][k];
				if (start_split == 0)
				{
					start_split = j;
				}
				if (end_split == 0)
				{
					end_split = k - 1;
				}
				if (start_split > end_split)
				{
					std::swap(start_split, end_split);
				}

				long long best_cost = LLONG_MAX;
				int best_split = start_split;

				for (int m = start_split; m <= end_split; ++m)
				{
					long long merge_cost = min_cost[j][m] + min_cost[m + 1][k] + (prefix_sum[k] - prefix_sum[j - 1]);
					if (merge_cost < best_cost)
					{
						best_cost = merge_cost;
						best_split = m;
					}
				}

				min_cost[j][k] = best_cost;
				optimal_split[j][k] = best_split;
			}
		}

		std::cout << min_cost[1][chapter_count] << std::endl;
	}

	return 0;
}
