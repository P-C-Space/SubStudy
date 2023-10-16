package com.nhnacademy;

public class FixPoint {
    public static void main(String[] args) {
        double x = 1.0; // 초기 추정값
        double epsilon = 1e-6; // 수렴 조건 설정
        int maxIterations = 10000; // 최대 반복 횟수

        for (int i = 0; i < maxIterations; i++) {
            double nextX = x - (x * x - 2) / (2 * x); // 고정점 반복법 공식
            if (Math.abs(nextX - x) < epsilon) {
                System.out.println("수렴 결과: " + nextX);
                break;
            }
            x = nextX;
        }
    }
}
