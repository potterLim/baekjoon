#include <array>
#include <cstdint>
#include <iostream>
#include <string>
#include <vector>

int main()
{
	int n = 0;
	int k = 0;
	if (!(std::cin >> n >> k))
	{
		return 0;
	}

	std::vector<uint32_t> forbiddenMasks(n, 0u);
	for (int i = 0; i < k; ++i)
	{
		std::string v;
		if (!(std::cin >> v))
		{
			return 0;
		}
		for (int j = 0; j < n; ++j)
		{
			int bit = static_cast<int>(v[j] - 'a');
			forbiddenMasks[j] |= (1u << bit);
		}
	}

	std::string a;
	std::string b;
	if (!(std::cin >> a))
	{
		return 0;
	}
	if (!(std::cin >> b))
	{
		return 0;
	}

	std::vector<std::string> allowedLetters(n);
	std::vector<std::array<int, 26>> rankMap(n);
	for (int i = 0; i < n; ++i)
	{
		for (int c = 0; c < 26; ++c)
		{
			rankMap[i][c] = -1;
		}
		for (int c = 0; c < 26; ++c)
		{
			bool isForbidden = (forbiddenMasks[i] >> c) & 1u;
			if (!isForbidden)
			{
				allowedLetters[i].push_back(static_cast<char>('a' + c));
				rankMap[i][c] = static_cast<int>(allowedLetters[i].size()) - 1;
			}
		}
	}

	std::vector<int> digitsA(n);
	std::vector<int> digitsB(n);
	std::vector<int> radices(n);
	for (int i = 0; i < n; ++i)
	{
		int idxA = rankMap[i][static_cast<int>(a[i] - 'a')];
		int idxB = rankMap[i][static_cast<int>(b[i] - 'a')];
		digitsA[i] = idxA;
		digitsB[i] = idxB;
		radices[i] = static_cast<int>(allowedLetters[i].size());
	}

	std::vector<int> digitsC(n, 0);
	int carry = 0;
	for (int i = n - 1; i >= 0; --i)
	{
		int sum = digitsA[i] + digitsB[i] + carry;
		int base = radices[i];
		if (base == 0)
		{
			return 0;
		}
		digitsC[i] = sum % base;
		carry = sum / base;
	}

	std::string result;
	result.resize(n);
	for (int i = 0; i < n; ++i)
	{
		result[i] = allowedLetters[i][digitsC[i]];
	}

	std::cout << result << std::endl;
	return 0;
}
