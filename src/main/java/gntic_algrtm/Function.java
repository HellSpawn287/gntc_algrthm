package gntic_algrtm;

public class Function {
    private Chromosome chromosome;

    static double calculatePhenotypesSum(Population population) {
        double function = 0;
        for (int i = 0; i < population.size(); i++) {
            if (population.getSingleChromosome(i).getPhenotype() == 0) {
                function += Math.sqrt(population.getSingleChromosome(i).getPhenotype() + 1);
            } else {
                function += 2 * Math.log10(population.getSingleChromosome(i).getPhenotype())
                        + Math.sqrt(population.getSingleChromosome(i).getPhenotype() + 1);
            }
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

        return withBiggest;
    }

    Chromosome rouletteSelectionFunction(Population population) {

        double populationSum = calculatePhenotypesSum(population);

        double chrom1_1_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(0), populationSum);
        double chrom1_2_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(1), populationSum);
        double chrom1_3_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(2), populationSum);
        double chrom1_4_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(3), populationSum);
        double chrom1_5_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(4), populationSum);
        double chrom1_6_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(5), populationSum);
        double chrom1_7_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(6), populationSum);
        double chrom1_8_EndPoint = getChromosomePercentage(
                population.getSingleChromosome(7), populationSum);

        double range_One = chrom1_1_EndPoint;
        double range_Two = range_One + chrom1_2_EndPoint;
        double range_Three = range_Two + chrom1_3_EndPoint;
        double range_Four = range_Three + chrom1_4_EndPoint;
        double range_Five = range_Four + chrom1_5_EndPoint;
        double range_Six = range_Five + chrom1_6_EndPoint;
        double range_Seven = range_Six + chrom1_7_EndPoint;
        double range_Eight = range_Seven + chrom1_8_EndPoint;

        System.out.println(
                "\nRoulette selection ranges :" +
                        "\nChromosome 1:  from " + 0 + " to -> " + range_One +
                        "\nChromosome 2:  from " + range_One + " to -> " + range_Two +
                        "\nChromosome 3:  from " + range_Two + " to -> " + range_Three +
                        "\nChromosome 4:  from " + range_Three + " to -> " + range_Four +
                        "\nChromosome 5:  from " + range_Four + " to -> " + range_Five +
                        "\nChromosome 6:  from " + range_Five + " to -> " + range_Six +
                        "\nChromosome 7:  from " + range_Six + " to -> " + range_Seven +
                        "\nChromosome 8:  from " + range_Seven + " to -> " + range_Eight +
                        "\n" +
                        "\n"
        );

        double randomRouletteNumber = (Math.floor(Math.random() * range_Eight));

        System.out.println("Random number is: =>> " + randomRouletteNumber);

        if (isBetween(randomRouletteNumber, 0, range_One)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(0) + "]");
            return this.chromosome = population.getSingleChromosome(0);
        } else if (isBetween(randomRouletteNumber, range_One, range_Two)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(1) + "]");
            return this.chromosome = population.getSingleChromosome(1);
        } else if (isBetween(randomRouletteNumber, range_Two, range_Three)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(2) + "]");
            return this.chromosome = population.getSingleChromosome(2);
        } else if (isBetween(randomRouletteNumber, range_Three, range_Four)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(3) + "]");
            return this.chromosome = population.getSingleChromosome(3);
        } else if (isBetween(randomRouletteNumber, range_Four, range_Five)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(4) + "]");
            return this.chromosome = population.getSingleChromosome(4);
        } else if (isBetween(randomRouletteNumber, range_Five, range_Six)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(5) + "]");
            return this.chromosome = population.getSingleChromosome(5);
        } else if (isBetween(randomRouletteNumber, range_Six, range_Seven)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(6) + "]");
            return this.chromosome = population.getSingleChromosome(6);
        } else if (isBetween(randomRouletteNumber, range_Seven, range_Eight)) {
            System.out.println("Random number " +
                    randomRouletteNumber + "\tis in range ONE.");
            System.out.println("Selected chromosome ONE : ["
                    + population.getSingleChromosome(7) + "]");
            return this.chromosome = population.getSingleChromosome(7);
        } else {
            System.out.println("Random number " + randomRouletteNumber + "\tis NOT in ANY range.");
        }
        return this.chromosome;
    }

    private double getChromosomePercentage(Chromosome chromosome, double populationSum) {
        double chromosomePercentage;
        if (chromosome.getPhenotype() == 0) {
            chromosomePercentage = ((Math.sqrt(chromosome.getPhenotype() + 1) / populationSum) * 100);
        } else {
            chromosomePercentage = ((2 * Math.log10(chromosome.getPhenotype())
                    + Math.sqrt(chromosome.getPhenotype() + 1) / populationSum) * 100);
        }
        return chromosomePercentage;
    }

    private boolean isBetween(double x, double lower, double upper) {
        return lower <= x && x < upper;
    }

    static int findBiggestPopulationPhenotype(Population population){
        int biggestPhenotype = 0;
        for (int i = 0; i < population.size(); i++) {
            biggestPhenotype = Math.max(population.getSingleChromosome(i).getPhenotype(), biggestPhenotype);
        }

        System.out.println("\n+++++++++++++++++" +
                "\nBiggest phenotype is: " + biggestPhenotype
        + "\n+++++++++++++++++++");

        return biggestPhenotype;
    }
}
