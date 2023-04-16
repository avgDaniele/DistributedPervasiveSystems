/*
package veterinarian;

public class WaitingRoom {

    private Object catLock;
    private Object dogLock;

    private int cats;
    private int dogs;

    public WaitingRoom(){
        catLock = new Object();
        dogLock = new Object();
        cats = 0;
        dogs = 0;
    }
    public synchronized void enterRoom(AnimalThread animal) throws InterruptedException {
        while (!canAccess(animal.getType())) {
            try {
                switch (animal.getType()) {
                    case CAT:
                        System.out.println(String.format("cat %s waiting", animal.getIdAnimale()));
                        synchronized (catLock){
                            catLock.wait();
                        }
                        break;
                    case DOG:
                        System.out.println(String.format("dog %s waiting", animal.getIdAnimale()));
                        synchronized (dogLock){
                            dogLock.wait();
                        }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        switch (animal.getType()) {
            case CAT:
                System.out.println(String.format("cat %s entered", animal.getIdAnimale()));
                cats++;
                break;
            case DOG:
                System.out.println(String.format("dog %s entered", animal.getIdAnimale()));
                dogs++;
                break;
        }
    }

    private boolean canAccess(AnimalEnum animal) {
        switch (animal) {
            case CAT:
                return !(dogs > 0);
            case DOG:
                return !(cats > 0 || dogs >= 2);
        }
        return false;
    }

    public synchronized void exitRoom(AnimalThread animal) {
        switch (animal.getType()) {
            case CAT:
                System.out.println(String.format("cat %s exited", animal.getIdAnimale()));
                cats --;
                if (cats == 0)
                    synchronized (dogLock){
                        dogLock.notifyAll();
                    }
                break;
            case DOG:
                System.out.println(String.format("notify all cats"));
                dogs --;
                if(dogs == 0)
                    synchronized (catLock){
                        catLock.notifyAll();
                    }
                else
                    System.out.println(String.format("notify a dog"));
                    synchronized (dogLock){
                        dogLock.notify();
                    }
                break;
        }
    }
}
*/
  package veterinarian;

  public class WaitingRoom {

      private Object catLock;
      private Object dogLock;

      private int cats;
      private int dogs;

      public WaitingRoom() {
          catLock = new Object();
          dogLock = new Object();
          cats = 0;
          dogs = 0;
      }

      public synchronized void enterRoom(AnimalThread animal) throws InterruptedException {
          while (!canAccess(animal.getType())) {
              try {
                  switch (animal.getType()) {
                      case CAT:
                          System.out.println(String.format("cat %s waiting", animal.getIdAnimale()));
                          wait();
                          break;
                      case DOG:
                          System.out.println(String.format("dog %s waiting", animal.getIdAnimale()));
                          wait();
                  }
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          switch (animal.getType()) {
              case CAT:
                  System.out.println(String.format("cat %s entered", animal.getIdAnimale()));
                  cats++;
                  break;
              case DOG:
                  System.out.println(String.format("dog %s entered", animal.getIdAnimale()));
                  dogs++;
                  break;
          }
          //Thread.sleep((long) (Math.random() * 1000));
      }

      private boolean canAccess(AnimalEnum animal) {
          switch (animal) {
              case CAT:
                  return !(dogs > 0);
              case DOG:
                  return !(cats > 0 || dogs >= 2);
              default:
                  return false;
          }
      }

      public synchronized void exitRoom(AnimalThread animal) {
          switch (animal.getType()) {
              case CAT:
                  System.out.println(String.format("cat %s exited", animal.getIdAnimale()));
                  cats--;
                  notifyAll();
                  break;
              case DOG:
                  System.out.println(String.format("dog %s exited", animal.getIdAnimale()));
                  dogs--;
                  notifyAll();
                  break;
          }
      }
  }
