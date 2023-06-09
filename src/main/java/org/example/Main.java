package org.example;

public class Main {
    public static class KnightTour {
        private int[][] board;
        private int n;
        private int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
        private int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};

        public KnightTour(int n) {
            board = new int[n][n];
            this.n = n;
        }

        public void solve() {
            // начинаем обход с клетки (0, 0)
            board[0][0] = 1;
            if (solveRecursive(2, 0, 0)) {
                printSolution();
            } else {
                System.out.println("Решение не найдено");
            }
        }

        private boolean solveRecursive(int moveCount, int x, int y) {
            if (moveCount > n * n) {
                return true;
            }

            // находим следующий возможный ход с наименьшим числом доступных ходов
            int minMoves = Integer.MAX_VALUE;
            int nextX = -1;
            int nextY = -1;
            for (int i = 0; i < 8; i++) {
                int newX = x + xMoves[i];
                int newY = y + yMoves[i];
                if (isValidMove(newX, newY)) {
                    int moves = countAvailableMoves(newX, newY);
                    if (moves < minMoves) {
                        minMoves = moves;
                        nextX = newX;
                        nextY = newY;
                    }
                }
            }

            // если не удалось найти следующий ход, возвращаемся назад
            if (nextX == -1) {
                return false;
            }

            // делаем следующий ход
            board[nextX][nextY] = moveCount;
            if (solveRecursive(moveCount + 1, nextX, nextY)) {
                return true;
            }
            // если решение не найдено, отменяем ход и продолжаем поиск
            board[nextX][nextY] = 0;
            return false;
        }

        private boolean isValidMove(int x, int y) {
            return x >= 0 && x < n && y >= 0 && y < n && board[x][y] == 0;
        }

        private int countAvailableMoves(int x, int y) {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                int newX = x + xMoves[i];
                int newY = y + yMoves[i];
                if (isValidMove(newX, newY)) {
                    count++;
                }
            }
            return count;
        }

        private void printSolution() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            int n = 5;
            KnightTour tour = new KnightTour(n);
            tour.solve();
        }
    }
}