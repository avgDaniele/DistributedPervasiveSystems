package theatre;

public class BookingService {
    private final int numeberOfSeats = 100;
    private int freeSeats;

    public BookingService(){
        this.freeSeats = numeberOfSeats;
    }

    public synchronized boolean freeSeats(int n, Thread th) throws InterruptedException {
        if(numeberOfSeats >= this.freeSeats+n){
            th.sleep(5000);
            int m = this.freeSeats;
            this.freeSeats = m + n;
            return true;
        }else{
            th.sleep(5000);
            return false;
        }
    }

    public synchronized boolean bookSeats(int n, Thread th) throws InterruptedException {
        if(this.freeSeats >= n){
            th.sleep(5000);
            int m = this.freeSeats;
            this.freeSeats = m - n;
            return true;
        }else{
            th.sleep(5000);
            return false;
        }
    }

    public int getAvailableSeats(Thread th) throws InterruptedException {
        th.sleep(2000);
        return this.freeSeats;
    }
}
