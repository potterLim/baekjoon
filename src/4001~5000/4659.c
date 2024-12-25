#include <stdio.h>
#include <string.h>

#define TRUE (1)
#define FALSE (0)

int has_vowel(const char* password)
{
    while (*password != '\0') {
        if (*password == 'a' || 
            *password == 'e' || 
            *password == 'i' || 
            *password == 'o' || 
            *password == 'u') {
            return TRUE;
        }
        password++;
    }

    return FALSE;
}

int is_not_three_consecutive_vowels_or_consonants(const char* password)
{
    int count_vowel = 0;
    int count_consonant = 0;

    while (*password != '\0') {
        if (*password == 'a' || 
            *password == 'e' || 
            *password == 'i' || 
            *password == 'o' || 
            *password == 'u') {
            count_vowel++;
            count_consonant = 0;
        } else {
            count_consonant++;
            count_vowel = 0;
        }

        if (count_vowel == 3 || count_consonant == 3) {
            return FALSE;
        }

        password++;
    }

    return TRUE;
}

int is_not_repeated_except_ee_oo(const char* password)
{
    while (*password != '\0' && *(password + 1) != '\0') {
        if (*password == *(password + 1)) {
            if (!(*password == 'e' && *(password + 1) == 'e') && 
                !(*password == 'o' && *(password + 1) == 'o')) {
                return FALSE;
            }
        }
        password++;
    }

    return TRUE;
}

int main(void)
{
    char password[21];

    while (1) {
        scanf("%s", password);

        if (strcmp(password, "end") == 0) {
            break;
        }

        if (has_vowel(password) != TRUE || 
            is_not_three_consecutive_vowels_or_consonants(password) != TRUE || 
            is_not_repeated_except_ee_oo(password) != TRUE) {
            printf("<%s> is not acceptable.\n", password);
        } else {
            printf("<%s> is acceptable.\n", password);
        }
    }
    
    return 0;
}

