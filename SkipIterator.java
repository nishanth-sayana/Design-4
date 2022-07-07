public class SkipIterator implements Iterator<Integer>{
    HashMap<Integer,Integer> map;
    integer nextEl;
    Iterator<integer> it;
    public SkipIterator(Iterator<Integer> it) {
        this.it=it;
        this.map=new HashMap<>();
        advance();
    }

    private void advance(){
        nextEl=null;
        while(nextEl==null && it.hasNext()){
            int el=it.next();
            if(map.contains(el)){
                map.put(el,map.get(el)-1);
                map.remove(el,0);
            }else{
                nextEl=el;
            }
        }
    }

    public boolean hasNext() {

        return !nextEl==null;
    }

    public Integer next() {
        Integer result=nextEl;
        advance();
        return result;
    }

    /**
     * The input parameter is an int, indicating that the next element equals 'val' needs to be skipped.
     * This method can be called multiple times in a row. skip(5), skip(5) means that the next two 5s should be skipped.
     */
    public void skip(int val) {
        if(nextEl!=null){
            map.put(val,map.getOrDefault(val,0)+1);
        }else{
            advance();
        }
    }
}
