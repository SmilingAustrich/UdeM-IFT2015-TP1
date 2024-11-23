package com.udem.ift2015.interfaces;

import java.lang.IllegalStateException;

/**
 * Interface pour l'ADT Position.
 *
 * Basé sur Goodrich, Tamassia, Goldwasser.
 *
 * @author      Francois Major
 * @version     1.0
 * @since       1.0
 */
public interface Position<E> {

    /**
     * Retourne l'élément correspondant (stocké à cette Position).
     *
     * @return l'élément stocké à cette Position
     * @throws IllegalStateException si la Position n'est plus valide
     */
    E getElement() throws IllegalStateException;
}