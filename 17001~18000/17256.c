#include <stdio.h>

int main () 
{
    int ax;
    int ay;
    int az;
    int cx;
    int cy;
    int cz;

    scanf("%d %d %d", &ax, &ay, &az);
    scanf("%d %d %d", &cx, &cy, &cz);
    printf("%d %d %d", cx-az, cy/ay, cz-ax);

    return 0;
}