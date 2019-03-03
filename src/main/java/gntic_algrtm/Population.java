package gntic_algrtm;

import java.util.Arrays;

public class Population {
    private Chromosome[] chromosomes;

    private Population(Builder populationBuilder) {
        this.chromosomes = populationBuilder.chromosomes;
    }

    Chromosome getSingleChromosome(int index) {
        return chromosomes[index];
    }

    int size() {
        return chromosomes.length;
    }

    void saveChromosome(int index, Chromosome chromosome) {
        chromosomes[index] = chromosome;
    }

    Chromosome getFittestPopulation() {
        Function fitness = new Function();
        if (chromosomes.length > 0) {
            Chromosome fittest = new Chromosome.Builder()
                    .configNullGenes()
                    .build();

            while (chromosomes[0] == null) {
                byte[] temp = {0, 0, 0, 0, 1};
                chromosomes[0].setGenes(temp);
            }
//            fittest = Objects.requireNonNull(chromosomes[0]);
            for (int i = 0; i < AlgorithmEngine.geneNumber; i++) {
                if (!(fittest == null)) {
                    while (fittest.getPhenotype() <= getSingleChromosome(i).getPhenotype()) {
                        fittest = fitness.rouletteSelectionFunction(this);
                        break;
                    }
                }
            }
            return fittest;
        } else {
            System.out.println("Empty population. There must be at least one chromosome");
            return null;
        }
    }


    @Override
    public String toString() {
        return "Population{\n" +
                "chromosomes= " + Arrays.toString(chromosomes) +
                '}';
    }

    public static class Builder {

        Chromosome[] chromosomes;
        int populationSize;
        int geneNumber;

        Builder() {
        }


        public Builder(int populationSize, int geneNumber) {
            this.populationSize = populationSize;
            this.geneNumber = geneNumber;
        }

        Builder configNullPopulation() {
            this.chromosomes = new Chromosome[AlgorithmEngine.TOURNAMENT_Size];
            if (populationSize <= AlgorithmEngine.TOURNAMENT_Size) {
                for (int i = 0; i < AlgorithmEngine.TOURNAMENT_Size; i++) {
                    Chromosome chromosome = new Chromosome.Builder()
                            .configNullGenes()
                            .build();
                    saveChromosome(i, chromosome);
                }
            } else {
                System.out.println("Population size can not be larger than 8");
            }
            return this;
        }

        public Builder initialize() {
            chromosomes = new Chromosome[AlgorithmEngine.TOURNAMENT_Size];
            if (populationSize <= AlgorithmEngine.TOURNAMENT_Size) {
                for (int i = 0; i < AlgorithmEngine.TOURNAMENT_Size; i++) {
                    Chromosome temp = new Chromosome.Builder()
                            .initialize()
                            .build();
                    this.saveChromosome(i, temp);
                }
            } else {
                System.out.println("Population size can not be larger than 8");
            }
            return this;
        }

        void saveChromosome(int index, Chromosome chromosome) {
            this.chromosomes[index] = chromosome;
        }

        public Population build() {
            return new Population(this);
        }

    }
}