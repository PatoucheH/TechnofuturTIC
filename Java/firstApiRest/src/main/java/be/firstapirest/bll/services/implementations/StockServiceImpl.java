package be.firstapirest.bll.services.implementations;

import be.firstapirest.bll.services.implementations.base.AbstractCRUDImpl;
import be.firstapirest.bll.services.interfaces.StockService;
import be.firstapirest.dl.entities.Stock;
import be.firstapirest.dal.repositories.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends AbstractCRUDImpl<Stock, Long, StockRepository>
                                implements StockService {

    public StockServiceImpl(StockRepository repository) {
        super(repository);
    }

    @Override
    public void update(Long id, Stock stock){
        Stock stockToUpdate = findById(id);
        stockToUpdate.setStockMove(stock.getStockMove());
        stockToUpdate.setQuantity(stock.getQuantity());
        stockToUpdate.setAlertQuantity(stock.getAlertQuantity());
        repository.save(stockToUpdate);
    }
}
