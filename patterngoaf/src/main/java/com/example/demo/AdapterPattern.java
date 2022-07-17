import java.util.Arrays;
import java.util.Random;

class AdapterPatern {
    public static void main(String[] args) {
        SequenseGeneratorAdapter sequenseGeneratorAdapter = new SequenseGeneratorAdapter(new RandomGenerator());

        System.out.println(Arrays.toString(sequenseGeneratorAdapter.generator(6)));
    }
}

class RandomGenerator{
    public int getRandomNumber() {
        return (int) (Integer.MAX_VALUE * Math.random());
    }
}

class SequenceGenerator{
    public int[] generator(int length) {
        Random rd = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt();
        }
        return arr;
    }
}

class SequenseGeneratorAdapter{
    RandomGenerator randomGenerator;

    public SequenseGeneratorAdapter(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public int[] generator(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomGenerator.getRandomNumber();
        }
        return arr;
    }
}