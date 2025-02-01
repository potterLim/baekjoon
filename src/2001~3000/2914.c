#include <stdio.h>

int main(void)
{
    int album_count;
    int avg_melody;
    int min_copyrighted_melody;

    scanf("%d %d", &album_count, &avg_melody);

    min_copyrighted_melody = (album_count * (avg_melody - 1)) + 1;
    printf("%d\n", min_copyrighted_melody);
    
    return 0;
}
