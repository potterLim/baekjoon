#include <stdio.h>
#include <stddef.h>
#include <string.h>

int main(void)
{
    char str[101];
    int count;
    int index_first;
    int index_second;
    char tmp;
    size_t i;

    scanf("%s", str);
    scanf("%d", &count);

    for (i = 0; i < count; ++i) {
        scanf("%d %d", &index_first, &index_second);
    }

    tmp = str[index_first];
    str[index_first] = str[index_second];
    str[index_second] = tmp;

    printf("%s\n", str);
    return 0;
}
