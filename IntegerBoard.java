public class IntegerBoard implements GameBoard<Integer>{
    private final int width; // Nombre de colonne
    private final int height; // Nombre de ligne
    private final int[][] matrix; // Matrice qui représente la grille du sudoku

    public IntegerBoard(int [][] initGrid){
        this.width = initGrid.length;
        this.height = initGrid[0].length;
        this.matrix = new int[height][width]; // Initialisation de la matrice qui va prendre
                                              // des dimensions de 9x9.

        // Remplissage de la grille de sudoku
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.matrix[i][j] = initGrid[i][j]; // Copie la valeur de inputMatrix[i][j]
            }
        }
    }

    // Méthode qui a pour but de vérifier les limites de la grille
    public void verifyBounds(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Indices hors de la grille");
        }
    }

    @Override
    public Integer getCell(int x, int y) throws IndexOutOfBoundsException {
        verifyBounds(x, y);
        return matrix[x][y]; // Retourne la valeur de la cellule
    }

    @Override
    public void setCell(int x, int y, Integer value) throws IndexOutOfBoundsException {
        verifyBounds(x,y);
        matrix[x][y] = value;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    // Affichage de la matrice
    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
