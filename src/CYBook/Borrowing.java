package CYBook;

import java.util.Date;

public class Borrowing {
    private int id_user;
    private final Date STARTING_DATE;
    private final Date END_DATE ;
    private final int ISBN;

    public Borrowing(int id_user ,Date STARTING_DATE,Date END_DATE,int ISBN ) {
        this.id_user = id_user;
        this.END_DATE =  END_DATE;
        this.STARTING_DATE = STARTING_DATE;
        this.ISBN = ISBN;

    }

    public Date getEND_DATE() {
        return END_DATE;
    }

    public Date getSTARTING_DATE() {
        return STARTING_DATE;
    }
}
