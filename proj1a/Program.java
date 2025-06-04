class Program{
    public static void main(String[] args){
        ArrayDeque<Character> s = new ArrayDeque<>();
        System.out.println(s.isEmpty());
        s.addLast('a');
        s.addLast('b');
        s.addLast('c');
        s.addLast('d');
        System.out.println(s.isEmpty());
        s.removeFirst();
        s.removeFirst();
        s.removeFirst();
        s.removeFirst();
        System.out.println(s.isEmpty());






    }
}