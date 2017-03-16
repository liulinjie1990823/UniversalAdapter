package com.llj.adapter.observable;

import java.util.List;

/**
 * Interface for a {@link List} implementation which is also observable via a
 * {@link ListObserver}.
 *
 * @param <T> The type of data in the list.
 */
public interface ObservableList<T> extends List<T> {
    /**
     * Gets a {@link ListObserver} which can be used to be notified of changes
     * to the list data.
     *
     * @return The {@link ListObserver}.
     */
    ListObserver<T> getListObserver();

    /**
     * Begins a transaction. Changes will be visible immediately, but the data
     * changed events will not be raised until a call is made to
     * {@link #endTransaction()}.
     *
     * @throws IllegalStateException if a transaction is already running.
     */
    void beginTransaction();

    /**
     * Ends the current transaction. This will raise the data changed events
     * if any modifications have been made.
     *
     * @throws IllegalStateException if no transaction is currently running.
     */
    void endTransaction();
}
