package com.udem.ift2015.model;

import com.udem.ift2015.interfaces.GameSolver;
import com.udem.ift2015.interfaces.GameBoard;

/**
 * SudokuApp est l'application principale pour tester le solveur de Sudoku.
 *
 * Cette classe contient plusieurs cas de test pour vérifier le bon fonctionnement du solveur de Sudoku.
 *
 * @author      Morteza Mahdiani (pour TP1-IFT2015-A24)
 * @author      modifié par Francois Major, le 25 octobre 2024
 *
 * @version     1.1 (25 octobre 2024)
 * @since       1.0 (22 octobre 2024)
 */
public class SudokuApp {

    /**
     * Cas de test 1 : Sudoku standard partiellement rempli.
     */
    public void testCase1() {
        System.out.println("*** Test Case 1 ***");
        Integer[][] puzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        runTest(puzzle);
    }

    /**
     * Cas de test 2 : Sudoku avec des valeurs initiales différentes.
     */
    public void testCase2() {
        System.out.println("*** Test Case 2 ***");
        Integer[][] puzzle = {
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {4, 0, 3, 0, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 6, 0},
                {0, 5, 0, 4, 6, 0, 0, 0, 0},
                {0, 0, 0, 3, 0, 8, 0, 0, 0},
                {0, 0, 0, 0, 9, 7, 0, 8, 0},
                {0, 6, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 5, 0, 0, 2, 0, 9},
                {0, 8, 0, 0, 0, 0, 0, 0, 0}
        };
        runTest(puzzle);
    }

    /**
     * Cas de test 3 : Sudoku presque rempli mais avec des valeurs manquantes.
     */
    public void testCase3() {
        System.out.println("*** Test Case 3 ***");
        Integer[][] puzzle = {
                {5, 1, 6, 8, 4, 9, 7, 3, 2},
                {3, 0, 7, 6, 0, 5, 0, 0, 0},
                {8, 0, 9, 7, 0, 0, 0, 6, 5},
                {1, 3, 5, 0, 6, 0, 9, 0, 7},
                {4, 7, 2, 5, 9, 1, 0, 0, 6},
                {9, 6, 8, 3, 7, 0, 0, 5, 0},
                {2, 5, 3, 1, 8, 6, 0, 7, 4},
                {6, 8, 4, 2, 0, 7, 5, 0, 0},
                {7, 9, 1, 0, 5, 0, 6, 0, 0}
        };
        runTest(puzzle);
    }

    /**
     * Cas de test 4 : Sudoku très partiellement rempli.
     */
    public void testCase4() {
        System.out.println("*** Test Case 4 ***");
        Integer[][] puzzle = {
                {0, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 6, 0, 0},
                {0, 0, 0, 5, 0, 9, 0, 0, 0},
                {6, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 7, 0, 0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 3, 0},
                {9, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 7, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 0, 0, 0, 0}
        };
        runTest(puzzle);
    }

    /**
     * Cas de test 5 : Sudoku 4x4 pour tester la flexibilité du solveur.
     */
    public void testCase5() {
        System.out.println("*** Test Case 5 ***");
        Integer[][] puzzle = {
                {1, 2, 3, 4},
                {3, 4, 0, 0},
                {0, 0, 0, 0},
                {4, 3, 2, 1}
        };
        runTest(puzzle);
    }

    /**
     * Cas de test 6 : Sudoku vide pour tester la capacité du solveur à remplir complètement une grille.
     */
    public void testCase6() {
        System.out.println("*** Test Case 6 ***");
        Integer[][] puzzle = new Integer[9][9];
        runTest(puzzle);
    }

    /**
     * Cas de test 7 : Sudoku 8x8 pour tester une taille de grille non standard.
     */
    public void testCase7() {
        System.out.println("*** Test Case 7 ***");
        Integer[][] puzzle = {
                {0, 0, 0, 2, 0, 4, 0, 0},
                {0, 3, 0, 0, 0, 0, 6, 0},
                {0, 0, 5, 0, 0, 0, 0, 7},
                {2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 3},
                {7, 0, 0, 0, 0, 5, 0, 0},
                {0, 1, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 7, 0, 0, 2, 0}
        };
        runTest(puzzle);
    }

    /**
     * Cas de test 8 : Sudoku 6x6 pour tester une autre taille de grille non standard.
     */
    public void testCase8() {
        System.out.println("*** Test Case 8 ***");
        Integer[][] puzzle = {
                {0, 0, 0, 3, 0, 0},
                {0, 4, 0, 0, 0, 0},
                {0, 0, 5, 0, 6, 0},
                {0, 0, 0, 0, 0, 3},
                {5, 0, 0, 0, 4, 0},
                {0, 0, 1, 0, 0, 0}
        };
        runTest(puzzle);
    }

    /**
     * Exécute un cas de test en créant une grille et en utilisant le solveur.
     *
     * @param puzzle la grille de Sudoku à résoudre
     */
    private void runTest(Integer[][] puzzle) {
        GameBoard<Integer> board = new IntegerBoard(puzzle);
        GameSolver solver = new SudokuSolver(board);
        if (solver.solve()) {
            System.out.println("Solution trouvée :");
            solver.printSolution();
        } else {
            System.out.println("Aucune solution trouvée.");
        }
        System.out.println("==============================\n");
    }

    /**
     * Point d'entrée principal de l'application.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SudokuApp test = new SudokuApp();
        test.testCase1();
        test.testCase2();
        test.testCase3();
        test.testCase4();
        test.testCase5();
        test.testCase6();
        test.testCase7();
        test.testCase8();
    }
}
