#include <iostream>
#include <vector>

void BuildPostorder(const std::vector<int>& preorder, int& indexRef, long long lowerBound, long long upperBound, std::vector<int>& postorder)
{
	if (indexRef >= static_cast<int>(preorder.size()))
	{
		return;
	}

	int value = preorder[indexRef];

	if (!(lowerBound < value && value < upperBound))
	{
		return;
	}

	indexRef += 1;

	BuildPostorder(preorder, indexRef, lowerBound, value, postorder);
	BuildPostorder(preorder, indexRef, value, upperBound, postorder);

	postorder.push_back(value);
}

int main()
{
	std::vector<int> preorder;
	int key;
	while (std::cin >> key)
	{
		preorder.push_back(key);
	}

	std::vector<int> postorder;
	int startIndex = 0;
	long long NEG_INF = -1e18;
	long long POS_INF = 1e18;

	BuildPostorder(preorder, startIndex, NEG_INF, POS_INF, postorder);

	for (int i = 0; i < static_cast<int>(postorder.size()); ++i)
	{
		std::cout << postorder[i] << std::endl;
	}

	return 0;
}
