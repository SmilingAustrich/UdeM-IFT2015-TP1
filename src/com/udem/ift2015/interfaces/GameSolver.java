package com.udem.ift2015.interfaces;

/**
 * Interface pour les solveurs de jeu (joueur).
 *
 * @author      Francois Major (pour TP1-IFT2015-A24)
 * @version     1.0
 * @since       1.0 (25 octobre 2024)
 */
public interface GameSolver {

    /**
     * Résout l'état actuel du jeu.
     *
     * @return vrai si une solution a été trouvée, faux sinon
     */
    boolean solve();

    /**
     * Fournit une description de la solution.
     *
     * Affiche une explication compréhensible de la solution.
     */
    void printSolution();
}