#include <stdio.h>
 
int main() {
    int arr[15][15] = {0,}; 
    int check;
    int h;
    int w;
    int i;
    int j;

    for (i=0; i < 15; ++i){
        arr[0][i] = i;
    }
  
    for (i=1; i < 15; ++i) {
        for (j=1; j < 15; ++j) {
            arr[i][j] = arr[i-1][j] + arr[i][j-1];
        } 
    }

    scanf("%d", &check); 
  
    for (i=0; i < check; ++i) {
        scanf("%d %d", &h, &w);  
        printf("%d\n", arr[h][w]);  
    }
	return 0; 
}