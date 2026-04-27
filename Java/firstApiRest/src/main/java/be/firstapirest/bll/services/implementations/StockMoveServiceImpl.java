package be.firstapirest.bll.services.implementations;

import be.firstapirest.bll.services.implementations.base.AbstractCRUDImpl;
import be.firstapirest.bll.services.interfaces.StockMoveService;
import be.firstapirest.dl.entities.StockMove;
import be.firstapirest.dal.repositories.StockMoveRepository;
import org.springframework.stereotype.Service;

@Service
public class StockMoveServiceImpl extends AbstractCRUDImpl<StockMove, Long, StockMoveRepository>
                                    implements StockMoveService {

    public StockMoveServiceImpl(StockMoveRepository stockMoveRepository) {
        super(stockMoveRepository);
    }

}
