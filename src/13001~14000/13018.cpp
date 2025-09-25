#include <iostream>
#include <vector>

int main()
{
	int n;
	int k;
	std::cin >> n >> k;

	if (k > n - 1)
	{
		std::cout << "Impossible" << std::endl;
		return 0;
	}

	if (n == 1)
	{
		if (k == 0)
		{
			std::cout << 1 << std::endl;
		}
		else
		{
			std::cout << "Impossible" << std::endl;
		}
		return 0;
	}

	std::vector<int> permutation(n + 1);
	for (int i = 1; i <= n; ++i)
	{
		permutation[i] = i;
	}

	int toReduce = (n - 1) - k;

	if (toReduce > 0 && (toReduce % 2 == 1))
	{
		int i = 2;
		int temp = permutation[1];
		permutation[1] = permutation[i];
		permutation[i] = temp;
		--toReduce;
	}

	int evenIndex = 2;
	int oddIndex = 3;

	while (toReduce > 0)
	{
		while (evenIndex <= n && permutation[evenIndex] != evenIndex)
		{
			evenIndex += 2;
		}
		while (oddIndex <= n && permutation[oddIndex] != oddIndex)
		{
			oddIndex += 2;
		}

		if (evenIndex > n || oddIndex > n)
		{
			std::cout << "Impossible" << std::endl;
			return 0;
		}

		int temp = permutation[evenIndex];
		permutation[evenIndex] = permutation[oddIndex];
		permutation[oddIndex] = temp;

		toReduce -= 2;

		evenIndex += 2;
		oddIndex += 2;
	}

	for (int i = 1; i <= n; ++i)
	{
		if (i > 1)
		{
			std::cout << ' ';
		}
		std::cout << permutation[i];
	}
	std::cout << std::endl;

	return 0;
}
