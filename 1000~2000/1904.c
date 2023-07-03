#include <stdio.h>

int tiles[1000000];

int main(void)
{
    int N;
    int i;
	scanf("%d", &N);

	tiles[0] = 1;
	tiles[1] = 2;

	for (i = 2; i < N; i++) {
		tiles[i] = (tiles[i - 2] + tiles[i - 1]) % 15746;
	}

	printf("%d\n", tiles[N - 1]);
	return 0;
}
