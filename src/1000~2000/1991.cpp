#include <iostream>
#include <vector>
#include <string>

void Preorder(int node, const std::vector<int>& leftChild, const std::vector<int>& rightChild, std::string& out)
{
	if (node == -1)
	{
		return;
	}

	out.push_back(static_cast<char>('A' + node));
	Preorder(leftChild[node], leftChild, rightChild, out);
	Preorder(rightChild[node], leftChild, rightChild, out);
}

void Inorder(int node, const std::vector<int>& leftChild, const std::vector<int>& rightChild, std::string& out)
{
	if (node == -1)
	{
		return;
	}

	Inorder(leftChild[node], leftChild, rightChild, out);
	out.push_back(static_cast<char>('A' + node));
	Inorder(rightChild[node], leftChild, rightChild, out);
}

void Postorder(int node, const std::vector<int>& leftChild, const std::vector<int>& rightChild, std::string& out)
{
	if (node == -1)
	{
		return;
	}

	Postorder(leftChild[node], leftChild, rightChild, out);
	Postorder(rightChild[node], leftChild, rightChild, out);
	out.push_back(static_cast<char>('A' + node));
}

int main()
{
	int nodeCount;
	std::cin >> nodeCount;

	std::vector<int> leftChild(26, -1);
	std::vector<int> rightChild(26, -1);

	for (int i = 0; i < nodeCount; ++i)
	{
		char parentChar;
		char leftChar;
		char rightChar;
		std::cin >> parentChar >> leftChar >> rightChar;

		int parent = parentChar - 'A';

		if (leftChar == '.')
		{
			leftChild[parent] = -1;
		}
		else
		{
			leftChild[parent] = leftChar - 'A';
		}

		if (rightChar == '.')
		{
			rightChild[parent] = -1;
		}
		else
		{
			rightChild[parent] = rightChar - 'A';
		}
	}

	std::string preorderResult;
	std::string inorderResult;
	std::string postorderResult;

	Preorder(0, leftChild, rightChild, preorderResult);
	Inorder(0, leftChild, rightChild, inorderResult);
	Postorder(0, leftChild, rightChild, postorderResult);

	std::cout << preorderResult << std::endl;
	std::cout << inorderResult << std::endl;
	std::cout << postorderResult << std::endl;

	return 0;
}
