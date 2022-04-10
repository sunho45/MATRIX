#include <stdio.h>
#pragma warning(disable:4996)
int matrix[501][2];// 행렬 
int dp[501][501]; //다이나믹 알고리즘 값 
void mat(int n);// 연산함수
int min(int x, int y) {
    return x < y ? x : y;
}
//두값중에 최솟값을 나타내는 함수

int main() {
    int n, i, j;
    printf("행렬의 개수 입력:");
    scanf(" %d", &n);// 행렬의 개수를 입력
    printf("행렬의 행과 열 입력\n");
    for (i = 0; i < n; i++) {
        printf("행 입력:");
        scanf("%d", &matrix[i][0]);
        printf("열 입력:");
        scanf("%d", &matrix[i][1]);
    }
    mat(n);
}

void mat(int n) {
    int a, b;
    int i, j, k;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n - i; j++) {
            a = j;
            b = j + i;
            if (a == b) {
                dp[a][b] = 0;
            }
            else {
                dp[a][b] = 987654321;
                for (k = a; k < b; k++) {
                    dp[a][b] = min(dp[a][b], dp[a][k] + dp[k + 1][b] + (matrix[a][0] * matrix[k][1] * matrix[b][1]));
                }
            }
        }
    }
    for (int i = 0; i < n; i++) {

        for (int j = 0; j < n; j++) {
            printf("%d ", dp[i][j]);



        }
        printf("\n");


    }

    printf("최솟연산횟수는 :%d", dp[0][n - 1]);

}