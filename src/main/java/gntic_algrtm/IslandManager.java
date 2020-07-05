package gntic_algrtm;

import java.util.List;
import java.util.stream.Collectors;

import static gntic_algrtm.AlgorithmEngine.evolve;

public class IslandManager {
    static List<Island> generation;

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
        destination.receiveMigratingChromosome(true, from.getMigrating_chromosome());
        destination.setMigrating_chromosome();
        System.out.println("\t\t\t >>> MIGRATION " + counter);
        destination.toString();
    }

    public static void generations(int generationNumber, int generationDistanceForMigration, List<Island> circleOfEvolution) {
        int generationCounter = 1;
        generation = List.copyOf(circleOfEvolution);
        if (generationCounter <= generationNumber) {
            if (generationCounter % generationDistanceForMigration == 0) {
                generation = runMigrationAndReturnGeneration(generation);
                generationCounter++;
            } else {
                System.out.println("----> NO MIGRATION");
                generation = islandsEvolveIndependently(generation);
            }
        }

    }

    private static List<Island> islandsEvolveIndependently(List<Island> generation) {
        List<Island> islandsEvolved = generation.stream().map(island -> {
            island.setMoms(evolve(island.getMoms(), island.getDads()));
            island.setDads(evolve(island.getMoms(), island.getDads()));
            island.setMigrating_chromosome();
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
}