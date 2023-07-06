#include <stdio.h>

int main(void)
{
	int N;
	int M;
    int i;
    int j;
	int count = 0;
	int a[15000] = {0, };
	scanf("%d %d", &N, &M);

	for (i = 0; i < N; i++) {
		scanf("%d", &a[i]);
	}

	for (i = 0; i < N; i++) {
		for (j = i + 1; j < N; j++) {
			if (a[i] + a[j] == M) {
				count++;
			}
		}
	}

	printf("%d\n", count);
	return 0;
}
