#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int histogram_size;

    while (1) {
        long long* heights;
        int* index_stack;
        int stack_top;
        int i;
        long long max_area;

        scanf("%d", &histogram_size);
        if (histogram_size == 0) {
            break;
        }

        heights = (long long*)malloc((size_t)(histogram_size + 1) * sizeof(long long));
        index_stack = (int*)malloc((size_t)(histogram_size + 1) * sizeof(int));

        for (i = 0; i < histogram_size; ++i) {
            scanf("%lld", &heights[i]);
        }
        heights[histogram_size] = 0;

        stack_top = -1;
        max_area = 0;

        for (i = 0; i <= histogram_size; ++i) {
            while (stack_top >= 0 && heights[index_stack[stack_top]] > heights[i]) {
                int height_index;
                long long height_value;
                long long width;
                long long area;

                height_index = index_stack[stack_top];
                --stack_top;

                height_value = heights[height_index];
                if (stack_top < 0) {
                    width = i;
                } else {
                    width = i - index_stack[stack_top] - 1;
                }
                area = height_value * width;
                if (area > max_area) {
                    max_area = area;
                }
            }
            ++stack_top;
            index_stack[stack_top] = i;
        }

        printf("%lld\n", max_area);

        free(heights);
        free(index_stack);
    }

    return 0;
}
