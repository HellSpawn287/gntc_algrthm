import gntic_algrtm.AlgorithmEngine;
import gntic_algrtm.Chromosome;
import gntic_algrtm.Function;
import gntic_algrtm.Population;

public class Client {
    public static void main(String[] args) {

        Population momsPopulation = new Population.Builder(8, 5)
                .initialize()
                .build();
        Population dadsPopulation = new Population.Builder(8, 5)
                .initialize()
                .build();

        Population best = AlgorithmEngine.evolve(momsPopulation, dadsPopulation);

        Chromosome result = Function.findChromosomeWithBiggestPhenotype(best);
        System.out.println("\n===========================\n" +
                "The fittest chromoseome: " + result.toString()
                + "\n has phenotype = " + result.getPhenotype());
    }
}
