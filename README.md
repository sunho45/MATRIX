# 연속행렬 곱셈 알고리즘

## 연속행렬 곱셈이란?
연속행렬이 주어졌을 때, 행렬의 곱셈 중 가장 최소의 연산을 수행하는 방법을 찾는 알고리즘이다. 이 알고리즘은 어떤 순서로 행렬을 곱할 지 결정하는 알고리즘이다.


만약 A,B,C,D,E 행렬이 있다면 다음과 같은 형태로 구성될 수 있다.

```
(AB)(CD)E=(ABC)(DE)=....
```

행렬을 어떤 순서로 곱하든 결과는 같다. 결과는 같더라도 연산과정을 최소한으로 줄이는 방식을 찾아야 된다.


만약 A가 10X25, B가 25X5, C가 5X30이면 

연산횟수는 
``` 
(AB)C=(10X25X5)+(10X5X30)=2750
A(BC)=(25X5X30)+(25X30X10)=11250
```
(AB)C 방식이 연산횟수가 2750으로 최소이므로 선택


## 연속행렬곱셈 알고리즘 in C

전체코드

```
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
        scanf("%d",  &matrix[i][1]);
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
                dp[a][b] = 999999999;//엄청 큰수
                for (k = a; k < b; k++) {
                    dp[a][b] = min(dp[a][b], dp[a][k] + dp[k + 1][b] + (matrix[a][0] * matrix[k][1] * matrix[b][1]));//DP[i][j] = i번째 행렬부터 j번째행렬까지 최소 연산 g횟수
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



```


## 작동방식

ex)


dp|1|2|3|4|
---|---|---|---|---|
1|0||||
2||0|||
3|||0||
4||||0|








## 맡은일과 느낀점
