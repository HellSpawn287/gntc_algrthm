package gntic_algrtm;

public class AlgorithmEngine {
    static int TOURNAMENT_Size;
    private static double CROSSOVER_POINT = 0.8;
    private static double MUTATION_RATE = 0.2;
    static int geneNumber = 5;
    private static int populationSize = 8;
    private static boolean elitism = true;

    static {
        TOURNAMENT_Size = populationSize;
    }

    public AlgorithmEngine(int geneNumber, int populationSize, boolean elitism) {
        AlgorithmEngine.geneNumber = geneNumber;
        AlgorithmEngine.populationSize = populationSize;
        AlgorithmEngine.elitism = elitism;
    }

    private static Chromosome crossover(Chromosome father, Chromosome mother) {
        Chromosome offspring = new Chromosome.Builder()
                .configNullGenes()
                .build();

        if (father.size() == mother.size()) {
            if (Math.random() <= CROSSOVER_POINT) {
                int max = father.size() - 2;
                int min = 1;
                int range = max - min + 1;

                float crossoverPoint = (int) (Math.random() * range) + min;
                System.out.println("Crossover point is from range "
                        + min + "-" + max + " is: " + crossoverPoint);
                for (int i = min; i < max; i++) {
                    for (int j = min; j < crossoverPoint; j++) {
                        offspring.setGenes(mother.getGenes());
                        offspring.setSingleGene(j, father.getSingleGene(j));
                    }
                }
            }
        }
        System.out.println("Offspring:\t= " + offspring);

        return offspring;
    }

    private static Chromosome mutate(Chromosome chromosome) {
        Chromosome result = chromosome;
        for (int i = 0; i < chromosome.size(); i++) {
            double random = Math.random();
            if (random <= MUTATION_RATE) {
                byte gene = (byte) Math.round(random);
                result.setSingleGene(i, gene);
                System.out.println("\t\t Mutation rate : " + random +
                        "\n\t\t Chromosome before : " + chromosome.toString() +
                        "\n\t\t Chromosome after : " + result.toString());
            }else {
                System.out.println("\t\t Mutation does NOT OCCURES. The mutation rate is : " + random);
            }
        }
        return result;
    }

    private static Chromosome selectionByTournament(Population population) {
        Population tournamentParticipants = new Population.Builder()
                .configNullPopulation()
                .build();
        for (int i = 0; i < TOURNAMENT_Size; i++) {
            tournamentParticipants.saveChromosome(i, population.getSingleChromosome(i));
        }

        return tournamentParticipants.getFittestPopulation();
    }

    public static Population evolve(Population population1, Population population2) {
        Population offspring = new Population.Builder()
                .configNullPopulation()
                .build();

        if (elitism) {
            int fathersPhenotype = Function.findBiggestPopulationPhenotype(population1);
            int mothersPhenotype = Function.findBiggestPopulationPhenotype(population2);
            if (fathersPhenotype >= mothersPhenotype){
                offspring.saveChromosome(0, population1.getFittestPopulation());
            } else {
                offspring.saveChromosome(0, population2.getFittestPopulation());
            }

        }

        int numberOfElitism;
        if (elitism) {
            numberOfElitism = 1;
        } else numberOfElitism = 0;

        for (int i = numberOfElitism ; i < populationSize ; i++) {
            Chromosome parent_One = selectionByTournament(population1);
            Chromosome parent_Two = selectionByTournament(population2);
            Chromosome child = crossover(parent_One, parent_Two);
            offspring.saveChromosome(i,child);
        }

        for (int i = numberOfElitism ; i < populationSize ; i++) {
            mutate(offspring.getSingleChromosome(i));
        }

        System.out.println("\n====>>>\nEvolved population : \n" + "Name: offspringPopulation, \n" + offspring.toString()
                + "\n offspring got fitness score\t= " + Function.calculatePhenotypesSum(offspring));

        return offspring;
    }

    public static int getTOURNAMENT_Size() {
        return TOURNAMENT_Size;
    }

    public static void setTOURNAMENT_Size(int TOURNAMENT_Size) {
        AlgorithmEngine.TOURNAMENT_Size = TOURNAMENT_Size;
    }

    public static double getCrossoverPoint() {
        return CROSSOVER_POINT;
    }

    public static void setCrossoverPoint(double crossoverPoint) {
        CROSSOVER_POINT = crossoverPoint;
    }

    public static double getMutationRate() {
        return MUTATION_RATE;
    }

    public static void setMutationRate(double mutationRate) {
        MUTATION_RATE = mutationRate;
    }

    public static int getGeneNumber() {
        return geneNumber;
    }

    public static void setGeneNumber(int geneNumber) {
        AlgorithmEngine.geneNumber = geneNumber;
    }
}
