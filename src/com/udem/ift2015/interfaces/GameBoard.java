package com.udem.ift2015.interfaces;

/**
 * Interface pour des plateaux de jeux orientés grille stockant des valeurs de type générique.
 *
 * @author      Francois Major (pour TP1-IFT2015-A24)
 * @version     1.0
 * @since       1.0 (25 octobre 2024)
 */
public interface GameBoard<T> {

    /**
     * Récupère la valeur à la position spécifiée.
     *
     * @param x l'index de la colonne (commence à 0)
     * @param y l'index de la rangée (commence à 0)
     * @return la valeur à la position spécifiée
     * @throws IndexOutOfBoundsException si la position est hors limites
     */
    T getCell(int x, int y) throws IndexOutOfBoundsException;

    /**
     * Définit la valeur à la position spécifiée.
     *
     * @param x l'index de la colonne (commence à 0)
     * @param y l'index de la rangée (commence à 0)
     * @param value la valeur à définir
     * @throws IndexOutOfBoundsException si la position est hors limites
     */
    void setCell(int x, int y, T value) throws IndexOutOfBoundsException;

    /**
     * Retourne la largeur du plateau de jeu.
     *
     * @return le nombre de colonnes
     */
    int getWidth();

    /**
     * Retourne la hauteur du plateau de jeu.
     *
     * @return le nombre de rangées
     */
    int getHeight();

    /**
     * Affiche le plateau de jeu.
     */
    void display();
}
