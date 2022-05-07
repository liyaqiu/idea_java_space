package design.designadpater;

public class AnimalAdpater implements Adpater{
    @Override
    public Doctor getDocker(Animal animal) {
        if(animal instanceof Cat){
            return new CatDocter();
        }else if(animal instanceof Dog){
            return new DogDoctor();
        }
        return null;
    }
}
