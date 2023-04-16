package veterinarian;

public class AnimalThread extends Thread{
    private AnimalEnum type;
    private WaitingRoom waitingRoom;
    private int id;
    public AnimalThread(int id, WaitingRoom waitingRoom, AnimalEnum type){
        super();
        this.waitingRoom = waitingRoom;
        this.type = type;
        this.id = id;
    }

    public AnimalEnum getType(){
        return this.type;
    }

    public int getIdAnimale(){
        return this.id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 3000));
            this.waitingRoom.enterRoom(this);
            Thread.sleep((long) (Math.random() * 1000));
            this.waitingRoom.exitRoom(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
