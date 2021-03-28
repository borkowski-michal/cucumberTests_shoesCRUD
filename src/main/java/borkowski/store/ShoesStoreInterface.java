package borkowski.store;

import borkowski.domain.Shoes;

import java.time.LocalDate;
import java.util.List;

public interface ShoesStoreInterface {

    Shoes create(Shoes shoes);

    List<Shoes> readAll();

    Shoes read(Long id);

    Shoes update(Shoes shoes);

    void delete(Long id);

    LocalDate getTimeNow();
}
