package borkowski.store;
import borkowski.domain.Shoes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class ShoesStoreImpl implements ShoesStoreInterface, TimeStampInterface {

    public static ArrayList<Shoes> shoes = new ArrayList<>();
    private LocalDate time;
    private boolean readTimeEnabled = true;
    private boolean addTimeEnabled = true;
    private boolean updateTimeEnabled = true;

    public ShoesStoreImpl(){}

    @Override
    public Shoes create(Shoes createShoes) throws IllegalArgumentException {
        for (Shoes sh:shoes){
            if(createShoes.getId().equals(sh.getId())){
                throw new IllegalArgumentException("In your Database exist domain in this Id");
            }
        }
        if(addTimeEnabled){
            createShoes.setAddTime(getTimeNow());
        }

        shoes.add(createShoes);
        return createShoes;
    }

    @Override
    public ArrayList<Shoes> readAll(){  //ReadAll
        for (Shoes sh:shoes){
            sh.setReadTime(getTimeNow());
        }
        return shoes;
    }

    @Override
    public Shoes read(Long id){      //READ
        for (Shoes sh: shoes){
            if(id.equals(sh.getId())){
//                if(readTimeEnabled){
//                    sh.setReadTime(getTimeNow());
//                }
                //sh.setReadTime(getTimeNow());
                return sh;
            }
        }
        throw new NoSuchElementException("In your Database doesn't exist domain in this Id");
    }

    @Override
    public Shoes update(Shoes updateShoes){     //UPDATE
        if(shoes.contains(updateShoes)){
            shoes.set(shoes.indexOf(updateShoes), updateShoes);
//            if(updateTimeEnabled){
//                updateShoes.setUpdateTime(getTimeNow());
//            }
            //updateShoes.setUpdateTime(getTimeNow());
            return updateShoes;
        }
        throw new NoSuchElementException("In your Database doesn't exist domain in this Id");
    }

    @Override
    public void delete(Long id){
        Shoes shoe = read(id);
        shoes.remove(shoe);
    }


    @Override
    public LocalDate getTimeNow() {
        return this.time;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    //Method for getting times for shoes by id

    public ArrayList<LocalDate> getAllTimesForShoes(Long id){
        ArrayList<LocalDate> allTime = new ArrayList<LocalDate>();
        for(Shoes sh: shoes){
            if(id.equals(sh.getId())){
                allTime.add(0, sh.getAddTime());
                allTime.add(1, sh.getReadTime());
                allTime.add(2, sh.getUpdateTime());
                return allTime;
            }
        }
        throw new NoSuchFieldError();
    }

    public boolean setReadTimeEnabled() {
        return this.readTimeEnabled = true;
    }

    public boolean setReadTimeDisabled() {
        return this.readTimeEnabled = false;
    }

    public boolean setAddTimeEnabled() {
        return this.addTimeEnabled = true;
    }

    public boolean setAddTimeDisabled() {
        return this.addTimeEnabled= false;
    }

    public boolean setUpdateTimeEnabled() {
        return this.updateTimeEnabled = true;
    }

    public boolean setUpdateTimeDisabled() {
        return this.updateTimeEnabled = false;
    }

    //@Override
    public int delete(Shoes sh) {
        long id = sh.getId();
        shoes.remove(sh);
        return (int) id;
    }
}


