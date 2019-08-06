package com.ls.dareman.Utils;

public interface DialogResultListener<T> {
    /**
     * listener call back result interface
     * @param result
     */
    void onDataResult(T result);
}
