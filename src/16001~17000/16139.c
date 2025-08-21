#include <stdio.h>
#include <stdlib.h>

#define ALPHABET_COUNT 26

int main(void)
{
    char inputString[200001];
    int queryCount;
    int stringLength;
    int** prefixSum;
    int i;

    scanf("%s", inputString);

    for (stringLength = 0; inputString[stringLength] != '\0'; ++stringLength) {
    }

    prefixSum = (int**)malloc(sizeof(int*) * ALPHABET_COUNT);
    for (i = 0; i < ALPHABET_COUNT; ++i) {
        prefixSum[i] = (int*)calloc((size_t)(stringLength + 1), sizeof(int));
    }

    for (i = 0; i < stringLength; ++i) {
        int charIndex = inputString[i] - 'a';
        int j;

        for (j = 0; j < ALPHABET_COUNT; ++j) {
            prefixSum[j][i + 1] = prefixSum[j][i];
        }

        prefixSum[charIndex][i + 1]++;
    }

    scanf("%d", &queryCount);

    for (i = 0; i < queryCount; ++i) {
        char targetChar;
        int left;
        int right;
        int targetIndex;

        scanf(" %c %d %d", &targetChar, &left, &right);

        targetIndex = targetChar - 'a';
        printf("%d\n", prefixSum[targetIndex][right + 1] - prefixSum[targetIndex][left]);
    }

    for (i = 0; i < ALPHABET_COUNT; ++i) {
        free(prefixSum[i]);
    }
    free(prefixSum);

    return 0;
}
