package dynamic;

import java.util.Scanner;

public class matrixchain {
    private static final int INF = Integer.MAX_VALUE / 2;

    private static int[][] MATRIX() { // 입력받을 행렬의 배열 생성

        return new int[3][501];
    }

    private static int[][] Dynamic_Programming_place() {

        return new int[501][501];//동적알고리즘 배열
    }


    private static void make_MATRIX(Scanner scanner, int[][] matrix, int num) { // 배열 입력

        for (int i = 1; i <= num; i++) {
            System.out.println(i + "번째 행,열 입력");
            matrix[0][i] = scanner.nextInt();
            matrix[1][i] = scanner.nextInt();
        }
    }

    private static int getNum(Scanner scanner) {
        System.out.println("행렬개수 입력: ");
        return scanner.nextInt();
    }

    private static void MATRIXCHAIN(int[][] matrix, int num, int[][] DP) {
        for(int i = 1; i <= num; i++)
            DP[i][i] = 0; //

        for(int l = 1; l <= num -1; l++) //l:부분문제 크기조절 인덱스 ex) num이 4면, l은 3->2->1까지
        {
            for(int i = 1; i <= num - l; i++)
            {
                int j = i + l;
                DP[i][j] = INF;
                for(int k = i; k <= (j-1); k++)
                {
                    int temp = DP[i][k] + DP[k+1][j] + (matrix[0][i] * matrix[1][k] * matrix[1][j]);

                    if(temp < DP[i][j]) {
                        DP[i][j] = temp;
                        System.out.println(i + "행" + j + "열: " + DP[i][j]);
                    }
                    else System.out.println("*"+i + "행" + j + "열: " + temp + "은 탈락*");
                }
            }

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = MATRIX(); // 입력할 행렬 선언

        int num = getNum(scanner); //행렬 개수 입력

        make_MATRIX(scanner, matrix, num);//행렬 입력

        int[][] DP = Dynamic_Programming_place(); // 동적알고리즘이 돌아갈 공간의 행렬 선언

        MATRIXCHAIN(matrix, num, DP); // 동적알고리즘 실행

        System.out.println("행렬의 최소 곱셈 횟수 : " + DP[1][num]);
    }

}