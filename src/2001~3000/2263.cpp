#include <iostream>
#include <vector>

std::vector<int> inorder;
std::vector<int> postorder;
std::vector<int> inorderIndex;

void BuildPreorder(int inorderStart, int inorderEnd, int postorderStart, int postorderEnd)
{
	if (inorderStart > inorderEnd || postorderStart > postorderEnd)
	{
		return;
	}

	int root = postorder[postorderEnd];
	std::cout << root << " ";

	int rootIndex = inorderIndex[root];
	int leftSize = rootIndex - inorderStart;
	int rightSize = inorderEnd - rootIndex;

	BuildPreorder(inorderStart, rootIndex - 1, postorderStart, postorderStart + leftSize - 1);

	BuildPreorder(rootIndex + 1, inorderEnd, postorderEnd - rightSize, postorderEnd - 1);
}

int main()
{
	int nodeCount;
	std::cin >> nodeCount;

	inorder.resize(nodeCount);
	postorder.resize(nodeCount);
	inorderIndex.resize(nodeCount + 1);

	for (int i = 0; i < nodeCount; ++i)
	{
		std::cin >> inorder[i];
		inorderIndex[inorder[i]] = i;
	}

	for (int i = 0; i < nodeCount; ++i)
	{
		std::cin >> postorder[i];
	}

	BuildPreorder(0, nodeCount - 1, 0, nodeCount - 1);

	return 0;
}
