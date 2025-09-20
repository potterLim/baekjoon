#include <iostream>
#include <vector>
#include <string>
#include <array>

int main()
{
	int n;
	if (!(std::cin >> n))
	{
		return 0;
	}

	std::vector<std::string> tokens(n + 1);
	int i = 1;
	while (i <= n)
	{
		std::cin >> tokens[i];
		++i;
	}

	char targetChar;
	std::cin >> targetChar;

	const int INF = 1000000000;
	std::vector<std::array<int, 2>> dp(n + 1);

	int i0 = 0;
	while (i0 <= n)
	{
		dp[i0][0] = INF;
		dp[i0][1] = INF;
		++i0;
	}

	int initT;
	if (tokens[1] == "T")
	{
		initT = 0;
	}
	else
	{
		initT = 1;
	}

	int initF;
	if (tokens[1] == "F")
	{
		initF = 0;
	}
	else
	{
		initF = 1;
	}

	dp[1][1] = initT;
	dp[1][0] = initF;

	int pos = 3;
	while (pos <= n)
	{
		char op = tokens[pos - 1][0];
		bool val;
		if (tokens[pos] == "T")
		{
			val = true;
		}
		else
		{
			val = false;
		}

		int r = 0;
		while (r <= 1)
		{
			int best = INF;

			int p = 0;
			while (p <= 1)
			{
				char op2And = '&';
				int opCostAnd;
				if (op2And == op)
				{
					opCostAnd = 0;
				}
				else
				{
					opCostAnd = 1;
				}

				int v2 = 0;
				while (v2 <= 1)
				{
					int valCost;
					if (v2 == static_cast<int>(val))
					{
						valCost = 0;
					}
					else
					{
						valCost = 1;
					}

					bool outAnd;
					if (p != 0 && v2 != 0)
					{
						outAnd = true;
					}
					else
					{
						outAnd = false;
					}

					if (static_cast<int>(outAnd) == r)
					{
						int baseCost = dp[pos - 2][p] + opCostAnd + valCost;
						if (baseCost < best)
						{
							best = baseCost;
						}
					}

					++v2;
				}

				char op2Or = '|';
				int opCostOr;
				if (op2Or == op)
				{
					opCostOr = 0;
				}
				else
				{
					opCostOr = 1;
				}

				int v3 = 0;
				while (v3 <= 1)
				{
					int valCost2;
					if (v3 == static_cast<int>(val))
					{
						valCost2 = 0;
					}
					else
					{
						valCost2 = 1;
					}

					bool outOr;
					if (p != 0 || v3 != 0)
					{
						outOr = true;
					}
					else
					{
						outOr = false;
					}

					if (static_cast<int>(outOr) == r)
					{
						int baseCost2 = dp[pos - 2][p] + opCostOr + valCost2;
						if (baseCost2 < best)
						{
							best = baseCost2;
						}
					}

					++v3;
				}

				++p;
			}

			dp[pos][r] = best;
			++r;
		}

		pos += 2;
	}

	int want;
	if (targetChar == 'T')
	{
		want = 1;
	}
	else
	{
		want = 0;
	}

	int answer;
	if (n == 1)
	{
		answer = dp[1][want];
	}
	else
	{
		answer = dp[n][want];
	}

	std::cout << answer << std::endl;
	return 0;
}
