package com.udem.ift2015.model;

import com.udem.ift2015.interfaces.Position;
import com.udem.ift2015.interfaces.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * LinkedGeneralTree est une implémentation de l'interface Tree pour un arbre général lié.
 *
 * Cette classe représente un arbre général où chaque nœud peut avoir plusieurs enfants.
 *
 * @param <E> le type des éléments stockés dans l'arbre
 *
 * @author      Francois Major
 * @version     1.0
 * @since       1.0
 */
public class LinkedGeneralTree<E> implements Tree<E> {
    private Node<E> root; // La racine de l'arbre
    private int size = 0; // Taille de l'arbre

    /**
     * Classe interne représentant un nœud de l'arbre.
     *
     * @param <E> le type des éléments stockés dans les nœuds
     */
    private static class Node<E> implements Position<E> {
        E data;
        Node<E> parent; // Référence au parent du nœud
        List<Node<E>> children;

        Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        @Override
        public E getElement() {
            return data;
        }
    }

    /**
     * Retourne la racine de l'arbre.
     *
     * @return la position du nœud racine
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Définit la racine de l'arbre avec les données fournies.
     *
     * @param data les données de la racine
     */
    @Override
    public void setRoot(E data) {
        this.root = new Node<>(data);
        size = 1;
    }

    /**
     * Ajoute un enfant à un nœud parent existant.
     *
     * @param parentData les données du nœud parent
     * @param childData les données du nœud enfant
     * @throws IllegalArgumentException si le parent n'existe pas dans l'arbre
     */
    @Override
    public void addChild(E parentData, E childData) {
        Node<E> parentNode = findNode(root, parentData); // Cherche le nœud parent
        if (parentNode != null) {
            Node<E> childNode = new Node<>(childData); // Création d'un nœud enfant
            childNode.parent = parentNode;
            parentNode.children.add(childNode);
        } else {
            throw new IllegalArgumentException("Le parent n'existe pas dans l'arbre");
        }
    }

    /**
     * Retourne le parent d'une position donnée.
     *
     * @param p la position du nœud enfant
     * @return la position du nœud parent
     * @throws IllegalArgumentException si la position est invalide
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.parent;
    }

    /**
     * Retourne les enfants d'une position donnée dans l'arbre.
     *
     * @param p la position du nœud parent
     * @return une collection itérable des positions des enfants
     * @throws IllegalArgumentException si la position est invalide
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        List<Position<E>> childrens = new ArrayList<>();
        for (Node<E> child : node.children) {
            childrens.add(child);
        }
        return childrens;
    }

    /**
     * Retourne le nombre d'enfants d'une position donnée.
     *
     * @param p la position du nœud
     * @return le nombre d'enfants
     * @throws IllegalArgumentException si la position est invalide
     */
    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.children.size();
    }

    /**
     * Vérifie si une position donnée est interne (c'est-à-dire a au moins un enfant).
     *
     * @param p la position du nœud
     * @return true si la position est interne, false sinon
     * @throws IllegalArgumentException si la position est invalide
     */
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    /**
     * Vérifie si une position donnée est externe (c'est-à-dire n'a aucun enfant).
     *
     * @param p la position du nœud
     * @return true si la position est externe, false sinon
     * @throws IllegalArgumentException si la position est invalide
     */
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    /**
     * Vérifie si une position donnée est la racine de l'arbre.
     *
     * @param p la position du nœud
     * @return true si la position est la racine, false sinon
     * @throws IllegalArgumentException si la position est invalide
     */
    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root;
    }

    /**
     * Retourne la taille de l'arbre (le nombre de nœuds).
     *
     * @return la taille de l'arbre
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Vérifie si l'arbre est vide.
     *
     * @return true si l'arbre est vide, false sinon
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Méthode récursive pour parcourir l'arbre en préfixe et ajouter les positions à la liste.
     *
     * @param node le nœud courant
     * @param positions la liste des positions à remplir
     */
    public void traverse(Node<E> node, List<Position<E>> positions) {
        positions.add(node);
        for (Node<E> child : node.children) {
            traverse(child, positions);
        }
    }

    /**
     * Retourne une collection contenant toutes les positions dans l'arbre.
     *
     * @return une collection itérable des positions dans l'arbre
     */
    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> positions = new ArrayList<>();
        if (root != null) {
            traverse(root, positions);
        }
        return positions;
    }

    /**
     * Recherche un nœud avec une donnée spécifique dans le sous-arbre.
     *
     * @param currentNode le nœud courant
     * @param data les données à chercher
     * @return le nœud trouvé ou null s'il n'existe pas
     */
    private Node<E> findNode(Node<E> currentNode, E data) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.data.equals(data)) {
            return currentNode;
        }
        for (Node<E> child : currentNode.children) {
            Node<E> result = findNode(child, data);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /**
     * Valide une position pour s'assurer qu'elle appartient à l'arbre.
     *
     * @param p la position à valider
     * @return le nœud validé
     * @throws IllegalArgumentException si la position est invalide
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position invalide.");
        }
        Node<E> node = (Node<E>) p;
        if (node.parent == node) {
            throw new IllegalArgumentException("Position n'appartient pas à cet arbre");
        }
        return node;
    }

    /**
     * Retourne un itérateur sur les éléments de l'arbre.
     *
     * @return un itérateur sur les éléments de l'arbre
     */
    @Override
    public Iterator<E> iterator() {
        List<E> elements = new ArrayList<>();
        for (Position<E> position : positions()) {
            elements.add(position.getElement());
        }
        return elements.iterator();
    }
}
