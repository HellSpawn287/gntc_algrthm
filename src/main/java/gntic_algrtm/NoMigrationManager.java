package gntic_algrtm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static gntic_algrtm.AlgorithmEngine.evolve;
import static gntic_algrtm.Function.findChromosomeWithBiggestPhenotype;

public class NoMigrationManager {
    public static int generationGlobal = 0;
    static PrintWriter writer = null;
    static File file;
    private static int count;

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
                offspring = generations(offspring, dads);
                printPopulationInFile(moms, dads);
                generationCounter++;
                generationGlobal = generationCounter;
            } while (generationCounter <= generationNumber);
        }

    }

    private static Population generations(Population moms, Population dads) {
        Population nextGeneration = evolve(moms, dads);

        Chromosome best = findChromosomeWithBiggestPhenotype(nextGeneration);

        return nextGeneration;
    }

    private static void printPopulationInFile(Population mom, Population dad) {
        file = new File("./algorithm_data/islandPopulation_" + count + ".txt");
        file.getParentFile().mkdirs();

        try {
            System.out.println(file.exists() ? "File created: " + file.getName() : "File already exist.");

            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (writer == null) throw new AssertionError();

        writer.println("Population ID:" + mom.getId() + " \tGeneration: " + generationGlobal + "\nThe biggest phenotypes");
        writer.print("Population " + count + ": \t mothers \t fathers \n" + "\t" + Function.findBiggestPopulationPhenotype(mom) + ",\t" + Function.findBiggestPopulationPhenotype(dad) + "\t");
        writer.println("\n\nThe population sums of adaptation function results");
        writer.print("Population " + count + ": \t mothers \t fathers \n" + "\t" + Function.calculatePopulationSumOfAdaptationFunction(mom) + ",\t" + Function.calculatePopulationSumOfAdaptationFunction(dad) + "\t");

        writer.println("\n\nThe adaptation function results for each chromosomes. \nPopulation " + count + ": \t mothers \t fathers \n");
        for (int populationCursor = 0; populationCursor < AlgorithmEngine.getTOURNAMENT_Size() - 1; populationCursor++) {
            if (writer != null) {
                writer.print("\t" + Function.calculateAdaptationFunction(mom.getSingleChromosome(populationCursor)) + "\t" + Function.calculateAdaptationFunction(dad.getSingleChromosome(populationCursor)) + ",\t");
            }
        }

        writer.println("\n\nThe phenotypes for each chromosomes. \nPopulation " + count + ": \t mothers \t fathers \n");
        for (int populationCursor = 0; populationCursor < AlgorithmEngine.getTOURNAMENT_Size() - 1; populationCursor++) {
            if (writer != null) {
                writer.print("\t" + mom.getSingleChromosome(populationCursor).getPhenotype() + ",\t" + dad.getSingleChromosome(populationCursor).getPhenotype() + ",\t");
            }
        }
        writer.close();
        count++;
    }
}