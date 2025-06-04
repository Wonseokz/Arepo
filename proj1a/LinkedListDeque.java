class LinkedListDeque<T>{
    class Node{
        T item;
        Node prev;
        Node next;
        public Node(){}
        public Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.item = null;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        Node newNode = new Node(item,sentinel,sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        sentinel.prev = sentinel.prev.prev.next;
        size += 1;
    }

    public void addLast(T item){
        Node newNode = new Node(item,sentinel.prev,sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        sentinel.next = sentinel.next.next.prev;
        size += 1;
    }

    public boolean isEmpty(){
        return sentinel.next == sentinel && sentinel.prev == sentinel;
    }

    public T removeFirst(){
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.prev = sentinel.prev.prev.next;
        size -= 1;
        return removedItem;
    }

    public T removeLast(){
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.next = sentinel.next.next.prev;
        size -= 1;
        return removedItem;
    }

    public T get(int index){
        if(index > size){
            return null;
        }
        int i = 0;
        Node scout = sentinel.next;
        while (i < index){
            scout = scout.next;
            i++;
        }
        return scout.item;
    }

    public T getRecursive(int index, Node scout){
        if(index == 0){
            return scout.item;
        }else {
            index -= 1;
            scout = scout.next;
            return getRecursive(index,scout);
        }
    }


    public T getRecursive(int index){
        if(index > size){
            return null;
        }
        Node scout = sentinel.next;
        return getRecursive(index,scout);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node scout = sentinel.next;
        while(scout != sentinel.prev){
            System.out.print(scout.item+" ");
            scout = scout.next;
        }
        System.out.println(scout.item);
    }
}