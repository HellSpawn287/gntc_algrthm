package gntic_algrtm;

public class Function {
    private Chromosome chromosome;

    private static double calculateAdaptationFunction(Chromosome chromosome) {
        double function = 0;
        //fenotyp nie może być ujemny. Inaczej ruletka nei zadziała
        if (chromosome.getPhenotype() == 0) {
            function += functionForZeroPhenotype(chromosome);
        } else {
            function += functionForNonZeroPhenotype(chromosome);
        }
        return function;
    }

    static double calculatePopulationSumOfAdaptationFunction(Population population) {
        double function = 0;
        for (int i = 0; i < population.size(); i++) {
            function = function + calculateAdaptationFunction(population.getSingleChromosome(i));
        }

        return function;
    }

    public static Chromosome findChromosomeWithBiggestPhenotype(Population population) {
        int biggestPhenotype = 0;
        Chromosome withBiggest = new Chromosome.Builder()
                .configNullGenes()
                .build();
        for (int i = 0; i < population.size(); i++) {
            biggestPhenotype = Math.max(population.getSingleChromosome(i).getPhenotype(), biggestPhenotype);
            if (!(withBiggest.getPhenotype() == biggestPhenotype)) {
                withBiggest = population.getSingleChromosome(i);
            }
        }

        System.out.println("\n\n==========================\n" +
                "Biggest phenotype of this population is : " + biggestPhenotype);
        System.out.println("Chromosome with biggest phenotype is: " + withBiggest.toString()
                + "\n=================");
        System.out.println("The adaptation function : y = " + calculateAdaptationFunction(withBiggest));
        return withBiggest;
    }

    Chromosome rouletteSelectionFunction(Population population) {

        double populationSum = calculatePopulationSumOfAdaptationFunction(population);
        System.out.println("\n\nPopulation chosen for roulette selection is: \n" +
                population.toString() + "\n its sum of each chromosome adaptation function = " + populationSum);
        double[] endPoints = new double[AlgorithmEngine.TOURNAMENT_Size];

        for (int i = 0; i < endPoints.length; i++) {
            endPoints[i] = getChromosomePercentage(population.getSingleChromosome(i), populationSum);
        }

        double[] ranges = new double[AlgorithmEngine.TOURNAMENT_Size];

        ranges[0] = endPoints[0];
        for (int i = 1; i < endPoints.length; i++) {
            ranges[i] = ranges[i - 1] + endPoints[i];
        }

        System.out.println("\n\nRoulette selection ranges :");
        for (int i = 0; i < ranges.length; i++) {
            if (i == 0) {
                System.out.println("\n\tChromosome " + i + ":  from " + 0 + "% to -> " + String.format("%.2f", ranges[i]) + "%");
            } else {
                System.out.println("\tChromosome " + (i + 1) + ":  from " + String.format("%.2f", ranges[i - 1]) +
                        "% to -> " + String.format("%.2f", ranges[i]) + "%");
            }
        }
        System.out.println("\n\n");

        double randomRouletteNumber = (Math.floor(Math.random() * ranges[7]));

        System.out.println("RESULT: " +
                "\n\tRandom number is: =>> " + randomRouletteNumber);

        if (isBetween(randomRouletteNumber, 0, ranges[0])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(0) + "]");

            return this.chromosome = population.getSingleChromosome(0);
        } else if (isBetween(randomRouletteNumber, ranges[0], ranges[1])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range TWO.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(1) + "]");

            return this.chromosome = population.getSingleChromosome(1);
        } else if (isBetween(randomRouletteNumber, ranges[1], ranges[2])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range THREE.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(2) + "]");

            return this.chromosome = population.getSingleChromosome(2);
        } else if (isBetween(randomRouletteNumber, ranges[2], ranges[3])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range FOUR.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(3) + "]");

            return this.chromosome = population.getSingleChromosome(3);
        } else if (isBetween(randomRouletteNumber, ranges[3], ranges[4])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range FIVE.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(4) + "]");

            return this.chromosome = population.getSingleChromosome(4);
        } else if (isBetween(randomRouletteNumber, ranges[4], ranges[5])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range SIX.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(5) + "]");

            return this.chromosome = population.getSingleChromosome(5);
        } else if (isBetween(randomRouletteNumber, ranges[5], ranges[6])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range SEVEN.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(6) + "]");

            return this.chromosome = population.getSingleChromosome(6);
        } else if (isBetween(randomRouletteNumber, ranges[6], ranges[7])) {
            System.out.println("\tRandom number " +
                    randomRouletteNumber + "\tis in range EIGHT.");
            System.out.println("\tSelected chromosome ONE : ["
                    + population.getSingleChromosome(7) + "]");

            return this.chromosome = population.getSingleChromosome(7);
        } else {
            System.out.println("\tRandom number " + randomRouletteNumber + "\tis NOT in ANY range.");
        }
        return this.chromosome;
    }

    private double getChromosomePercentage(Chromosome chromosome, double populationSum) {
        double chromosomePercentage;

        if (chromosome.getPhenotype() == 0) {
            chromosomePercentage = ((functionForZeroPhenotype(chromosome) / populationSum) * 100);
        } else {
            chromosomePercentage = (((functionForNonZeroPhenotype(chromosome)) / populationSum) * 100);
        }

        return chromosomePercentage;
    }

    private boolean isBetween(double x, double lower, double upper) {
        return lower <= x && x < upper;
    }

    static int findBiggestPopulationPhenotype(Population population) {
        int biggestPhenotype = 0;
        for (int i = 0; i < population.size(); i++) {
            biggestPhenotype = Math.max(population.getSingleChromosome(i).getPhenotype(), biggestPhenotype);
        }

        return biggestPhenotype;
    }

    private static double functionForNonZeroPhenotype(Chromosome chromosome) {
        return Math.abs(4 * Math.log10(chromosome.getPhenotype())
                + functionForZeroPhenotype(chromosome));
    }

    private static double functionForZeroPhenotype(Chromosome chromosome) {
        return Math.abs(7 * Math.sqrt(chromosome.getPhenotype() + 1));
    }
}