package gntic_algrtm;

import java.util.Objects;

public class Chromosome {
    private int geneNumber;
    private byte[] genes;

    private Chromosome(Builder builder) {
        this.geneNumber = builder.geneNumber;
        this.genes = builder.genes;
    }


    int size() {
        return genes.length;
    }

    public int getPhenotype() {
        String temp = this.toString();
        return Integer.parseInt(temp, 2);
    }

    byte getSingleGene(int index) {
        return genes[index];
    }

    byte setSingleGene(int index, byte value) {
        return this.genes[index] = value;
    }

    public int getGeneNumber() {
        return geneNumber;
    }

    byte[] getGenes() {
        return genes;
    }

    void setGenes(byte[] genes) {
        this.genes = genes;
        Objects.requireNonNull(this.genes);
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            geneString.append(getSingleGene(i));
        }
        return geneString.toString();
    }

    public static class Builder {
        private int geneNumber;
        private byte[] genes = new byte[AlgorithmEngine.geneNumber];

        public Builder geneNumber(int geneNumber) {
            this.geneNumber = geneNumber;
            return this;
        }

        public Builder configNullGenes() {
            byte[] nullGenes = {0, 0, 0, 0, 0, 0, 0, 0};
//            byte[] nullGenes = {0, 0, 0, 0, 0};
            this.genes = nullGenes;

            return this;
        }

        public Builder initialize() {
            for (int i = 0; i < AlgorithmEngine.geneNumber; i++) {
                byte gene = (byte) Math.round(Math.random());
                genes[i] = gene;
            }
            return this;
        }

        public Chromosome build() {
            return new Chromosome(this);
        }
    }

}