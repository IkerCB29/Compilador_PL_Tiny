package utils;

public class Pair<T1, T2> {
    private T1 _first;
    private T2 _second;

    public Pair(){
        this._first = null;
        this._second = null;
    }

    public Pair(T1 first, T2 second){
        this._first = first;
        this._second = second;
    }

    public void setFirst(T1 first) { this._first = first; }

    public T1 getFirst(){
        return _first;
    }

    public void setSecond(T2 second) { this._second = second; }

    public T2 getSecond(){
        return _second;
    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Pair<?,?>))
            return false;

        Pair<T1, T2> casted = (Pair<T1, T2>) o;
        return casted._first == this._first && casted._second == this._second;
    }
}
