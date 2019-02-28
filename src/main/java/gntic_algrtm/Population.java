package gntic_algrtm;

import java.util.Arrays;

public class Population {
    private Chromosome[] chromosomes;

    public Population(Builder populationBuilder) {
        this.chromosomes = populationBuilder.chromosomes;
    }

    public Chromosome getSingleChromosome(int index) {
        return chromosomes[index];
    }

    public int size() {
        return chromosomes.length;
    }

    public void saveChromosome(int index, Chromosome chromosome) {
        chromosomes[index] = chromosome;
    }

//    todo: Add method getFittestPopulation()


    @Override
    public String toString() {
        return "Population{\n" +
                "chromosomes= " + Arrays.toString(chromosomes) +
                '}';
    }

    public static class Builder {

        public Chromosome[] chromosomes;
        int populationSize;
        int geneNumber;

        public Builder() {
        }


        public Builder(int populationSize, int geneNumber) {
            this.populationSize = populationSize;
            this.geneNumber = geneNumber;
        }

        //  TODO:     class variable TOURNAMENT_Size = 8
        public Builder configNullPopulation() {
            this.chromosomes = new Chromosome[8];
            for (int i = 0; i < 8; i++) {
                Chromosome chromosome = new Chromosome.Builder()
                        .configNullGenes()
                        .build();
                saveChromosome(i, chromosome);
            }
            //        else {
            //            System.out.println("Population size can not be larger than 8");
            //        }

            return this;
        }

        public Builder initialize() {
            chromosomes = new Chromosome[8];
            //        TODO      : Add If loop to compare populationSize <= TORNAMENT_Size
            for (int i = 0; i < 8; i++) {
                Chromosome temp = new Chromosome.Builder()
                        .initialize()
                        .build();
                this.saveChromosome(i, temp);
            }
//        else {
//            System.out.println("Population size can not be larger than 8");
//        }
            return this;
        }

        public void saveChromosome(int index, Chromosome chromosome) {
            this.chromosomes[index] = chromosome;
        }

        public Population build(){
            return new Population(this);
        }

    }
}