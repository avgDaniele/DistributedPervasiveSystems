package veterinarian;

public class Veterinarian {

    public static void main(String argv[]) throws Exception {
        WaitingRoom waitingRoom = new WaitingRoom();

        AnimalThread cat1 = new AnimalThread(1, waitingRoom, AnimalEnum.CAT);
        AnimalThread cat2 = new AnimalThread(2, waitingRoom, AnimalEnum.CAT);
        AnimalThread cat3 = new AnimalThread(3, waitingRoom, AnimalEnum.CAT);
        AnimalThread cat4 = new AnimalThread(4, waitingRoom, AnimalEnum.CAT);
        AnimalThread cat5 = new AnimalThread(5, waitingRoom, AnimalEnum.CAT);
        AnimalThread dog1 = new AnimalThread(1,waitingRoom, AnimalEnum.DOG);
        AnimalThread dog2 = new AnimalThread(2,waitingRoom, AnimalEnum.DOG);
        AnimalThread dog3 = new AnimalThread(3,waitingRoom, AnimalEnum.DOG);
        AnimalThread dog4 = new AnimalThread(4,waitingRoom, AnimalEnum.DOG);
        AnimalThread dog5 = new AnimalThread(5,waitingRoom, AnimalEnum.DOG);

        cat1.start();
        cat2.start();
        cat3.start();
        cat4.start();
        cat5.start();
        dog1.start();
        dog2.start();
        dog3.start();
        dog4.start();
        dog5.start();
    }
}
