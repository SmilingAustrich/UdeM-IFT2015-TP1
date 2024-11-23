package com.udem.ift2015.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedGeneralTree<E> implements Tree<E> {
    private Node<E> root; // La racine de l'arbre
    private int size = 0; // Taille de l'arbre

    // Classe interne représentant un nœud de l'arbre
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

    @Override
    public Position<E> root() {
        return root;    // Retourne le noeud racine
    }

    @Override
    public void setRoot(E data) {
        this.root = new Node<>(data);
        size = 1;
    }

    @Override
    public void addChild(E parentData, E childData) {
        Node <E> parentNode = findNode(root, parentData);   // Cherche le noeud parent
        if (parentNode != null) {
            Node <E> childNode = new Node<>(childData);     // Création d'un noeud enfant
            childNode.parent = parentNode;
            parentNode.children.add(childNode);
        }
        else {
            throw new IllegalArgumentException("Le parent n'existe pas dans l'arbre");
        }
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node <E> node = validate(p);
        return node.parent;     // Retourne le parent
    }

    // Retourne les enfants d'une position dans l'arbre
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node <E> node = validate(p);
        List<Position<E>> childrens = new ArrayList<>();
        for(Node <E> child : node.children) {
            childrens.add(child);   // Ajoute chaque enfant comme Position<E>
        }
        return childrens;
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        Node <E> node = validate(p);
        return node.children.size();
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Méthode récursive pour parcourir un arbre en pré-fixe et ajouter les positions à la liste.
    public void traverse(Node <E> node, List<Position<E>> positions){
        positions.add(node);
        for (Node <E> child : node.children) {
            traverse(child, positions);
        }
    }

    // Retourne une collection contenant tous les positions dans l'arbre
    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> positions = new ArrayList<>();
        if (root != null) {
            traverse(root,positions);
        }
        return positions;
    }

    // Recherche un noeud avec sa donnée spécifique dans le sous-arbre
    private Node<E> findNode(Node<E> currentNode, E data){
        if (currentNode == null) {
            return null;
        }
        if (currentNode.data.equals(data)){
            return currentNode;
        }
        for (Node<E> child : currentNode.children) {
            Node <E> result = findNode(child,data);
            if (result != null){
                return result;
            }
        }
        return null;
    }

    // Valide une position pour s'assurer qu'elle appartient à l'arbre
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)){
            throw new IllegalArgumentException("position invalide.");
        }
        Node <E> node = (Node<E>) p;
        if (node.parent == node) {
            throw new IllegalArgumentException("Position n'appartient pas à cet arbre");
        }
        return node;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> elements = new ArrayList<>();
        for (Position<E> position : positions()) {
            elements.add(position.getElement());
        }
        return elements.iterator();
    }
}








