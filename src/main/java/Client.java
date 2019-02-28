import gntic_algrtm.Chromosome;
import gntic_algrtm.Population;

public class Client {
    public static void main(String[] args) {
        Chromosome chromosome = new Chromosome.Builder().initialize().build();
        System.out.println(chromosome);
        System.out.println(chromosome.getFenotype());

        Population population = new Population.Builder()
                .initialize()
                .build();
        System.out.println(population);
        System.out.println(population.getSingleChromosome(3).getFenotype());
    }
}
