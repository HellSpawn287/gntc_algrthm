package gntic_algrtm;

import static gntic_algrtm.AlgorithmEngine.evolve;

public class Island {
    Population moms;
    Population dads;
    Chromosome migrating_chromosome;

    public Island(Population moms, Population dads) {
        this.moms = moms;
        this.dads = dads;
        this.migrating_chromosome =
                findMigratingChromosome(moms, dads);
        System.out.println("\n===========================\n" +
                "The migration chromoseome: " + migrating_chromosome.toString()
                + "\n has phenotype = " + migrating_chromosome.getPhenotype());
    }

    //    isMomPopulationAReceiver ustawia populację - odbiorcę migrującego chromosomu
    public void receiveMigratingChromosome(boolean isMomPopulationAReceiver, Chromosome migrating_chromosome) {
        if (isMomPopulationAReceiver) this.moms.addChromosome(migrating_chromosome);
        else this.dads.addChromosome(migrating_chromosome);
    }

    private static Chromosome findMigratingChromosome(Population moms, Population dads) {
        return Function.findChromosomeWithBiggestPhenotype(evolve(moms, dads));
    }

    public Population getMoms() {
        return moms;
    }

    public void setMoms(Population moms) {
        this.moms = moms;
    }

    public Population getDads() {
        return dads;
    }

    public void setDads(Population dads) {
        this.dads = dads;
    }

    public Chromosome getMigrating_chromosome() {
        return migrating_chromosome;
    }

    public void setMigrating_chromosome() {
        this.migrating_chromosome = findMigratingChromosome(this.moms, this.dads);
        System.out.println("The migration chromoseome: " + migrating_chromosome.toString()
                + "\n has phenotype = " + migrating_chromosome.getPhenotype());
    }

    @Override
    public String toString() {
        return "Island {" +
                "\n moms = " + moms.toString() +
                "\n, dads = " + dads.toString() +
                "\n, migrating_chromosome = " + migrating_chromosome.toString() +
                " and has phenotype = " + migrating_chromosome.getPhenotype() +
                '}';
    }
}