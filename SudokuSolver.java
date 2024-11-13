public class SudokuSolver implements GameSolver {
    private IntegerBoard board;
    private IntegerBoard solution;
    private Tree<IntegerBoard> solutionTree;

    // Constructeur unique qui prend un GameBoard et initialise les autres attributs
    public SudokuSolver(GameBoard<Integer> board) {
        this.board = (IntegerBoard) board;
        this.solution = null; // Initialisation par défaut à null
        this.solutionTree = new LinkedGeneralTree<>(); // Initialisation de l'arbre
        this.solutionTree.setRoot(this.board); // Définir la racine de l'arbre comme la grille initiale
    }

    @Override
    public boolean solve() {
        return solveBoard();
    }

    @Override
    public void printSolution() {
        if (solution != null) {
            System.out.println("Sudoku résolu :");
            solution.display();
        } else {
            System.out.println("Aucune solution n'a été trouvée.");
        }
    }

    // Méthode pour valider un placement dans la grille
    public boolean isValidPlacement(int row, int col, Integer value) {
        // Vérifie la ligne
        for (int i = 0; i < board.getWidth(); i++) {
            if (board.getCell(row, i).equals(value)) {
                return false;
            }
        }

        // Vérifie la colonne
        for (int i = 0; i < board.getHeight(); i++) {
            if (board.getCell(i, col).equals(value)) {
                return false;
            }
        }

        // Vérifie la sous-grille
        int subGridSize = (int) Math.sqrt(board.getWidth());
        int boxRowStart = (row / subGridSize) * subGridSize;
        int boxColStart = (col / subGridSize) * subGridSize;

        for (int i = boxRowStart; i < boxRowStart + subGridSize; i++) {
            for (int j = boxColStart; j < boxColStart + subGridSize; j++) {
                if (board.getCell(i, j).equals(value)) {
                    return false;
                }
            }
        }

        return true; // Le placement est valide
    }

    // Méthode de résolution avec backtracking
    private boolean solveBoard() {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (board.getCell(row, col) == 0) { // Trouve une cellule vide
                    for (int num = 1; num <= board.getWidth(); num++) {
                        if (isValidPlacement(row, col, num)) {
                            board.setCell(row, col, num);
                            solutionTree.addChild(this.board, board); // Ajoute l'état à l'arbre
                            if (solveBoard()) {
                                solution = board; // Enregistre la solution trouvée
                                return true;
                            }
                            board.setCell(row, col, 0); // Backtracking
                        }
                    }
                    return false; // Retourne faux si aucune solution n'est trouvée pour cette cellule
                }
            }
        }
        return true; // Retourne vrai si la grille est entièrement remplie
    }
}

