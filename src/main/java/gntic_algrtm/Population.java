package gntic_algrtm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Population {
    private Chromosome[] chromosomes;

    private Population(Builder populationBuilder) {
        this.chromosomes = populationBuilder.chromosomes;
    }

    Chromosome getSingleChromosome(int index) {
        return chromosomes[index];
    }

    public int size() {
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
                byte[] temp = {0, 0, 0, 0, 0, 0, 0, 0};
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

    public Chromosome[] addChromosome(Chromosome arrival) {
        List<Chromosome> temporal = new ArrayList<>(Arrays.asList(this.chromosomes));
        temporal.add(arrival);
        Chromosome[] extendedChromosomes = temporal.toArray(this.chromosomes);
        setChromosomes(extendedChromosomes);
        return extendedChromosomes;
    }

    public void setChromosomes(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
    }

    @Override
    public String toString() {
        return "\t\tPopulation {" +
                "\n\t\t\tchromosomes= " + Arrays.toString(chromosomes) +
                "\n\t\t}";
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