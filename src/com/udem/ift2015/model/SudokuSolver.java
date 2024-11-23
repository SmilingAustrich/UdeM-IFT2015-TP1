package com.udem.ift2015.model;

import com.udem.ift2015.interfaces.GameSolver;

public class SudokuSolver implements GameSolver {
    private IntegerBoard board;
    private IntegerBoard solution;
    private Tree<IntegerBoard> solutionTree;

    // Constructeur qui prend un GameBoard et initialise les autres attributs
    public SudokuSolver(GameBoard<Integer> board) {
        this.board = (IntegerBoard) board;
        this.solution = null;
        this.solutionTree = new LinkedGeneralTree<>();
        this.solutionTree.setRoot(this.board); // Initialise la racine de l'arbre comme étant la grille initiale
    }

    public boolean solve(){
        return solveBoard();
    }

    @Override
    public void printSolution() {
        if (solution != null) {
            System.out.println("Sudoku résolu :");
            solution.display();
        } else {
            System.out.println("Aucune solution n'a été trouvé");
        }
    }

    private boolean isValidPlacement (int row, int col, Integer value) {
        // Vérification des lignes
        for (int i = 0; i < board.getWidth(); i++ ) {
            if (board.getCell(row,i).equals(value)){
                return false;
            }
        }

        // Vérification des colonnes
        for (int i = 0; i < board.getHeight(); i++) {
            if (board.getCell(i,col).equals(value)){
                return false;
            }
        }

        // Vérification de la sous grille
        int sousGrilleTaille = (int) Math.sqrt(board.getWidth()); // ex : pour une grille 9*9, la largeur de la sous grille = 3
        // Imaginons qu'on a une grille 4*4 et qu'on veut la sous-grille en bas à gauche, pour débuter l'analyse de
        // cette sous-grille, on doit savoir on est à quel ligne et quelle colonne, sachant qu'on est dans une grille
        // 4*4, on sera dans la 3ème ligne (index = 2) et 1ère colonne (index = 0), donc le calcul sera la suivante :
        // (2/2) * 2 = 2, (0/2) * 2 = 0.
        int sousGrilleLigne = (row/sousGrilleTaille) * sousGrilleTaille;
        int sousGrilleColonne = (col/sousGrilleTaille) * sousGrilleTaille;

        for (int i = sousGrilleLigne; i < sousGrilleLigne + sousGrilleTaille; i++) {
            for (int j = sousGrilleColonne; j < sousGrilleColonne + sousGrilleTaille; j++){
                if (board.getCell(i,j).equals(value)){
                    return false;
                }
            }
        }
        return true;

    }

    private boolean solveBoard(){
        // Vérifier les cellules vides dans la grille
        for (int row = 0; row < board.getWidth(); row++){
            for (int col = 0; col < board.getHeight(); col++){
                if (board.getCell(row,col).equals(0)){  // Présence de cellule vide
                    for (int chiffre = 1; chiffre <= board.getWidth(); chiffre++){
                        if (isValidPlacement(row,col,chiffre)){
                            board.setCell(row,col,chiffre); // Si le placement est valide, le chiffre sera intégré
                                                            // la cellule.
                            solutionTree.addChild(this.board,board); // Sauvegarde l'état dans l'arbre
                            if (solveBoard()) {
                                solution = board;
                                return true;
                            }
                            // Concept de backtracking, si une cellule ne présente aucune solution, on retourne dans la
                            // dernière cellule vide (qui a une solution) afin de l'initialiser à 0 pour explorer
                            // d'autres possibilités.
                            board.setCell(row,col,0);
                        }
                    }
                    return false; // Si aucune solution n'est trouvée, retourne faux
                }
            }
        }
        return true; // Si aucune cellule vide n'est trouvé, la grille est n'est donc pas vide
    }
}

