package ru.relex.delivery.services.internal;

import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;

public interface DishService {

  CreatedDish createDish(NewDish dish, long restaurantId);

  CreatedDish getById(long id);

  CreatedDish[] getByRestaurantId(long id);

  CreatedDish[] getAll();

  CreatedDish update(long id, UpdatableDish updatableDish);

  boolean deleteById(long id);
}
