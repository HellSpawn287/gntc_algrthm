package gntic_algrtm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static gntic_algrtm.AlgorithmEngine.evolve;

public class IslandManager {
    public static int generationGlobal = 0;
    public static int migrationCounter;
    static List<Island> generation;
    static PrintWriter writer = null;
    static File file;
    private static int count;

    public static Island createAndPopulateIsland(int populationSize, int geneNumber) {
        Population momsPopulation = buildPopulation(populationSize, geneNumber);
        Population dadsPopulation = buildPopulation(populationSize, geneNumber);
        return new Island(momsPopulation, dadsPopulation);
    }

    public static Population buildPopulation(int populationSize, int geneNumber) {
        return new Population.Builder(populationSize, geneNumber)
                .initialize()
                .build();
    }

    public static void migration(Island from, Island destination, int counter) {
        migrationCounter++;
        destination.receiveMigratingChromosome(true, from.getMigrating_chromosome());
        destination.setMigrating_chromosome();
        System.out.println("\t\t\t >>> MIGRATION " + counter +"\n" +destination.toString());
        printPopulationInFile(destination);
    }

    public static void generations(int generationNumber, int generationDistanceForMigration, List<Island> circleOfEvolution) {
        int generationCounter = 0;
        generation = List.copyOf(circleOfEvolution);
        while (generationCounter < generationNumber) {
            if (generationCounter % generationDistanceForMigration <= 2) {
                generation = runMigrationAndReturnGeneration(generation);
                generationCounter++;
                generationGlobal = generationCounter;

            } else {
                System.out.println("----> NO MIGRATION");
                generation = islandsEvolveIndependently(generation);
                generationCounter++;
                generationGlobal = generationCounter;
            }
        }
    }

    private static List<Island> islandsEvolveIndependently(List<Island> generation) {
        List<Island> islandsEvolved = generation.stream().map(island -> {
            island.setMoms(evolve(island.getMoms(), island.getDads()));
            island.setDads(evolve(island.getMoms(), island.getDads()));
            island.setMigrating_chromosome();
            printPopulationInFile(island);
            return island;
        }).collect(Collectors.toList());
        return islandsEvolved;
    }


    private static List<Island> runMigrationAndReturnGeneration(List<Island> circleOfEvolution) {
        int counter = 1;
        List<Island> nextGeneration = List.copyOf(circleOfEvolution);
        int size = nextGeneration.size();
        for (int i = 0; i < size - 1; i++) {
            if (circleOfEvolution.get(i + 1) != null) {
                IslandManager.migration(nextGeneration.get(i), nextGeneration.get(i + 1), counter);
                counter++;
            }
            IslandManager.migration(nextGeneration.get(size - 1), nextGeneration.get(0), counter);
            counter++;
        }
        return nextGeneration;
    }

    public static void runMigrationOnce(List<Island> circleOfEvolution) {
        int counter = 1;
        for (int i = 0; i < circleOfEvolution.size() - 1; i++) {
            if (circleOfEvolution.get(i + 1) != null) {
                IslandManager.migration(circleOfEvolution.get(i), circleOfEvolution.get(i + 1), counter);
                counter++;
            }
            IslandManager.migration(circleOfEvolution.get(circleOfEvolution.size() - 1), circleOfEvolution.get(0), counter);
            counter++;
        }
    }

    private static void printPopulationInFile(Island island) {
        Population mom = island.getMoms();
        Population dad = island.getDads();
        file = new File("./algorithm_data/islandPopulation_" + count + ".txt");
        file.getParentFile().mkdirs();

        try {
            System.out.println(file.exists() ? "File created: " + file.getName() : "File already exist.");

            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (writer == null) throw new AssertionError();

        writer.println("Island ID:" + island.getId() + " \tGeneration: " + generationGlobal + "\nThe biggest phenotypes");
        writer.print("Island " + count + ": \t mothers \t fathers \n" + "\t" + Function.findBiggestPopulationPhenotype(mom) + ",\t" + Function.findBiggestPopulationPhenotype(dad) + ",\t");
        writer.println("\n\nThe population sum of adaptation function results");
        writer.print("Island " + count + ": \t mothers \t fathers \n" + "\t" + Function.calculatePopulationSumOfAdaptationFunction(mom) + ",\t" + Function.calculatePopulationSumOfAdaptationFunction(dad) + ",\t");

        writer.println("\n\nThe adaptation function results for each chromosomes. \nIsland " + count + ": \t mothers \t fathers \n");
        for (int populationCursor = 0; populationCursor < AlgorithmEngine.getTOURNAMENT_Size() - 1; populationCursor++) {
            if (writer != null) {
                writer.print("\t" + Function.calculateAdaptationFunction(mom.getSingleChromosome(populationCursor)) + "\t" + Function.calculateAdaptationFunction(dad.getSingleChromosome(populationCursor)) + ",\t");
            }
        }

        writer.println("\n\nThe phenotypes for each chromosomes. \nIsland " + count + ": \t mothers \t fathers \n");
        for (int populationCursor = 0; populationCursor < AlgorithmEngine.getTOURNAMENT_Size() - 1; populationCursor++) {
            if (writer != null) {
                writer.print("\t" + mom.getSingleChromosome(populationCursor).getPhenotype() + ",\t" + dad.getSingleChromosome(populationCursor).getPhenotype() + ",\t");
            }
        }
        writer.close();
        count++;
    }
}