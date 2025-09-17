#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <stdint.h>

const int MOD1 = 1000000007;
const int MOD2 = 1000000009;
const int BASE = 1000003;

struct DoubleHash
{
	int h1;
	int h2;
};

uint64_t PackHash(int h1, int h2)
{
	return (static_cast<uint64_t>(static_cast<uint32_t>(h1)) << 32) ^ static_cast<uint32_t>(h2);
}

void BuildPowers(int maxLen, std::vector<int>& pow1, std::vector<int>& pow2)
{
	pow1.resize(maxLen + 1);
	pow2.resize(maxLen + 1);
	pow1[0] = 1;
	pow2[0] = 1;
	for (int i = 1; i <= maxLen; ++i)
	{
		long long v1 = static_cast<long long>(pow1[i - 1]) * BASE % MOD1;
		long long v2 = static_cast<long long>(pow2[i - 1]) * BASE % MOD2;
		pow1[i] = static_cast<int>(v1);
		pow2[i] = static_cast<int>(v2);
	}
}

void BuildPrefixHash(const std::vector<int>& a, std::vector<int>& pref1, std::vector<int>& pref2)
{
	int n = static_cast<int>(a.size());
	pref1.resize(n + 1);
	pref2.resize(n + 1);
	pref1[0] = 0;
	pref2[0] = 0;
	for (int i = 0; i < n; ++i)
	{
		long long v1 = (static_cast<long long>(pref1[i]) * BASE + a[i]) % MOD1;
		long long v2 = (static_cast<long long>(pref2[i]) * BASE + a[i]) % MOD2;
		pref1[i + 1] = static_cast<int>(v1);
		pref2[i + 1] = static_cast<int>(v2);
	}
}

DoubleHash GetRangeHash(int l, int r, const std::vector<int>& pref1, const std::vector<int>& pref2, const std::vector<int>& pow1, const std::vector<int>& pow2)
{
	int len = r - l + 1;

	long long x1 = (pref1[r + 1] - static_cast<long long>(pref1[l]) * pow1[len]) % MOD1;
	if (x1 < 0)
		x1 += MOD1;

	long long x2 = (pref2[r + 1] - static_cast<long long>(pref2[l]) * pow2[len]) % MOD2;
	if (x2 < 0)
		x2 += MOD2;

	DoubleHash dh;
	dh.h1 = static_cast<int>(x1);
	dh.h2 = static_cast<int>(x2);
	return dh;
}

void CanonicalWindowHashesOfLengthK(const std::vector<int>& a, int k, const std::vector<int>& pow1, const std::vector<int>& pow2, std::set<uint64_t>& outSet)
{
	int n = static_cast<int>(a.size());
	std::vector<int> pref1, pref2;
	BuildPrefixHash(a, pref1, pref2);

	std::vector<int> b = a;
	std::reverse(b.begin(), b.end());
	std::vector<int> prefR1, prefR2;
	BuildPrefixHash(b, prefR1, prefR2);

	outSet.clear();
	for (int t = 0; t + k <= n; ++t)
	{
		DoubleHash hf = GetRangeHash(t, t + k - 1, pref1, pref2, pow1, pow2);

		int rL = (n - k) - t;
		int rR = (n - 1) - t;
		DoubleHash hr = GetRangeHash(rL, rR, prefR1, prefR2, pow1, pow2);

		uint64_t packedF = PackHash(hf.h1, hf.h2);
		uint64_t packedR = PackHash(hr.h1, hr.h2);
		uint64_t canonical = (packedF < packedR) ? packedF : packedR;

		outSet.insert(canonical);
	}
}

int main()
{
	int programCount;
	int minLength;
	std::cin >> programCount >> minLength;

	std::vector<std::vector<int>> programs(programCount);
	int maxLen = minLength;

	for (int i = 0; i < programCount; ++i)
	{
		int m;
		std::cin >> m;
		programs[i].resize(m);
		if (m > maxLen)
			maxLen = m;
		for (int j = 0; j < m; ++j)
		{
			std::cin >> programs[i][j];
		}
	}

	std::vector<int> pow1;
	std::vector<int> pow2;
	BuildPowers(maxLen, pow1, pow2);

	std::set<uint64_t> intersectionSet;
	std::set<uint64_t> currentSet;

	CanonicalWindowHashesOfLengthK(programs[0], minLength, pow1, pow2, intersectionSet);

	for (int i = 1; i < programCount; ++i)
	{
		CanonicalWindowHashesOfLengthK(programs[i], minLength, pow1, pow2, currentSet);

		std::set<uint64_t> nextSet;
		for (std::set<uint64_t>::const_iterator it = intersectionSet.begin(); it != intersectionSet.end(); ++it)
		{
			if (currentSet.find(*it) != currentSet.end())
			{
				nextSet.insert(*it);
			}
		}

		intersectionSet.swap(nextSet);
		if (intersectionSet.empty())
		{
			break;
		}
	}

	if (!intersectionSet.empty())
	{
		std::cout << "YES" << std::endl;
	}
	else
	{
		std::cout << "NO" << std::endl;
	}

	return 0;
}
