import Entity.Animals;
import Entity.PetStore;
import Utils.*;

import java.util.List;

void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    PetStore store = App.createPetStore(sc);
    App.start(sc, store);
    sc.close();
}

