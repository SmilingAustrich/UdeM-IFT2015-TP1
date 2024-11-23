package com.udem.ift2015.interfaces;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;

/**
 * Interface pour l'ADT Arbre.
        *
        * Utilise l'interface Position définie dans la liste ADT.
        *
        * Basé sur Goodrich, Tamassia, Goldwasser.
 *
         * @author      Francois Major
 * @version     1.0
        * @since       1.0
        */
public interface Tree<E> extends Iterable<E> {

    /**
     * Retourne la racine de l'arbre.
     *
     * @return la position de la racine
     */
    Position<E> root();

    /**
     * Retourne le parent de la position donnée.
     *
     * @param p la position pour laquelle on veut le parent
     * @return la position du parent
     * @throws IllegalArgumentException si la position est invalide
     */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    /**
     * Retourne un itérable contenant les enfants de la position donnée.
     *
     * @param p la position pour laquelle on veut les enfants
     * @return un itérable des positions des enfants
     * @throws IllegalArgumentException si la position est invalide
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    /**
     * Retourne le nombre d'enfants de la position donnée.
     *
     * @param p la position pour laquelle on veut le nombre d'enfants
     * @return le nombre d'enfants
     * @throws IllegalArgumentException si la position est invalide
     */
    int numChildren(Position<E> p) throws IllegalArgumentException;

    /**
     * Vérifie si la position donnée est interne (a des enfants).
     *
     * @param p la position à vérifier
     * @return vrai si la position est interne, faux sinon
     * @throws IllegalArgumentException si la position est invalide
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Vérifie si la position donnée est externe (n'a pas d'enfants).
     *
     * @param p la position à vérifier
     * @return vrai si la position est externe, faux sinon
     * @throws IllegalArgumentException si la position est invalide
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Vérifie si la position donnée est la racine de l'arbre.
     *
     * @param p la position à vérifier
     * @return vrai si la position est la racine, faux sinon
     * @throws IllegalArgumentException si la position est invalide
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    /**
     * Retourne la taille de l'arbre (le nombre de nœuds).
     *
     * @return la taille de l'arbre
     */
    int size();

    /**
     * Vérifie si l'arbre est vide.
     *
     * @return vrai si l'arbre est vide, faux sinon
     */
    boolean isEmpty();

    /**
     * Retourne un itérateur sur les éléments stockés dans l'arbre.
     *
     * @return un itérateur sur les éléments de l'arbre
     */
    Iterator<E> iterator();

    /**
     * Retourne un itérable contenant toutes les positions de l'arbre.
     *
     * @return un itérable des positions de l'arbre
     */
    Iterable<Position<E>> positions();

    /**
     * Définit la racine de l'arbre avec les données fournies.
     *
     * @param data les données pour la racine
     */
    void setRoot(E data);

    /**
     * Ajoute un enfant à un nœud parent existant.
     *
     * @param parentData les données du parent
     * @param childData les données de l'enfant à ajouter
     */
    void addChild(E parentData, E childData);
}
