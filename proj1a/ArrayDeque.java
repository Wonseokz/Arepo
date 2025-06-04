import java.lang.reflect.Array;

class ArrayDeque<T>{

    public T[] items;
    public int size;
    public int capacity;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque(){
        size = 0;
        capacity = 8;
        nextFirst = 3;
        nextLast = 4;
        items = (T[]) new Object[capacity];
    }

    public void resize(){
        if(size >= capacity){
            T[] newItems = (T[])new Object[capacity * 2];
//        System.arraycopy(items,0,newItems, capacity - capacity/2, capacity);
            for (int i = 0, start = capacity - capacity / 2, next = ringUp(nextFirst) ;
                 i < capacity; ++i, ++start, next = ringUp(next))
            {
                newItems[start] = items[next];
            }
            nextFirst = (capacity - (capacity / 2)) - 1;
            nextLast = (capacity + (capacity / 2)) ;

            items = newItems;
            capacity = capacity * 2;
        }else if(size <= capacity / 4){
            //Todo
        }

    }

    public void addFirst(T item){
         resize();

        items[nextFirst] = item;
        nextFirst = ringDown(nextFirst);

        size += 1;
    }

    public void addLast(T item){
        resize();

        items[nextLast] = item;
        nextLast = ringUp(nextLast);

        size += 1;
    }

    private int ringDown(int next){
        return (((next - 1) % capacity)+capacity)%capacity;
    }

    private int ringUp(int next){
        return (((next + 1) % capacity)+capacity)%capacity;
    }

    public T removeFirst(){
        T removedItem = items[ringUp(nextFirst)];
        nextFirst = ringUp(nextFirst);

        size -= 1;
        return removedItem;
    }

    public T removeLast(){
        T removedItem = items[ringDown(nextLast)];
        nextLast = ringDown(nextLast);

        size -= 1;
        return removedItem;
    }

    public T get(int index){
        return items[nextFirst + index + 1];
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = ringUp(nextFirst), count = 0;count < size ;++count,i = ringUp(i)){
            if(count == size -1){
                System.out.println(items[i]);
                return;
            }
            System.out.print(items[i]+" ");
        }
    }

    public boolean isEmpty(){
        return (nextFirst == nextLast - 1);
    }

}