package be.firstapirest.bll.services.interfaces;

import be.firstapirest.bll.services.interfaces.base.BaseCRUDService;
import be.firstapirest.dl.entities.Stock;

public interface StockService extends BaseCRUDService<Stock, Long> {

    void update(Long id, Stock stock);
}
