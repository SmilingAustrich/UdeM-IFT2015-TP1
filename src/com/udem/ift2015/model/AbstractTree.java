package com.udem.ift2015.model;

import com.udem.ift2015.interfaces.Tree;
import com.udem.ift2015.interfaces.Position;

/**
 * AbstractTree est une classe abstraite pour l'ADT Tree
 * fournissant certaines fonctionnalités de l'interface Tree.
 *
 * Basé sur Goodrich, Tamassia, Goldwasser.
 *
 * @author      Francois Major
 * @version     1.0
 * @since       1.0
 */

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.lang.Iterable;

public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }

    @Override
    public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }

    @Override
    public boolean isRoot(Position<E> p) { return p == this.root(); }

    @Override
    public boolean isEmpty() { return this.size() == 0; }

    /**
     * Retourne le nombre de niveaux séparant la position p de la racine (profondeur de p).
     *
     * Exécution en O(profondeur de p + 1); O(n) dans le pire des cas.
     *
     * @param p la position pour laquelle la profondeur est calculée
     * @return la profondeur de la position p
     */
    public int depth(Position<E> p) {
        if (this.isRoot(p)) return 0;
        return 1 + this.depth(this.parent(p));
    }

    /**
     * Retourne la hauteur du sous-arbre enraciné à la position p.
     *
     * Temps d'exécution dans le pire des cas : O(n).
     *
     * @param p la position pour laquelle la hauteur est calculée
     * @return la hauteur du sous-arbre enraciné à p
     */
    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c : this.children(p))
            h = Math.max(h, 1 + this.height(c));
        return h;
    }

    //----- classe interne pour l'itérateur d'éléments
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() { return posIterator.hasNext(); }

        public E next() { return posIterator.next().getElement(); }

        public void remove() { posIterator.remove(); }
    } //----- fin de la classe interne pour l'itérateur d'éléments

    @Override
    public Iterator<E> iterator() { return new ElementIterator(); }

    /**
     * Retourne un itérable des positions de l'arbre.
     *
     * @return un itérable des positions de l'arbre en parcours préfixe
     */
    @Override
    public Iterable<Position<E>> positions() { return preorder(); }

    /**
     * Ajoute les positions du sous-arbre enraciné à p à la liste donnée en utilisant le parcours préfixe.
     *
     * @param p la position racine du sous-arbre
     * @param snapshot la liste des positions à remplir
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p); // pour le parcours préfixe, on ajoute p avant d'explorer les sous-arbres
        for (Position<E> c : children(p))
            preorderSubtree(c, snapshot);
    }

    /**
     * Retourne une collection itérable des positions de l'arbre en parcours préfixe.
     *
     * @return une collection itérable des positions de l'arbre
     */
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!this.isEmpty())
            preorderSubtree(this.root(), snapshot); // remplir le snapshot de façon récursive
        return snapshot;
    }

    /**
     * Ajoute les positions du sous-arbre enraciné à p à la liste donnée en utilisant le parcours suffixe.
     *
     * @param p la position racine du sous-arbre
     * @param snapshot la liste des positions à remplir
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(p); // pour le parcours suffixe, on ajoute p après avoir exploré les sous-arbres
    }

    /**
     * Retourne une collection itérable des positions de l'arbre en parcours suffixe.
     *
     * @return une collection itérable des positions de l'arbre
     */
    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!this.isEmpty())
            postorderSubtree(this.root(), snapshot); // remplir le snapshot de façon récursive
        return snapshot;
    }

    /**
     * Parcours en largeur de l'arbre.
     *
     * @return une collection itérable des positions de l'arbre en parcours en largeur
     */
    public Iterable<Position<E>> breadthfirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!this.isEmpty()) {
            Queue<Position<E>> fringe = new LinkedList<>();
            fringe.offer(this.root()); // commencer avec la racine
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.poll(); // retirer de l'avant de la file
                snapshot.add(p); // ajouter cette position
                for (Position<E> c : children(p))
                    fringe.offer(c); // ajouter les enfants à la fin de la file
            }
        }
        return snapshot;
    }
}
