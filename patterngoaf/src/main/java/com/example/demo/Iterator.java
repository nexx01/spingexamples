class IteratorPattern {
    public static void main(String[] args) {
        String[][] strings=new String[][]{
                {"John","Mary"},{"Alica","Bob"},{"Den"},{"Test","Rus","Java"}
        };


        Iterator iterator = new IteratorImpl(strings);

        for (int i = 0; i < 100; i++) {
            if(iterator.hasNext()){
                System.out.println(iterator.next());

            }
        }
    }
}

interface Iterator{

    String next();

    boolean hasNext();

}

class IteratorImpl implements Iterator {

    String[][] strings;
    int indexX;
    int indexY;

    public IteratorImpl(String[][] strings) {
        this.strings = strings;
    }

    @Override
    public String next() {
        if(indexY<strings.length&&indexX<strings[indexY].length){
            return strings[indexY][indexX++];
        } else if (indexY<strings.length-1&&indexX>=strings[indexY].length){
            indexY++;
            indexX = 0;
            return strings[indexY][indexX++];
        }

        return null;
    }

    @Override
    public boolean hasNext() {
        return indexY<strings.length-1 || indexX< strings.length-1;
    }
}