package gntic_algrtm;

public class Chromosome {
    private int geneNumber;
    private byte[] genes;

    public Chromosome(Builder builder) {
        this.geneNumber = builder.geneNumber;
        this.genes = builder.genes;
    }


    public int size() {
        return genes.length;
    }

    public int getFenotype() {
        String temp = this.toString();
        return Integer.parseInt(temp, 2);
    }

    public byte getSingleGene(int index) {
        return genes[index];
    }

    public byte setSingleGene(int index, byte value) {
        return this.genes[index] = value;
    }

    public int getGeneNumber() {
        return geneNumber;
    }

    public byte[] getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getSingleGene(i);
        }
        return geneString;
    }

    public static class Builder {
        private int geneNumber;
        private byte[] genes = new byte[5];

        public Builder geneNumber(int geneNumber) {
            this.geneNumber = geneNumber;
            return this;
        }

        public Builder configNullGenes() {
            byte[] nullGenes = {0, 0, 0, 0, 0};
            this.genes = nullGenes;
            return this;
        }

        public Builder initialize() {
            for (int i = 0; i < 5; i++) {
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
