package com.udem.ift2015.model;
import com.udem.ift2015.interfaces.GameBoard;

/**
 * IntegerBoard est une implémentation de l'interface GameBoard pour une grille d'entiers.
 *
 * Cette classe représente une grille de Sudoku sous forme de matrice d'entiers,
 * permettant de vérifier et de modifier les valeurs des cellules.
 *
 * @author      Francois Major
 * @version     1.0
 * @since       1.0
 */

public class IntegerBoard implements GameBoard<Integer> {
    private final int width; // Nombre de colonnes
    private final int height; // Nombre de lignes
    private int[][] matrix; // Matrice qui représente la grille du Sudoku

    /**
     * Constructeur qui initialise la grille à partir d'une matrice donnée.
     *
     * @param initGrid la matrice initiale qui représente la grille
     */
    public IntegerBoard(Integer[][] initGrid) {
        this.width = initGrid.length;
        this.height = initGrid[0].length;
        this.matrix = new int[height][width]; // Initialisation de la matrice avec les dimensions fournies

        // Remplissage de la grille de Sudoku
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.matrix[i][j] = (initGrid[i][j] != null) ? initGrid[i][j] : 0;
            }
        }
    }

    /**
     * Méthode qui vérifie si les indices donnés sont dans les limites de la grille.
     *
     * @param x l'indice de la colonne
     * @param y l'indice de la ligne
     * @throws IndexOutOfBoundsException si les indices sont hors des limites de la grille
     */
    public void verifyBounds(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Indices hors de la grille");
        }
    }

    /**
     * Retourne la valeur de la cellule à la position donnée.
     *
     * @param x l'indice de la colonne
     * @param y l'indice de la ligne
     * @return la valeur de la cellule à la position (x, y)
     * @throws IndexOutOfBoundsException si les indices sont hors des limites de la grille
     */
    @Override
    public Integer getCell(int x, int y) throws IndexOutOfBoundsException {
        verifyBounds(x, y);
        return matrix[x][y];
    }

    /**
     * Modifie la valeur de la cellule à la position donnée.
     *
     * @param x l'indice de la colonne
     * @param y l'indice de la ligne
     * @param value la nouvelle valeur de la cellule
     * @throws IndexOutOfBoundsException si les indices sont hors des limites de la grille
     */
    @Override
    public void setCell(int x, int y, Integer value) throws IndexOutOfBoundsException {
        verifyBounds(x, y);
        matrix[x][y] = value;
    }

    /**
     * Retourne le nombre de colonnes de la grille.
     *
     * @return le nombre de colonnes
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Retourne le nombre de lignes de la grille.
     *
     * @return le nombre de lignes
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Affiche la matrice représentant la grille de Sudoku.
     */
    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
