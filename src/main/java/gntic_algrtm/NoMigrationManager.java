package gntic_algrtm;

import static gntic_algrtm.AlgorithmEngine.evolve;
import static gntic_algrtm.Function.findChromosomeWithBiggestPhenotype;

public class NoMigrationManager {

    public static Population buildPopulation(int populationSize, int geneNumber) {
        return new Population.Builder(populationSize, geneNumber)
                .initialize()
                .build();
    }

    public static Chromosome findBestChromosomeFromEvolvedPopulation(Population moms, Population dads) {
        return findChromosomeWithBiggestPhenotype(evolve(moms, dads));
    }

    public static void runGenerations(int generationNumber, Population moms, Population dads) {
        int generationCounter = 1;
        Population offspring = moms;
        if (generationCounter <= generationNumber) {
            do {
                System.out.println("GENERATION : " + generationCounter);
                Population temp = generations(offspring, dads);
                offspring = temp;
                generationCounter ++;
            } while (generationCounter <= generationNumber);
        }

    }

    private static Population generations(Population moms, Population dads) {
        Population nextGeneration = evolve(moms, dads);

        Chromosome best = findChromosomeWithBiggestPhenotype(nextGeneration);

        return nextGeneration;
    }
}