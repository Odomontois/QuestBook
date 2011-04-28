package ru.nol.qbook.persistence;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {    

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return Keeper.pmfInstance;
    }
    private static class Keeper{
    	private static final PersistenceManagerFactory pmfInstance =
            JDOHelper.getPersistenceManagerFactory("transactions-optional");
    }
}
