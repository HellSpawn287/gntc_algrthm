import gntic_algrtm.AlgorithmEngine;
import gntic_algrtm.Island;
import gntic_algrtm.IslandManager;

import java.util.ArrayList;
import java.util.List;

public class ClientGAWithIslands {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        AlgorithmEngine.setTOURNAMENT_Size(62);

        List<Island> circleOfEvolution2_20 = createWorldOfIslands(2, 20, 8);
        List<Island> circleOfEvolution2_40 = createWorldOfIslands(2, 40, 8);
        List<Island> circleOfEvolution2_60 = createWorldOfIslands(2, 60, 8);
        List<Island> circleOfEvolution5_20 = createWorldOfIslands(5, 20, 8);
        List<Island> circleOfEvolution5_40 = createWorldOfIslands(5, 40, 8);
        List<Island> circleOfEvolution5_60 = createWorldOfIslands(5, 60, 8);
        List<Island> circleOfEvolution10_20 = createWorldOfIslands(10, 20, 8);
        List<Island> circleOfEvolution10_40 = createWorldOfIslands(10, 40, 8);
        List<Island> circleOfEvolution10_60 = createWorldOfIslands(10, 60, 8);
        List<Island> circleOfEvolution20_20 = createWorldOfIslands(20, 20, 8);
        List<Island> circleOfEvolution20_40 = createWorldOfIslands(20, 40, 8);
        List<Island> circleOfEvolution20_60 = createWorldOfIslands(20, 60, 8);

        IslandManager.generations(20,3, circleOfEvolution2_20);

//        IslandManager.generations(20,3, circleOfEvolution2_40);
//        IslandManager.generations(20,3, circleOfEvolution2_60);
//        IslandManager.generations(20,3, circleOfEvolution5_20);
//        IslandManager.generations(20,3, circleOfEvolution5_40);
//        IslandManager.generations(20,3, circleOfEvolution5_60);
//        IslandManager.generations(20,3, circleOfEvolution10_20);
//        IslandManager.generations(20,3, circleOfEvolution10_40);
//        IslandManager.generations(20,3, circleOfEvolution10_60);
//        IslandManager.generations(20,3, circleOfEvolution20_20);
//        IslandManager.generations(20,3, circleOfEvolution20_40);
//        IslandManager.generations(20,3, circleOfEvolution20_60);

        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("Finished: \n generations: " + IslandManager.generationGlobal + "\n migrations: " + IslandManager.migrationCounter + " Time: " + elapsedTimeSec + "seconds.");

    }

    public static List<Island> createWorldOfIslands(int numberOfIslands, int populationSize, int geneNumber) {
        if (!(AlgorithmEngine.getTOURNAMENT_Size() > populationSize)) {
            throw new RuntimeException("Tournament size = " + AlgorithmEngine.getTOURNAMENT_Size() + " is to small for population size = " + populationSize);
        }
        List<Island> circleOfEvolution = new ArrayList<>();

        System.out.println("Adding to the Cirle!");
        for (int i = 0; i < numberOfIslands; i++) {
            Island island = IslandManager.createAndPopulateIsland(populationSize, geneNumber);
            circleOfEvolution.add(island);
        }

        return circleOfEvolution;
    }
}