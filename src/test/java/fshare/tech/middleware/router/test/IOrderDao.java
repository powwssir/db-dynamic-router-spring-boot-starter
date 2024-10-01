package fshare.tech.middleware.router.test;

import fshare.tech.middleware.router.annotation.DBRouter;

public interface IOrderDao {

    @DBRouter(dbKey = "1")
    void insertOrder(String req);
}