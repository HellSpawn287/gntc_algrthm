import gntic_algrtm.AlgorithmEngine;
import gntic_algrtm.NoMigrationManager;
import gntic_algrtm.Population;

public class ClientGA_NoMigration {
    public static void main(String[] args) {

        AlgorithmEngine.setTOURNAMENT_Size(20);

        Population mom = NoMigrationManager.buildPopulation(20, 8);
        Population dad = NoMigrationManager.buildPopulation(10, 8);

//        Chromosome bestChromosomeFromEvolvedPopulation = Manager.findBestChromosomeFromEvolvedPopulation(mom, mom);
//        System.out.println("The best chromosome: " + bestChromosomeFromEvolvedPopulation.toString()
//                + "\n has phenotype = " + bestChromosomeFromEvolvedPopulation.getPhenotype());
        NoMigrationManager.runGenerations(3, mom, mom);
    }
}
