#include <stdio.h>

int main(void)
{
	int num;
	int i;
	long long result = 0;
	scanf("%d", &num);

	for (i = 1; i <= num; i *= 10) {
		result += num - i + 1;
	}

	printf("%lld\n", result);
	return 0;
}
